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
package lineage2.gameserver.templates.player;

public final class BaseArmorDefence
{
	private final int _chest;
	private final int _legs;
	private final int _helmet;
	private final int _boots;
	private final int _gloves;
	private final int _underwear;
	private final int _cloak;
	
	public BaseArmorDefence(int chest, int legs, int helmet, int boots, int gloves, int underwear, int cloak)
	{
		_chest = chest;
		_legs = legs;
		_helmet = helmet;
		_boots = boots;
		_gloves = gloves;
		_underwear = underwear;
		_cloak = cloak;
	}
	
	public int getChestDef()
	{
		return _chest;
	}
	
	public int getLegsDef()
	{
		return _legs;
	}
	
	public int getHelmetDef()
	{
		return _helmet;
	}
	
	public int getBootsDef()
	{
		return _boots;
	}
	
	public int getGlovesDef()
	{
		return _gloves;
	}
	
	public int getUnderwearDef()
	{
		return _underwear;
	}
	
	public int getCloakDef()
	{
		return _cloak;
	}
}
