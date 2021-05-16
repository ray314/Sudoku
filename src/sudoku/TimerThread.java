/**
 * 
 */
package sudoku;

import java.awt.Font;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author Justin Yeung
 *
 */
public class TimerThread extends Thread {

	public int seconds; // Seconds
	//public long minutes;
	public Object lock = this;
	public boolean pause = false;
	private JLabel label;
	/**
	 * Custom label that has a timer
	 */
	public TimerThread(JLabel label) {
		// TODO Auto-generated constructor stub
		seconds = 0;
		//minutes = 0;
		this.label = label;
		//setFont(new Font("Arial", Font.PLAIN, 14));
	}
	
	public void resumeThread()
	{	
		pause = false;
		synchronized (lock) // Prevent exception
		{
			lock.notifyAll(); // Wake up thread
		}
	}

	public void pauseThread()
	{
		synchronized (lock)
		{
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void run() {
		
		while (SudokuPuzzle.playing)
		{
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (pause)
			{
				pauseThread();
			}
			
			seconds += 1;
			//minutes = TimeUnit.SECONDS.toMinutes(seconds); // Convert seconds to minutes
			label.setText(Long.toString(seconds) + "s");
		}
	}

}
