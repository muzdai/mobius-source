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
package lineage2.loginserver.gameservercon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lineage2.commons.threading.RunnableImpl;
import lineage2.loginserver.Config;
import lineage2.loginserver.ThreadPoolManager;
import lineage2.loginserver.gameservercon.lspackets.PingRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameServerConnection
{
	private static final Logger _log = LoggerFactory.getLogger(GameServerConnection.class);
	final ByteBuffer readBuffer = ByteBuffer.allocate(64 * 1024).order(ByteOrder.LITTLE_ENDIAN);
	final Queue<SendablePacket> sendQueue = new ArrayDeque<>();
	final Lock sendLock = new ReentrantLock();
	final AtomicBoolean isPengingWrite = new AtomicBoolean();
	private final Selector selector;
	private final SelectionKey key;
	GameServer gameServer;
	private Future<?> _pingTask;
	int _pingRetry;
	
	private class PingTask extends RunnableImpl
	{
		public PingTask()
		{
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void runImpl()
		{
			if (Config.GAME_SERVER_PING_RETRY > 0)
			{
				if (_pingRetry > Config.GAME_SERVER_PING_RETRY)
				{
					_log.warn("Gameserver " + gameServer.getId() + " [" + gameServer.getName() + "] : ping timeout!");
					closeNow();
					return;
				}
			}
			_pingRetry++;
			sendPacket(new PingRequest());
		}
	}
	
	public GameServerConnection(SelectionKey key)
	{
		this.key = key;
		selector = key.selector();
	}
	
	public void sendPacket(SendablePacket packet)
	{
		boolean wakeUp;
		sendLock.lock();
		try
		{
			sendQueue.add(packet);
			wakeUp = enableWriteInterest();
		}
		catch (CancelledKeyException e)
		{
			return;
		}
		finally
		{
			sendLock.unlock();
		}
		if (wakeUp)
		{
			selector.wakeup();
		}
	}
	
	protected boolean disableWriteInterest() throws CancelledKeyException
	{
		if (isPengingWrite.compareAndSet(true, false))
		{
			key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
			return true;
		}
		return false;
	}
	
	protected boolean enableWriteInterest() throws CancelledKeyException
	{
		if (isPengingWrite.getAndSet(true) == false)
		{
			key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
			return true;
		}
		return false;
	}
	
	public void closeNow()
	{
		key.interestOps(SelectionKey.OP_CONNECT);
		selector.wakeup();
	}
	
	public void onDisconnection()
	{
		try
		{
			stopPingTask();
			readBuffer.clear();
			sendLock.lock();
			try
			{
				sendQueue.clear();
			}
			finally
			{
				sendLock.unlock();
			}
			isPengingWrite.set(false);
			if ((gameServer != null) && gameServer.isAuthed())
			{
				_log.info("Connection with gameserver " + gameServer.getId() + " [" + gameServer.getName() + "] lost.");
				_log.info("Setting gameserver down.");
				gameServer.setDown();
			}
			gameServer = null;
		}
		catch (Exception e)
		{
			_log.error("", e);
		}
	}
	
	ByteBuffer getReadBuffer()
	{
		return readBuffer;
	}
	
	GameServer getGameServer()
	{
		return gameServer;
	}
	
	void setGameServer(GameServer gameServer)
	{
		this.gameServer = gameServer;
	}
	
	public String getIpAddress()
	{
		return ((SocketChannel) key.channel()).socket().getInetAddress().getHostAddress();
	}
	
	public void onPingResponse()
	{
		_pingRetry = 0;
	}
	
	public void startPingTask()
	{
		if (Config.GAME_SERVER_PING_DELAY == 0)
		{
			return;
		}
		_pingTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new PingTask(), Config.GAME_SERVER_PING_DELAY, Config.GAME_SERVER_PING_DELAY);
	}
	
	public void stopPingTask()
	{
		if (_pingTask != null)
		{
			_pingTask.cancel(false);
			_pingTask = null;
		}
	}
}
