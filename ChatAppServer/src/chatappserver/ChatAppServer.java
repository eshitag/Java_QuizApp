package chatappserver;

import java.io.*;
import java.net.*;
import components.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChatAppServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serversocket = new ServerSocket(4400);
        Socket clientsocket = null;

        ArrayList<User> LoggedUsers = new ArrayList<User>();

        System.out.println("Server started...");

        while (true) {
            clientsocket = serversocket.accept();
            ObjectInputStream ois = new ObjectInputStream(clientsocket.getInputStream());
            components.Command cmd = (Command) ois.readObject();

            if (cmd.commandName.equals("#Register#")) {
                User us = (User) cmd;
                datalayer.DalUser objDal = new datalayer.DalUser();
                objDal.register(us);

            } else if (cmd.commandName.equals("#Login#")) {
                User user = (User) cmd;
                datalayer.DalUser objDal = new datalayer.DalUser();
                User us = objDal.authenticate(user);

                if (us.userno > 0) {
                    LoggedUsers.add(us);
                }

                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(us);
                oos.close();

            } else if (cmd.commandName.equals("#getData#")) {
                User obj = (User) cmd;
                datalayer.DalUser objDal = new datalayer.DalUser();
                User us = objDal.getData(obj);

                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(us);
                oos.close();

            } else if (cmd.commandName.equals("#ChangePassword#")) {
                User obj = (User) cmd;
                datalayer.DalUser objDal = new datalayer.DalUser();
                objDal.changePassword(obj);

            } else if (cmd.commandName.equals("#GetUsers#")) {
                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(LoggedUsers);
                oos.close();

            } else if (cmd.commandName.equals("#SendMessage#")) {
                Message msg = (Message) cmd;
                datalayer.DALMessage objDal = new datalayer.DALMessage();
                objDal.addMessage(msg);

            } else if (cmd.commandName.equals("#GetMessages#")) {
                Message msg = (Message) cmd;
                datalayer.DALMessage objDal = new datalayer.DALMessage();
                ArrayList<Message> AllMessages = objDal.getMessages(msg.fromUserNo, msg.toUserNo);

                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(AllMessages);
                oos.close();

            } else if (cmd.commandName.equals("#Logout#")) {
                
                User user = (User) cmd;
                
                for(int i=0;i<LoggedUsers.size();i++)
                {
                    if(LoggedUsers.get(i).userno==user.userno)
                    {
                        LoggedUsers.remove(i);
                        break;
                    }
                }

            } else if(cmd.commandName.equals("#check#")){

                User user = (User) cmd;
                
                User us = null;
                
                for(int i=0;i<LoggedUsers.size();i++)
                {
                    if(user.userno==LoggedUsers.get(i).userno)
                    {
                        us = LoggedUsers.get(i);
                        break;
                    }
                }

                if(us==null)
                {
                    us = new User();
                }

                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(us);
                oos.close();
                
            }

            ois.close();
            clientsocket.close();
        }//While Loop

    }//Main
}
