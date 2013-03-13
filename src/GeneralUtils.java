
import java.net.SocketException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josh
 */
public class GeneralUtils extends Thread{
    byte State;
    boolean runUpdateThread = false;
    
    public void updateAllDSConsoles(int mode, boolean enabled){
        byte out = 0x43;
        if ( mode == 1 ) {
            out = 0x53;
        } // Autonomous
        else if ( mode == 2 ){
                out = 0x43; // Teleop
        }
        if ( enabled ){ out += 0x20;}
	State = out;
    }
    void startUpdates()
    {
            if ( runUpdateThread ) return;
            runUpdateThread = true;
            this.start();
    }

    void stopUpdates()
    {
            runUpdateThread = false;
    }
    public void run() 
	{
		/*while ( runUpdateThread )
		{
			
			try {
				DSSender.GetInstance().updateTeam(this, State, Alliance, Station);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
    
}
