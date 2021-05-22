package datalayer;

import components.User;
import java.sql.*;

public class DalUser extends DBOperations {
    
    
    public void register(components.User obj)throws Exception{
        
        try{
        makeConnection();
        
        PreparedStatement ps=con.prepareStatement("insert into Users values(?,?,? )");
        ps.setString(1,obj.name);
        ps.setString(2, obj.userID);
        ps.setString(3, obj.pwd);
        ps.execute();
        con.close();
    } 
        catch(Exception ex){
            System.out.println(ex);
        }
                   
    }
    
    
    public components.User authenticate(User us1){
        User obj=new User();
        
        try{
            makeConnection();
            PreparedStatement ps=con.prepareStatement("Select * from Users where UserID=? and Password=?");
            ps.setString(1, us1.userID);
            ps.setString(2,us1.pwd);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()==true){
                obj.userno=rs.getInt("UserNo");
                obj.name=rs.getString("Name");
                obj.userID=rs.getString("UserID");
                obj.pwd=rs.getString("Password");
            }
            rs.close();
            
        }
        
        catch(Exception ex){
            System.out.println(ex);
        }
        return obj;
    }
    
    
    public components.User getData(User obj){
        
        try{
            makeConnection();
            PreparedStatement ps=con.prepareStatement("Select * from users where UserNo=?");
            ps.setInt(1, obj.userno);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()==true)
            {
                obj.name=rs.getString("Name");
                obj.userID=rs.getString("UserID");
                obj.pwd=rs.getString("Password");
                obj.userno=rs.getInt("UserNo");
            }
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return obj;
    }
    
    
    public User changePassword(User obj)
    {
        try{
            makeConnection();
            PreparedStatement ps=con.prepareStatement("Update Users set Password=? where UserNo=?");
            ps.setString(1, obj.pwd);
            ps.setInt(2, obj.userno);
            
            ps.executeQuery();
            
            }
        
        catch(Exception ex){
            System.out.println(ex);
        }
        return obj;
    }
}

