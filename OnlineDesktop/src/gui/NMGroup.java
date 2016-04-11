/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import bean.GroupBean;
import datalayer.common.DBOperation;
import java.awt.Color;
import java.net.URL;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import onlinedesktop.CommnicateJPanel;

/**
 *
 * @author java
 */
public class NMGroup extends JPanel {

    JLabel jLabel, jLabelPic;
    int x = 75, y = 58, width = 100, height = 60;
    int x1 = 5, y1 = 58, width1 = 70, height1 = 60;
    DBOperation db;
    Vector data;
    //ImageIcon icon = new ImageIcon("src\\gui\\group.jpeg");
    ImageIcon icon;
    JLabel[] label;
    int size;

    public NMGroup() {

        URL myurl = this.getClass().getResource("group.jpeg");
        icon = new ImageIcon(myurl);
        db = new DBOperation();
        data = db.getGroupRecord();

       //setLayout(new GridLayout(0, 1));
       setLayout(null);
        //setSize(600, 600);
        //setPreferredSize(new Dimension(600, 600));
        jLabel = new JLabel("All Groups");
        jLabel.setForeground(Color.red);
        jLabel.setBounds(40, 20, 110, 20);
        this.add(jLabel);

        label = new JLabel[data.size()];
        size = data.size();
        GroupBean bean = null;
        for (int i = 0; i < size; i++) {
            label[i] = new JLabel();

            Vector col = (Vector) data.get(i);
            bean = (GroupBean) col.get(2);
            label[i].setText(bean.toString());

            label[i].addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel1MouseClicked(evt);
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel1MouseEntered(evt);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel1MouseExited(evt);
                }
            });
            label[i].setBounds(x, y, width, height);
            jLabelPic = new JLabel(icon);
            jLabelPic.setBounds(x1, y1, width1, height1);
            this.add(jLabelPic);
            this.add(label[i]);

            y += 70;
            y1 += 70;
        }

        setVisible(true);

    }

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {

        String event;
        for (int i = 0; i < size; i++) {
            if (evt.getSource() == label[i]) {
                event = label[i].getText();
                new CommnicateJPanel(event);
            }

        }

    }

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {
        String event;
        for (int i = 0; i < size; i++) {
            if (evt.getSource() == label[i]) {
                label[i].setForeground(Color.red);
            }

        }
    }

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {
        String event;
        for (int i = 0; i < size; i++) {
            if (evt.getSource() == label[i]) {
                label[i].setForeground(Color.black);
            }

        }
    }
}
