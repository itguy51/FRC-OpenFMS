import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Team extends Thread 
{
	static byte ROBOT_TELEOP = 0x43;
	static byte ROBOT_AUTONOMOUS = 0x53;
	static byte ROBOT_ENABLED = 0x20;
	static byte ROBOT_DISABLED = 0;
	
	static byte ALLIANCE_BLUE = 0x42;
	static byte ALLIANCE_RED = 0x52;
	
	static byte STATION_ONE = 0x31;
	static byte STATION_TWO = 0x32;
	static byte STATION_THREE = 0x33;

	boolean runUpdateThread = false;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	private InetAddress addr;
	
	byte State;
	byte Alliance;
	byte Station;
        int teamnum;
	
	
	Team(int number, int Alliance, int Station)
	{
                teamnum = number;
		this.Alliance = DSSender.byteForAlliance(Alliance);
		this.Station = DSSender.byteForStation(Station);
		
		String teamIP = "10.".concat(new Float((float)number/(float)100).toString().concat(".5"));
		try {
			addr = InetAddress.getByName(teamIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public void setTeamNumber(int number){
            String teamIP = "10.".concat(new Float((float)number/(float)100).toString().concat(".5"));
		try {
			addr = InetAddress.getByName(teamIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        }
	void startDSUpdates()
	{
		if ( runUpdateThread ) return;
		runUpdateThread = true;
		this.start();
	}
	
	void stopDSUpdates()
	{
		runUpdateThread = false;
                this.stop();
	}
	
	String getAddress()
	{
		return addr.getHostAddress();
	}
	
	InetAddress getInetAddress()
	{
		return addr;
	}
        public int getTeamNumber(){
            return teamnum;
        }
	
	void setState(int mode, boolean enabled)
	{
		byte out = 0x43;
		if ( mode == 1 )
			out = 0x53; // Autonomous
		else if ( mode == 2 )
			out = 0x43; // Teleop
		if ( enabled ) out += 0x20;
		State = out;
	}

	public void run() 
	{
		while ( runUpdateThread )
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
		}
	}
}
