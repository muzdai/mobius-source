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
package lineage2.gameserver.network.clientpackets;

import lineage2.gameserver.instancemanager.commission.CommissionShopManager;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.instances.NpcInstance;

public class RequestCommissionList extends L2GameClientPacket
{
	private int listType;
	private int category;
	private int rareType;
	private int itemGrade;
	private String searchName;
	
	@Override
	protected void readImpl()
	{
		listType = readD();
		category = readD();
		rareType = readD();
		itemGrade = readD();
		searchName = readS();
	}
	
	@Override
	protected void runImpl()
	{
		Player player = getClient().getActiveChar();
		if (player == null)
		{
			return;
		}
		NpcInstance npc = player.getLastNpc();
		if ((npc == null) || !npc.isInRangeZ(npc, Creature.INTERACTION_DISTANCE))
		{
			return;
		}
		CommissionShopManager.getInstance().showItems(listType, category, rareType, itemGrade, searchName, player);
	}
}
