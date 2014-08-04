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

import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class _10328_RequestOfSealedEvilFragments extends Quest implements ScriptFile
{
	private static final int panteleon = 32972;
	private static final int kakai = 30565;
	private static final int evil = 17577;
	
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
	
	public _10328_RequestOfSealedEvilFragments()
	{
		super(false);
		addStartNpc(panteleon);
		addTalkId(panteleon);
		addTalkId(kakai);
		addLevelCheck(1, 20);
		addQuestCompletedCheck(_10327_BookOfGiants.class);
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		
		if (event.equalsIgnoreCase("quest_ac"))
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
			htmltext = "0-4.htm";
			
			if (st.getQuestItemsCount(evil) < 1)
			{
				st.giveItems(evil, 1, false);
			}
		}
		
		if (event.equalsIgnoreCase("qet_rev"))
		{
			htmltext = "1-3.htm";
			st.getPlayer().addExpAndSp(13000, 4000);
			st.giveItems(57, 20000);
			st.exitCurrentQuest(false);
			st.playSound(SOUND_FINISH);
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int cond = st.getCond();
		int npcId = npc.getNpcId();
		String htmltext = "noquest";
		
		if (npcId == panteleon)
		{
			if (st.isCompleted())
			{
				htmltext = "0-c.htm";
			}
			else if ((cond == 0) && isAvailableFor(st.getPlayer()))
			{
				htmltext = "start.htm";
			}
			else if (cond == 1)
			{
				htmltext = "0-5.htm";
			}
			else
			{
				htmltext = "0-nc.htm";
			}
		}
		else if (npcId == kakai)
		{
			if (st.isCompleted())
			{
				htmltext = "1-c.htm";
			}
			else if (cond == 0)
			{
				htmltext = TODO_FIND_HTML;
			}
			else if (cond == 1)
			{
				st.takeAllItems(evil);
				htmltext = "1-1.htm";
			}
		}
		
		return htmltext;
	}
}
