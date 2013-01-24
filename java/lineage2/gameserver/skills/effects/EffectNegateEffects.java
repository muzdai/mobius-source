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
package lineage2.gameserver.skills.effects;

import lineage2.gameserver.model.Effect;
import lineage2.gameserver.stats.Env;

public class EffectNegateEffects extends Effect
{
	public EffectNegateEffects(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onExit()
	{
		super.onExit();
	}
	
	@Override
	public boolean onActionTime()
	{
		for (Effect e : _effected.getEffectList().getAllEffects())
		{
			if (!e.getStackType().contains(EffectTemplate.NO_STACK))
			{
				for (String arg : getStackType())
				{
					if (e.getStackType().contains(arg))
					{
						if (e.getStackOrder() <= getStackOrder())
						{
							e.exit();
						}
					}
				}
			}
		}
		return false;
	}
}
