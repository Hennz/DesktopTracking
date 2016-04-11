/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onlinedesktop;

import java.awt.Container;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author java
 */
public class MainFrame extends Thread{
    private JFrame frame = new JFrame();
    public static Container container;
    private ServerSocket server;
    private Socket client;
    private SingleUserDesktop obj;
    int x=5,y=5,width=50,height=50;

    MainFrame(){
        
    }

    public static void main(String arg[]){
        new MainFrame().initialize(8888);
    }
    public void initialize(int port){
        try{
            ServerSocket sc = new ServerSocket(port);
            drawGUI();
             while(true){
                client = sc.accept();
                System.out.println("New client Connected to the server");
                //Per each client create a ClientHandler
                start();

            }

        } catch(Exception e){
            System.out.println("Exception :- "+e);
        }
    }
    public void drawGUI(){
        container = frame.getContentPane();
        container.setLayout(null);
        frame.setSize(900,900);
        frame.setVisible(true);
    }
    @Override
    public void run(){
            obj=new SingleUserDesktop(client);
            obj.setBounds(x, y, width, height);
            container.add(obj);
            x+=60;
            y+=60;

    }

}
