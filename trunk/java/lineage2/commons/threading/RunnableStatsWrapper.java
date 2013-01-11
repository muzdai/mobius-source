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
package lineage2.commons.threading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableStatsWrapper implements Runnable
{
	private static final Logger _log = LoggerFactory.getLogger(RunnableStatsWrapper.class);
	private final Runnable _runnable;
	
	RunnableStatsWrapper(Runnable runnable)
	{
		_runnable = runnable;
	}
	
	public static Runnable wrap(Runnable runnable)
	{
		return new RunnableStatsWrapper(runnable);
	}
	
	@Override
	public void run()
	{
		RunnableStatsWrapper.execute(_runnable);
	}
	
	public static void execute(Runnable runnable)
	{
		long begin = System.nanoTime();
		try
		{
			runnable.run();
			RunnableStatsManager.getInstance().handleStats(runnable.getClass(), System.nanoTime() - begin);
		}
		catch (Exception e)
		{
			_log.error("Exception in a Runnable execution:", e);
		}
	}
}
