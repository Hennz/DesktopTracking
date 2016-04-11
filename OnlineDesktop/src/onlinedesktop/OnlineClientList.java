/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedesktop;

import java.awt.Container;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author java
 */
public class OnlineClientList extends Thread {

    private Socket socket;
    public static Vector onlineVector = new Vector();
    public static Vector socketVector = new Vector();
    public static JFrame frameList[];
    public static HashMap frameMap=new HashMap();

    public OnlineClientList() {
    }

    public void getOnlineClient(ServerSocket server) {
        this.start();
        try {
            while (true) {
                System.out.println("socket creation-------------");
                socket = server.accept();
                socketVector.add(socket);
                System.out.println("socket creation successssssssssssss----");
                System.out.println("New client Connected to the server");
                //Per each client create a ClientHandler
                InetAddress address = socket.getInetAddress();
                onlineVector.add(address.getHostAddress() + "--" + address.getHostName());
                System.out.println("OnlineClientList---------socketVector---+--size------- " + socketVector.size() + " " + socketVector);
                System.out.println("OnlineClientList---------onlineVector---+--size------- " + socketVector.size() + " " + onlineVector);

            }
        } catch (Exception e) {
            System.out.println("exception creating socket--------- " + e);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("OnlineClientList Thread---------------------------------");
            try {
                int j = 0;
                for (int i = 0; i < socketVector.size(); i++) {
                    Socket s = (Socket) socketVector.get(i);
                    InetAddress address = s.getInetAddress();
                    try {
                        OutputStream is = s.getOutputStream();
                        is.write(i);
                    } catch (Exception e) {
                        frameMap.remove(address.getHostName());
                        OnlineClientList.socketVector.remove(i);
                        OnlineClientList.onlineVector.remove(i);
                        j++;

                        System.out.println("Thread  Exception:::::::_" + e);
                    }
                    System.out.println("OnlineClientList------after thread execute---socketVector---+--size------- " + socketVector.size() + " " + socketVector);
                    System.out.println("OnlineClientList------after thread execute---onlineVector---+--size------- " + socketVector.size() + " " + onlineVector);
                }

              //  createFrames();

                Thread.sleep(4000);

            } catch (Exception e) {
                System.out.println("Exception in Thread OnlineClientList-----------" + e);
            }

        }
    }

    public void createFrames() {
        InetAddress address;
        frameList = new JFrame[socketVector.size()];
        for (int i = 0; i < frameList.length; i++) {
            Socket client = (Socket) socketVector.get(i);
            address = client.getInetAddress();
            frameList[i] = new JFrame(address.getHostAddress() + "--" + address.getHostName());
            //JFrame frame = new JFrame(address.getHostAddress()+"--"+address.getHostName());
            Container contain = frameList[i].getContentPane();
            frameList[i].setSize(500, 500);
          
            contain.add(new SingleUserDesktop(client));
            frameList[i].setVisible(false);
        } System.out.println("Total Frame Created---------------- "+frameList.length);

    }
  
}
