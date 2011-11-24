import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class DSReceiver extends Thread {
	DatagramSocket sock;
	DSReceiver()
	{
		try {
			sock = new DatagramSocket(1160);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		byte[] data = new byte[50];
		DatagramPacket p = new DatagramPacket(data, data.length);
		while(true)
		{
			try {
				sock.receive(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.printf("Got data from %d%d DS Batt: %d\n", (int)data[4], (int)data[5], (int)data[42]);
			
			p.setLength(data.length);
		}
	}
}
