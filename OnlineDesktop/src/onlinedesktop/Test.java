/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedesktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author java
 */
public class Test extends JFrame{
JScrollPane scrollPane;
JPanel panel,p;
JLabel label;
JTextField textField;
JButton button;
Test(){
super("Name");
        setVisible(true);
        setSize(400, 200);
        setLayout(null);
        panel = new JPanel();
        panel.setSize(300, 100);
        panel.setLayout(null);
//panel.setVisible(true);
        panel.setBackground(Color.YELLOW);
        label = new JLabel("Label");
        textField = new JTextField();
        button = new JButton("add");
        label.setBounds(0, 0, 100, 30);
        textField.setBounds(120, 0, 100, 30);
        button.setBounds(0, 50, 80, 30);
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        p = new JPanel();
        p.setVisible(true);
        p.setSize(500, 500);
        p.setLayout(new BorderLayout());
        p.add(panel, BorderLayout.CENTER);
        
        p.setPreferredSize(new Dimension(p.getSize().width, 1000));

        scrollPane = new JScrollPane(p);
//scrollPane.add(panel);
        scrollPane.setSize(300, 100);
//scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//scrollPane.se
        scrollPane.setVisible(true);
        add(scrollPane);
        setSize(300,300);
    }

    public static void main(String a[]) {
        new Test();
    }
}
