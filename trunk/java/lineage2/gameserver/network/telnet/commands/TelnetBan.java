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
package lineage2.gameserver.network.telnet.commands;

import java.util.LinkedHashSet;
import java.util.Set;

import lineage2.gameserver.network.telnet.TelnetCommand;
import lineage2.gameserver.network.telnet.TelnetCommandHolder;
import lineage2.gameserver.utils.AdminFunctions;

public class TelnetBan implements TelnetCommandHolder
{
	private final Set<TelnetCommand> _commands = new LinkedHashSet<>();
	
	public TelnetBan()
	{
		_commands.add(new TelnetCommand("kick")
		{
			@Override
			public String getUsage()
			{
				return "kick <name>";
			}
			
			@Override
			public String handle(String[] args)
			{
				if ((args.length == 0) || args[0].isEmpty())
				{
					return null;
				}
				if (AdminFunctions.kick(args[0], "telnet"))
				{
					return "Player kicked.\n";
				}
				return "Player not found.\n";
			}
		});
	}
	
	@Override
	public Set<TelnetCommand> getCommands()
	{
		return _commands;
	}
}
