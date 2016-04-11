/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bean.GroupBean;
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;
import datalayer.common.DBOperation;
import java.awt.Color;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author java
 */
public class ViewAllUsers extends JPanel implements Runnable {

    private DBOperation db;
    private JLabel lblHeadMsg;
    private final Vector allGroupsVector;
    private Vector data;
    private HashMap groupDataMap;
    private Thread thread, groupThread;
    private GroupBean bean;
    private String msg;
    private int groupX;
    private int groupPicY=70,groupNameY=70;
    private final int GROUP_PIC_X=30,GROUP_NAME_X=100,GROUP_PIC_WIDTH=70,GROUP_PIC_HEIGHT=70,GROUP_NAME_WIDTH=200,GROUP_NAME_HEIGHT=70;
    private JLabel lblGroupPic,lblGroupName;
    private ImageIcon icon;

    public ViewAllUsers() {
        db = new DBOperation();
        allGroupsVector = db.getGroups();
        lblHeadMsg = new JLabel("All Groups And Host Name");
        lblHeadMsg.setForeground(Color.red);
        lblHeadMsg.setBounds(130, 10, 100, 60);
        System.out.println("allUsersVector---------------------- --------------------- ----------- " + allGroupsVector);
        this.add(lblHeadMsg);
        URL myurl = this.getClass().getResource("group.jpeg");
        icon = new ImageIcon(myurl);
        groupDataMap=new HashMap();
        for (int i = 0; i < allGroupsVector.size(); i++) {
            bean = (GroupBean) allGroupsVector.get(i);
            String strGroupName = bean.getGroupName();
            data = db.getGroupIP(strGroupName);
            groupDataMap.put(strGroupName, data);
        }
        System.out.println("mapppp---------------- "+groupDataMap);
        setLayout(null);
        onlineClients();
    }

    public void onlineClients() {
        try {
            Set set=groupDataMap.entrySet();
            Iterator itr=set.iterator();
            while(itr.hasNext()){
                Map.Entry map=(Map.Entry)itr.next();
                System.out.println("kye == "+map.getKey());
                System.out.println("Value == "+map.getValue());
                lblGroupName=new JLabel(map.getKey().toString());
                lblGroupPic=new JLabel(icon);
                lblGroupPic.setBounds(GROUP_PIC_X,groupPicY,GROUP_PIC_WIDTH,GROUP_PIC_HEIGHT);
                lblGroupName.setBounds(GROUP_NAME_X,groupNameY,GROUP_NAME_WIDTH,GROUP_NAME_HEIGHT);
                groupPicY+=70;
                groupNameY+=70;
                setGroupData();
                add(lblGroupPic);
                add(lblGroupName);
            }
            setVisible(true);
        } catch (Exception e) {
            System.out.println("Exception onlineClients()---------------- " + e);
        }
    }
    public void setGroupData(){
        try{
            
        } catch(Exception e){
            System.out.println("Exception setGroupData()-------------- "+e);
        }
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " -:- Thread Started");
        String strGroupName = Thread.currentThread().getName();
        //getGroupData(strGroupName);
    }

    public void getGroupData() {
        try {
            //this.msg = msg;
            groupThread = new Thread(this);
            groupThread.start();
            //geting all the user data from database.
            data = db.getGroupIP(this.msg);
            onlineClients();
        } catch (Exception e) {
            System.out.println("Exception  in show()-------------------------- " + e);
        }
    }
}
