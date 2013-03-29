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
package instances;

import lineage2.gameserver.model.entity.Reflection;
import lineage2.gameserver.utils.Location;

public class TeredorCavern extends Reflection
{
	private static int Teredor = 25785;
	private static Location TeredorSpawnCoords = new Location(176160, -185200, -3800);
	
	@Override
	protected void onCreate()
	{
		super.onCreate();
		addSpawnWithoutRespawn(Teredor, TeredorSpawnCoords, 0);
	}
}