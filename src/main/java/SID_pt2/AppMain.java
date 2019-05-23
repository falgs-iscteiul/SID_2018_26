package SID_pt2;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Test;

public class AppMain {
	
	static App app = new App(); 
	static Timer timer = new Timer(); 
	static int period = 5000;
        
	public static void activate () {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				app.Migrate();
			}
		}, 0, period);
	}
	
	public static void main (String[] args) {
		activate();
	}
      
        
    

}
