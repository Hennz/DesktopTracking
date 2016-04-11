/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onlinedesktop;

import gui.NMGroup;
import gui.NMGroupIP;
import java.util.HashMap;

/**
 *
 * @author java
 */
public class CommnicateJPanel {
    public static NMGroupIP groupIP;
    public static NMGroup nmGroup;
    public static HashMap map;

    public CommnicateJPanel(NMGroupIP groupIP) {
        this.groupIP=groupIP;
    }

    public CommnicateJPanel(HashMap map){
        this.map=map;
    }

    public CommnicateJPanel(String msg) {
          groupIP.removeAll();
          groupIP.repaint();
          groupIP.show(msg);
          groupIP.setVisible(true);
    }
    

}
