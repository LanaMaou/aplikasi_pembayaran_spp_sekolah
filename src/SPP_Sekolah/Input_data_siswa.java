
package SPP_Sekolah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import notification.Notification;


public class Input_data_siswa extends javax.swing.JFrame {

    String date_format;
    String date_format2;
    Form_Menu_Petugas frm = new Form_Menu_Petugas(null);
    public Input_data_siswa() {
        initComponents();
        model_cbx_idkelas();
        autonisn();
        maxdaterange();
        cbx_idkelas.setSelectedIndex(-1);
        txt_nama_kelas_siswa.setText("");
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Panel_Round.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nisn = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_nis = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbx_idkelas = new combobox.Combobox();
        txt_komp_keahlian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txt_notelp = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txt_id_spp = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        btn_save = new rojerusan.RSMaterialButtonRectangle();
        btn_refresh = new rojerusan.RSMaterialButtonRectangle();
        jLabel11 = new javax.swing.JLabel();
        tanggal_input = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txt_nama_kelas_siswa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/student-card.png"))); // NOI18N
        jLabel1.setText("Input Data Siswa");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel2.setText("NISN");

        txt_nisn.setEditable(false);
        txt_nisn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nisn.setToolTipText("Input NISN");
        txt_nisn.setBorder(null);
        txt_nisn.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel3.setText("NIS");

        txt_nis.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nis.setToolTipText("Input NIS");
        txt_nis.setBorder(null);
        txt_nis.setName(""); // NOI18N
        txt_nis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nisActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel6.setText("NAMA");

        txt_nama.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nama.setToolTipText("Input Nama");
        txt_nama.setBorder(null);
        txt_nama.setName(""); // NOI18N
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel7.setText("ID KELAS");

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel10.setText("KOMP KEAHLIAN");

        cbx_idkelas.setToolTipText("");
        cbx_idkelas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_idkelas.setLabeText("Masukan Id Kelas");
        cbx_idkelas.setLightWeightPopupEnabled(false);
        cbx_idkelas.setLineColor(new java.awt.Color(255, 255, 255));
        cbx_idkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_idkelasActionPerformed(evt);
            }
        });

        txt_komp_keahlian.setEditable(false);
        txt_komp_keahlian.setBackground(new java.awt.Color(204, 204, 204));
        txt_komp_keahlian.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_komp_keahlian.setForeground(new java.awt.Color(255, 255, 255));
        txt_komp_keahlian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_komp_keahlian.setToolTipText("Input Komp Keahlian");
        txt_komp_keahlian.setBorder(null);
        txt_komp_keahlian.setName(""); // NOI18N
        txt_komp_keahlian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_komp_keahlianActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel4.setText("ALAMAT");

        txt_alamat.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_alamat.setToolTipText("Input Alamat");
        txt_alamat.setBorder(null);
        txt_alamat.setName(""); // NOI18N
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel8.setText("NO TELPON");

        txt_notelp.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_notelp.setToolTipText("Input No Telpon");
        txt_notelp.setBorder(null);
        txt_notelp.setName(""); // NOI18N
        txt_notelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_notelpActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel9.setText("ID SPP");

        txt_id_spp.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_id_spp.setToolTipText("Input ID SPP");
        txt_id_spp.setBorder(null);
        txt_id_spp.setName(""); // NOI18N
        txt_id_spp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_sppActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(204, 204, 255));
        btn_save.setForeground(new java.awt.Color(0, 0, 0));
        btn_save.setText("simpan");
        btn_save.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(204, 204, 255));
        btn_refresh.setForeground(new java.awt.Color(0, 0, 0));
        btn_refresh.setText("bersihkan");
        btn_refresh.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel11.setText("TANGGAL INPUT");

        tanggal_input.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel12.setText("Nama Kelas");

        txt_nama_kelas_siswa.setEditable(false);
        txt_nama_kelas_siswa.setBackground(new java.awt.Color(204, 204, 204));
        txt_nama_kelas_siswa.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nama_kelas_siswa.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_kelas_siswa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nama_kelas_siswa.setToolTipText("Input Komp Keahlian");
        txt_nama_kelas_siswa.setBorder(null);
        txt_nama_kelas_siswa.setName(""); // NOI18N
        txt_nama_kelas_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_kelas_siswaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_alamat)
                    .addComponent(txt_nis)
                    .addComponent(txt_nisn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_notelp)
                    .addComponent(jSeparator7)
                    .addComponent(txt_id_spp)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_nama)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txt_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(tanggal_input, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txt_nama_kelas_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addContainerGap(553, Short.MAX_VALUE)))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(txt_nisn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txt_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txt_notelp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txt_id_spp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tanggal_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nama_kelas_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound1Layout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addComponent(jLabel2)
                    .addGap(60, 60, 60)
                    .addComponent(jLabel3)
                    .addContainerGap(669, Short.MAX_VALUE)))
        );

        getContentPane().add(panelRound1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nisActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void cbx_idkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_idkelasActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "Select komp_keahlian,nama_kelas from tb_kelas where id_kelas ='"+cbx_idkelas.getSelectedItem()+"'";
            Connection conn = (Connection)koneksi.koneksiDB();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()){
                txt_komp_keahlian.setText(rs.getString("komp_keahlian"));
                txt_nama_kelas_siswa.setText(rs.getString("nama_kelas"));
            }
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER_RIGHT,
                "Ada Kesalahan Di Cbx Id kelas"+ e);
            panel.showNotification();
        }
    }//GEN-LAST:event_cbx_idkelasActionPerformed

    private void txt_komp_keahlianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_komp_keahlianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_komp_keahlianActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void txt_notelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_notelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_notelpActionPerformed

    private void txt_id_sppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_sppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_sppActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here
        savedata_siswa();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        resetText();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_nama_kelas_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_kelas_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_kelas_siswaActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Input_data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Input_data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Input_data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Input_data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Input_data_siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btn_refresh;
    public rojerusan.RSMaterialButtonRectangle btn_save;
    private combobox.Combobox cbx_idkelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private Panel_Round.PanelRound panelRound1;
    private com.toedter.calendar.JDateChooser tanggal_input;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_id_spp;
    private javax.swing.JTextField txt_komp_keahlian;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nama_kelas_siswa;
    private javax.swing.JTextField txt_nis;
    private javax.swing.JTextField txt_nisn;
    private javax.swing.JTextField txt_notelp;
    // End of variables declaration//GEN-END:variables
    private void savedata_siswa() {
        Calendar calendar = Calendar.getInstance();
        Date tanggalsekarang = calendar.getTime();
        
        Form_Menu_Admin frm = new Form_Menu_Admin(null);
        String tanggal1 = "2023-05-01";
        Date tanggal2 = tanggal_input.getDate();
        int selisihBulan = 0;
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        date_format = String.valueOf(format.format(tanggal_input.getDate()));
        try {
            date1 = format.parse(tanggal1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        date_format2 = String.valueOf(format.format(date1));
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(tanggal2);

        while (cal1.before(cal2)) {
            cal1.add(Calendar.MONTH, 1);
            selisihBulan++;
        }       
        while (cal1.after(cal2)) {
            cal1.add(Calendar.MONTH, -1);
            selisihBulan--;
        }       
        
        if(selisihBulan <0){
            selisihBulan = -selisihBulan;
        }

       
        
        try {
            String sql = "Insert into tb_siswa values('" + txt_nisn.getText()
                    + "','" + txt_nis.getText()
                    + "','" + txt_nama.getText()
                    + "','" + cbx_idkelas.getSelectedItem()
                    + "','" + txt_nama_kelas_siswa.getText()
                    + "','" + txt_alamat.getText()
                    + "','" + txt_notelp.getText()
                    + "','" + txt_id_spp.getText()+ "');";
            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            
            String sql2 = "Insert into cek_pembayaran values('"+txt_nisn.getText()
                    + "','" + date_format
                    + "','" + date_format2
                    + "','" + "Belum Lunas"
                    + "','" + String.valueOf(selisihBulan)
                    + "','" + txt_nama.getText()
                    + "','" + txt_notelp.getText()+"');";
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst2.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER,
                    "Save Succesfull");
            panel.showNotification();
            
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Save Data Siswa "+ e);
        }
    }
    public void resetText() {
        txt_nisn.setText("");
        txt_nis.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        cbx_idkelas.setSelectedIndex(-1);
        txt_id_spp.setText("");
        txt_notelp.setText("");
        txt_komp_keahlian.setText("");
        txt_nisn.setEditable(true);
        tanggal_input.setDate(null);
    }

    public void model_cbx_idkelas(){
        try {
        Connection conn = (Connection)koneksi.koneksiDB();
        String sql = "Select id_kelas from tb_kelas";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            cbx_idkelas.addItem(rs.getString("id_kelas"));
        }
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER_RIGHT,
                    "Ada Kesalahan Di Cbx Id Kelas"+ e);
            panel.showNotification();
        }
        cbx_idkelas.setSelectedIndex(-1);
        txt_komp_keahlian.setText("");
        
    }
    
    public void maxdaterange(){
        String tanggal1 = "01-05-2023";
        Date date1 = null;
        Calendar calendar = Calendar.getInstance();
        Date tanggal_sekarang = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            date1 = format.parse(tanggal1);
        } catch (Exception e) {
        }
        
        tanggal_input.setMinSelectableDate(date1);
        tanggal_input.setSelectableDateRange(null, date1);
        
        tanggal_input.setDate(date1);
        
    }
    
    public void autonisn(){
        try {
            String sql2 = "Select MAX(nisn) as max_nisn from tb_siswa";
            Statement stat2 = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs2 = stat2.executeQuery(sql2);
            if(rs2.next()){
                int data = rs2.getInt("max_nisn");
                data++;
                txt_nisn.setText(String.valueOf(data));
            }
        } catch (Exception e) {
        }
    }
}
