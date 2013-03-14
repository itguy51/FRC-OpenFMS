public class FMS {
	FMS()
	{
		DSReceiver ds = new DSReceiver();
		ds.start();
                //FMSService serv = new FMSService();
                //serv.start();
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFMSDialog().setVisible(true);
            }
        });
                System.out.println("FMS Daemon Online");
		/*try {
			System.out.println("Enter team number:");
			Team team = new Team(Utils.readInt(System.in), 2, 1);
			team.setState(1, false);
			team.startDSUpdates();
			
			System.out.println("Press enter to start");
			Utils.readLine(System.in);
			
			team.setState(1, true);
			System.out.println("Autonomous Start");
			Thread.sleep(15000);
			
			System.out.println("Autonomous End");
			team.setState(2, false);
			Thread.sleep(1000);
			
			team.setState(2, true);
			System.out.println("Teleop Start");
			Thread.sleep(120000);
			
			System.out.println("Teleop End");
			team.setState(2, false);
			
			Thread.sleep(1000);
			team.stopDSUpdates();
		}
		catch ( InterruptedException e)
		{
		
		}*/

	}
	
	public static void main(String[] args)
	{
		FMS fms = new FMS();
	}

}
