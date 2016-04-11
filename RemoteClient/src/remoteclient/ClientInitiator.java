
/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * Please do not remove the above lines
 */

package remoteclient;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * This class is responsible for connecting to the server
 * and starting ScreenSpyer and ServerDelegate classes
 */
public class ClientInitiator extends Thread{

    Socket socket = null;
    private boolean notConnected=true;
    private static String serverAddress="MAAN-PC";
    public static void main(String[] args){
        
        Properties props = new Properties();
        try {
            File f=new File(".");
             System.out.println("file------------ "+f.getAbsolutePath());
             props.load(new FileInputStream("serverhost.properties"));
             String ip = props.getProperty("IP");
             String hostName=props.getProperty("hostName");
             System.out.println(ip);
             System.out.println(hostName);
             
             /*if(!hostName.equals("")){
                serverAddress=hostName;
             } else if(!ip.equals("")){
                serverAddress=ip;
             }
                     */
             serverAddress=ip;
             
             new ClientInitiator().start();
       }     //catch exception in case properties file does not exist

             catch(IOException e)
             {
             e.printStackTrace();
             }
             
             System.out.println("serverAddress---------- "+serverAddress);
       //new ClientInitiator().initialize(serverAddress, 8888);
    }
    @Override
    public void run(){
        while(notConnected){
            try{
                 System.out.println("Connecting to server ..........");
                 socket = new Socket(serverAddress, 8888);
                 System.out.println("Connection Established.");
                 notConnected=false;
                 initialize();
            } catch(Exception e){
                try{
                    System.out.println("Exception creating serversocket connection--- "+e);
                    Thread.sleep(1*60*1000);
                } catch(Exception e1){
                    System.out.println("Thread sleep()--- "+e1);
                }
            }
        }
    }
    public void initialize(){

        Robot robot = null; //Used to capture the screen
        Rectangle rectangle = null; //Used to represent screen dimensions

        try {
            //System.out.println("Connecting to server ..........");
            //socket = new Socket(ip, port);
            //System.out.println("Connection Established.");

            //Get default screen device
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();

            //Get screen dimensions
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            //Prepare Robot object
            robot = new Robot(gDev);

            //draw client gui
            //drawGUI();
            //ScreenSpyer sends screenshots of the client screen
            new ScreenSpyer(socket,robot,rectangle);
            //ServerDelegate recieves server commands and execute them
            new ServerDelegate(socket,robot);
        }catch (AWTException ex) {
                ex.printStackTrace();
        }catch(Exception e){
                e.printStackTrace();
        }
    }

    private void drawGUI() {
        JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
    }
}
