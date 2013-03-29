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
package ai.EvilIncubator;

import java.util.List;

import lineage2.gameserver.ai.CtrlEvent;
import lineage2.gameserver.ai.Ranger;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.instances.NpcInstance;

/**
 * @author KilRoy
 */
public class EvilIncubatorArcher extends Ranger
{
	
	public EvilIncubatorArcher(NpcInstance actor)
	{
		super(actor);
	}
	
	@Override
	protected boolean thinkActive()
	{
		NpcInstance actor = getActor();
		if (actor.isDead())
		{
			return false;
		}
		
		List<NpcInstance> around = actor.getAroundNpc(3000, 3000);
		if ((around != null) && !around.isEmpty())
		{
			for (NpcInstance npc : around)
			{
				if (npc.isMonster())
				{
					actor.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, npc, 300);
				}
			}
		}
		return true;
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		if ((attacker == null) || attacker.isPlayable())
		{
			return;
		}
		
		super.onEvtAttacked(attacker, damage);
	}
	
	@Override
	public int getMaxAttackTimeout()
	{
		return 0;
	}
	
	@Override
	protected boolean maybeMoveToHome()
	{
		return false;
	}
	
	@Override
	protected boolean randomWalk()
	{
		return false;
	}
}