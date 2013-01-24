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
package lineage2.gameserver.ai;

import lineage2.gameserver.geodata.GeoEngine;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.instances.NpcInstance;

public class Ranger extends DefaultAI
{
	public Ranger(NpcInstance actor)
	{
		super(actor);
	}
	
	@Override
	protected boolean thinkActive()
	{
		return super.thinkActive() || defaultThinkBuff(10);
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		NpcInstance actor = getActor();
		if (actor.isDead() || (attacker == null) || (actor.getDistance(attacker) > 200))
		{
			return;
		}
		if (actor.isMoving)
		{
			return;
		}
		int posX = actor.getX();
		int posY = actor.getY();
		int posZ = actor.getZ();
		int old_posX = posX;
		int old_posY = posY;
		int old_posZ = posZ;
		int signx = posX < attacker.getX() ? -1 : 1;
		int signy = posY < attacker.getY() ? -1 : 1;
		int range = (int) (((0.71 * actor.calculateAttackDelay()) / 1000) * actor.getMoveSpeed());
		posX += signx * range;
		posY += signy * range;
		posZ = GeoEngine.getHeight(posX, posY, posZ, actor.getGeoIndex());
		if (GeoEngine.canMoveToCoord(old_posX, old_posY, old_posZ, posX, posY, posZ, actor.getGeoIndex()))
		{
			addTaskMove(posX, posY, posZ, false);
			addTaskAttack(attacker);
		}
	}
	
	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}
	
	@Override
	public int getRatePHYS()
	{
		return 25;
	}
	
	@Override
	public int getRateDOT()
	{
		return 40;
	}
	
	@Override
	public int getRateDEBUFF()
	{
		return 25;
	}
	
	@Override
	public int getRateDAM()
	{
		return 50;
	}
	
	@Override
	public int getRateSTUN()
	{
		return 50;
	}
	
	@Override
	public int getRateBUFF()
	{
		return 5;
	}
	
	@Override
	public int getRateHEAL()
	{
		return 50;
	}
}
