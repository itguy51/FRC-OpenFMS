import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class DSSender 
{
	static DSSender instance = null;
	
	private DatagramSocket dsock;
	
	static DSSender GetInstance()
	{
		if ( instance == null )
			instance = new DSSender();
		return instance;
	}
	
	DSSender()
	{
		try {
			dsock = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	void sendPacket(DatagramPacket p)
	{
		try {
			dsock.send(p);
		} catch (IOException e) {
			return;
		}
	}
	
	public static byte byteForStation(int station)
	{
		switch ( station )
		{
			case 1:
				return 0x31;
			case 2:
				return 0x32;
			case 3:
				return 0x33;
		}
		return 0x31; // default to 1
	}
	
	public static byte byteForAlliance(int alliance)
	{
		switch ( alliance )
		{
			case 1:
				return 0x52; // Red
			case 2:
				return 0x42; // Blue
		}
		return 0x52; // default to Red
	}
	
	void updateTeam(Team t, byte robotState, byte allianceColor, byte station) throws SocketException
	{
		sendPacket(buildPacket(t.getInetAddress(), robotState, allianceColor, station));
	}
	
	DatagramPacket buildPacket(InetAddress addr, byte robotState, byte allianceColor, byte station) throws SocketException
	{
		byte[] data = new byte[74];

		for ( int i = 0; i<74; i++ )
			data[i] = 0;
		
		// Packet number
		data[0] = (byte) 9818;
		data[1] = (byte) 4342;
		
		// Robot state
		data[2] = robotState;
		
		// Alliance Station
		data[3] = allianceColor; // Color
		data[4] = station; // Station number
		
		
		// FMS version
		data[18] = 0;
		data[19] = 6;
		data[20] = 2;
		data[21] = 5;
		data[22] = 0;
		data[23] = 8;
		data[24] = 4;
		data[25] = 6;
		
		CRC32 check = new CRC32();
		check.update(data);
		byte[] crc = ByteBuffer.allocate(4).putInt((int)check.getValue()).array();
		
		// CRC hash
		data[70] = crc[0];
		data[71] = crc[1];
		data[72] = crc[2];
		data[73] = crc[3];
		
		return new DatagramPacket(data, data.length, addr, 1120);
	}
}
