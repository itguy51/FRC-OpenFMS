
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Ref Screen Skeleton Interface
 * Listens on port 9508 and expects Client Polling
 * Packet Structure
 *  Bytes 1-12 are the Red Alliance Team Numbers (Zero-Padded)
 *  Bytes 13-15 are the Blue Alliance Team Numbers (Zero-Padded)
 *  Bytes 16-19 are Red Alliance Bot States (Enabled/Disabled)
 *  Bytes 20-23 are Blue Alliance Bot States (Enabled/Disabled)
 *  Bytes 24-26 are the Match Timer (Seconds Elapsed)
 *  Byte 27 is the Match State (0 - Disabled, 1 - Autonomous, 2 - Teleoperated)
 *  An example packet follows
 *  0038021040204570000300581111110302
 *  In this case, Red Bots are 38, 210, and 4020
 *  Blue bots are 4570, 3, and 58
 *  All bots are enabled
 *  Match has been running for 30 seconds
 *  The match is currently in Teleop mode
 * To GET a packet, the client must 'ping' this server knowing some criteria.
 * The REFSCREEN must know the current Login ID - Simply for security purposes.
 * !--- TBA Functionality ---!
 * If a ping is SIGNED, and contains a REQUEST_FOR_AUTHORIZATION packet, an FTA
 * may AUTHORIZE the DEVICE after the match has completed. An AUTHORIZED_DEVICE
 * may issue DIRECTIVES to the FMS system.
 */

/**
 *
 * @author Josh
 */

public class RefScreenInterface extends Thread{
    /*
     * Why Strings? Because if a packet is plaintext, then more applications
     * can tend to manipulate it faster. Because the reference screen targets 
     * ARM Java, where String operations tend to be faster than Byte operations
     * from my tests. I could be wrong, because I haven't targetted Android 4.0 
     * yet.
     */
    public String redAlliance = "";
    public String blueAlliance = "";
    public String redAllianceStates = "";
    public String blueAllianceStates = "";
    public String matchState = "";
    public String matchTime = "";
    public void setRedAlliance(int red1, int red2, int red3){
        redAlliance = red1 + "" + red2 + "" + red3;
    }
    public void setBlueAlliance(int blue1, int blue2, int blue3){
        blueAlliance = blue1 + "" + blue2 + "" + blue3;
    }
    public void setRedAllianceStates(int red1, int red2, int red3){
        redAllianceStates = red1 + "" + red2 + "" + red3;
    }
    public void setBlueAllianceStates(int blue1, int blue2, int blue3){
        blueAllianceStates = blue1 + "" + blue2 + "" + blue3;
    }
    public void setMatchState(int state){
        matchState = state + "";
    }
    public void setMatchTime(int time){
        matchTime = time + "";
    }
    @Override
    public void run(){
        //So what you're gonna need to do 'ere is set up a server that pitches out packets when asked.
        //Use whatever language to interface here, it's documented.
         ServerSocket welcomeSocket = null;
         String clientSentence;
        try {
            welcomeSocket = new ServerSocket(9508);
        } catch (IOException ex) {
            Logger.getLogger(RefScreenInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

         while(true)
         {
            try {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                   new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                //System.out.println("Received: " + clientSentence);
                outToClient.writeBytes(redAlliance + blueAlliance + redAllianceStates + blueAllianceStates + matchTime + matchState);
            } catch (IOException ex) {
                Logger.getLogger(RefScreenInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
}
