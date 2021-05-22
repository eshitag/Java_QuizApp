package chatappclient;

import components.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JList;

public class Welcome extends javax.swing.JDialog {

    ArrayList<User> LoggedUsers = new ArrayList<User>();

    public Welcome(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        User obj = new User();
        obj.userno = ChatAppClient.LoggedUserNo;
        obj.commandName = "#getData#";

        try {
            Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
            ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
            oos.writeObject(obj);

            ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
            User user = (User) ois.readObject();

            jTextField1.setText(user.name);
            ois.close();
            oos.close();
            ss.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void refreshUsers() {
        Request obj = new Request();
        obj.commandName = "#GetUsers#";
        try {
            Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
            ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
            oos.writeObject(obj);

            ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());

            LoggedUsers = (ArrayList<User>) ois.readObject();
            DefaultListModel dlm = new DefaultListModel();

            int index = 0, Counter = 0;

            for (User us : LoggedUsers) {

                if (us.userno != ChatAppClient.LoggedUserNo) {
                    dlm.addElement(us.name);
                } else {
                    index = Counter;
                }

                Counter++;
            }

            LoggedUsers.remove(index);

            jList1.setModel(dlm);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");

        jLabel1.setText("Welcome");

        jTextField1.setEditable(false);

        jToolBar1.setRollover(true);
        jToolBar1.setToolTipText("");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_key_477139.png"))); // NOI18N
        jButton1.setText("Change Password");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_view-refresh_118801 (1).png"))); // NOI18N
        jButton2.setText("Refresh Users");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator1);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_logout_59277.png"))); // NOI18N
        jButton4.setText("Logout");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jScrollPane1.setViewportView(jList1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatappclient/if_chat_173174.png"))); // NOI18N
        jButton3.setText("Start Chat");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ChangePassword obj = new ChangePassword(null, true);
        obj.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        refreshUsers();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        User obj = new User();
        obj.userno = ChatAppClient.LoggedUserNo;
        obj.commandName = "#Logout#";

        try {
            Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
            ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
            oos.writeObject(obj);

            oos.close();
            ss.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jList1.getSelectedIndex() >= 0) {
            int FriendUserNo = LoggedUsers.get(jList1.getSelectedIndex()).userno;

            User user = new User();
            user.userno = FriendUserNo;
            user.commandName = "#check#";

            try {

                Socket ss = new Socket(ChatAppClient.IPAddress, ChatAppClient.PortNo);
                ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
                oos.writeObject(user);
                
                ObjectInputStream ois= new ObjectInputStream(ss.getInputStream());
                User us=(User)ois.readObject();
                if(us.userno==FriendUserNo)
                {
                    Chat obj = new Chat(null, true);
                obj.startChat(FriendUserNo);
                    
                }
                else{
                JOptionPane.showMessageDialog(null, "User has logged out");
                }
                
                oos.close();
                ss.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }//IF

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
