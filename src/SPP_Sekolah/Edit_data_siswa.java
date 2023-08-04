
package SPP_Sekolah;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import notification.Notification;

public class Edit_data_siswa extends javax.swing.JFrame {

    String nisn;
    String nis;
    String nama;
    String idkelas;
    String alamat;
    String notelp;
    String idspp;
    String nama_kelas;
    
    public Edit_data_siswa() {
        initComponents();
        model_cbx_idkelas();
        
        
    }
    
    public void TransferData(){
        txt_nis.setText(nis);
        txt_nama.setText(nama);
        cbx_idkelas.setSelectedItem(idkelas);
        txt_nama_kelas.setText(nama_kelas);
        txt_alamat.setText(alamat);
        txt_notelp.setText(notelp);
        txt_id_spp.setText(idspp);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new Panel_Round.PanelRound();
        jLabel1 = new javax.swing.JLabel();
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
        txt_nama_kelas = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/student-card.png"))); // NOI18N
        jLabel1.setText("Edit Data Siswa");

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
        jLabel10.setText("Komp Keahlian");

        cbx_idkelas.setToolTipText("");
        cbx_idkelas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_idkelas.setLabeText("Masukan Id Kelas");
        cbx_idkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_idkelasActionPerformed(evt);
            }
        });

        txt_komp_keahlian.setEditable(false);
        txt_komp_keahlian.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        txt_id_spp.setToolTipText("Input No Telpon");
        txt_id_spp.setBorder(null);
        txt_id_spp.setName(""); // NOI18N
        txt_id_spp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_sppActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(204, 204, 255));
        btn_save.setForeground(new java.awt.Color(0, 0, 0));
        btn_save.setText("Edit");
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
        jLabel11.setText("Nama Kelas");

        txt_nama_kelas.setEditable(false);
        txt_nama_kelas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nama_kelas.setToolTipText("Input No Telpon");
        txt_nama_kelas.setBorder(null);
        txt_nama_kelas.setName(""); // NOI18N
        txt_nama_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_kelasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                            .addComponent(txt_nama)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbx_idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                                .addComponent(txt_notelp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_alamat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_nis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jSeparator8))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                    .addComponent(txt_id_spp, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_nama_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(1, 1, 1)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txt_notelp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id_spp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nama_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
            String sql = "Select komp_keahlian from tb_kelas where id_kelas ='"+cbx_idkelas.getSelectedItem()+"'";
            Connection conn = (Connection)koneksi.koneksiDB();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()){
                txt_komp_keahlian.setText(rs.getString("komp_keahlian"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di CBX iD KELAS"+e);
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
        Form_Menu_Petugas frm = new Form_Menu_Petugas(null);
        editdata_siswa();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        resetText();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_nama_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_kelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_kelasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btn_refresh;
    public rojerusan.RSMaterialButtonRectangle btn_save;
    public combobox.Combobox cbx_idkelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private Panel_Round.PanelRound panelRound1;
    public javax.swing.JTextField txt_alamat;
    public javax.swing.JTextField txt_id_spp;
    public javax.swing.JTextField txt_komp_keahlian;
    public javax.swing.JTextField txt_nama;
    public javax.swing.JTextField txt_nama_kelas;
    public javax.swing.JTextField txt_nis;
    public javax.swing.JTextField txt_notelp;
    // End of variables declaration//GEN-END:variables

    public void resetText() {
        txt_nis.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        cbx_idkelas.setSelectedIndex(-1);
        txt_id_spp.setText("");
        txt_notelp.setText("");
        txt_komp_keahlian.setText("");
    }

    public void model_cbx_idkelas(){
        try {
        java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
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
    
    private void editdata_siswa() {
        try {
            String sql = "update tb_siswa set nis='" + txt_nis.getText()
                    + "',nama='" + txt_nama.getText()
                    + "',id_kelas='" + cbx_idkelas.getSelectedItem()
                    + "',nama_kelas='" + txt_nama_kelas.getText()
                    + "',alamat='" + txt_alamat.getText()
                    + "',no_telp='" + txt_notelp.getText()
                    + "',id_spp='" + txt_id_spp.getText()
                    + "' WHERE nisn='" + this.nisn+ "'";

            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER,
                    "Edit Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER,
                    "Ada Kesalahan Di Edit Data"+ e);
            panel.showNotification();
        }
        resetText();
    }
}
