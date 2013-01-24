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
package quests;

import lineage2.commons.util.Rnd;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class _354_ConquestofAlligatorIsland extends Quest implements ScriptFile
{
	public final int KLUCK = 30895;
	public final int CROKIAN_LAD = 20804;
	public final int DAILAON_LAD = 20805;
	public final int CROKIAN_LAD_WARRIOR = 20806;
	public final int FARHITE_LAD = 20807;
	public final int NOS_LAD = 20808;
	public final int SWAMP_TRIBE = 20991;
	public final int ALLIGATOR_TOOTH = 5863;
	public final int TORN_MAP_FRAGMENT = 5864;
	public final int PIRATES_TREASURE_MAP = 5915;
	public final int CHANCE = 35;
	public final int CHANCE2 = 10;
	public final int[] MOBLIST =
	{
		CROKIAN_LAD,
		DAILAON_LAD,
		CROKIAN_LAD_WARRIOR,
		FARHITE_LAD,
		NOS_LAD,
		SWAMP_TRIBE
	};
	public final int[][] RANDOM_REWARDS =
	{
		{
			736,
			15
		},
		{
			1061,
			20
		},
		{
			734,
			10
		},
		{
			735,
			5
		},
		{
			1878,
			25
		},
		{
			1875,
			10
		},
		{
			1879,
			10
		},
		{
			1880,
			10
		},
		{
			956,
			1
		},
		{
			955,
			1
		}
	};
	
	@Override
	public void onLoad()
	{
	}
	
	@Override
	public void onReload()
	{
	}
	
	@Override
	public void onShutdown()
	{
	}
	
	public _354_ConquestofAlligatorIsland()
	{
		super(false);
		addStartNpc(30895);
		for (int i : MOBLIST)
		{
			addKillId(i);
		}
		addQuestItem(new int[]
		{
			ALLIGATOR_TOOTH,
			TORN_MAP_FRAGMENT
		});
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		long amount = st.getQuestItemsCount(ALLIGATOR_TOOTH);
		if (event.equalsIgnoreCase("30895-00a.htm"))
		{
			st.exitCurrentQuest(true);
		}
		else if (event.equalsIgnoreCase("1"))
		{
			st.setState(STARTED);
			st.setCond(1);
			htmltext = "30895-02.htm";
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("30895-06.htm"))
		{
			if (st.getQuestItemsCount(TORN_MAP_FRAGMENT) > 9)
			{
				htmltext = "30895-07.htm";
			}
		}
		else if (event.equalsIgnoreCase("30895-05.htm"))
		{
			if (amount > 0)
			{
				if (amount > 99)
				{
					st.giveItems(ADENA_ID, amount * 300);
					st.takeItems(ALLIGATOR_TOOTH, -1);
					st.playSound(SOUND_ITEMGET);
					int random = Rnd.get(RANDOM_REWARDS.length);
					st.giveItems(RANDOM_REWARDS[random][0], RANDOM_REWARDS[random][1]);
					htmltext = "30895-05b.htm";
				}
				else
				{
					st.giveItems(ADENA_ID, amount * 100);
					st.takeItems(ALLIGATOR_TOOTH, -1);
					st.playSound(SOUND_ITEMGET);
					htmltext = "30895-05a.htm";
				}
			}
		}
		else if (event.equalsIgnoreCase("30895-08.htm"))
		{
			st.giveItems(PIRATES_TREASURE_MAP, 1);
			st.takeItems(TORN_MAP_FRAGMENT, -1);
			st.playSound(SOUND_ITEMGET);
		}
		else if (event.equalsIgnoreCase("30895-09.htm"))
		{
			st.exitCurrentQuest(true);
			st.playSound(SOUND_FINISH);
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int cond = st.getCond();
		if (cond < 1)
		{
			if (st.getPlayer().getLevel() < 38)
			{
				htmltext = "30895-00.htm";
			}
			else
			{
				htmltext = "30895-01.htm";
			}
		}
		else
		{
			htmltext = "30895-03.htm";
		}
		return htmltext;
	}
	
	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if (Rnd.chance(CHANCE))
		{
			st.giveItems(ALLIGATOR_TOOTH, 1);
			st.playSound(SOUND_ITEMGET);
		}
		if (Rnd.chance(CHANCE2) && (st.getQuestItemsCount(TORN_MAP_FRAGMENT) < 10))
		{
			st.giveItems(TORN_MAP_FRAGMENT, 1);
			if (st.getQuestItemsCount(TORN_MAP_FRAGMENT) < 10)
			{
				st.playSound(SOUND_ITEMGET);
			}
			else
			{
				st.playSound(SOUND_MIDDLE);
			}
		}
		return null;
	}
}
