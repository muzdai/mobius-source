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
package lineage2.gameserver.model.entity.olympiad;

import lineage2.gameserver.Config;

public enum CompType
{
	TEAM(2, Config.ALT_OLY_TEAM_RITEM_C, 5, false),
	NON_CLASSED(2, Config.ALT_OLY_NONCLASSED_RITEM_C, 5, true),
	CLASSED(2, Config.ALT_OLY_CLASSED_RITEM_C, 3, true);
	private int _minSize;
	private int _reward;
	private int _looseMult;
	private boolean _hasBuffer;
	
	private CompType(int minSize, int reward, int looseMult, boolean hasBuffer)
	{
		_minSize = minSize;
		_reward = reward;
		_looseMult = looseMult;
		_hasBuffer = hasBuffer;
	}
	
	public int getMinSize()
	{
		return _minSize;
	}
	
	public int getReward()
	{
		return _reward;
	}
	
	public int getLooseMult()
	{
		return _looseMult;
	}
	
	public boolean hasBuffer()
	{
		return _hasBuffer;
	}
}
