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
package ai.seedofinfinity;

import lineage2.gameserver.ai.DefaultAI;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.tables.SkillTable;

import org.apache.commons.lang3.ArrayUtils;

public class AliveTumor extends DefaultAI
{
	private long checkTimer = 0;
	private int coffinsCount = 0;
	private static final int[] regenCoffins =
	{
		18706,
		18709,
		18710
	};
	
	public AliveTumor(NpcInstance actor)
	{
		super(actor);
		actor.startImmobilized();
	}
	
	@Override
	protected boolean thinkActive()
	{
		NpcInstance actor = getActor();
		if ((checkTimer + 10000) < System.currentTimeMillis())
		{
			checkTimer = System.currentTimeMillis();
			int i = 0;
			for (NpcInstance n : actor.getAroundNpc(400, 300))
			{
				if (ArrayUtils.contains(regenCoffins, n.getNpcId()) && !n.isDead())
				{
					i++;
				}
			}
			if (coffinsCount != i)
			{
				coffinsCount = i;
				coffinsCount = Math.min(coffinsCount, 12);
				if (coffinsCount > 0)
				{
					actor.altOnMagicUseTimer(actor, SkillTable.getInstance().getInfo(5940, coffinsCount));
				}
			}
		}
		return super.thinkActive();
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
	}
	
	@Override
	protected void onEvtAggression(Creature target, int aggro)
	{
	}
}
