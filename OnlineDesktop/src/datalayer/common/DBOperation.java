/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer.common;


import bean.GroupBean;
import bean.HostBean;
import java.util.*;
import java.sql.*;


/**
 *
 * @author Administrator
 */
public class DBOperation {

    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    ArrayList al;

    public DBOperation() {
        try {
        } catch (Exception e) {
            System.out.println("DBOperations:" + e);
        }
    }
    public boolean addNewGroup(String groupName,String description){
        try{
            conn=DBConnection.connect();
            stmt=conn.createStatement();
            String query="insert into nm_group(group_name,group_desc) values('"+groupName+"','"+description+"');";
            int result=stmt.executeUpdate(query);
            if(result>0){
                return true;
            }
        } catch(Exception e){

        }
        return false;
    }
    public Vector getGroupRecord(){
        Vector rows=new Vector();
        try{
            conn=DBConnection.connect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select *from nm_group");
            GroupBean bean;
            
            int i=0;
            Vector data;
            while(rs.next()){
                data=new Vector();
                data.add(false);
                data.add(++i);
                bean=new GroupBean();
                bean.setGroupId(rs.getInt(1));
                bean.setGroupName(rs.getString(2));
                data.add(bean);
                data.add(rs.getString(3));
                rows.add(data);
            }
        } catch(Exception e){
            System.out.println("Exception :-- "+e);
        }
        return rows;
    }
    public Vector getGroups(){
        Vector data=new Vector();
        try{
            conn=DBConnection.connect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select *from nm_group");
            GroupBean bean;
            while(rs.next()){
                bean=new GroupBean();
                bean.setGroupId(rs.getInt(1));
                bean.setGroupName(rs.getString(2));
                data.add(bean);

            }
        } catch(Exception e){
            System.out.println("Exception :-- "+e);
        }
        return data;
    }
    public Vector getGroupIP(String groupName){
        Vector data=new Vector();
        try{
            conn=DBConnection.connect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select group_id from nm_group where group_name='"+groupName+"';");
            rs.next();
            int groupID=rs.getInt(1);
            rs=stmt.executeQuery("select grooup_user_ip,host_name from group_user where group_id="+groupID);
            while(rs.next()){
                data.add(rs.getString(1)+":"+rs.getString(2));
            }
            System.out.println("data------- "+data);
        } catch(Exception e){
            System.out.println("Exception :- "+e);
        }
        return data;
    }
    public boolean updateGroupRecord(Vector data){
        try{
            conn=DBConnection.connect();
            String query="update nm_group set group_name=?,group_desc=? where group_id=?";
            pstmt=conn.prepareStatement(query);
            for(int i=0;i<data.size();i++){
                Vector row=(Vector)data.get(i);
                GroupBean bean=(GroupBean)row.get(0);
                pstmt.setString(1,bean.getGroupName());
                pstmt.setString(2, row.get(1).toString());
                pstmt.setInt(3, bean.getGroupId());
                pstmt.executeUpdate();

            }
            return true;
        } catch(Exception e){
            System.out.println("Exception is ============= "+e);
            return false;
        }
     

    }
    public boolean addHostName(int groupID,String hostName,String ipAddress){
        try{
             conn=DBConnection.connect();
             stmt=conn.createStatement();
             int rslt=stmt.executeUpdate("insert into group_user (host_name,group_id,grooup_user_ip) values('"+hostName+"',"+groupID+",'"+ipAddress+"')");
             if(rslt>0){
                 return true;
             }
        } catch(Exception e){
            System.out.println("Exception --------- "+e);
        }
        return false;
    }
    public Vector getAllHostName(){
        Vector hostName=new Vector();
        try{
            conn=DBConnection.connect();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select *,nm.group_name from group_user as gu inner join nm_group as nm where gu.group_id=nm.group_id");
            Vector data;
            HostBean bean;
            int i=0;
            while(rs.next()){
                data=new Vector();
                bean=new HostBean();
                bean.setHostName(rs.getString("host_name"));
                bean.setGroupUserID(rs.getInt("group_user_id"));
                
                data.add(false);
                data.add(++i);
                data.add(rs.getString("grooup_user_ip"));
                data.add(bean);
                data.add(rs.getString("group_name"));
                hostName.add(data);
            }
        } catch(Exception e){
            System.out.println("Exception -----getAllHostName()--------- "+e);
        }
        return hostName;
    }
    public boolean delGroupRecord(Vector groupName){
        boolean blnRslt=false;
        try{
            conn=DBConnection.connect();
            pstmt=conn.prepareStatement("delete from nm_group where group_name=?");
            for(int i=0;i<groupName.size();i++){
                pstmt.setString(1,groupName.get(i).toString());
                int rslt=pstmt.executeUpdate();
                if(rslt>0){
                    blnRslt=true;
                }else{
                    blnRslt=false;
                }
            }
            
        } catch(Exception e){
            System.out.println("Exception is === delGroupRecord() ====== "+e);
        }

        return blnRslt;
    }
    public boolean delHostName(Vector userID){
        try{
            conn=DBConnection.connect();
            pstmt=conn.prepareStatement("delete from group_user where group_user_id=?");
            for(int i=0;i<userID.size();i++){
                pstmt.setInt(1,(Integer)userID.get(i));
                pstmt.executeUpdate();
            }
            return true;
        } catch(Exception e){
            System.out.println("Exception delHostName()--- "+e);
            return false;
        }
        
    }
    public boolean updateHostName(Vector hostName){
         try{
            conn=DBConnection.connect();
            pstmt=conn.prepareStatement("update group_user set grooup_user_ip=?,host_name=? where group_user_id=?");
            HostBean bean;
            Vector data;
            for(int i=0;i<hostName.size();i++){
                data=(Vector)hostName.get(i);
                pstmt.setString(1,data.get(2).toString() );
                pstmt.setString(2,data.get(1).toString());
                pstmt.setInt(3, (Integer)data.get(0));
                pstmt.executeUpdate();
            }
            return true;
        } catch(Exception e){
            System.out.println("Exception delHostName()--- "+e);
            return false;
        } 
    }
    
}
