
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josh
 */
public class GovernThread extends Thread{
    private String state = "Disabled";
    private Team red1, red2, red3, blue1, blue2, blue3;
    private boolean red1b, red2b, red3b, blue1b, blue2b, blue3b;
    
    public GovernThread(){
        red1b = red2b = red3b = blue1b = blue2b = blue3b = true;
        System.out.println("Ok1");
        red1 = new Team(0000, 1, 1);
        System.out.println("Ok2");
        red2 = new Team(0001, 1, 2);
        System.out.println("Ok3");
        red3 = new Team(0002, 1, 3);
        System.out.println("Ok4");
        blue1 = new Team(0003, 2, 1);
        System.out.println("Ok5");
        blue2 = new Team(0004, 2, 2);
        System.out.println("Ok6");
        blue3 = new Team(0005, 2, 3);
        System.out.println("Ok7");
    }

    void setBlue3TeamNumber(int parseInt) {
        blue3.setTeamNumber(parseInt);
    }
    void setBlue2TeamNumber(int parseInt) {
        blue2.setTeamNumber(parseInt);
    }
    void setBlue1TeamNumber(int parseInt) {
        blue1.setTeamNumber(parseInt);
    }
    void setRed1TeamNumber(int parseInt) {
        red1.setTeamNumber(parseInt);
    }
    void setRed2TeamNumber(int parseInt) {
        red2.setTeamNumber(parseInt);
    }
    void setRed3TeamNumber(int parseInt) {
        red3.setTeamNumber(parseInt);
    }
    
    
    
    
    
    public void setred1(boolean enable){
	red1.setState(2, enable);
	red1b = enable;
    }
    public void setred2(boolean enable){
            red2.setState(2, enable);
            red2b = enable;
    }
    public void setred3(boolean enable){
            red3.setState(2, enable);
            red3b = enable;
    }
    public void setblue1(boolean enable){
            blue1.setState(2, enable);
            blue1b = enable;
    }
    public void setblue2(boolean enable){
            blue2.setState(2, enable);
            blue2b = enable;
    }
    public void setblue3(boolean enable){
            blue3.setState(2, enable);
            blue3b = enable;
    }



    
    
    
    
    
    
    public void startComms(){
        red1.startDSUpdates();
        red2.startDSUpdates();
        red3.startDSUpdates();
        blue1.startDSUpdates();
        blue2.startDSUpdates();
        blue3.startDSUpdates();
    }
    public void stopComms(){
        red1.stopDSUpdates();
        red2.stopDSUpdates();
        red3.stopDSUpdates();
        blue1.stopDSUpdates();
        blue2.stopDSUpdates();
        blue3.stopDSUpdates();
    }
    public String getMatchState(){
        return state;
    }
    public void run(){
        try {
            red1.setState(1, red1b);
            red2.setState(1, red2b);
            red3.setState(1, red3b);
            blue1.setState(1, blue1b);
            blue2.setState(1, blue2b);
            blue3.setState(1, blue3b);
            System.out.println("Autonomous Start");
            state = "Autonomous";
            Thread.sleep(15000);

            System.out.println("Autonomous End");
            
            red1.setState(2, false);
            red2.setState(2, false);
            red3.setState(2, false);
            blue1.setState(2, false);
            blue2.setState(2, false);
            blue3.setState(2, false);
            state = "Disabled";
            Thread.sleep(1000);

            red1.setState(2, red1b);
            red2.setState(2, red2b);
            red3.setState(2, red3b);
            blue1.setState(2, blue1b);
            blue2.setState(2, blue2b);
            blue3.setState(2, blue3b);
            System.out.println("Teleop Start");
            state = "Teleop";
            Thread.sleep(120000);

            System.out.println("Teleop End");
            red1.setState(2, false);
            red2.setState(2, false);
            red3.setState(2, false);
            blue1.setState(2, false);
            blue2.setState(2, false);
            blue3.setState(2, false);
            state = "Disabled";
            Thread.sleep(1000);
            stop();
        } catch (InterruptedException ex) {
            Logger.getLogger(GovernThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
