import java.io.IOException;


public class Utils 
{
	static String readLine(java.io.InputStream s)
	{
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(s));
		try {
			return br.readLine();
		} catch (IOException e) {
			return new String();
		}
	}
	
	static int readInt(java.io.InputStream s)
	{
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(s));
		try {
			return Integer.parseInt(br.readLine());
		} catch (IOException e) {
			return 0;
		}
	}
	
}
