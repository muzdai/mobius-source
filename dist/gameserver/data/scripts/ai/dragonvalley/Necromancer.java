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
package ai.dragonvalley;

import lineage2.commons.util.Rnd;
import lineage2.gameserver.ai.CtrlEvent;
import lineage2.gameserver.ai.Mystic;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.utils.NpcUtils;

public class Necromancer extends Mystic
{
	public Necromancer(NpcInstance actor)
	{
		super(actor);
	}
	
	@Override
	protected void onEvtDead(Creature killer)
	{
		super.onEvtDead(killer);
		if (Rnd.chance(30))
		{
			NpcInstance n = NpcUtils.spawnSingle(Rnd.chance(50) ? 22818 : 22819, getActor().getLoc());
			n.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, killer, 2);
		}
	}
}
