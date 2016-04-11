/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author java
 */
public class TEst {
    public static void main(String arg[]){
         JFrame frame= new JFrame("test frame");
         JPanel panel= new JPanel() {
            @Override
             public void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 g.setColor(Color.BLACK);
                 g.drawOval(0, 0, 200, 200);
             }
         };

         JLabel label= new JLabel("test");
         panel.add(label);
         label.setBounds(400, 400, 60, 20);
         panel.setPreferredSize(new Dimension(500, 500));
         panel.setLayout(null);
         int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
         int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
         JScrollPane sp= new JScrollPane(panel,v,h);
         frame.getContentPane().add(sp, BorderLayout.CENTER);
         frame.pack();
         frame.setSize(150,150);
         frame.setVisible(true);
    }
}
