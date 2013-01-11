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
package ai.freya;

import java.util.concurrent.ScheduledFuture;

import lineage2.commons.threading.RunnableImpl;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.ai.CtrlEvent;
import lineage2.gameserver.ai.Fighter;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.model.instances.NpcInstance;

public class IceKnightNormal extends Fighter
{
	boolean iced;
	private ScheduledFuture<?> task;
	
	public IceKnightNormal(NpcInstance actor)
	{
		super(actor);
		MAX_PURSUE_RANGE = 6000;
	}
	
	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();
		NpcInstance actor = getActor();
		iced = true;
		actor.setNpcState(1);
		actor.block();
		Reflection r = actor.getReflection();
		if ((r != null) && (r.getPlayers() != null))
		{
			for (Player p : r.getPlayers())
			{
				this.notifyEvent(CtrlEvent.EVT_AGGRESSION, p, 300);
			}
		}
		task = ThreadPoolManager.getInstance().schedule(new ReleaseFromIce(), 6000L);
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		NpcInstance actor = getActor();
		if (iced)
		{
			iced = false;
			if (task != null)
			{
				task.cancel(false);
			}
			actor.unblock();
			actor.setNpcState(2);
		}
		super.onEvtAttacked(attacker, damage);
	}
	
	private class ReleaseFromIce extends RunnableImpl
	{
		public ReleaseFromIce()
		{
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void runImpl()
		{
			if (iced)
			{
				iced = false;
				getActor().setNpcState(2);
				getActor().unblock();
			}
		}
	}
	
	@Override
	protected void teleportHome()
	{
	}
}
