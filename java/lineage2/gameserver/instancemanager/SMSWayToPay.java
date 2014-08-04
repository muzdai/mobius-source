/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lineage2.gameserver.instancemanager;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lineage2.commons.dbutils.DbUtils;
import lineage2.gameserver.Config;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.database.DatabaseFactory;
import lineage2.gameserver.model.GameObjectsStorage;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.utils.Log;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class SMSWayToPay
{
	/**
	 * Field SELECT_PLAYER_OBJID. (value is ""SELECT obj_Id FROM characters WHERE char_name=?"")
	 */
	private static final String SELECT_PLAYER_OBJID = "SELECT obj_Id FROM characters WHERE char_name=?";
	/**
	 * Field SELECT_CHARACTER_SMS_DATA. (value is ""SELECT * FROM character_sms_donate WHERE id=? AND wText=?"")
	 */
	private static final String SELECT_CHARACTER_SMS_DATA = "SELECT * FROM character_sms_donate WHERE id=? AND wText=?";
	/**
	 * Field INSERT_SMS_DATA. (value is ""INSERT INTO character_sms_donate (id, service_id, status, time, curr_id, sum, profit, email, client_id, wNumber, wPhone, wText, wCost, wProfit, wCountry) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"")
	 */
	private static final String INSERT_SMS_DATA = "INSERT INTO character_sms_donate (id, service_id, status, time, curr_id, sum, profit, email, client_id, wNumber, wPhone, wText, wCost, wProfit, wCountry) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * Field DELETE_SMS_DATA. (value is ""DELETE FROM character_sms_donate WHERE time<?"")
	 */
	private static final String DELETE_SMS_DATA = "DELETE FROM character_sms_donate WHERE time<?";
	/**
	 * Field UPDATE_SMS_DATA. (value is ""UPDATE character_sms_donate SET has_reward=1 WHERE wText=?"")
	 */
	private static final String UPDATE_SMS_DATA = "UPDATE character_sms_donate SET has_reward=1 WHERE wText=?";
	/**
	 * Field SELECT_RPOFIT_SMS_DATA. (value is ""SELECT wCost, wProfit FROM character_sms_donate WHERE wText=? AND has_reward=0"")
	 */
	private static final String SELECT_RPOFIT_SMS_DATA = "SELECT wCost, wProfit FROM character_sms_donate WHERE wText=? AND has_reward=0";
	
	/**
	 * @author Mobius
	 */
	public static class DataContainer
	{
		/**
		 * Field id.
		 */
		public int id;
		/**
		 * Field service_id.
		 */
		public int service_id;
		/**
		 * Field status.
		 */
		public int status;
		/**
		 * Field time.
		 */
		public long time;
		/**
		 * Field curr_id.
		 */
		public int curr_id;
		/**
		 * Field sum.
		 */
		public float sum;
		/**
		 * Field profit.
		 */
		public float profit;
		/**
		 * Field email.
		 */
		public String email;
		/**
		 * Field client_id.
		 */
		public int client_id;
		/**
		 * Field client_params.
		 */
		public String client_params;
	}
	
	/**
	 * Field _instance.
	 */
	private static SMSWayToPay _instance;
	
	/**
	 * Method getInstance.
	 * @return SMSWayToPay
	 */
	public static SMSWayToPay getInstance()
	{
		if ((_instance == null) && Config.SMS_PAYMENT_MANAGER_ENABLED)
		{
			_instance = new SMSWayToPay();
		}
		
		return _instance;
	}
	
	/**
	 * Constructor for SMSWayToPay.
	 */
	public SMSWayToPay()
	{
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new ConnectAndUpdate(), Config.SMS_PAYMENT_MANAGER_INTERVAL, Config.SMS_PAYMENT_MANAGER_INTERVAL);
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new Clean(), Config.SMS_PAYMENT_MANAGER_INTERVAL, Config.SMS_PAYMENT_MANAGER_INTERVAL);
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new GiveReward(), Config.SMS_PAYMENT_MANAGER_INTERVAL, Config.SMS_PAYMENT_MANAGER_INTERVAL);
	}
	
	/**
	 * Method pageToString.
	 * @param address String
	 * @return String
	 */
	private static String pageToString(String address)
	{
		String str = null;
		
		try
		{
			URL url = new URL(address);
			URLConnection con = url.openConnection();
			Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
			Matcher m = p.matcher(con.getContentType());
			String charset = m.matches() ? m.group(1) : "ISO-8859-1";
			Reader r = new InputStreamReader(con.getInputStream(), charset);
			StringBuilder buf = new StringBuilder();
			
			while (true)
			{
				int ch = r.read();
				
				if (ch < 0)
				{
					break;
				}
				
				buf.append((char) ch);
			}
			
			str = buf.toString();
		}
		catch (MalformedURLException e)
		{
		}
		catch (Exception e)
		{
		}
		
		return str;
	}
	
	/**
	 * Method parse.
	 */
	public void parse()
	{
		ArrayList<DataContainer> Containers = new ArrayList<>();
		String[] arrayedDatas = pageToString(Config.SMS_PAYMENT_WEB_ADDRESS).split("\\{");
		
		for (String arrayedData : arrayedDatas)
		{
			if (!arrayedData.contains("}") || (arrayedData.replace(" ", "").length() < 30))
			{
				continue;
			}
			
			String[] formedStr = arrayedData.substring(arrayedData.indexOf('}')).replace("\n", "").replace("}", "").split(";");
			
			if (formedStr.length > 1)
			{
				DataContainer dc = new DataContainer();
				dc.id = Integer.parseInt(formedStr[0]);
				dc.service_id = Integer.parseInt(formedStr[1]);
				dc.status = Integer.parseInt(formedStr[2]);
				dc.time = Long.parseLong(formedStr[3]);
				dc.curr_id = Integer.parseInt(formedStr[4]);
				dc.sum = Float.parseFloat(formedStr[5].replace(',', '.'));
				dc.profit = Float.parseFloat(formedStr[6].replace(',', '.'));
				dc.email = formedStr[7];
				dc.client_id = Integer.parseInt(formedStr[8]);
				dc.client_params = arrayedData.substring(0, arrayedData.indexOf('}'));
				Containers.add(dc);
				StringTokenizer inputST = new StringTokenizer(dc.client_params, ",");
				Hashtable<String, String> outputCollection = new Hashtable<>();
				
				while (inputST.hasMoreTokens())
				{
					String s1 = inputST.nextToken();
					
					if ((s1.length() > 0) && (s1.charAt(0) == '{'))
					{
						s1 = s1.substring(1);
					}
					else if (s1.endsWith("}"))
					{
						s1 = s1.substring(0, s1.length() - 1);
					}
					
					StringTokenizer subST = new StringTokenizer(s1, ":");
					
					while (subST.hasMoreTokens())
					{
						String key = subST.nextToken();
						String value = subST.nextToken();
						
						if (((key.length() > 0) && (key.charAt(0) == '"')) && key.endsWith("\""))
						{
							key = key.substring(1, key.length() - 1);
						}
						
						if (((value.length() > 0) && (value.charAt(0) == '"')) && value.endsWith("\""))
						{
							value = value.substring(1, value.length() - 1);
						}
						
						outputCollection.put(key, value);
					}
				}
				
				if ((dc.time + (Config.SMS_PAYMENT_SAVE_DAYS * 86400)) > (System.currentTimeMillis() / 1000))
				{
					checkAndSave(dc.id, dc.service_id, dc.status, dc.time, dc.curr_id, dc.sum, dc.profit, dc.email, dc.client_id, Integer.parseInt(outputCollection.get("wNumber")), outputCollection.get("wPhone"), outputCollection.get("wText"), Float.parseFloat(outputCollection.get("wCost")), Float.parseFloat(outputCollection.get("wProfit")), outputCollection.get("wCountry"));
				}
			}
		}
	}
	
	/**
	 * Method checkAndSave.
	 * @param id int
	 * @param service_id int
	 * @param status int
	 * @param time long
	 * @param curr_id int
	 * @param sum float
	 * @param profit float
	 * @param email String
	 * @param client_id int
	 * @param wNumber int
	 * @param wPhone String
	 * @param wText String
	 * @param wCost float
	 * @param wProfit float
	 * @param wCountry String
	 */
	public void checkAndSave(int id, int service_id, int status, long time, int curr_id, float sum, float profit, String email, int client_id, int wNumber, String wPhone, String wText, float wCost, float wProfit, String wCountry)
	{
		int objId = 0;
		Connection con = null;
		PreparedStatement selectPlayerStatement = null, selectSmsDataStatement = null, insertSmsStatement = null;
		ResultSet rsetPlayer = null, rsetSms = null;
		
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			selectPlayerStatement = con.prepareStatement(SELECT_PLAYER_OBJID);
			selectPlayerStatement.setString(1, wText);
			rsetPlayer = selectPlayerStatement.executeQuery();
			
			if (rsetPlayer.next())
			{
				objId = rsetPlayer.getInt("obj_Id");
				
				if (objId > 0)
				{
					selectSmsDataStatement = con.prepareStatement(SELECT_CHARACTER_SMS_DATA);
					selectSmsDataStatement.setInt(1, id);
					selectSmsDataStatement.setString(2, wText);
					rsetSms = selectSmsDataStatement.executeQuery();
					
					if (!rsetSms.next())
					{
						insertSmsStatement = con.prepareStatement(INSERT_SMS_DATA);
						insertSmsStatement.setInt(1, id);
						insertSmsStatement.setInt(2, service_id);
						insertSmsStatement.setInt(3, status);
						insertSmsStatement.setLong(4, time);
						insertSmsStatement.setInt(5, curr_id);
						insertSmsStatement.setFloat(6, sum);
						insertSmsStatement.setFloat(7, profit);
						insertSmsStatement.setString(8, email);
						insertSmsStatement.setInt(9, client_id);
						insertSmsStatement.setInt(10, wNumber);
						insertSmsStatement.setString(11, wPhone);
						insertSmsStatement.setString(12, wText);
						insertSmsStatement.setFloat(13, wCost);
						insertSmsStatement.setFloat(14, wProfit);
						insertSmsStatement.setString(15, wCountry);
						insertSmsStatement.execute();
						insertSmsStatement.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, selectPlayerStatement, rsetPlayer);
			DbUtils.closeQuietly(con, selectSmsDataStatement, rsetSms);
			DbUtils.closeQuietly(con, insertSmsStatement);
		}
	}
	
	/**
	 * Method clean.
	 */
	synchronized void clean()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -Config.SMS_PAYMENT_SAVE_DAYS);
		Connection con = null;
		PreparedStatement statement = null;
		
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(DELETE_SMS_DATA);
			statement.setLong(1, calendar.getTimeInMillis() / 1000);
			statement.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}
	
	/**
	 * Method giveReward.
	 */
	synchronized void giveReward()
	{
		Connection con = null;
		PreparedStatement selectStatement = null, updateStatement = null;
		ResultSet rsetProfit = null;
		
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			
			for (Player player : GameObjectsStorage.getAllPlayers())
			{
				String wText = player.getName();
				float profit = 0;
				selectStatement = con.prepareStatement(SELECT_RPOFIT_SMS_DATA);
				selectStatement.setString(1, wText);
				rsetProfit = selectStatement.executeQuery();
				
				if (Config.SMS_PAYMENT_TYPE)
				{
					while (rsetProfit.next())
					{
						profit += rsetProfit.getFloat("wProfit");
					}
				}
				else
				{
					while (rsetProfit.next())
					{
						profit += rsetProfit.getFloat("wCost");
					}
				}
				
				if (profit > 0)
				{
					updateStatement = con.prepareStatement(UPDATE_SMS_DATA);
					updateStatement.setString(1, wText);
					updateStatement.executeUpdate();
					player.sendMessage("Thank you for your SMS. Best regards " + Config.SMS_PAYMENT_SERVER_ADDRESS);
					
					for (int i = 0; i < Config.SMS_PAYMENT_REWARD.length; i += 2)
					{
						if (Config.SMS_PAYMENT_REWARD[i] == -100)
						{
							player.addPcBangPoints((int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit), false);
							Log.add(player.getName() + " | " + player.getObjectId() + " | SMS reward item ID | " + Config.SMS_PAYMENT_REWARD[i] + " | SMS reward count | " + (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit) + " |", "sms");
						}
						else if (Config.SMS_PAYMENT_REWARD[i] == -200)
						{
							if (player.getClan() != null)
							{
								player.getClan().incReputation((int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit), false, "SMSPayment");
								Log.add(player.getName() + " | " + player.getObjectId() + " | SMS reward item ID | " + Config.SMS_PAYMENT_REWARD[i] + " | SMS reward count | " + (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit) + " |", "sms");
							}
							else
							{
								player.getInventory().addItem(Config.SMS_PAYMENT_REWARD_NO_CLAN[i], (int) (Config.SMS_PAYMENT_REWARD_NO_CLAN[i + 1] * profit));
								Log.add(player.getName() + " | " + player.getObjectId() + " | SMS reward item ID | " + Config.SMS_PAYMENT_REWARD_NO_CLAN[i] + " | SMS reward count | " + (int) (Config.SMS_PAYMENT_REWARD_NO_CLAN[i + 1] * profit) + " |", "sms");
							}
						}
						else if (Config.SMS_PAYMENT_REWARD[i] == -300)
						{
							player.setFame(player.getFame() + (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit), "SMSPayment");
							Log.add(player.getName() + " | " + player.getObjectId() + " | SMS reward item ID | " + Config.SMS_PAYMENT_REWARD[i] + " | SMS reward count | " + (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit) + " |", "sms");
						}
						else
						{
							player.getInventory().addItem(Config.SMS_PAYMENT_REWARD[i], (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit));
							Log.add(player.getName() + " | " + player.getObjectId() + " | SMS reward item ID | " + Config.SMS_PAYMENT_REWARD[i] + " | SMS reward count | " + (int) (Config.SMS_PAYMENT_REWARD[i + 1] * profit) + " |", "sms");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(con, selectStatement, rsetProfit);
			DbUtils.closeQuietly(con, updateStatement);
		}
	}
	
	/**
	 * @author Mobius
	 */
	private class ConnectAndUpdate implements Runnable
	{
		/**
		 * Constructor for ConnectAndUpdate.
		 */
		public ConnectAndUpdate()
		{
			// TODO Auto-generated constructor stub
		}
		
		/**
		 * Method run.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			parse();
		}
	}
	
	/**
	 * @author Mobius
	 */
	private class Clean implements Runnable
	{
		/**
		 * Constructor for Clean.
		 */
		public Clean()
		{
			// TODO Auto-generated constructor stub
		}
		
		/**
		 * Method run.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			clean();
		}
	}
	
	/**
	 * @author Mobius
	 */
	private class GiveReward implements Runnable
	{
		/**
		 * Constructor for GiveReward.
		 */
		public GiveReward()
		{
			// TODO Auto-generated constructor stub
		}
		
		/**
		 * Method run.
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			giveReward();
		}
	}
}
