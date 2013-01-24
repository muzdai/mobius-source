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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lineage2.commons.dbutils.DbUtils;
import lineage2.gameserver.database.DatabaseFactory;
import lineage2.gameserver.templates.StatsSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerVariables
{
	private static final Logger _log = LoggerFactory.getLogger(ServerVariables.class);
	private static StatsSet server_vars = null;
	
	private static StatsSet getVars()
	{
		if (server_vars == null)
		{
			server_vars = new StatsSet();
			LoadFromDB();
		}
		return server_vars;
	}
	
	private static void LoadFromDB()
	{
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement("SELECT * FROM server_variables");
			rs = statement.executeQuery();
			while (rs.next())
			{
				server_vars.set(rs.getString("name"), rs.getString("value"));
			}
		}
		catch (Exception e)
		{
			_log.error("", e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement, rs);
		}
	}
	
	private static void SaveToDB(String name)
	{
		Connection con = null;
		PreparedStatement statement = null;
		try
		{
			con = DatabaseFactory.getInstance().getConnection();
			String value = getVars().getString(name, "");
			if (value.isEmpty())
			{
				statement = con.prepareStatement("DELETE FROM server_variables WHERE name = ?");
				statement.setString(1, name);
				statement.execute();
			}
			else
			{
				statement = con.prepareStatement("REPLACE INTO server_variables (name, value) VALUES (?,?)");
				statement.setString(1, name);
				statement.setString(2, value);
				statement.execute();
			}
		}
		catch (Exception e)
		{
			_log.error("", e);
		}
		finally
		{
			DbUtils.closeQuietly(con, statement);
		}
	}
	
	public static boolean getBool(String name)
	{
		return getVars().getBool(name);
	}
	
	public static boolean getBool(String name, boolean defult)
	{
		return getVars().getBool(name, defult);
	}
	
	public static int getInt(String name)
	{
		return getVars().getInteger(name);
	}
	
	public static int getInt(String name, int defult)
	{
		return getVars().getInteger(name, defult);
	}
	
	public static long getLong(String name)
	{
		return getVars().getLong(name);
	}
	
	public static long getLong(String name, long defult)
	{
		return getVars().getLong(name, defult);
	}
	
	public static double getFloat(String name)
	{
		return getVars().getDouble(name);
	}
	
	public static double getFloat(String name, double defult)
	{
		return getVars().getDouble(name, defult);
	}
	
	public static String getString(String name)
	{
		return getVars().getString(name);
	}
	
	public static String getString(String name, String defult)
	{
		return getVars().getString(name, defult);
	}
	
	public static void set(String name, boolean value)
	{
		getVars().set(name, value);
		SaveToDB(name);
	}
	
	public static void set(String name, int value)
	{
		getVars().set(name, value);
		SaveToDB(name);
	}
	
	public static void set(String name, long value)
	{
		getVars().set(name, value);
		SaveToDB(name);
	}
	
	public static void set(String name, double value)
	{
		getVars().set(name, value);
		SaveToDB(name);
	}
	
	public static void set(String name, String value)
	{
		getVars().set(name, value);
		SaveToDB(name);
	}
	
	public static void unset(String name)
	{
		getVars().unset(name);
		SaveToDB(name);
	}
}
