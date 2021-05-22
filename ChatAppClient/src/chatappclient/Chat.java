package chatappclient;

import components.Message;
import components.Request;
import components.User;
import java.awt.Color;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class Chat extends javax.swing.JDialog {

    int FriendUserNo;
    ArrayList<User> LoggedUsers = new ArrayList<User>();
    Timer timer = null;

    private int MessageCounter = 0;

    class innerclass extends TimerTask {

        public void run() {
            loadMessages();
        }
    }

    public Chat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }

    public void startChat(int pUserNo) {
        FriendUserNo = pUserNo;
        loadMessages();
        timer = new Timer();
        timer.schedule(new innerclass(), 1000, 2000);
        
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat");

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel1.setText("Type your message here");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_Paul-09_2624634.png"))); // NOI18N
        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_Delete_1493279.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(jTextArea1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter some text before");
        }
        else
        {
            Message obj = new Message();
            obj.commandName = "#SendMessage#";
            obj.fromUserNo = ChatAppClient.LoggedUserNo;
            obj.toUserNo = FriendUserNo;
            obj.Message = jTextArea1.getText();
            jTextArea1.setText("");

            try {
                Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
                ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(obj);
                ss.close();

                loadMessages();
            } 
            catch (Exception ex) {
                System.out.println(ex);
            }
        
        }//else

    }//GEN-LAST:event_jButton1ActionPerformed

    private void loadMessages() {

        Message msg = new Message();
        msg.commandName = "#GetMessages#";
        msg.fromUserNo = ChatAppClient.LoggedUserNo;
        msg.toUserNo = FriendUserNo;

        try {
            Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
            ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
            oos.writeObject(msg);

            ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
            ArrayList<Message> AllMessages = (ArrayList<Message>) ois.readObject();
            
            ois.close();
            oos.close();
            ss.close();
            
            if (AllMessages.size() > MessageCounter) {
                Document doc5=jTextPane1.getStyledDocument();
                doc5.remove(0, doc5.getLength());
                
                
                for (Message message : AllMessages) {

                    if (message.fromUserNo == ChatAppClient.LoggedUserNo) {

                        SimpleAttributeSet as = new SimpleAttributeSet();
                        StyleConstants.setForeground(as, Color.blue);
                        StyleConstants.setBackground(as, Color.white);
                        jTextPane1.setCharacterAttributes(as, true);
                        Document doc = jTextPane1.getStyledDocument();

                        try {
                            doc.insertString(doc.getLength(), "You on - " + message.date + "\n", as);
                        } catch (Exception ex) {
                            System.out.println("ex");
                        }

                        Document doc1 = jTextPane1.getStyledDocument();
                        try {
                            doc1.insertString(doc1.getLength(), message.Message + "\n\n", null);
                        } catch (Exception ex) {
                            System.out.println("ex");
                        }

                    }//IF 
                    else {

                        SimpleAttributeSet as = new SimpleAttributeSet();
                        StyleConstants.setForeground(as, Color.red);
                        StyleConstants.setBackground(as, Color.white);
                        StyleConstants.setAlignment(as, StyleConstants.ALIGN_LEFT);
                        jTextPane1.setCharacterAttributes(as, true);
                        Document doc = jTextPane1.getStyledDocument();
                        try {
                            doc.insertString(doc.getLength(), "Friend on - " + message.date + "\n", as);
                            
                        } catch (Exception ex) {
                            System.out.println("ex");
                        }
                        Document doc1 = jTextPane1.getStyledDocument();

                        try {
                            SimpleAttributeSet bs = new SimpleAttributeSet();
                            StyleConstants.setAlignment(bs, StyleConstants.ALIGN_LEFT);
                            doc1.insertString(doc1.getLength(), message.Message + "\n\n", bs);
                        } catch (Exception ex) {
                            System.out.println("ex");
                        }

                    }//else
                   
                }//for
            }//IF
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        jTextPane1.setCaretPosition(0);
    }//function

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
