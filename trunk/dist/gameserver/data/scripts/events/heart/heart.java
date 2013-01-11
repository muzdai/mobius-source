/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package events.heart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lineage2.commons.text.PrintfFormat;
import lineage2.commons.util.Rnd;
import lineage2.gameserver.Announcements;
import lineage2.gameserver.Config;
import lineage2.gameserver.data.htm.HtmCache;
import lineage2.gameserver.listener.actor.OnDeathListener;
import lineage2.gameserver.listener.actor.player.OnPlayerEnterListener;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.SimpleSpawner;
import lineage2.gameserver.model.actor.listener.CharListenerList;
import lineage2.gameserver.model.instances.MonsterInstance;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.scripts.Functions;
import lineage2.gameserver.scripts.ScriptFile;
import lineage2.gameserver.utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class heart extends Functions implements ScriptFile, OnDeathListener, OnPlayerEnterListener
{
	private static final Logger _log = LoggerFactory.getLogger(heart.class);
	private static boolean _active = false;
	private static final List<SimpleSpawner> _spawns = new ArrayList<>();
	private static final Map<Integer, Integer> Guesses = new HashMap<>();
	private static String links_en = "", links_ru = "";
	private static final String[][] variants =
	{
		{
			"Rock",
			"Камень"
		},
		{
			"Scissors",
			"Ножницы"
		},
		{
			"Paper",
			"Бумага"
		}
	};
	static
	{
		PrintfFormat fmt = new PrintfFormat("<br><a action=\"bypass -h scripts_events.heart.heart:play %d\">\"%s!\"</a>");
		for (int i = 0; i < variants.length; i++)
		{
			links_en += fmt.sprintf(new Object[]
			{
				i,
				variants[i][0]
			});
			links_ru += fmt.sprintf(new Object[]
			{
				i,
				variants[i][1]
			});
		}
	}
	private static final int EVENT_MANAGER_ID = 31227;
	private static final int[] hearts =
	{
		4209,
		4210,
		4211,
		4212,
		4213,
		4214,
		4215,
		4216,
		4217
	};
	private static final int[] potions =
	{
		1374,
		1375,
		6036,
		1539
	};
	private static final int[] scrolls =
	{
		3926,
		3927,
		3928,
		3929,
		3930,
		3931,
		3932,
		3933,
		3934,
		3935
	};
	
	public void startEvent()
	{
		Player player = getSelf();
		if (!player.getPlayerAccess().IsEventGm)
		{
			return;
		}
		if (SetActive("heart", true))
		{
			spawnEventManagers();
			System.out.println("Event 'Change of Heart' started.");
			Announcements.getInstance().announceByCustomMessage("scripts.events.ChangeofHeart.AnnounceEventStarted", null);
		}
		else
		{
			player.sendMessage("Event 'Change of Heart' already started.");
		}
		_active = true;
		show("admin/events.htm", player);
	}
	
	public void stopEvent()
	{
		Player player = getSelf();
		if (!player.getPlayerAccess().IsEventGm)
		{
			return;
		}
		if (SetActive("heart", false))
		{
			unSpawnEventManagers();
			System.out.println("Event 'Change of Heart' stopped.");
			Announcements.getInstance().announceByCustomMessage("scripts.events.ChangeofHeart.AnnounceEventStoped", null);
		}
		else
		{
			player.sendMessage("Event 'Change of Heart' not started.");
		}
		_active = false;
		show("admin/events.htm", player);
	}
	
	public void letsplay()
	{
		Player player = getSelf();
		if (!player.isQuestContinuationPossible(true))
		{
			return;
		}
		zeroGuesses(player);
		if (haveAllHearts(player))
		{
			show(link(HtmCache.getInstance().getNotNull("scripts/events/heart/hearts_01.htm", player), false), player);
		}
		else
		{
			show("scripts/events/heart/hearts_00.htm", player);
		}
	}
	
	public void play(String[] var)
	{
		Player player = getSelf();
		if (!player.isQuestContinuationPossible(true) || (var.length == 0))
		{
			return;
		}
		if (!haveAllHearts(player))
		{
			if (var[0].equalsIgnoreCase("Quit"))
			{
				show("scripts/events/heart/hearts_00b.htm", player);
			}
			else
			{
				show("scripts/events/heart/hearts_00a.htm", player);
			}
			return;
		}
		if (var[0].equalsIgnoreCase("Quit"))
		{
			int curr_guesses = getGuesses(player);
			takeHeartsSet(player);
			reward(player, curr_guesses);
			show("scripts/events/heart/hearts_reward_" + curr_guesses + ".htm", player);
			zeroGuesses(player);
			return;
		}
		int var_cat = Rnd.get(variants.length);
		int var_player = 0;
		try
		{
			var_player = Integer.parseInt(var[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		if (var_player == var_cat)
		{
			show(fillvars(HtmCache.getInstance().getNotNull("scripts/events/heart/hearts_same.htm", player), var_player, var_cat, player), player);
			return;
		}
		if (playerWins(var_player, var_cat))
		{
			incGuesses(player);
			int curr_guesses = getGuesses(player);
			if (curr_guesses == 10)
			{
				takeHeartsSet(player);
				reward(player, curr_guesses);
				zeroGuesses(player);
			}
			show(fillvars(HtmCache.getInstance().getNotNull("scripts/events/heart/hearts_level_" + curr_guesses + ".htm", player), var_player, var_cat, player), player);
			return;
		}
		takeHeartsSet(player);
		reward(player, getGuesses(player) - 1);
		show(fillvars(HtmCache.getInstance().getNotNull("scripts/events/heart/hearts_loose.htm", player), var_player, var_cat, player), player);
		zeroGuesses(player);
	}
	
	private void reward(Player player, int guesses)
	{
		switch (guesses)
		{
			case -1:
			case 0:
				addItem(player, scrolls[Rnd.get(scrolls.length)], 1);
				break;
			case 1:
				addItem(player, potions[Rnd.get(potions.length)], 10);
				break;
			case 2:
				addItem(player, 1538, 1);
				break;
			case 3:
				addItem(player, 3936, 1);
				break;
			case 4:
				addItem(player, 951, 2);
				break;
			case 5:
				addItem(player, 948, 4);
				break;
			case 6:
				addItem(player, 947, 1);
				break;
			case 7:
				addItem(player, 730, 3);
				break;
			case 8:
				addItem(player, 729, 1);
				break;
			case 9:
				addItem(player, 960, 2);
				break;
			case 10:
				addItem(player, 959, 1);
				break;
		}
	}
	
	private String fillvars(String s, int var_player, int var_cat, Player player)
	{
		boolean rus = false;
		return link(s.replaceFirst("Player", player.getName()).replaceFirst("%var_payer%", variants[var_player][rus ? 1 : 0]).replaceFirst("%var_cat%", variants[var_cat][rus ? 1 : 0]), rus);
	}
	
	private String link(String s, boolean rus)
	{
		return s.replaceFirst("%links%", rus ? links_ru : links_en);
	}
	
	private boolean playerWins(int var_player, int var_cat)
	{
		if (var_player == 0)
		{
			return var_cat == 1;
		}
		if (var_player == 1)
		{
			return var_cat == 2;
		}
		if (var_player == 2)
		{
			return var_cat == 0;
		}
		return false;
	}
	
	private int getGuesses(Player player)
	{
		return Guesses.containsKey(player.getObjectId()) ? Guesses.get(player.getObjectId()) : 0;
	}
	
	private void incGuesses(Player player)
	{
		int val = 1;
		if (Guesses.containsKey(player.getObjectId()))
		{
			val = Guesses.remove(player.getObjectId()) + 1;
		}
		Guesses.put(player.getObjectId(), val);
	}
	
	private void zeroGuesses(Player player)
	{
		if (Guesses.containsKey(player.getObjectId()))
		{
			Guesses.remove(player.getObjectId());
		}
	}
	
	private void takeHeartsSet(Player player)
	{
		for (int heart_id : hearts)
		{
			removeItem(player, heart_id, 1);
		}
	}
	
	private boolean haveAllHearts(Player player)
	{
		for (int heart_id : hearts)
		{
			if (player.getInventory().getCountOf(heart_id) < 1)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void onDeath(Creature cha, Creature killer)
	{
		if (_active && SimpleCheckDrop(cha, killer))
		{
			((NpcInstance) cha).dropItem(killer.getPlayer(), hearts[Rnd.get(hearts.length)], Util.rollDrop(1, 1, Config.EVENT_CHANGE_OF_HEART_CHANCE * killer.getPlayer().getRateItems() * ((MonsterInstance) cha).getTemplate().rateHp * 10000L, true));
		}
	}
	
	@Override
	public void onPlayerEnter(Player player)
	{
		if (_active)
		{
			Announcements.getInstance().announceToPlayerByCustomMessage(player, "scripts.events.ChangeofHeart.AnnounceEventStarted", null);
		}
	}
	
	private static boolean isActive()
	{
		return IsActive("heart");
	}
	
	private void spawnEventManagers()
	{
		final int EVENT_MANAGERS[][] =
		{
			{
				146936,
				26654,
				-2208,
				16384
			},
			{
				82168,
				148842,
				-3464,
				7806
			},
			{
				82204,
				53259,
				-1488,
				16384
			},
			{
				18924,
				145782,
				-3088,
				44034
			},
			{
				111794,
				218967,
				-3536,
				20780
			},
			{
				-14539,
				124066,
				-3112,
				50874
			},
			{
				147271,
				-55573,
				-2736,
				60304
			},
			{
				87801,
				-143150,
				-1296,
				28800
			},
			{
				-80684,
				149458,
				-3040,
				16384
			},
		};
		SpawnNPCs(EVENT_MANAGER_ID, EVENT_MANAGERS, _spawns);
	}
	
	private void unSpawnEventManagers()
	{
		deSpawnNPCs(_spawns);
	}
	
	@Override
	public void onLoad()
	{
		CharListenerList.addGlobal(this);
		if (isActive())
		{
			_active = true;
			spawnEventManagers();
			_log.info("Loaded Event: Change of Heart [state: activated]");
		}
		else
		{
			_log.info("Loaded Event: Change of Heart[state: deactivated]");
		}
	}
	
	@Override
	public void onReload()
	{
		unSpawnEventManagers();
	}
	
	@Override
	public void onShutdown()
	{
		unSpawnEventManagers();
	}
}
