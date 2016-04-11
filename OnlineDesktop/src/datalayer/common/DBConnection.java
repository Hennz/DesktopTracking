/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datalayer.common;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class DBConnection
{
	public static Connection con;
	public DBConnection(){
	}
  	public static Connection connect()
   	{
      		try
        		{
     			Class.forName("com.mysql.jdbc.Driver");
     			con = DriverManager.getConnection("jdbc:mysql:///onlinedesktop", "root", "root");
        		}
		catch(Exception e)
		{
			System.out.println("connection error:"+e);
		}
       		return con;
   	}
}
