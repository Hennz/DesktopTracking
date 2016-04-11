/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onlinedesktop;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author java
 */
public class SingleUserDesktop extends JPanel implements Runnable{
    JTextField txtField=new JTextField("Test");
    
    private Socket cSocket = null;
    SingleUserDesktop(){
        
    }
   public SingleUserDesktop(Socket cSocket){
        this.cSocket = cSocket;
        Thread thread=new Thread(this);
        thread.start();
    }
    
    public void drawGUI(){
        setLayout(new BorderLayout());
        setSize(450,450);
        this.setFocusable(true);
        this.setVisible(true);
    }
    public void run(){
        drawGUI();

        //used to represent client screen size
        System.out.println("used to represent client screen size");
        Rectangle clientScreenDim = null;
        //Used to read screenshots and client screen dimension
        System.out.println("Used to read screenshots and client screen dimension");
        ObjectInputStream ois = null;
        //start drawing GUI
        System.out.println("start drawing GUI");

        try{
            //Read client screen dimension
            System.out.println("Read client screen dimension");
            ois = new ObjectInputStream(cSocket.getInputStream());
            clientScreenDim =(Rectangle) ois.readObject();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } 
        //Start recieveing screenshots
        System.out.println("Start recieveing screenshots");
        //new ClientScreenReciever(cSocket,this);
        new ClientScreenReciever(ois,this);
        //Start sending events to the client
        System.out.println("Start sending events to the client");
       // new ClientCommandsSender(cSocket,this,clientScreenDim);
        
    }
}
