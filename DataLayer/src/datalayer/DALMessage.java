package datalayer;
import components.Message;
import java.sql.*;
import java.util.ArrayList;

public class DALMessage extends DBOperations{
    
    public void addMessage(Message msg)
    {
        try
        {
            makeConnection();
            PreparedStatement ps=con.prepareStatement("insert into messages values(getdate(),?,?,?)");
            
            ps.setInt(1, msg.fromUserNo);
            ps.setInt(2, msg.toUserNo);
            ps.setString(3, msg.Message);
            ps.execute();
            con.close();
        }
        
        catch(Exception ex)
        {
            System.out.println(ex);
        
    }
}
    
    public ArrayList<Message> getMessages(int pFromUserNo, int pToUserNo)
    {
        
        ArrayList<components.Message> AllMessages = new ArrayList<components.Message>();
        
        try {
            
            makeConnection();
            PreparedStatement ps = con.prepareStatement("select M.MessageId, M.MessageDate, M.FromUserNo, M.ToUserNo, M.Messages From Messages as [M] where fromUserNo=? and M.toUserNo=?\n" +
" union \n" +
" select M.MessageId, M.MessageDate, M.FromUserNo, M.ToUserNo, M.Messages From Messages as [M] where fromUserNo=? and M.ToUserNo=? \n" +
" order by MessageDate desc");
            
            
            ps.setInt(1, pFromUserNo);
            ps.setInt(2, pToUserNo);
            ps.setInt(3, pToUserNo);
            ps.setInt(4, pFromUserNo);
            
            
            ResultSet rs = ps.executeQuery();
            Message m;
            
            while(rs.next())
            {
                m = new Message();
                
                m.messageID = rs.getInt("MessageId");
                m.date = rs.getString("MessageDate");
                m.fromUserNo = rs.getInt("FromUserNo");
                m.toUserNo = rs.getInt("ToUserNo");
                m.Message = rs.getString("Messages");
                
                AllMessages.add(m);
                
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return AllMessages;
        
    }//Function
    
    
}//class DALMessage
    
