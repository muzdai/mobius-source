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
package lineage2.gameserver.network.loginservercon.lspackets;

import lineage2.gameserver.network.loginservercon.LoginServerCommunication;
import lineage2.gameserver.network.loginservercon.ReceivablePacket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginServerFail extends ReceivablePacket
{
	private static final Logger _log = LoggerFactory.getLogger(LoginServerFail.class);
	private static final String[] reasons =
	{
		"none",
		"IP banned",
		"IP reserved",
		"wrong hexid",
		"ID reserved",
		"no free ID",
		"not authed",
		"already logged in"
	};
	private int _reason;
	
	public String getReason()
	{
		return reasons[_reason];
	}
	
	@Override
	protected void readImpl()
	{
		_reason = readC();
	}
	
	@Override
	protected void runImpl()
	{
		_log.warn("Authserver registration failed! Reason: " + getReason());
		LoginServerCommunication.getInstance().restart();
	}
}
