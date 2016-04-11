/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import datalayer.common.DBOperation;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import onlinedesktop.OnlineClientList;
import onlinedesktop.SingleUserDesktop;

/**
 *
 * @author java
 */
public class NMGroupIP extends JPanel implements Runnable {

    JLabel lbl;
    DBOperation db;
    JLabel[] lblIP, lblOnline;
    Vector data, offLineUser, onLineUser, otherUsers;
    ImageIcon icon;
    Vector onlineVector = OnlineClientList.onlineVector;
    Thread thread;
    int mapSize = OnlineClientList.onlineVector.size();
    private String msg, user;
    private boolean flagFrame = false;
    private JFrame[] frameList;

    public NMGroupIP() {
        db = new DBOperation();
        lbl = new JLabel("No Group Selected");
        lbl.setForeground(Color.RED);
        lbl.setBounds(130, 10, 100, 60);
        this.add(lbl);
    }

    public void show(String msg) {
        try {
            this.msg = msg;
            thread = new Thread(this);
            thread.start();
            //geting all the user data from database.
            data = db.getGroupIP(this.msg);
            onlineClients();
        } catch (Exception e) {
            System.out.println("Exception  in show()-------------------------- " + e);
        }
    }

    public void onlineClients() {
        try {
            boolean bln = true;
            String[] ipHostName;
            int i = 0;
            offLineUser = new Vector();
            onLineUser = new Vector();
            boolean flag = true;
            for (int j = 0; j < data.size(); j++) {
                ipHostName = (data.get(j).toString()).split(":");
                for (int k = 0; k < onlineVector.size(); k++) {
                    String ipHost[] = onlineVector.get(k).toString().split("--");
                    if (ipHostName[1].equalsIgnoreCase(ipHost[1]) || ipHostName[0].equals(ipHost[0])) {
                        onLineUser.add(data.get(j));
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {

                    offLineUser.add(data.get(j));
                    flag = true;
                }
            }
            //  mapSize = onLineUser.size();

            clientDesktopGUI();

        } catch (Exception e) {
            System.out.println("Exception  in onlineClients()-------------------------- " + e);
        }
    }

    public void clientDesktopGUI() {
        int x = 130, y = 60, width = 200, height = 60;
        int x1 = 50, y1 = 60, width1 = 100, height1 = 60;
        int z = x;
        int z1 = x1;
        //set default layout null.
        setLayout(null);

        //label for image
        JLabel img;
        //lblOnline = new JLabel[map.size()];
        String headMsg = "ALL IP ADRESS AND HOST NAME OF " + this.msg;
        // icon = new ImageIcon("src\\gui\\ofline3.jpg");
        URL myurl = this.getClass().getResource("ofline3.jpg");
        icon = new ImageIcon(myurl);
        boolean bln = true;

        lblOnline = new JLabel[onLineUser.size()];
        frameList = new JFrame[onLineUser.size()];

        for (int i = 0; i < onLineUser.size(); i++) {
            lblOnline[i] = new JLabel();
            lblOnline[i].setText(onLineUser.get(i).toString());
            lblOnline[i].addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel1MouseClicked(evt);
                }
            });

            img = new JLabel(icon);
            img.setBounds(x1, y1, width1, height1);
            lblOnline[i].setBounds(x, y, width, height);

            this.add(img);
            this.add(lblOnline[i]);

            if (bln) {
                x += 350;
                x1 += 350;
                bln = false;
            } else {
                x = z;
                x1 = z1;
                y += 70;
                y1 += 70;
                bln = true;
            }

        }
        lblIP = new JLabel[offLineUser.size()];
        icon = new ImageIcon("src\\gui\\offline.png");
        myurl = this.getClass().getResource("offline.png");
        icon = new ImageIcon(myurl);
        for (int i = 0; i < offLineUser.size(); i++) {
            lblIP[i] = new JLabel();
            lblIP[i].setText(offLineUser.get(i).toString());

            lblIP[i].addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel1MouseClicked(evt);
                }
            });
            img = new JLabel(icon);
            img.setBounds(x1, y1, width1, height1);
            lblIP[i].setBounds(x, y, width, height);


            this.add(img);
            this.add(lblIP[i]);

            if (bln) {
                x += 350;
                x1 += 350;
                bln = false;

            } else {
                x = z;
                x1 = z1;
                y += 70;
                y1 += 70;
                bln = true;

            }

        }
        lbl = new JLabel(headMsg);
        lbl.setForeground(Color.RED);
        lbl.setBounds(330, 2, 600, 60);
        this.add(lbl);

    }

    private void jLabel1MouseClicked(MouseEvent evt) {
        try {
            boolean flag = false;
            System.out.println("11111111111111111111111");
            for (int i = 0; i < mapSize; i++) {
                if (evt.getSource() == lblOnline[i]) {
                    flagFrame = true;
                    flag = true;
                    final int f = i;
                    System.out.println("222222222222222222222222");
                    int socketNumber = -1;
                    String[] lblHost = lblOnline[i].getText().split(":");
                    for (int l = 0; l < onlineVector.size(); l++) {
                        System.out.println("33333333333333333333333333");
                        String ipHost[] = onlineVector.get(l).toString().split("--");
                        if (ipHost[1].equalsIgnoreCase(lblHost[1]) || ipHost[0].equals(lblHost[0])) {
                            socketNumber = l;
                            break;
                        }
                    }
                    System.out.println("444444444444444444");

                    final Socket client = (Socket) OnlineClientList.socketVector.get(socketNumber);
                    final int frameNumber = socketNumber;
                    java.awt.EventQueue.invokeLater(new Runnable() {

                        InetAddress address = client.getInetAddress();

                        public void run() {
                            if (OnlineClientList.frameMap.get(address.getHostName()) != null) {
                                System.out.println("old user-------------------------old user------------------");
                                JFrame frame = (JFrame) OnlineClientList.frameMap.get(address.getHostName());
                                frame.setVisible(true);
                            } else if (frameList[f] == null) {
                                System.out.println("--------------------------- new user");
                                frameList[f] = new JFrame(address.getHostAddress() + "--" + address.getHostName());
                                //JFrame frame = new JFrame(address.getHostAddress()+"--"+address.getHostName());
                                Container contain = frameList[f].getContentPane();
                                frameList[f].setSize(500, 500);
                                contain.add(new SingleUserDesktop(client));
                                frameList[f].setVisible(true);
                                OnlineClientList.frameMap.put(address.getHostName(), frameList[f]);
                            }
                            //OnlineClientList.frameList[frameNumber].setVisible(true);
                        }
                    });
                    System.out.println("55555555555555555555555555555555555");
                }

            }
            if (flag == false) {
                JOptionPane.showMessageDialog(this, "Ofline User Selected", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("jLabel1MouseClicked-----------------------errorrrrr00000000000000 --" + e);
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println("NMGroupIP Thread-");
                int size = OnlineClientList.onlineVector.size();
                if (mapSize != size) {
                    this.removeAll();
                    mapSize = size;
                    onlineVector = OnlineClientList.onlineVector;
                    onlineClients();
                    this.repaint();
                }
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("Thread2 Exception  :- " + e);
                }
            } catch (Exception e) {
                System.out.println("in run thread exception 999999999999999999999999999999 " + e);
            }
        }
    }
}
