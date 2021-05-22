/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.sql.*;


/**
 *
 * @author hp
 */
public class DBOperations {
    protected Connection con=null;
    protected void makeConnection(){
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con=DriverManager.getConnection("jdbc:sqlserver://LAPTOP-087GVQJP\\MridulDataServer:23142;database=ProjectDB;userName=sa;Password=mridul");
            
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    protected void closeConnection()
    {
        try{
            con.close();
            con=null;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

        
           
}
