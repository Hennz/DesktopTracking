/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 *
 * @author java
 */
public class HostBean {
    private String hostName,ipAddress;
    private int groupUserID;
    public HostBean(){

    }

    public int getGroupUserID() {
        return groupUserID;
    }

    public void setGroupUserID(int groupUserID) {
        this.groupUserID = groupUserID;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    @Override
    public String toString(){
        return hostName;
    }
}
