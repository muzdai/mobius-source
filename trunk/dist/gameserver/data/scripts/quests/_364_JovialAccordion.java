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
package quests;

import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class _364_JovialAccordion extends Quest implements ScriptFile
{
	private static int BARBADO = 30959;
	private static int SWAN = 30957;
	private static int SABRIN = 30060;
	private static int BEER_CHEST = 30960;
	private static int CLOTH_CHEST = 30961;
	private static int KEY_1 = 4323;
	private static int KEY_2 = 4324;
	private static int BEER = 4321;
	private static int ECHO = 4421;
	
	public _364_JovialAccordion()
	{
		super(false);
		addStartNpc(BARBADO);
		addTalkId(SWAN);
		addTalkId(SABRIN);
		addTalkId(BEER_CHEST);
		addTalkId(CLOTH_CHEST);
		addQuestItem(KEY_1);
		addQuestItem(KEY_2);
		addQuestItem(BEER);
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		if (st.getState() == CREATED)
		{
			if (npcId != BARBADO)
			{
				return htmltext;
			}
			st.setCond(0);
			st.set("ok", "0");
		}
		int cond = st.getCond();
		if (npcId == BARBADO)
		{
			if (cond == 0)
			{
				htmltext = "30959-01.htm";
			}
			else if (cond == 3)
			{
				htmltext = "30959-03.htm";
				st.giveItems(ECHO, 1);
				st.playSound(SOUND_FINISH);
				st.exitCurrentQuest(true);
			}
			else if (cond > 0)
			{
				htmltext = "30959-02.htm";
			}
		}
		else if (npcId == SWAN)
		{
			if (cond == 1)
			{
				htmltext = "30957-01.htm";
			}
			else if (cond == 3)
			{
				htmltext = "30957-05.htm";
			}
			else if (cond == 2)
			{
				if ((st.getInt("ok") == 1) && (st.getQuestItemsCount(KEY_1) == 0))
				{
					st.setCond(3);
					htmltext = "30957-04.htm";
				}
				else
				{
					htmltext = "30957-03.htm";
				}
			}
		}
		else if ((npcId == SABRIN) && (cond == 2) && (st.getQuestItemsCount(BEER) > 0))
		{
			st.set("ok", "1");
			st.takeItems(BEER, -1);
			htmltext = "30060-01.htm";
		}
		else if ((npcId == BEER_CHEST) && (cond == 2))
		{
			htmltext = "30960-01.htm";
		}
		else if ((npcId == CLOTH_CHEST) && (cond == 2))
		{
			htmltext = "30961-01.htm";
		}
		return htmltext;
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		int _state = st.getState();
		int cond = st.getCond();
		if (event.equalsIgnoreCase("30959-02.htm") && (_state == CREATED) && (cond == 0))
		{
			st.setCond(1);
			st.setState(STARTED);
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("30957-02.htm") && (_state == STARTED) && (cond == 1))
		{
			st.setCond(2);
			st.giveItems(KEY_1, 1);
			st.giveItems(KEY_2, 1);
		}
		else if (event.equalsIgnoreCase("30960-03.htm") && (cond == 2) && (st.getQuestItemsCount(KEY_2) > 0))
		{
			st.takeItems(KEY_2, -1);
			st.giveItems(BEER, 1);
			htmltext = "30960-02.htm";
		}
		else if (event.equalsIgnoreCase("30961-03.htm") && (cond == 2) && (st.getQuestItemsCount(KEY_1) > 0))
		{
			st.takeItems(KEY_1, -1);
			htmltext = "30961-02.htm";
		}
		return htmltext;
	}
	
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
}
