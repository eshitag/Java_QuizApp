package chatappclient;

import java.io.*;
import java.net.*;

public class ChatAppClient {

    public static int LoggedUserNo = 0;
    public static final String IPAddress = "127.0.0.1";
    public static final int PortNo = 4400;
    
    
    public static void main(String[] args) throws Exception {
        Gateway gateway = new Gateway();
        gateway.setVisible(true);

    }

}
