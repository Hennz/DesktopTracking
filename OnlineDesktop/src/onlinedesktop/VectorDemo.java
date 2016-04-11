/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onlinedesktop;

import java.util.Vector;

/**
 *
 * @author java
 */
public class VectorDemo {
    public static void main(String arg[]){
        Vector obj=new Vector();
        obj.add("1");
        obj.add("2");
        obj.add("3");
        obj.add("4");
        obj.add("5");
        obj.add("6");
        obj.add("7");
        System.out.println("obj--------1-----------"+obj);
        obj.remove(3);
        obj.add("333");
        System.out.println("obj--------1-----------"+obj);
        obj.remove(5);
        System.out.println("obj--------1-----------"+obj);
    }
}
