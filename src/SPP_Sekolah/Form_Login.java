package SPP_Sekolah;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import notification.Notification;


public class Form_Login extends javax.swing.JFrame {

    String level;
    String nama_user,id_spp;
    public Form_Login() {
        initComponents();
        addPlaceholderstyle(txt_username);
        addPlaceholderstyle(txt_password);
        maxi();
    }

    public void addPlaceholderstyle(JTextField textfield){
        Font font = textfield.getFont();
        textfield.setFont(font);
        textfield.setForeground(new Color(204,204,204));
    }
    public void removePlaceholderstyle(JTextField textfield){
        Font font = textfield.getFont();
        font = font.deriveFont(Font.PLAIN|Font.BOLD);
        textfield.setFont(font);
        textfield.setForeground(Color.BLACK);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Utama = new javax.swing.JPanel();
        Panel_Exit = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRound1 = new Panel_Round.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        btn_login = new rojerusan.RSMaterialButtonRectangle();
        jLabel8 = new javax.swing.JLabel();
        panelRound2 = new Panel_Round.PanelRound();
        txt_password = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        txt_username = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Panel_Utama.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Utama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_UtamaMouseClicked(evt);
            }
        });
        Panel_Utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_Exit.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_ExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_ExitLayout = new javax.swing.GroupLayout(Panel_Exit);
        Panel_Exit.setLayout(Panel_ExitLayout);
        Panel_ExitLayout.setHorizontalGroup(
            Panel_ExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Panel_ExitLayout.setVerticalGroup(
            Panel_ExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Panel_Utama.add(Panel_Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1860, 0, 30, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Data_security.png"))); // NOI18N
        Panel_Utama.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, 510));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form Login");
        Panel_Utama.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 680, 500, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Aplikasi Spp Sekolah");
        Panel_Utama.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 720, 180, -1));

        panelRound1.setBackground(new java.awt.Color(0, 204, 153));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sign In");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        btn_login.setBackground(new java.awt.Color(255, 255, 255));
        btn_login.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_login.setForeground(new java.awt.Color(0, 0, 0));
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Login Dengan Username and Password Mu");

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(25);
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopLeft(25);
        panelRound2.setRoundTopRight(25);

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(204, 204, 204));
        txt_password.setText("Password");
        txt_password.setToolTipText("Masukan Password");
        txt_password.setBorder(null);
        txt_password.setEchoChar('\u0000');
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        txt_username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_username.setForeground(new java.awt.Color(204, 204, 204));
        txt_username.setText("Username");
        txt_username.setToolTipText("Masukan Username");
        txt_username.setBorder(null);
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addComponent(txt_username)
                    .addComponent(txt_password, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        Panel_Utama.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 290, 400, 440));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loading-new.png"))); // NOI18N
        Panel_Utama.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 190, 580, -1));

        exit.setFont(new java.awt.Font("Calibri", 0, 45)); // NOI18N
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("x");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        Panel_Utama.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1890, 0, 30, 32));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/coin.png"))); // NOI18N
        Panel_Utama.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, -70, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/particles.png"))); // NOI18N
        Panel_Utama.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 880, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/particle-hijau.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        Panel_Utama.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1780, 860, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Utama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1920, 953));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        proses_login();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void txt_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusGained
        // TODO add your handling code here:
        if(txt_username.getText().equals("Username")){
            txt_username.setText(null);
            txt_username.requestFocus();
            removePlaceholderstyle(txt_username);
        }
    }//GEN-LAST:event_txt_usernameFocusGained

    private void txt_passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusGained
        // TODO add your handling code here:
        if(txt_password.getText().equals("Password")){
            txt_password.setText(null);
            txt_password.requestFocus();
            txt_password.setEchoChar('*');
            removePlaceholderstyle(txt_password);
        }
    }//GEN-LAST:event_txt_passwordFocusGained

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
        if(txt_username.getText().length() == 0){
            addPlaceholderstyle(txt_username);
            txt_username.setText("Username");
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        // TODO add your handling code here:
        if(txt_password.getText().length() == 0){
            addPlaceholderstyle(txt_password);
            txt_password.setText("Password");
            txt_password.setEchoChar('\u0000');
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        this.requestFocusInWindow(true);
    }//GEN-LAST:event_formWindowGainedFocus

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.requestFocusInWindow(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void Panel_UtamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_UtamaMouseClicked
        // TODO add your handling code here:
        this.requestFocusInWindow(true);
    }//GEN-LAST:event_Panel_UtamaMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void Panel_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_ExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Panel_ExitMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Exit;
    private javax.swing.JPanel Panel_Utama;
    private rojerusan.RSMaterialButtonRectangle btn_login;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private Panel_Round.PanelRound panelRound1;
    private Panel_Round.PanelRound panelRound2;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

    private void proses_login(){
        String user = String.valueOf(txt_username.getText());
        String pass = String.valueOf(txt_password.getText());
        
        
        try {
            Statement stat = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery("Select * from tb_petugas where username='"+user+"' and password='"+pass+"'");
            if (rs.next()) {
                level = rs.getString("level");
                this.nama_user = rs.getString("nama_petugas");
                this.id_spp = rs.getString("id_petugas");
                if (level.equals("admin")) {
                    Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Succesfull");
                    panel.showNotification();
                    this.setVisible(false);
                    new Form_Menu_Admin(nama_user).setVisible(true);
                }else if(level.equals("petugas")){
                    Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Succesfull");
                    panel.showNotification();
                    this.setVisible(false);
                    new Form_Menu_Petugas(nama_user).setVisible(true);
                }else if(level.equals("siswa")){
                    Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Login Succesfull");
                    panel.showNotification();
                    this.setVisible(false);
                    Form_Menu_Siswa fsiswa = new Form_Menu_Siswa(null);
                    fsiswa.id_spp_detail = this.id_spp;
                    new Form_Menu_Siswa(nama_user).setVisible(true);
                }
            }else{
                Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Masukan Username Dan Password Dengan Benar!");
                panel.showNotification();
                
            }
            
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Login Failed");
            panel.showNotification();
        }
        
    }
    
    public void maxi(){
        this.setExtendedState(MAXIMIZED_BOTH);
    }
}
