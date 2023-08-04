package SPP_Sekolah;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import notification.Notification;
import sun.rmi.transport.ObjectTable;

public class Form_Menu_Admin extends javax.swing.JFrame {

    String status, nisn, id_petugas,tanggal_bayar, tanggal_akhir_bayar, batas_tanggal,nama_kelas;
    DefaultTableModel tabmode;
    String nama_petugas;
    DefaultTableModel tabmode_sudah_bayar;
    DefaultTableModel tabmode_belum_bayar;
    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String batas_tanggal_awal = "2023-05-01";
    public Form_Menu_Admin(String nama_petugas1) {
        initComponents();
        dashboard_data_petugas();
        tab_home.setBackground(Color.white);
        maxi();
        this.nama_petugas = nama_petugas1;
        judul.setText("Selamat Datang, "+nama_petugas);
        
    }
    
//    public void function_tanggal_awal(){
//        try {
//            String selecttgl = "Select tgl_sekarang from cek_pembayaran";
//            statement = (Statement)koneksi.koneksiDB().createStatement();
//            resultSet = statement.executeQuery(selecttgl);
//            if (resultSet.next()) {
//                String updatetgl = "Update cek_pembayaran set tgl_sekarang='"+batas_tanggal_awal+"' where tgl_sekarang ='"+ resultSet.getString("tgl_sekarang")+"'";
//                statement.executeUpdate(updatetgl);
//            }
//                    
//        } catch (Exception e) {
//        }
//        
//        try {
//            // Menghubungkan ke database
//            Connection conn = (Connection)koneksi.koneksiDB();
//            
//            // Membuat pernyataan SQL untuk seleksi data
//            String selectQuery = "SELECT tgl_terakhir_bayar FROM cek_pembayaran";
//            statement = conn.createStatement();
//
//            // Menjalankan pernyataan SQL dan mendapatkan hasil
//            resultSet = statement.executeQuery(selectQuery);
//
//            // Mengolah hasil seleksi data
//            while (resultSet.next()) {
//                Date field2 = resultSet.getDate("tgl_terakhir_bayar");
//
//                // Menghitung selisih dalam bulan
//                Calendar cal1 = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Date date1 = null;
//                
//                try {
//                    date1 = sdf.parse(batas_tanggal_awal);
//                } catch (Exception e) {
//                }
//                cal1.setTime(date1);
//
//                Calendar cal2 = Calendar.getInstance();
//                cal2.setTime(field2);
//
//                int selisihBulan = 0;
//
//                
//                 // Jika cal1 lebih besar dari cal2
//                while (cal1.before(cal2)) {
//                        cal1.add(Calendar.MONTH, 2);
//                        selisihBulan++;
//                }
//                while (cal1.after(cal2)) {
//                        cal2.add(Calendar.MONTH, 1);
//                        selisihBulan--;
//                }
//                
//                
//                
//                // Mengubah nilai negatif agar menjadi positif
//                if (selisihBulan < 0) {
//                    selisihBulan = -selisihBulan;
//                }
//                
//                // Memperbarui field selisih di database
//                String updateQuery = "UPDATE cek_pembayaran SET jumlah_bulan = " + selisihBulan + ", tgl_sekarang = '" + batas_tanggal_awal + "' ,status_pembayaran = 'Belum Lunas' WHERE tgl_sekarang > tgl_terakhir_bayar";
//                statement.executeUpdate(updateQuery);
//                String updateQueryafter = "UPDATE cek_pembayaran SET jumlah_bulan = " + selisihBulan + ",status_pembayaran = 'Sudah Lunas' WHERE tgl_sekarang < tgl_terakhir_bayar";
//                statement.executeUpdate(updateQueryafter);
//                
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void hapusDataTable(){
        DefaultTableModel model = (DefaultTableModel)Table_Siswa.getModel();
        int rowcount = model.getRowCount();
        
        // Hapus semua baris pada tabel
        for (int i = rowcount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    
    public void resetText_kelas() {
        hapustabel();
        txt_id_kelas.setText("");
        txt_nm_kelas.setText("");
        txt_search.setText("");
        cbx_komp_keahlian.setSelectedIndex(-1);
        txt_id_kelas.setEditable(true);
        tampilkandata_kelas();
    }
    
    public void resetText_pembayaran(){
        txt_status.setText("");
        txt_nisn.setText("");
        txt_nama_siswa_pmb.setText("");
        txt_idkelas.setText("");
        txt_nama_kelas_pmb.setText("");
        txt_id_spp_pmb.setText("");
        txt_nominal_bayar.setText("");
        txt_uang_bayar.setText("");
        txt_uang_kembalian.setText("");
        cbx_jumlah_bulan.setSelectedIndex(-1);
        choose_tgl_akhir_pmb.setDate(null);
        choose_batas_pmb.setDate(null);
                
    }

    public void hapustabel() {
        int row = tabmode.getRowCount();
        for (int i = 0; i < row; i++) {
            tabmode.removeRow(0);
        }
    }
    
    public void hapustabel_cekbayar(){
        int row = tabmode_belum_bayar.getRowCount();
        int row2 = tabmode_sudah_bayar.getRowCount();
        for (int i = 0; i < row; i++) {
            tabmode_belum_bayar.removeRow(0);
        }
        for (int i = 0; i < row2; i++) {
            tabmode_sudah_bayar.removeRow(0);
        }
        txt_cek_bayar.setText("");
        txt_nisn_cek_pembayaran.setText("");
        tampil_siswa_belum_bayar();
        tampil_siswa_sudah_bayar();
    }
    
    public void hapustabel_pembayaran(){
        hapustabel();
        txt_cek_bayar_pmb.setText("");
        
    }
    
    public void dashboard_data_petugas(){
        int belum_bayar=0 ,sudah_bayar=0;
        try {
            
            String sql = "Select status_pembayaran from cek_pembayaran where status_pembayaran ='Belum Lunas'";
            String sql2 = "Select status_pembayaran from cek_pembayaran where status_pembayaran ='Sudah Lunas'";
            Statement stat = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                belum_bayar++;
            }
            
            ResultSet rs2 = stat.executeQuery(sql2);
            while(rs2.next()){
                sudah_bayar++;
            }
        label_belum_bayar_petugas.setText(String.valueOf(belum_bayar)+" Siswa");
        label_sudah_bayar_petugas.setText(String.valueOf(sudah_bayar)+" Siswa");
        } catch (Exception e) {
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Side = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tab_home = new Panel_Round.PanelRound();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tab_kelas = new Panel_Round.PanelRound();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tab_siswa = new Panel_Round.PanelRound();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tab_cek_pembayaran = new Panel_Round.PanelRound();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tab_pembayaran = new Panel_Round.PanelRound();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tab_detail_pembayaran = new Panel_Round.PanelRound();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tab_petugas = new Panel_Round.PanelRound();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        Utama = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel_data = new Panel_Round.PanelRound();
        panel_data2 = new Panel_Round.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        label_sudah_bayar_petugas = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        label_belum_bayar_petugas = new javax.swing.JLabel();
        panelRound1 = new Panel_Round.PanelRound();
        jLabel31 = new javax.swing.JLabel();
        Panel_Cek_Pembayaran = new javax.swing.JPanel();
        panel_judul_kelas2 = new Panel_Round.PanelRound();
        jLabel36 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_belum_bayar = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_sudah_bayar = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_cek_bayar = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txt_nisn_cek_pembayaran = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        btn_cek_bayar = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table_cek = new javax.swing.JTable();
        btn_search_cek_pembayaran = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        refresh_cek_bayar = new javax.swing.JLabel();
        Panel_Kelas = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_save_kelas = new rojerusan.RSMaterialButtonRectangle();
        btn_delete_kelas = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_kelas = new rojerusan.RSMaterialButtonRectangle();
        btn_refresh_kelas = new rojerusan.RSMaterialButtonRectangle();
        panelRound2 = new Panel_Round.PanelRound();
        panelRound3 = new Panel_Round.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        txt_id_kelas = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_nm_kelas = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        cbx_komp_keahlian = new combobox.Combobox();
        panel_judul_kelas = new Panel_Round.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btn_search = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Kelas = new javax.swing.JTable();
        Panel_Siswa = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Siswa = new javax.swing.JTable();
        btn_insert_siswa = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_siswa = new rojerusan.RSMaterialButtonRectangle();
        btn_delete_siswa = new rojerusan.RSMaterialButtonRectangle();
        panel_judul_kelas1 = new Panel_Round.PanelRound();
        jLabel34 = new javax.swing.JLabel();
        btn_search1 = new javax.swing.JLabel();
        txt_search_siswa = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        refresh_siswa = new javax.swing.JLabel();
        Panel_Detail_Pembayaran = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table_detail_pembayaran = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        txt_search_detail_pmb = new javax.swing.JTextField();
        btn_search_detail = new javax.swing.JLabel();
        refresh_detail_bayar = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        Panel_Pembayaran = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_idpembayaran = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        txt_cek_bayar_pmb = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        btn_search_cek_pembayaran_pmb = new javax.swing.JLabel();
        refresh_cek_bayar_pmb = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Table_cek_pmb = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txt_nisn = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        txt_nama_siswa_pmb = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        txt_idkelas = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        txt_nama_kelas_pmb = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        choose_tgl_bayar_pmb = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        choose_tgl_akhir_pmb = new com.toedter.calendar.JDateChooser();
        jLabel53 = new javax.swing.JLabel();
        choose_batas_pmb = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        txt_id_spp_pmb = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel55 = new javax.swing.JLabel();
        cbx_jumlah_bulan = new combobox.Combobox();
        jLabel56 = new javax.swing.JLabel();
        txt_nominal_bayar = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        txt_uang_bayar = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        txt_uang_kembalian = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        btn_bayar_pmb = new rojerusan.RSMaterialButtonRectangle();
        jLabel59 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        btn_simpan_pmb = new rojerusan.RSMaterialButtonRectangle();
        btn_simpan_pmb1 = new rojerusan.RSMaterialButtonRectangle();
        Panel_Petugas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_save_petugas = new rojerusan.RSMaterialButtonRectangle();
        btn_delete_petugas = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_petugas = new rojerusan.RSMaterialButtonRectangle();
        btn_refresh_petugas = new rojerusan.RSMaterialButtonRectangle();
        panelRound4 = new Panel_Round.PanelRound();
        panelRound5 = new Panel_Round.PanelRound();
        jLabel62 = new javax.swing.JLabel();
        txt_idpetugas = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        cbx_level_petugas = new combobox.Combobox();
        jLabel67 = new javax.swing.JLabel();
        txt_password = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel68 = new javax.swing.JLabel();
        txt_nama_petugas = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        panel_judul_kelas3 = new Panel_Round.PanelRound();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        btn_search2 = new javax.swing.JLabel();
        txt_search_petugas = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        Table_Petugas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Admin");

        Side.setBackground(new java.awt.Color(153, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menu Admin");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        tab_home.setBackground(new java.awt.Color(204, 204, 255));
        tab_home.setRoundBottomLeft(25);
        tab_home.setRoundBottomRight(25);
        tab_home.setRoundTopLeft(25);
        tab_home.setRoundTopRight(25);
        tab_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_homeMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel18.setText("Dashboard");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N

        javax.swing.GroupLayout tab_homeLayout = new javax.swing.GroupLayout(tab_home);
        tab_home.setLayout(tab_homeLayout);
        tab_homeLayout.setHorizontalGroup(
            tab_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_homeLayout.setVerticalGroup(
            tab_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/office-worker-putih-32px.png"))); // NOI18N

        tab_kelas.setBackground(new java.awt.Color(204, 204, 255));
        tab_kelas.setRoundBottomLeft(25);
        tab_kelas.setRoundBottomRight(25);
        tab_kelas.setRoundTopLeft(25);
        tab_kelas.setRoundTopRight(25);
        tab_kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_kelasMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel21.setText("Data Kelas");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Classroom-hitam.png"))); // NOI18N

        javax.swing.GroupLayout tab_kelasLayout = new javax.swing.GroupLayout(tab_kelas);
        tab_kelas.setLayout(tab_kelasLayout);
        tab_kelasLayout.setHorizontalGroup(
            tab_kelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_kelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_kelasLayout.setVerticalGroup(
            tab_kelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_kelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_kelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_siswa.setBackground(new java.awt.Color(204, 204, 255));
        tab_siswa.setRoundBottomLeft(25);
        tab_siswa.setRoundBottomRight(25);
        tab_siswa.setRoundTopLeft(25);
        tab_siswa.setRoundTopRight(25);
        tab_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_siswaMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel23.setText("Data Siswa");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/student-card.png"))); // NOI18N

        javax.swing.GroupLayout tab_siswaLayout = new javax.swing.GroupLayout(tab_siswa);
        tab_siswa.setLayout(tab_siswaLayout);
        tab_siswaLayout.setHorizontalGroup(
            tab_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_siswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_siswaLayout.setVerticalGroup(
            tab_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_siswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_cek_pembayaran.setBackground(new java.awt.Color(204, 204, 255));
        tab_cek_pembayaran.setRoundBottomLeft(25);
        tab_cek_pembayaran.setRoundBottomRight(25);
        tab_cek_pembayaran.setRoundTopLeft(25);
        tab_cek_pembayaran.setRoundTopRight(25);
        tab_cek_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_cek_pembayaranMouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel25.setText("Cek Pembayaran");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cek_bayar.png"))); // NOI18N

        javax.swing.GroupLayout tab_cek_pembayaranLayout = new javax.swing.GroupLayout(tab_cek_pembayaran);
        tab_cek_pembayaran.setLayout(tab_cek_pembayaranLayout);
        tab_cek_pembayaranLayout.setHorizontalGroup(
            tab_cek_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_cek_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab_cek_pembayaranLayout.setVerticalGroup(
            tab_cek_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_cek_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_cek_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pembayaran.setBackground(new java.awt.Color(204, 204, 255));
        tab_pembayaran.setRoundBottomLeft(25);
        tab_pembayaran.setRoundBottomRight(25);
        tab_pembayaran.setRoundTopLeft(25);
        tab_pembayaran.setRoundTopRight(25);
        tab_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_pembayaranMouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel27.setText("Pembayaran");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bill.png"))); // NOI18N

        javax.swing.GroupLayout tab_pembayaranLayout = new javax.swing.GroupLayout(tab_pembayaran);
        tab_pembayaran.setLayout(tab_pembayaranLayout);
        tab_pembayaranLayout.setHorizontalGroup(
            tab_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_pembayaranLayout.setVerticalGroup(
            tab_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_detail_pembayaran.setBackground(new java.awt.Color(204, 204, 255));
        tab_detail_pembayaran.setRoundBottomLeft(25);
        tab_detail_pembayaran.setRoundBottomRight(25);
        tab_detail_pembayaran.setRoundTopLeft(25);
        tab_detail_pembayaran.setRoundTopRight(25);
        tab_detail_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_detail_pembayaranMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel29.setText("Detail Pembayaran");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/detail_bayar.png"))); // NOI18N

        javax.swing.GroupLayout tab_detail_pembayaranLayout = new javax.swing.GroupLayout(tab_detail_pembayaran);
        tab_detail_pembayaran.setLayout(tab_detail_pembayaranLayout);
        tab_detail_pembayaranLayout.setHorizontalGroup(
            tab_detail_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_detail_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_detail_pembayaranLayout.setVerticalGroup(
            tab_detail_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_detail_pembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_detail_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_petugas.setBackground(new java.awt.Color(204, 204, 255));
        tab_petugas.setRoundBottomLeft(25);
        tab_petugas.setRoundBottomRight(25);
        tab_petugas.setRoundTopLeft(25);
        tab_petugas.setRoundTopRight(25);
        tab_petugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_petugasMouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel60.setText("Data Petugas");

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/office-worker.png"))); // NOI18N

        javax.swing.GroupLayout tab_petugasLayout = new javax.swing.GroupLayout(tab_petugas);
        tab_petugas.setLayout(tab_petugasLayout);
        tab_petugasLayout.setHorizontalGroup(
            tab_petugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_petugasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_petugasLayout.setVerticalGroup(
            tab_petugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_petugasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_petugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SideLayout = new javax.swing.GroupLayout(Side);
        Side.setLayout(SideLayout);
        SideLayout.setHorizontalGroup(
            SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(SideLayout.createSequentialGroup()
                .addGroup(SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(SideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab_home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_siswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_cek_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_detail_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tab_petugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        SideLayout.setVerticalGroup(
            SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_cek_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_detail_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Utama.setBackground(new java.awt.Color(255, 255, 255));
        Utama.setLayout(new java.awt.CardLayout());

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));

        judul.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        judul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        judul.setText("Nama Admin");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Sekolah_Menu-786px.jpg"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("JetBrains Mono", 1, 48)); // NOI18N
        jLabel7.setText("APLIKASI PEMBAYARAN");

        jLabel8.setFont(new java.awt.Font("JetBrains Mono", 1, 48)); // NOI18N
        jLabel8.setText("SPP SEKOLAH");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/transaction-history.png"))); // NOI18N

        panel_data.setBackground(new java.awt.Color(153, 153, 255));
        panel_data.setRoundBottomLeft(50);
        panel_data.setRoundBottomRight(50);
        panel_data.setRoundTopLeft(50);
        panel_data.setRoundTopRight(50);
        panel_data.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_data2.setBackground(new java.awt.Color(255, 255, 255));
        panel_data2.setRoundBottomLeft(50);
        panel_data2.setRoundBottomRight(50);
        panel_data2.setRoundTopLeft(40);
        panel_data2.setRoundTopRight(40);
        panel_data2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setForeground(new java.awt.Color(0, 204, 51));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sucess.png"))); // NOI18N
        panel_data2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel11.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel11.setText("Siswa Yang Sudah Lunas");
        panel_data2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel12.setText("Total :");
        panel_data2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        label_sudah_bayar_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        label_sudah_bayar_petugas.setText("0");
        panel_data2.add(label_sudah_bayar_petugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/warning.png"))); // NOI18N
        panel_data2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, -1, -1));

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel15.setText("Siswa Yang Belum Lunas");
        panel_data2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel16.setText("Total :");
        panel_data2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        label_belum_bayar_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        label_belum_bayar_petugas.setText("0");
        panel_data2.add(label_belum_bayar_petugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, 40));

        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        panel_data2.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 130));

        panel_data.add(panel_data2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 150));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/coin-kuning.png"))); // NOI18N

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(jLabel7)
                    .addComponent(jLabel31))
                .addGap(93, 93, 93))
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_data, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(405, 405, 405))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addComponent(judul)
                .addGap(73, 73, 73)
                .addComponent(panel_data, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DashboardLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(DashboardLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9)))
                        .addGap(22, 22, 22))))
        );

        Utama.add(Dashboard, "card4");

        Panel_Cek_Pembayaran.setBackground(new java.awt.Color(255, 255, 255));

        panel_judul_kelas2.setBackground(new java.awt.Color(153, 204, 255));
        panel_judul_kelas2.setRoundBottomLeft(30);
        panel_judul_kelas2.setRoundBottomRight(30);
        panel_judul_kelas2.setRoundTopLeft(30);
        panel_judul_kelas2.setRoundTopRight(30);

        jLabel36.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Cek Pembayaran");

        javax.swing.GroupLayout panel_judul_kelas2Layout = new javax.swing.GroupLayout(panel_judul_kelas2);
        panel_judul_kelas2.setLayout(panel_judul_kelas2Layout);
        panel_judul_kelas2Layout.setHorizontalGroup(
            panel_judul_kelas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );
        panel_judul_kelas2Layout.setVerticalGroup(
            panel_judul_kelas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_judul_kelas2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cek_bayar-100px-biru.png"))); // NOI18N

        Table_belum_bayar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_belum_bayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_belum_bayar.setGridColor(new java.awt.Color(255, 255, 255));
        Table_belum_bayar.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_belum_bayar.setMinimumSize(new java.awt.Dimension(10, 100));
        Table_belum_bayar.setRowHeight(25);
        jScrollPane1.setViewportView(Table_belum_bayar);

        Table_sudah_bayar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_sudah_bayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_sudah_bayar.setGridColor(new java.awt.Color(255, 255, 255));
        Table_sudah_bayar.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_sudah_bayar.setMinimumSize(new java.awt.Dimension(10, 100));
        Table_sudah_bayar.setRowHeight(25);
        jScrollPane4.setViewportView(Table_sudah_bayar);

        jLabel37.setFont(new java.awt.Font("JetBrains Mono", 1, 30)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sucess.png"))); // NOI18N
        jLabel37.setText("Siswa Yang Sudah Lunas");

        jLabel38.setFont(new java.awt.Font("JetBrains Mono", 1, 30)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/warning.png"))); // NOI18N
        jLabel38.setText("Siswa Yang Belum Lunas");

        jLabel39.setFont(new java.awt.Font("JetBrains Mono", 1, 20)); // NOI18N
        jLabel39.setText("Cari NISN dengan memasukan Namamu");

        txt_cek_bayar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_cek_bayar.setToolTipText("Input Nama Untuk Pencarian");
        txt_cek_bayar.setBorder(null);
        txt_cek_bayar.setName(""); // NOI18N

        txt_nisn_cek_pembayaran.setEditable(false);
        txt_nisn_cek_pembayaran.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel40.setFont(new java.awt.Font("JetBrains Mono", 1, 20)); // NOI18N
        jLabel40.setText("Cek Pembayaran Menggunakan NISNmu");

        btn_cek_bayar.setBackground(new java.awt.Color(204, 204, 255));
        btn_cek_bayar.setForeground(new java.awt.Color(0, 0, 0));
        btn_cek_bayar.setText("Cek Pembayaran");
        btn_cek_bayar.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_cek_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cek_bayarActionPerformed(evt);
            }
        });

        Table_cek.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_cek.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_cek.setGridColor(new java.awt.Color(255, 255, 255));
        Table_cek.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_cek.setRowHeight(25);
        Table_cek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_cekMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Table_cek);

        btn_search_cek_pembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-32px.png"))); // NOI18N
        btn_search_cek_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_search_cek_pembayaranMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("JetBrains Mono", 1, 20)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Data Hasil Pencarian");

        refresh_cek_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh-ungu.png"))); // NOI18N
        refresh_cek_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_cek_bayarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Cek_PembayaranLayout = new javax.swing.GroupLayout(Panel_Cek_Pembayaran);
        Panel_Cek_Pembayaran.setLayout(Panel_Cek_PembayaranLayout);
        Panel_Cek_PembayaranLayout.setHorizontalGroup(
            Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_judul_kelas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                                    .addComponent(jLabel39)
                                    .addComponent(txt_cek_bayar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_search_cek_pembayaran)
                                .addGap(18, 18, 18)
                                .addComponent(refresh_cek_bayar)))
                        .addGap(35, 35, 35)
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nisn_cek_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(btn_cek_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        Panel_Cek_PembayaranLayout.setVerticalGroup(
            Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Cek_PembayaranLayout.createSequentialGroup()
                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(refresh_cek_bayar)
                                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                                        .addComponent(panel_judul_kelas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel39)
                                        .addGap(2, 2, 2)
                                        .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_cek_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_search_cek_pembayaran))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40)
                        .addGap(6, 6, 6)
                        .addComponent(txt_nisn_cek_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_Cek_PembayaranLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_cek_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_Cek_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        Utama.add(Panel_Cek_Pembayaran, "card3");

        Panel_Kelas.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_save_kelas.setBackground(new java.awt.Color(204, 204, 255));
        btn_save_kelas.setForeground(new java.awt.Color(0, 0, 0));
        btn_save_kelas.setText("Simpan");
        btn_save_kelas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_save_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_kelasActionPerformed(evt);
            }
        });

        btn_delete_kelas.setBackground(new java.awt.Color(204, 204, 255));
        btn_delete_kelas.setForeground(new java.awt.Color(0, 0, 0));
        btn_delete_kelas.setText("hapus");
        btn_delete_kelas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_delete_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_kelasActionPerformed(evt);
            }
        });

        btn_edit_kelas.setBackground(new java.awt.Color(204, 204, 255));
        btn_edit_kelas.setForeground(new java.awt.Color(0, 0, 0));
        btn_edit_kelas.setText("ubah");
        btn_edit_kelas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_edit_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_kelasActionPerformed(evt);
            }
        });

        btn_refresh_kelas.setBackground(new java.awt.Color(204, 204, 255));
        btn_refresh_kelas.setForeground(new java.awt.Color(0, 0, 0));
        btn_refresh_kelas.setText("bersihkan");
        btn_refresh_kelas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_refresh_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_kelasActionPerformed(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(153, 204, 255));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel6.setText("ID KELAS");

        txt_id_kelas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_id_kelas.setToolTipText("Input Id Kelas");
        txt_id_kelas.setBorder(null);
        txt_id_kelas.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel3.setText("NAMA KELAS");

        txt_nm_kelas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nm_kelas.setToolTipText("Input Nama Kelas");
        txt_nm_kelas.setBorder(null);
        txt_nm_kelas.setName(""); // NOI18N
        txt_nm_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nm_kelasActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel32.setText("KOMPETENSI KEAHLIAN");

        cbx_komp_keahlian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rekayasa Perangkat Lunak", "Perhotelan", "Kecantikan", "Tata Boga", "Tata Busana", "Usaha Pariwisata", "Seni Musik" }));
        cbx_komp_keahlian.setSelectedIndex(-1);
        cbx_komp_keahlian.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_komp_keahlian.setLabeText("Pilih Komp Keahlian");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addComponent(txt_nm_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addComponent(cbx_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txt_id_kelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel6))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(txt_id_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(txt_nm_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_komp_keahlian, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_refresh_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_save_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btn_edit_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_refresh_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_judul_kelas.setBackground(new java.awt.Color(153, 204, 255));
        panel_judul_kelas.setRoundBottomLeft(30);
        panel_judul_kelas.setRoundBottomRight(30);
        panel_judul_kelas.setRoundTopLeft(30);
        panel_judul_kelas.setRoundTopRight(30);

        jLabel5.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Data Kelas");

        javax.swing.GroupLayout panel_judul_kelasLayout = new javax.swing.GroupLayout(panel_judul_kelas);
        panel_judul_kelas.setLayout(panel_judul_kelasLayout);
        panel_judul_kelasLayout.setHorizontalGroup(
            panel_judul_kelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        panel_judul_kelasLayout.setVerticalGroup(
            panel_judul_kelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_judul_kelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Classroom-100px.png"))); // NOI18N

        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loupe.png"))); // NOI18N
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
        });

        txt_search.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_search.setBorder(null);
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane2.setBorder(null);

        Table_Kelas.setAutoCreateRowSorter(true);
        Table_Kelas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_Kelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Kelas", "Nama Kelas", "Kompetensi Keahlian"
            }
        ));
        Table_Kelas.setGridColor(new java.awt.Color(255, 255, 255));
        Table_Kelas.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_Kelas.setRowHeight(25);
        Table_Kelas.setSelectionBackground(new java.awt.Color(16, 181, 232));
        Table_Kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_KelasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_Kelas);

        javax.swing.GroupLayout Panel_KelasLayout = new javax.swing.GroupLayout(Panel_Kelas);
        Panel_Kelas.setLayout(Panel_KelasLayout);
        Panel_KelasLayout.setHorizontalGroup(
            Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_KelasLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Panel_KelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_KelasLayout.createSequentialGroup()
                        .addComponent(panel_judul_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_KelasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_KelasLayout.createSequentialGroup()
                                .addComponent(btn_search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_KelasLayout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))))))
        );
        Panel_KelasLayout.setVerticalGroup(
            Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_KelasLayout.createSequentialGroup()
                .addGroup(Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_judul_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_search)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_KelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_KelasLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        Utama.add(Panel_Kelas, "card2");

        Panel_Siswa.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(null);

        Table_Siswa.setAutoCreateRowSorter(true);
        Table_Siswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Table_Siswa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_Siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nisn", "Nis", "Nama", "Id Kelas", "Komp Keahlian", "Alamat", "No Telp", "Id Spp"
            }
        ));
        Table_Siswa.setGridColor(new java.awt.Color(255, 255, 255));
        Table_Siswa.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_Siswa.setRowHeight(25);
        Table_Siswa.setSelectionBackground(new java.awt.Color(16, 181, 232));
        Table_Siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_SiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Table_Siswa);

        btn_insert_siswa.setBackground(new java.awt.Color(204, 204, 255));
        btn_insert_siswa.setForeground(new java.awt.Color(0, 0, 0));
        btn_insert_siswa.setText("tambah");
        btn_insert_siswa.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_insert_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insert_siswaActionPerformed(evt);
            }
        });

        btn_edit_siswa.setBackground(new java.awt.Color(204, 204, 255));
        btn_edit_siswa.setForeground(new java.awt.Color(0, 0, 0));
        btn_edit_siswa.setText("ubah");
        btn_edit_siswa.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_edit_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_siswaActionPerformed(evt);
            }
        });

        btn_delete_siswa.setBackground(new java.awt.Color(204, 204, 255));
        btn_delete_siswa.setForeground(new java.awt.Color(0, 0, 0));
        btn_delete_siswa.setText("hapus");
        btn_delete_siswa.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_delete_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_siswaActionPerformed(evt);
            }
        });

        panel_judul_kelas1.setBackground(new java.awt.Color(153, 204, 255));
        panel_judul_kelas1.setRoundBottomLeft(30);
        panel_judul_kelas1.setRoundBottomRight(30);
        panel_judul_kelas1.setRoundTopLeft(30);
        panel_judul_kelas1.setRoundTopRight(30);

        jLabel34.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Data Siswa");

        javax.swing.GroupLayout panel_judul_kelas1Layout = new javax.swing.GroupLayout(panel_judul_kelas1);
        panel_judul_kelas1.setLayout(panel_judul_kelas1Layout);
        panel_judul_kelas1Layout.setHorizontalGroup(
            panel_judul_kelas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        panel_judul_kelas1Layout.setVerticalGroup(
            panel_judul_kelas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_judul_kelas1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loupe.png"))); // NOI18N
        btn_search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_search1MouseClicked(evt);
            }
        });

        txt_search_siswa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_search_siswa.setBorder(null);
        txt_search_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_siswaActionPerformed(evt);
            }
        });
        txt_search_siswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_siswaKeyReleased(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/student-card-100px-biru.png"))); // NOI18N

        refresh_siswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh-ungu.png"))); // NOI18N
        refresh_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_siswaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_SiswaLayout = new javax.swing.GroupLayout(Panel_Siswa);
        Panel_Siswa.setLayout(Panel_SiswaLayout);
        Panel_SiswaLayout.setHorizontalGroup(
            Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SiswaLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_SiswaLayout.createSequentialGroup()
                        .addComponent(btn_insert_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_search1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator5)
                            .addComponent(txt_search_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refresh_siswa)
                        .addGap(5, 5, 5)))
                .addContainerGap(147, Short.MAX_VALUE))
            .addGroup(Panel_SiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_judul_kelas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35))
        );
        Panel_SiswaLayout.setVerticalGroup(
            Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_SiswaLayout.createSequentialGroup()
                .addGroup(Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_SiswaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_judul_kelas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_insert_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_SiswaLayout.createSequentialGroup()
                        .addGroup(Panel_SiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_search1)
                            .addComponent(txt_search_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_SiswaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(refresh_siswa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Utama.add(Panel_Siswa, "card5");

        Panel_Detail_Pembayaran.setBackground(new java.awt.Color(255, 255, 255));

        Table_detail_pembayaran.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Table_detail_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_detail_pembayaran.setGridColor(new java.awt.Color(255, 255, 255));
        Table_detail_pembayaran.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_detail_pembayaran.setRowHeight(25);
        jScrollPane5.setViewportView(Table_detail_pembayaran);

        jLabel45.setFont(new java.awt.Font("JetBrains Mono", 1, 35)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/detail_bayar.png"))); // NOI18N
        jLabel45.setText("Detail Pembayaran");

        txt_search_detail_pmb.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_search_detail_pmb.setToolTipText("Input Nama Untuk Pencarian");
        txt_search_detail_pmb.setBorder(null);
        txt_search_detail_pmb.setName(""); // NOI18N

        btn_search_detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-32px.png"))); // NOI18N
        btn_search_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_search_detailMouseClicked(evt);
            }
        });

        refresh_detail_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh-ungu.png"))); // NOI18N
        refresh_detail_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_detail_bayarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_Detail_PembayaranLayout = new javax.swing.GroupLayout(Panel_Detail_Pembayaran);
        Panel_Detail_Pembayaran.setLayout(Panel_Detail_PembayaranLayout);
        Panel_Detail_PembayaranLayout.setHorizontalGroup(
            Panel_Detail_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1657, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Detail_PembayaranLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_Detail_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_search_detail_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search_detail)
                .addGap(18, 18, 18)
                .addComponent(refresh_detail_bayar)
                .addContainerGap())
        );
        Panel_Detail_PembayaranLayout.setVerticalGroup(
            Panel_Detail_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Detail_PembayaranLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel45)
                .addGap(78, 78, 78)
                .addGroup(Panel_Detail_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(refresh_detail_bayar)
                    .addComponent(txt_search_detail_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_detail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );

        Utama.add(Panel_Detail_Pembayaran, "card6");

        Panel_Pembayaran.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("JetBrains Mono", 1, 40)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Pembayaran");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exchange-rate-biru.png"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exchange-rate-biru.png"))); // NOI18N

        jLabel43.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel43.setText("ID PEMBAYARAN");

        txt_idpembayaran.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_idpembayaran.setToolTipText("Input Id Pembayaran");
        txt_idpembayaran.setBorder(null);
        txt_idpembayaran.setName(""); // NOI18N

        jLabel44.setFont(new java.awt.Font("JetBrains Mono", 1, 20)); // NOI18N
        jLabel44.setText("Cari NISN dengan memasukan Namamu");

        txt_cek_bayar_pmb.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_cek_bayar_pmb.setToolTipText("Input Nama Untuk Pencarian");
        txt_cek_bayar_pmb.setBorder(null);
        txt_cek_bayar_pmb.setName(""); // NOI18N
        txt_cek_bayar_pmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cek_bayar_pmbActionPerformed(evt);
            }
        });

        btn_search_cek_pembayaran_pmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-32px.png"))); // NOI18N
        btn_search_cek_pembayaran_pmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_search_cek_pembayaran_pmbMouseClicked(evt);
            }
        });

        refresh_cek_bayar_pmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh-ungu.png"))); // NOI18N
        refresh_cek_bayar_pmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh_cek_bayar_pmbMouseClicked(evt);
            }
        });

        Table_cek_pmb.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_cek_pmb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_cek_pmb.setGridColor(new java.awt.Color(255, 255, 255));
        Table_cek_pmb.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_cek_pmb.setRowHeight(25);
        Table_cek_pmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_cek_pmbMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Table_cek_pmb);

        jLabel46.setFont(new java.awt.Font("JetBrains Mono", 1, 20)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Data Hasil Pencarian");

        txt_nisn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nisn.setToolTipText("Input Id Pembayaran");
        txt_nisn.setBorder(null);
        txt_nisn.setName(""); // NOI18N

        jLabel47.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel47.setText("NISN");

        jLabel48.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel48.setText("Nama Siswa");

        txt_nama_siswa_pmb.setEditable(false);
        txt_nama_siswa_pmb.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_nama_siswa_pmb.setToolTipText("Input Nama Siswa");
        txt_nama_siswa_pmb.setBorder(null);
        txt_nama_siswa_pmb.setName(""); // NOI18N
        txt_nama_siswa_pmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_siswa_pmbActionPerformed(evt);
            }
        });

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel49.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel49.setText("ID KELAS");

        txt_idkelas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_idkelas.setToolTipText("Input Id Pembayaran");
        txt_idkelas.setBorder(null);
        txt_idkelas.setName(""); // NOI18N

        jLabel50.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel50.setText("Nama Kelas");

        txt_nama_kelas_pmb.setEditable(false);
        txt_nama_kelas_pmb.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_nama_kelas_pmb.setToolTipText("Input Nama Siswa");
        txt_nama_kelas_pmb.setBorder(null);
        txt_nama_kelas_pmb.setName(""); // NOI18N
        txt_nama_kelas_pmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_kelas_pmbActionPerformed(evt);
            }
        });

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));

        jLabel51.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel51.setText("Tanggal Bayar");

        choose_tgl_bayar_pmb.setToolTipText("Masukan Tanggal Pembayaran");

        jLabel52.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel52.setText("Tanggal Pembayaran Terakhir");

        choose_tgl_akhir_pmb.setToolTipText("Masukan Tanggal Pembayaran");

        jLabel53.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel53.setText("Batas Pembayaran SPP");

        choose_batas_pmb.setToolTipText("Masukan Tanggal Pembayaran");

        jLabel54.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel54.setText("ID SPP");

        txt_id_spp_pmb.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_id_spp_pmb.setToolTipText("Input Id Pembayaran");
        txt_id_spp_pmb.setBorder(null);
        txt_id_spp_pmb.setName(""); // NOI18N

        jLabel55.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel55.setText("Ingin Bayar Berapa Bulan");

        cbx_jumlah_bulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Bulan", "2 Bulan", "3 Bulan", "4 Bulan", "5 Bulan", "6 Bulan", "7 Bulan", "8 Bulan", "9 Bulan", "10 Bulan", "11 Bulan", "12 Bulan" }));
        cbx_jumlah_bulan.setSelectedIndex(-1);
        cbx_jumlah_bulan.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_jumlah_bulan.setLabeText("Pilih Jumlah Bulan");
        cbx_jumlah_bulan.setLightWeightPopupEnabled(false);
        cbx_jumlah_bulan.setLineColor(new java.awt.Color(255, 255, 255));
        cbx_jumlah_bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_jumlah_bulanActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel56.setText("Nominal Yang Harus Dibayar");

        txt_nominal_bayar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nominal_bayar.setToolTipText("Input Id Pembayaran");
        txt_nominal_bayar.setBorder(null);
        txt_nominal_bayar.setName(""); // NOI18N

        jLabel57.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel57.setText("Uang Yang Dibayarkan");

        txt_uang_bayar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_uang_bayar.setToolTipText("Input Id Pembayaran");
        txt_uang_bayar.setBorder(null);
        txt_uang_bayar.setName(""); // NOI18N

        jLabel58.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel58.setText("Jumlah Uang Kembalian");

        txt_uang_kembalian.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_uang_kembalian.setToolTipText("Input Id Pembayaran");
        txt_uang_kembalian.setBorder(null);
        txt_uang_kembalian.setName(""); // NOI18N

        btn_bayar_pmb.setBackground(new java.awt.Color(204, 204, 255));
        btn_bayar_pmb.setForeground(new java.awt.Color(0, 0, 0));
        btn_bayar_pmb.setText("Bayar");
        btn_bayar_pmb.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_bayar_pmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayar_pmbActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel59.setText("Status Pembayaran");

        txt_status.setEditable(false);
        txt_status.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_status.setToolTipText("Input Id Pembayaran");
        txt_status.setBorder(null);
        txt_status.setName(""); // NOI18N

        btn_simpan_pmb.setBackground(new java.awt.Color(204, 204, 255));
        btn_simpan_pmb.setForeground(new java.awt.Color(0, 0, 0));
        btn_simpan_pmb.setText("bersihkan input");
        btn_simpan_pmb.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_simpan_pmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_pmbActionPerformed(evt);
            }
        });

        btn_simpan_pmb1.setBackground(new java.awt.Color(204, 204, 255));
        btn_simpan_pmb1.setForeground(new java.awt.Color(0, 0, 0));
        btn_simpan_pmb1.setText("simpan");
        btn_simpan_pmb1.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_simpan_pmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_pmb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_PembayaranLayout = new javax.swing.GroupLayout(Panel_Pembayaran);
        Panel_Pembayaran.setLayout(Panel_PembayaranLayout);
        Panel_PembayaranLayout.setHorizontalGroup(
            Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(choose_tgl_bayar_pmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_nisn, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                            .addComponent(jLabel49)
                            .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_idkelas, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
                        .addComponent(choose_batas_pmb, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41))
                    .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id_spp_pmb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_PembayaranLayout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addGap(276, 276, 276)))
                    .addComponent(jLabel43)
                    .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_idpembayaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel57)
                    .addComponent(txt_uang_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(txt_uang_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_bayar_pmb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
                .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_jumlah_bulan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(choose_tgl_akhir_pmb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nama_siswa_pmb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addComponent(txt_status, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nama_kelas_pmb, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nominal_bayar))
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PembayaranLayout.createSequentialGroup()
                                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_cek_bayar_pmb))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_search_cek_pembayaran_pmb)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(refresh_cek_bayar_pmb)
                                        .addGap(23, 23, 23))))))
                    .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_simpan_pmb, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(btn_simpan_pmb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(14, 14, 14))
        );
        Panel_PembayaranLayout.setVerticalGroup(
            Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel17)))
                        .addGap(158, 158, 158)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(2, 2, 2)
                                .addComponent(txt_idpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel47)
                                .addGap(2, 2, 2)
                                .addComponent(txt_nisn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_idkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(2, 2, 2)
                                .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel48)
                                .addGap(1, 1, 1)
                                .addComponent(txt_nama_siswa_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nama_kelas_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(9, 9, 9)
                                .addComponent(choose_tgl_bayar_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addGap(9, 9, 9)
                                .addComponent(choose_tgl_akhir_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jLabel55))
                        .addGap(9, 9, 9)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choose_batas_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_jumlah_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addGap(8, 8, 8)
                                .addComponent(txt_id_spp_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(8, 8, 8)
                                .addComponent(txt_nominal_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57)
                        .addGap(1, 1, 1)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_uang_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_bayar_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan_pmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(jLabel58)
                        .addGap(8, 8, 8)
                        .addComponent(txt_uang_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_PembayaranLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(Panel_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_cek_bayar_pmb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_search_cek_pembayaran_pmb)
                                    .addComponent(refresh_cek_bayar_pmb)))
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        Utama.add(Panel_Pembayaran, "card7");

        Panel_Petugas.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btn_save_petugas.setBackground(new java.awt.Color(204, 204, 255));
        btn_save_petugas.setForeground(new java.awt.Color(0, 0, 0));
        btn_save_petugas.setText("simpan");
        btn_save_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_save_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_petugasActionPerformed(evt);
            }
        });

        btn_delete_petugas.setBackground(new java.awt.Color(204, 204, 255));
        btn_delete_petugas.setForeground(new java.awt.Color(0, 0, 0));
        btn_delete_petugas.setText("hapus");
        btn_delete_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_delete_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_petugasActionPerformed(evt);
            }
        });

        btn_edit_petugas.setBackground(new java.awt.Color(204, 204, 255));
        btn_edit_petugas.setForeground(new java.awt.Color(0, 0, 0));
        btn_edit_petugas.setText("ubah");
        btn_edit_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_edit_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_petugasActionPerformed(evt);
            }
        });

        btn_refresh_petugas.setBackground(new java.awt.Color(204, 204, 255));
        btn_refresh_petugas.setForeground(new java.awt.Color(0, 0, 0));
        btn_refresh_petugas.setText("bersihkan");
        btn_refresh_petugas.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        btn_refresh_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refresh_petugasActionPerformed(evt);
            }
        });

        panelRound4.setBackground(new java.awt.Color(153, 204, 255));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        panelRound5.setBackground(new java.awt.Color(255, 255, 255));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        jLabel62.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel62.setText("ID PETUGAS");

        txt_idpetugas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_idpetugas.setToolTipText("Input Id Kelas");
        txt_idpetugas.setBorder(null);
        txt_idpetugas.setName(""); // NOI18N

        jLabel63.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel63.setText("USERNAME");

        txt_username.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_username.setToolTipText("Input Nama Kelas");
        txt_username.setBorder(null);
        txt_username.setName(""); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel64.setText("Level");

        cbx_level_petugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "admin", "petugas", "siswa" }));
        cbx_level_petugas.setSelectedIndex(-1);
        cbx_level_petugas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbx_level_petugas.setLabeText("Pilih Level Petugas");
        cbx_level_petugas.setLightWeightPopupEnabled(false);
        cbx_level_petugas.setLineColor(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel67.setText("PASSWORD");

        txt_password.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_password.setToolTipText("Input Nama Kelas");
        txt_password.setBorder(null);
        txt_password.setName(""); // NOI18N
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel68.setText("Nama Petugas");

        txt_nama_petugas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txt_nama_petugas.setToolTipText("Input Nama Kelas");
        txt_nama_petugas.setBorder(null);
        txt_nama_petugas.setName(""); // NOI18N
        txt_nama_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_petugasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator19)
                        .addComponent(txt_username, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                        .addComponent(cbx_level_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator18, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel63)
                        .addComponent(txt_idpetugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                        .addComponent(jLabel64)
                        .addComponent(jLabel62))
                    .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator21)
                        .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67))
                    .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator22)
                        .addComponent(txt_nama_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addGap(2, 2, 2)
                .addComponent(txt_idpetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addGap(2, 2, 2)
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel67)
                .addGap(2, 2, 2)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel68)
                .addGap(2, 2, 2)
                .addComponent(txt_nama_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_level_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_refresh_petugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btn_save_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btn_edit_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_refresh_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_judul_kelas3.setBackground(new java.awt.Color(153, 204, 255));
        panel_judul_kelas3.setRoundBottomLeft(30);
        panel_judul_kelas3.setRoundBottomRight(30);
        panel_judul_kelas3.setRoundTopLeft(30);
        panel_judul_kelas3.setRoundTopRight(30);

        jLabel65.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Data Petugas");

        javax.swing.GroupLayout panel_judul_kelas3Layout = new javax.swing.GroupLayout(panel_judul_kelas3);
        panel_judul_kelas3.setLayout(panel_judul_kelas3Layout);
        panel_judul_kelas3Layout.setHorizontalGroup(
            panel_judul_kelas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );
        panel_judul_kelas3Layout.setVerticalGroup(
            panel_judul_kelas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_judul_kelas3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/office-worker-100px-biru.png"))); // NOI18N

        btn_search2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loupe.png"))); // NOI18N
        btn_search2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_search2MouseClicked(evt);
            }
        });

        txt_search_petugas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_search_petugas.setBorder(null);
        txt_search_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_search_petugasActionPerformed(evt);
            }
        });
        txt_search_petugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_petugasKeyReleased(evt);
            }
        });

        jSeparator20.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator20.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane8.setBorder(null);

        Table_Petugas.setAutoCreateRowSorter(true);
        Table_Petugas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Table_Petugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Petugas", "Username", "Password", "Nama Petugas", "Level"
            }
        ));
        Table_Petugas.setGridColor(new java.awt.Color(255, 255, 255));
        Table_Petugas.setIntercellSpacing(new java.awt.Dimension(5, 5));
        Table_Petugas.setRowHeight(25);
        Table_Petugas.setSelectionBackground(new java.awt.Color(16, 181, 232));
        Table_Petugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_PetugasMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(Table_Petugas);

        javax.swing.GroupLayout Panel_PetugasLayout = new javax.swing.GroupLayout(Panel_Petugas);
        Panel_Petugas.setLayout(Panel_PetugasLayout);
        Panel_PetugasLayout.setHorizontalGroup(
            Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PetugasLayout.createSequentialGroup()
                .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_PetugasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_judul_kelas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PetugasLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PetugasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PetugasLayout.createSequentialGroup()
                                .addComponent(btn_search2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PetugasLayout.createSequentialGroup()
                                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))))))
        );
        Panel_PetugasLayout.setVerticalGroup(
            Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PetugasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_judul_kelas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_search2)
                    .addComponent(txt_search_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_PetugasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PetugasLayout.createSequentialGroup()
                        .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Utama.add(Panel_Petugas, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Utama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Side, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_homeMouseClicked
        //remove all panel
        dashboard_data_petugas();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Dashboard);
        Utama.repaint();
        Utama.revalidate();
        
        //color tab
        tab_home.setBackground(Color.white);
        tab_kelas.setBackground(new Color(204,204,255));
        tab_siswa.setBackground(new Color(204,204,255));
        tab_cek_pembayaran.setBackground(new Color(204,204,255));
        tab_pembayaran.setBackground(new Color(204,204,255));
        tab_detail_pembayaran.setBackground(new Color(204,204,255));
        tab_petugas.setBackground(new Color(204,204,255));
        
    }//GEN-LAST:event_tab_homeMouseClicked

    private void tab_kelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_kelasMouseClicked
        tabmode_kelas();
        tampilkandata_kelas();
        //remove all panel
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Kelas);
        Utama.repaint();
        Utama.revalidate();
        
        //color tab
        tab_kelas.setBackground(Color.white);
        tab_home.setBackground(new Color(204,204,255));
        tab_siswa.setBackground(new Color(204,204,255));
        tab_cek_pembayaran.setBackground(new Color(204,204,255));
        tab_pembayaran.setBackground(new Color(204,204,255));
        tab_detail_pembayaran.setBackground(new Color(204,204,255));
        tab_petugas.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_tab_kelasMouseClicked

    private void tab_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_siswaMouseClicked
        // TODO add your handling code here:
        tabmode_siswa();
        tampilkandata_siswa();
        tablemodelsiswa();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Siswa);
        Utama.repaint();
        Utama.revalidate();
        
        //color tab
        tab_siswa.setBackground(Color.white);
        tab_home.setBackground(new Color(204,204,255));
        tab_kelas.setBackground(new Color(204,204,255));
        tab_cek_pembayaran.setBackground(new Color(204,204,255));
        tab_pembayaran.setBackground(new Color(204,204,255));
        tab_detail_pembayaran.setBackground(new Color(204,204,255));
        tab_petugas.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_tab_siswaMouseClicked

    private void tab_cek_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_cek_pembayaranMouseClicked
        // TODO add your handling code here:
        tabmode_table_check_pembayaran_petugas();
        tabmode_siswa_sudah_bayar_petugas();
        tampil_siswa_sudah_bayar();
        table_model_sudah_bayar();
        tabmode_siswa_belum_bayar_petugas();
        table_model_belum_bayar();
        tampil_siswa_belum_bayar();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Cek_Pembayaran);
        Utama.repaint();
        Utama.revalidate();

        //color tab
        tab_cek_pembayaran.setBackground(Color.white);
        tab_home.setBackground(new Color(204, 204, 255));
        tab_kelas.setBackground(new Color(204, 204, 255));
        tab_siswa.setBackground(new Color(204, 204, 255));
        tab_pembayaran.setBackground(new Color(204, 204, 255));
        tab_detail_pembayaran.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_tab_cek_pembayaranMouseClicked

    private void tab_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_pembayaranMouseClicked
        // TODO add your handling code here:
        
        try {
            String sql2 = "Select MAX(id_pembayaran) as max_id_pembayaran from tb_pembayaran";
            Statement stat2 = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs2 = stat2.executeQuery(sql2);
            if(rs2.next()){
                int data = rs2.getInt("max_id_pembayaran");
                data++;
                txt_idpembayaran.setText("000"+String.valueOf(data));
            }
        } catch (Exception e) {
        }
        
        tabmode_table_check_pembayaran_petugas_pembayaran();
        tanggal_sekarang_pembayaran();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Pembayaran);
        Utama.repaint();
        Utama.revalidate();

        //color tab
        tab_pembayaran.setBackground(Color.white);
        tab_home.setBackground(new Color(204, 204, 255));
        tab_kelas.setBackground(new Color(204, 204, 255));
        tab_siswa.setBackground(new Color(204, 204, 255));
        tab_detail_pembayaran.setBackground(new Color(204, 204, 255));
        tab_cek_pembayaran.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_tab_pembayaranMouseClicked

    private void tab_detail_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_detail_pembayaranMouseClicked
        // TODO add your handling code here:
        tabmode_detail_pembayaran();
        table_model_detail_pembayaran();
        tampilkan_data_detail_pembayaran();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Detail_Pembayaran);
        Utama.repaint();
        Utama.revalidate();

        //color tab
        tab_detail_pembayaran.setBackground(Color.white);
        tab_home.setBackground(new Color(204, 204, 255));
        tab_kelas.setBackground(new Color(204, 204, 255));
        tab_siswa.setBackground(new Color(204, 204, 255));
        tab_pembayaran.setBackground(new Color(204, 204, 255));
        tab_cek_pembayaran.setBackground(new Color(204, 204, 255));
        tab_petugas.setBackground(new Color(204, 204, 255));
        
    }//GEN-LAST:event_tab_detail_pembayaranMouseClicked

    private void txt_nm_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nm_kelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nm_kelasActionPerformed

    private void btn_save_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_kelasActionPerformed
        // TODO add your handling code here:
        save_kelas();
    }//GEN-LAST:event_btn_save_kelasActionPerformed

    private void btn_delete_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_kelasActionPerformed
        // TODO add your handling code here:
        delete_data_kelas();
    }//GEN-LAST:event_btn_delete_kelasActionPerformed

    private void btn_edit_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_kelasActionPerformed
        // TODO add your handling code here:
        editdata_kelas();
    }//GEN-LAST:event_btn_edit_kelasActionPerformed

    private void btn_refresh_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refresh_kelasActionPerformed
        // TODO add your handling code here:
        resetText_kelas();
    }//GEN-LAST:event_btn_refresh_kelasActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        hapustabel();
        String key = txt_search.getText();
        try {
            String sql = "Select * From tb_kelas WHERE id_kelas LIKE '%"+key+"%' OR nama_kelas LIKE '"+key+"' OR komp_keahlian LIKE '%"+key+"%'";
            Connection conn = (Connection)koneksi.koneksiDB();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String[] data = {
                    rs.getString("id_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getString("komp_keahlian")
                };
                tabmode.addRow(data);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void Table_KelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_KelasMouseClicked
        // TODO add your handling code here:
        table_mouse_klik_kelas();
    }//GEN-LAST:event_Table_KelasMouseClicked

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        // TODO add your handling code here:
        search_data_kelas();
    }//GEN-LAST:event_btn_searchMouseClicked

    private void Table_SiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_SiswaMouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_Table_SiswaMouseClicked

    private void btn_insert_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insert_siswaActionPerformed
        // TODO add your handling code here:
        new Input_data_siswa().setVisible(true);
    }//GEN-LAST:event_btn_insert_siswaActionPerformed

    private void btn_edit_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_siswaActionPerformed
        // TODO add your handling code here:
        try {
            int row = Table_Siswa.getSelectedRow();
            Edit_data_siswa edit = new Edit_data_siswa();
            edit.nisn = Table_Siswa.getValueAt(row, 0).toString();
            edit.nis = Table_Siswa.getValueAt(row, 1).toString();
            edit.nama = Table_Siswa.getValueAt(row, 2).toString();
            edit.idkelas = Table_Siswa.getValueAt(row, 3).toString();
            edit.nama_kelas = Table_Siswa.getValueAt(row, 5).toString();
            edit.alamat = Table_Siswa.getValueAt(row, 6).toString();
            edit.notelp = Table_Siswa.getValueAt(row, 7).toString();
            edit.idspp = Table_Siswa.getValueAt(row, 8).toString();
            edit.TransferData();
            edit.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Edit Data Siswa " + e);
        }
        
        
    }//GEN-LAST:event_btn_edit_siswaActionPerformed

    private void btn_delete_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_siswaActionPerformed
        // TODO add your handling code here:
        delete_data_siswa();
    }//GEN-LAST:event_btn_delete_siswaActionPerformed

    private void btn_search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_search1MouseClicked
        // TODO add your handling code here:
        hapustabel();
        String key = txt_search_siswa.getText();
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            String sql = "Select tb_siswa.*, tb_kelas.id_kelas,komp_keahlian from tb_siswa join tb_kelas "
                    + "on tb_kelas.id_kelas = tb_siswa.id_kelas WHERE nama "
                    + "LIKE '%" + key + "%' OR nisn LIKE '%" + key + "%' OR komp_keahlian LIKE '%"+ key +"%' OR alamat LIKE '%"+ key +"%' OR tb_siswa.nama_kelas LIKE'"+key+"'";
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("nisn"),
                    set.getString("nis"),
                    set.getString("nama"),
                    set.getString("id_kelas"),
                    set.getString("komp_keahlian"),
                    set.getString("nama_kelas"),
                    set.getString("alamat"),
                    set.getString("no_telp"),
                    set.getString("id_spp")};
                tabmode.addRow(data);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Text Search Key Release Siswa "+e);
        }
    }//GEN-LAST:event_btn_search1MouseClicked

    private void txt_search_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_siswaActionPerformed

    private void txt_search_siswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_siswaKeyReleased
        // TODO add your handling code here:
        hapustabel();
        String key = txt_search_siswa.getText();
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            String sql = "Select tb_siswa.*, tb_kelas.id_kelas,komp_keahlian from tb_siswa join tb_kelas "
                    + "on tb_kelas.id_kelas = tb_siswa.id_kelas WHERE nama "
                    + "LIKE '%" + key + "%' OR nisn LIKE '%" + key + "%' OR komp_keahlian LIKE '%"+ key +"%' OR alamat LIKE '%"+ key +"%' OR tb_siswa.nama_kelas LIKE'"+key+"'";
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("nisn"),
                    set.getString("nis"),
                    set.getString("nama"),
                    set.getString("id_kelas"),
                    set.getString("komp_keahlian"),
                    set.getString("nama_kelas"),
                    set.getString("alamat"),
                    set.getString("no_telp"),
                    set.getString("id_spp")};
                tabmode.addRow(data);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Text Search Key Release Siswa "+e);
        }
    }//GEN-LAST:event_txt_search_siswaKeyReleased

    private void refresh_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_siswaMouseClicked
        // TODO add your handling code here:
        hapustabel();
        txt_search_siswa.setText("");
        this.requestFocusInWindow(true);
        tampilkandata_siswa();
    }//GEN-LAST:event_refresh_siswaMouseClicked

    private void btn_cek_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cek_bayarActionPerformed
        try {
            String sql = "Select * from cek_pembayaran where nisn='" + txt_nisn_cek_pembayaran.getText() + "'";
            Statement stat = (Statement) koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                status = rs.getString("status_pembayaran");
                if (status.equals("Sudah Lunas")) {
                    int pesan = JOptionPane.showConfirmDialog(null, "Anda Sudah Melakukan Pembayaran. \n Apakah Anda Ingin Melakukan Pembayaran Lagi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (pesan == 0) {
                        this.nisn = txt_nisn_cek_pembayaran.getText();
                        Utama.removeAll();
                        Utama.repaint();
                        Utama.revalidate();

                        //add new panel
                        Utama.add(Panel_Pembayaran);
                        Utama.repaint();
                        Utama.revalidate();
                        tab_pembayaran.setBackground(Color.white);
                        tab_home.setBackground(new Color(204, 204, 255));
                        tab_kelas.setBackground(new Color(204, 204, 255));
                        tab_siswa.setBackground(new Color(204, 204, 255));
                        tab_detail_pembayaran.setBackground(new Color(204, 204, 255));
                        tab_cek_pembayaran.setBackground(new Color(204, 204, 255));
                        input_data_pembayaran();
                        tabmode_table_check_pembayaran_petugas_pembayaran();
                    }
                } else {
                    int pesan = JOptionPane.showConfirmDialog(null, "Anda Belum Melakukan Pembayaran. \n Apakah Anda Ingin Melakukan Pembayaran Sekarang?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (pesan == 0) {
                        Utama.removeAll();
                        Utama.repaint();
                        Utama.revalidate();

                        //add new panel
                        Utama.add(Panel_Pembayaran);
                        Utama.repaint();
                        Utama.revalidate();
                        tab_pembayaran.setBackground(Color.white);
                        tab_home.setBackground(new Color(204, 204, 255));
                        tab_kelas.setBackground(new Color(204, 204, 255));
                        tab_siswa.setBackground(new Color(204, 204, 255));
                        tab_detail_pembayaran.setBackground(new Color(204, 204, 255));
                        tab_cek_pembayaran.setBackground(new Color(204, 204, 255));
                        this.nisn = txt_nisn_cek_pembayaran.getText();
                        input_data_pembayaran();
                        tabmode_table_check_pembayaran_petugas_pembayaran();
                    }
                }
            }
            hapustabel();
            txt_cek_bayar.setText("");
            txt_nisn_cek_pembayaran.setText("");
        } catch (Exception e) {
        }
        txt_cek_bayar.setText("");
        txt_nisn_cek_pembayaran.setText("");
    }//GEN-LAST:event_btn_cek_bayarActionPerformed

    private void Table_cekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_cekMouseClicked
        // TODO add your handling code here:
        try {
            int row = Table_cek.getSelectedRow();
            String table_klik = (Table_cek.getModel().getValueAt(row, 0).toString());
            txt_nisn_cek_pembayaran.setText(table_klik);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Table_cekMouseClicked

    private void btn_search_cek_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_search_cek_pembayaranMouseClicked
        // TODO add your handling code here:
        hapustabel();
        try {
            String sql = "Select * from cek_pembayaran where nama LIKE'%"+txt_cek_bayar.getText()+"%'";
            Statement stat = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String[] data ={
                    rs.getString("nisn"),
                    rs.getString("status_pembayaran"),
                    rs.getString("nama"),
                    rs.getString("no_telp"),
                };
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Btn Search Cek Pembayaran"+ e);
        }
        
    }//GEN-LAST:event_btn_search_cek_pembayaranMouseClicked

    private void refresh_cek_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_cek_bayarMouseClicked
        // TODO add your handling code here:
        hapustabel();
        hapustabel_cekbayar();
    }//GEN-LAST:event_refresh_cek_bayarMouseClicked

    private void txt_cek_bayar_pmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cek_bayar_pmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cek_bayar_pmbActionPerformed

    private void btn_search_cek_pembayaran_pmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_search_cek_pembayaran_pmbMouseClicked
        // TODO add your handling code here:
        hapustabel();
        try {
            String sql = "Select * from cek_pembayaran where nama LIKE'%"+txt_cek_bayar_pmb.getText()+"%'";
            Statement stat = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String[] data ={
                    rs.getString("nisn"),
                    rs.getString("status_pembayaran"),
                    rs.getString("nama"),
                    rs.getString("no_telp"),
                };
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Btn Search Pembayaran"+ e);
        }
    }//GEN-LAST:event_btn_search_cek_pembayaran_pmbMouseClicked

    private void refresh_cek_bayar_pmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_cek_bayar_pmbMouseClicked
        // TODO add your handling code here:
        hapustabel_pembayaran();
    }//GEN-LAST:event_refresh_cek_bayar_pmbMouseClicked

    private void Table_cek_pmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_cek_pmbMouseClicked
        // TODO add your handling code here:
        Calendar calendar = Calendar.getInstance();
        Date tanggalsekarang = calendar.getTime();
        
        try {
        int row = Table_cek_pmb.getSelectedRow();
        String table_klik = (Table_cek_pmb.getModel().getValueAt(row, 0).toString());
        String sql = "Select * from cek_pembayaran join tb_siswa on cek_pembayaran.nisn = tb_siswa.nisn where cek_pembayaran.nisn='"+table_klik+"'";
        Statement stat = (Statement)koneksi.koneksiDB().createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if(rs.next()){
            String add1 = rs.getString("status_pembayaran");
            txt_status.setText(add1);
            String add2 = rs.getString("nisn");
            txt_nisn.setText(add2);
            String add3 = rs.getString("nama");
            txt_nama_siswa_pmb.setText(add3);
            String add4 = rs.getString("id_kelas");
            txt_idkelas.setText(add4);
            String add5 = rs.getString("nama_kelas");
            txt_nama_kelas_pmb.setText(add5);
            Date add6 = tanggalsekarang;
            choose_tgl_bayar_pmb.setDate(add6);
            Date add7 = rs.getDate("tgl_terakhir_bayar");
            choose_tgl_akhir_pmb.setDate(add7);
            Date add8 = rs.getDate("tgl_sekarang");
            choose_batas_pmb.setDate(add8);
            String add9 = rs.getString("id_spp");
            txt_id_spp_pmb.setText(add9);
            
                    
            
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Mouse Click Table Pembayaran" + e );
        }
    }//GEN-LAST:event_Table_cek_pmbMouseClicked

    private void txt_nama_siswa_pmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_siswa_pmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_siswa_pmbActionPerformed

    private void txt_nama_kelas_pmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_kelas_pmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_kelas_pmbActionPerformed

    private void btn_bayar_pmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayar_pmbActionPerformed
        // TODO add your handling code here:
        simple_tanggal_pembayaran();
        Calendar calendar = Calendar.getInstance();
        Date tanggalsekarang = calendar.getTime();

        String status_bayar = "";
        String tanggal1 = "2023-05-01";
        Date tanggal2 = choose_tgl_akhir_pmb.getDate();
        int selisihBulan = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(tanggal1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date_tanggal_sekarang = String.valueOf(format.format(tanggalsekarang));
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
        if (selisihBulan >= 0) {
            status_bayar = "Sudah Lunas";
        } else {
            status_bayar = "Belum Lunas";
        }
        int nominal= Integer.valueOf(txt_nominal_bayar.getText());
        int uang_bayar= Integer.valueOf(txt_uang_bayar.getText());
        
            if (uang_bayar < nominal) {
                JOptionPane.showMessageDialog(null, "Uang Anda Kurang");
            }else{
            int hasil = uang_bayar - nominal;
            txt_uang_kembalian.setText(String.valueOf(hasil));
            txt_status.setText(status_bayar);
            }
 
        
    }//GEN-LAST:event_btn_bayar_pmbActionPerformed

    private void btn_simpan_pmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_pmbActionPerformed
        // TODO add your handling code here:
        resetText_pembayaran();
    }//GEN-LAST:event_btn_simpan_pmbActionPerformed

    private void btn_simpan_pmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_pmb1ActionPerformed
        // TODO add your handling code here:
        simple_tanggal_pembayaran();
        Calendar calendar = Calendar.getInstance();
        Date tanggalsekarang = calendar.getTime();

        String status_bayar = "";
        String tanggal1 = "2023-05-01";
        Date tanggal2 = choose_tgl_akhir_pmb.getDate();
        int selisihBulan = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(tanggal1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date_tanggal_sekarang = String.valueOf(format.format(tanggalsekarang));
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
        if (selisihBulan > 0) {
            status_bayar = "Sudah Lunas";
        } else {
            status_bayar = "Belum Lunas";
        }

        if (selisihBulan < 0) {
            selisihBulan = -selisihBulan;
        }
        String tanggal_akhir_bayar = String.valueOf(format.format(choose_tgl_akhir_pmb.getDate()));
        Date currentDate = choose_tgl_akhir_pmb.getDate();

        
        try {
            String sql = "Insert into tb_pembayaran values('" + txt_idpembayaran.getText()
                    + "','" + txt_status.getText()
                    + "','" + txt_nisn.getText()
                    + "','" + date_tanggal_sekarang
                    + "','" + tanggal_akhir_bayar
                    + "','" + batas_tanggal
                    + "','" + selisihBulan
                    + "','" + txt_id_spp_pmb.getText()
                    + "','" + txt_nominal_bayar.getText()
                    + "','" + txt_uang_bayar.getText()
                    + "','" + txt_uang_kembalian.getText()
                    + "');";
            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            String sql2 = "Update cek_pembayaran set tgl_terakhir_bayar = '" + tanggal_akhir_bayar + "'"
                    + ", jumlah_bulan= '" + selisihBulan + "'"
                    + ", status_pembayaran= '" + txt_status.getText() + "'"
                    + " where nisn='" + txt_nisn.getText() + "';";
            PreparedStatement pst2 = conn.prepareStatement(sql2);
            pst2.executeUpdate();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER,
                    "Save Succesfull");
            panel.showNotification();
            
            resetText_pembayaran();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di BTN Save Pembayaran" + e);
        }
    }//GEN-LAST:event_btn_simpan_pmb1ActionPerformed

    private void cbx_jumlah_bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_jumlah_bulanActionPerformed
        // TODO add your handling code here:
        try {
            int bulan = cbx_jumlah_bulan.getSelectedIndex();
            bulan++;
            bulan = bulan * 100000;
            txt_nominal_bayar.setText(String.valueOf(bulan));
        } catch (Exception e) {
        }
        tambah_bulan_chooser();
    }//GEN-LAST:event_cbx_jumlah_bulanActionPerformed

    private void tab_petugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_petugasMouseClicked
        // TODO add your handling code here:
        tabmode_petugas();
        tampilkandata_formpetugas();
        Utama.removeAll();
        Utama.repaint();
        Utama.revalidate();

        //add new panel
        Utama.add(Panel_Petugas);
        Utama.repaint();
        Utama.revalidate();
        
        //color tab
        tab_petugas.setBackground(Color.white);
        tab_home.setBackground(new Color(204,204,255));
        tab_kelas.setBackground(new Color(204,204,255));
        tab_siswa.setBackground(new Color(204,204,255));
        tab_pembayaran.setBackground(new Color(204,204,255));
        tab_cek_pembayaran.setBackground(new Color(204,204,255));
        tab_detail_pembayaran.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_tab_petugasMouseClicked

    private void btn_save_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_petugasActionPerformed
        // TODO add your handling code here:
        save_petugas();
        
        
    }//GEN-LAST:event_btn_save_petugasActionPerformed

    private void btn_delete_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_petugasActionPerformed
        // TODO add your handling code here:
        delete_data_petugas();
    }//GEN-LAST:event_btn_delete_petugasActionPerformed

    private void btn_edit_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_petugasActionPerformed
        // TODO add your handling code here:
        editdata_petugas();
    }//GEN-LAST:event_btn_edit_petugasActionPerformed

    private void btn_refresh_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refresh_petugasActionPerformed
        // TODO add your handling code here:
        resetText_petugas();
    }//GEN-LAST:event_btn_refresh_petugasActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void btn_search2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_search2MouseClicked
        // TODO add your handling code here:
        search_data_petugas();
    }//GEN-LAST:event_btn_search2MouseClicked

    private void txt_search_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_search_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_search_petugasActionPerformed

    private void txt_search_petugasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_petugasKeyReleased
        // TODO add your handling code here:
        search_data_petugas();
    }//GEN-LAST:event_txt_search_petugasKeyReleased

    private void Table_PetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_PetugasMouseClicked
        // TODO add your handling code here:
        table_mouse_klik_petugas();
    }//GEN-LAST:event_Table_PetugasMouseClicked

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_nama_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_petugasActionPerformed

    private void btn_search_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_search_detailMouseClicked
        // TODO add your handling code here:
        hapustabel();
        String key = txt_search_detail_pmb.getText();
        try {
            String sql = "Select tb_pembayaran.*, tb_siswa.nama from tb_pembayaran join tb_siswa on tb_pembayaran.nisn = tb_siswa.nisn where id_pembayaran "
                    + "                                         LIKE'%"+key+"%' OR tb_pembayaran.nisn LIKE '%"+key+"%' OR nama LIKE'%"+key+"%' OR status LIKE'%"+key+"%'";
            Statement stat = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String[] data ={
                    rs.getString("id_pembayaran"),
                    rs.getString("status"),
                    rs.getString("nisn"),
                    rs.getString("nama"),
                    rs.getString("tgl_bayar"),
                    rs.getString("tgl_terakhir_bayar"),
                    rs.getString("batas_pembayaran"),
                    rs.getString("jumlah_bulan"),
                    rs.getString("id_spp"),
                    rs.getString("nominal_bayar"),
                    rs.getString("jumlah_bayar"),
                    rs.getString("kembalian"),
                    
                };
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Btn Search Detail Pembayaran"+ e);
        }
    }//GEN-LAST:event_btn_search_detailMouseClicked

    private void refresh_detail_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh_detail_bayarMouseClicked
        // TODO add your handling code here:
        txt_search_detail_pmb.setText("");
        hapustabel();
        tampilkan_data_detail_pembayaran();
    }//GEN-LAST:event_refresh_detail_bayarMouseClicked
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Panel_Cek_Pembayaran;
    private javax.swing.JPanel Panel_Detail_Pembayaran;
    private javax.swing.JPanel Panel_Kelas;
    private javax.swing.JPanel Panel_Pembayaran;
    private javax.swing.JPanel Panel_Petugas;
    private javax.swing.JPanel Panel_Siswa;
    private javax.swing.JPanel Side;
    private javax.swing.JTable Table_Kelas;
    private javax.swing.JTable Table_Petugas;
    public javax.swing.JTable Table_Siswa;
    private javax.swing.JTable Table_belum_bayar;
    private javax.swing.JTable Table_cek;
    private javax.swing.JTable Table_cek_pmb;
    private javax.swing.JTable Table_detail_pembayaran;
    private javax.swing.JTable Table_sudah_bayar;
    private javax.swing.JPanel Utama;
    private rojerusan.RSMaterialButtonRectangle btn_bayar_pmb;
    private rojerusan.RSMaterialButtonRectangle btn_cek_bayar;
    private rojerusan.RSMaterialButtonRectangle btn_delete_kelas;
    private rojerusan.RSMaterialButtonRectangle btn_delete_petugas;
    private rojerusan.RSMaterialButtonRectangle btn_delete_siswa;
    private rojerusan.RSMaterialButtonRectangle btn_edit_kelas;
    private rojerusan.RSMaterialButtonRectangle btn_edit_petugas;
    private rojerusan.RSMaterialButtonRectangle btn_edit_siswa;
    private rojerusan.RSMaterialButtonRectangle btn_insert_siswa;
    private rojerusan.RSMaterialButtonRectangle btn_refresh_kelas;
    private rojerusan.RSMaterialButtonRectangle btn_refresh_petugas;
    private rojerusan.RSMaterialButtonRectangle btn_save_kelas;
    private rojerusan.RSMaterialButtonRectangle btn_save_petugas;
    private javax.swing.JLabel btn_search;
    private javax.swing.JLabel btn_search1;
    private javax.swing.JLabel btn_search2;
    private javax.swing.JLabel btn_search_cek_pembayaran;
    private javax.swing.JLabel btn_search_cek_pembayaran_pmb;
    private javax.swing.JLabel btn_search_detail;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_pmb;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_pmb1;
    private combobox.Combobox cbx_jumlah_bulan;
    private combobox.Combobox cbx_komp_keahlian;
    private combobox.Combobox cbx_level_petugas;
    private com.toedter.calendar.JDateChooser choose_batas_pmb;
    private com.toedter.calendar.JDateChooser choose_tgl_akhir_pmb;
    private com.toedter.calendar.JDateChooser choose_tgl_bayar_pmb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JLabel judul;
    private javax.swing.JLabel label_belum_bayar_petugas;
    private javax.swing.JLabel label_sudah_bayar_petugas;
    private Panel_Round.PanelRound panelRound1;
    private Panel_Round.PanelRound panelRound2;
    private Panel_Round.PanelRound panelRound3;
    private Panel_Round.PanelRound panelRound4;
    private Panel_Round.PanelRound panelRound5;
    private Panel_Round.PanelRound panel_data;
    private Panel_Round.PanelRound panel_data2;
    private Panel_Round.PanelRound panel_judul_kelas;
    private Panel_Round.PanelRound panel_judul_kelas1;
    private Panel_Round.PanelRound panel_judul_kelas2;
    private Panel_Round.PanelRound panel_judul_kelas3;
    private javax.swing.JLabel refresh_cek_bayar;
    private javax.swing.JLabel refresh_cek_bayar_pmb;
    private javax.swing.JLabel refresh_detail_bayar;
    private javax.swing.JLabel refresh_siswa;
    private Panel_Round.PanelRound tab_cek_pembayaran;
    private Panel_Round.PanelRound tab_detail_pembayaran;
    private Panel_Round.PanelRound tab_home;
    private Panel_Round.PanelRound tab_kelas;
    private Panel_Round.PanelRound tab_pembayaran;
    private Panel_Round.PanelRound tab_petugas;
    private Panel_Round.PanelRound tab_siswa;
    private javax.swing.JTextField txt_cek_bayar;
    private javax.swing.JTextField txt_cek_bayar_pmb;
    private javax.swing.JTextField txt_id_kelas;
    private javax.swing.JTextField txt_id_spp_pmb;
    private javax.swing.JTextField txt_idkelas;
    private javax.swing.JTextField txt_idpembayaran;
    private javax.swing.JTextField txt_idpetugas;
    private javax.swing.JTextField txt_nama_kelas_pmb;
    private javax.swing.JTextField txt_nama_petugas;
    private javax.swing.JTextField txt_nama_siswa_pmb;
    private javax.swing.JTextField txt_nisn;
    private javax.swing.JTextField txt_nisn_cek_pembayaran;
    private javax.swing.JTextField txt_nm_kelas;
    private javax.swing.JTextField txt_nominal_bayar;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_search_detail_pmb;
    private javax.swing.JTextField txt_search_petugas;
    private javax.swing.JTextField txt_search_siswa;
    private javax.swing.JTextField txt_status;
    private javax.swing.JTextField txt_uang_bayar;
    private javax.swing.JTextField txt_uang_kembalian;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
    
    private void maxi(){
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    
    //function Panel Kelas
    //    
    //    
    //    
    //  
    
    private void tabmode_kelas(){
        Object[] row = {"Id Kelas", "Nama Kelas", "Kompetensi Keahlian"};
        tabmode = new DefaultTableModel(null, row);
        Table_Kelas.setModel(tabmode);
    }
    
    public void tampilkandata_kelas() {
        try {
            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            String sql = "Select * from tb_kelas";
            java.sql.Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("id_kelas"),
                    set.getString("nama_kelas"),
                    set.getString("komp_keahlian")};
                tabmode.addRow(data);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Tampilkan Data , Detail " + e);
        }
    }
    
    private void save_kelas() {
        hapustabel();
        try {
            String sql = "Insert into tb_kelas values('" + txt_id_kelas.getText()
                    + "','" + txt_nm_kelas.getText()
                    + "','" + cbx_komp_keahlian.getSelectedItem()+ "');";
            java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(new Form_Menu_Admin(null), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Save Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(new Form_Menu_Admin(null), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Save Data"+ e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_kelas();
    }

    private void editdata_kelas() {
        hapustabel();
        try {
            String sql = "update tb_kelas set nama_kelas='" + txt_nm_kelas.getText()
                    + "',komp_keahlian='" + cbx_komp_keahlian.getSelectedItem()
                    + "' WHERE id_kelas='" + txt_id_kelas.getText() + "'";

            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Edit Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Edit Data "+e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_kelas();
    }
    
    public void delete_data_kelas(){
        hapustabel();

        try{
            String sql = "DELETE FROM tb_kelas WHERE id_kelas = '"+txt_id_kelas.getText()+"'";

            java.sql.Connection conn=(java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Delete Succesfull");
            panel.showNotification();
        }
        catch (Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Delete Data "+e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_kelas();

    }
    
    public void search_data_kelas(){
       hapustabel();
      String key = txt_search.getText();
       try{
            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            String sql = "Select * From tb_kelas WHERE id_kelas LIKE '%"+key+"%' OR nama_kelas LIKE '"+key+"' OR komp_keahlian LIKE '%"+key+"%'";
            java.sql.Statement stat=conn.createStatement();
            ResultSet set=stat.executeQuery(sql);
            while (set.next()){
                String[] data={set.getString("id_kelas"),
                            set.getString("nama_kelas"),
                            set.getString("komp_keahlian")};
                tabmode.addRow(data);
                }

        }catch(Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Search Data "+e);
            panel.showNotification();
        }
    }
    
    private void table_mouse_klik_kelas(){
        try{
            txt_id_kelas.setEditable(false);
            int row = Table_Kelas.getSelectedRow();
            String table_klik=(Table_Kelas.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn=(java.sql.Connection)koneksi.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery
            ("select * from tb_kelas where id_kelas='"+table_klik+"'");
            if(sql.next()){
                String add1 = sql.getString("id_kelas");
                txt_id_kelas.setText(add1);
                String add2 = sql.getString("nama_kelas");
                txt_nm_kelas.setText(add2);
                String add3 = sql.getString("komp_keahlian");
                cbx_komp_keahlian.setSelectedItem(add3);
            }
        }catch (Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Table Mouse Click "+e);
            panel.showNotification();
        }
    }
    
    //function Panel Siswa
    //    
    //    
    //    
    //  
    
    public void tabmode_siswa(){
        Object[] row = {"NISN", "NIS", "Nama", "Id Kelas", "Komp Keahlian", "Nama Kelas", "Alamat", "No Telpon", "Id SPP"};
        tabmode = new DefaultTableModel(null, row);
        Table_Siswa.setModel(tabmode);
    }
    
    public void tampilkandata_siswa() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            String sql = "Select tb_siswa.*, tb_kelas.id_kelas,komp_keahlian from tb_siswa join tb_kelas on tb_kelas.id_kelas = tb_siswa.id_kelas";
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("nisn"),
                    set.getString("nis"),
                    set.getString("nama"),
                    set.getString("id_kelas"),
                    set.getString("komp_keahlian"),
                    set.getString("nama_kelas"),
                    set.getString("alamat"),
                    set.getString("no_telp"),
                    set.getString("id_spp")};

                tabmode.addRow(data);
                Table_Siswa.setModel(tabmode);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Tampilkan Data "+e);
        }
    }
    
    public void tablemodelsiswa(){
        TableColumn column;
        Table_Siswa.getColumnModel().getColumn(0).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(1).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(2).setPreferredWidth(200);
        Table_Siswa.getColumnModel().getColumn(3).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(4).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(5).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(6).setPreferredWidth(200);
        Table_Siswa.getColumnModel().getColumn(7).setPreferredWidth(40);
        Table_Siswa.getColumnModel().getColumn(8).setPreferredWidth(40);
    }
    
    public void delete_data_siswa(){
        int pesan = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (pesan == 0) {
            int row = Table_Siswa.getSelectedRow();
            String nisn = Table_Siswa.getValueAt(row, 0).toString();
            hapustabel();

        try {
            String sql = "DELETE FROM tb_siswa WHERE nisn = '" + nisn + "'";

            Connection conn = (Connection) koneksi.koneksiDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER,
                    "Delete Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER,
                    "Ada Kesalahan Di Delete Data"+ e);
            panel.showNotification();
        }
        tampilkandata_siswa();
        txt_search.setText("");
        }
        
    }
    public void tabmode_table_check_pembayaran_petugas(){
        Object[] row = {"NISN", "Status Pembayaran", "Nama Siswa", "No Telpon"};
        tabmode = new DefaultTableModel(null, row);
        Table_cek.setModel(tabmode);
    }
    public void tabmode_table_check_pembayaran_petugas_pembayaran(){
        Object[] row = {"NISN", "Status Pembayaran", "Nama Siswa", "No Telpon"};
        tabmode = new DefaultTableModel(null, row);
        Table_cek_pmb.setModel(tabmode);
    }
    public void tabmode_siswa_sudah_bayar_petugas(){
        Object[] row = {"NISN", "Tgl Terakhir Bayar", "Batas Pembayaran SPP", "Status Pembayaran", "Jumlah Bulan", "Nama Siswa", "No Telpon"};
        tabmode_sudah_bayar = new DefaultTableModel(null, row);
        Table_sudah_bayar.setModel(tabmode_sudah_bayar);
    }
    public void tabmode_siswa_belum_bayar_petugas(){
        Object[] row = {"NISN", "Tgl Terakhir Bayar", "Batas Pembayaran SPP", "Status Pembayaran", "Jumlah Bulan", "Nama Siswa", "No Telpon"};
        tabmode_belum_bayar  = new DefaultTableModel(null, row);
        Table_belum_bayar.setModel(tabmode_belum_bayar);
    }
    
    public void tampil_siswa_sudah_bayar(){
        try {
            String sql = "Select * from cek_pembayaran where status_pembayaran ='Sudah Lunas'";
        Statement stat = (Statement)koneksi.koneksiDB().createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            String[] data = {
                rs.getString("nisn"),
                rs.getString("tgl_terakhir_bayar"),
                rs.getString("tgl_sekarang"),
                rs.getString("status_pembayaran"),
                rs.getString("jumlah_bulan"),
                rs.getString("nama"),
                rs.getString("no_telp")
            };
        tabmode_sudah_bayar.addRow(data);
        }
        } catch (Exception e) {
        }
        
    }
    public void table_model_sudah_bayar(){
        Table_sudah_bayar.getColumnModel().getColumn(0).setPreferredWidth(15);
        Table_sudah_bayar.getColumnModel().getColumn(1).setPreferredWidth(40);
        Table_sudah_bayar.getColumnModel().getColumn(2).setPreferredWidth(60);
        Table_sudah_bayar.getColumnModel().getColumn(3).setPreferredWidth(40);
        Table_sudah_bayar.getColumnModel().getColumn(4).setPreferredWidth(20);
        Table_sudah_bayar.getColumnModel().getColumn(5).setPreferredWidth(120);
        Table_sudah_bayar.getColumnModel().getColumn(6).setPreferredWidth(60);
    }
    
    public void table_model_belum_bayar(){
        Table_belum_bayar.getColumnModel().getColumn(0).setPreferredWidth(15);
        Table_belum_bayar.getColumnModel().getColumn(1).setPreferredWidth(40);
        Table_belum_bayar.getColumnModel().getColumn(2).setPreferredWidth(63);
        Table_belum_bayar.getColumnModel().getColumn(3).setPreferredWidth(43);
        Table_belum_bayar.getColumnModel().getColumn(4).setPreferredWidth(20);
        Table_belum_bayar.getColumnModel().getColumn(5).setPreferredWidth(120);
        Table_belum_bayar.getColumnModel().getColumn(6).setPreferredWidth(55);
    }
    
    public void table_model_detail_pembayaran() {
        Table_detail_pembayaran.getColumnModel().getColumn(0).setPreferredWidth(15);
        Table_detail_pembayaran.getColumnModel().getColumn(1).setPreferredWidth(40);
        Table_detail_pembayaran.getColumnModel().getColumn(2).setPreferredWidth(63);
        Table_detail_pembayaran.getColumnModel().getColumn(3).setPreferredWidth(200);
        Table_detail_pembayaran.getColumnModel().getColumn(4).setPreferredWidth(60);
        Table_detail_pembayaran.getColumnModel().getColumn(5).setPreferredWidth(110);
        Table_detail_pembayaran.getColumnModel().getColumn(6).setPreferredWidth(55);
        Table_detail_pembayaran.getColumnModel().getColumn(7).setPreferredWidth(55);
        Table_detail_pembayaran.getColumnModel().getColumn(8).setPreferredWidth(55);
        Table_detail_pembayaran.getColumnModel().getColumn(9).setPreferredWidth(55);
        Table_detail_pembayaran.getColumnModel().getColumn(10).setPreferredWidth(55);
        Table_detail_pembayaran.getColumnModel().getColumn(11).setPreferredWidth(55);
    }
    public void tampil_siswa_belum_bayar(){
        try {
            String sql = "Select * from cek_pembayaran where status_pembayaran ='Belum Lunas'";
        Statement stat = (Statement)koneksi.koneksiDB().createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            String[] data = {
                rs.getString("nisn"),
                rs.getString("tgl_terakhir_bayar"),
                rs.getString("tgl_sekarang"),
                rs.getString("status_pembayaran"),
                rs.getString("jumlah_bulan"),
                rs.getString("nama"),
                rs.getString("no_telp")
            };
        tabmode_belum_bayar.addRow(data);
        }
        } catch (Exception e) {
        }
        
    }
    
    public void input_data_pembayaran(){
        Calendar calendar = Calendar.getInstance();
        Date tanggalsekarang = calendar.getTime();
        try {
            String sql = "Select * from cek_pembayaran join tb_siswa on cek_pembayaran.nisn = tb_siswa.nisn where cek_pembayaran.nisn='" + this.nisn + "'";
            Statement stat = (Statement) koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String add1 = rs.getString("status_pembayaran");
                txt_status.setText(add1);
                String add2 = rs.getString("nisn");
                txt_nisn.setText(add2);
                String add3 = rs.getString("nama");
                txt_nama_siswa_pmb.setText(add3);
                String add4 = rs.getString("id_kelas");
                txt_idkelas.setText(add4);
                String add5 = rs.getString("nama_kelas");
                txt_nama_kelas_pmb.setText(add5);
                Date add6 = tanggalsekarang;
                choose_tgl_bayar_pmb.setDate(add6);
                Date add7 = rs.getDate("tgl_terakhir_bayar");
                choose_tgl_akhir_pmb.setDate(add7);
                Date add8 = rs.getDate("tgl_sekarang");
                choose_batas_pmb.setDate(add8);
                String add9 = rs.getString("id_spp");
                txt_id_spp_pmb.setText(add9);

            }
            String sql2 = "Select MAX(id_pembayaran) as max_id_pembayaran from tb_pembayaran";
            Statement stat2 = (Statement)koneksi.koneksiDB().createStatement();
            ResultSet rs2 = stat2.executeQuery(sql2);
            if(rs2.next()){
                int data = rs2.getInt("max_id_pembayaran");
                data++;
                txt_idpembayaran.setText("000"+String.valueOf(data));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Function Input Data Pembayaran");
        }
        
    }
    
    public void tanggal_sekarang_pembayaran(){
        Calendar cal = Calendar.getInstance();
        Date date1 = cal.getTime();
        choose_tgl_bayar_pmb.setDate(date1);
    }
    
    public void simple_tanggal_pembayaran(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tanggal_bayar = String.valueOf(format.format(choose_tgl_bayar_pmb.getDate()));
        tanggal_akhir_bayar = String.valueOf(format.format(choose_tgl_akhir_pmb.getDate()));
        batas_tanggal = String.valueOf(format.format(choose_batas_pmb.getDate()));
    }
    
    public void tabmode_detail_pembayaran() {
        Object[] row = {"Id Pembayaran", "Status", "Nisn", "Nama Siswa", "Tanggal Bayar", "Tanggal Pembayaran Terakhir", "Batas Pembayaran", "Jumlah Bulan", "Id SPP", "Nominal Bayar", "Jumlah Bayar", "Kembalian"};
        tabmode = new DefaultTableModel(null, row);
        Table_detail_pembayaran.setModel(tabmode);
    }

    public void tampilkan_data_detail_pembayaran() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            String sql = "Select tb_pembayaran.*, tb_siswa.nama from tb_pembayaran join tb_siswa on tb_pembayaran.nisn = tb_siswa.nisn";
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("id_pembayaran"),
                    set.getString("status"),
                    set.getString("nisn"),
                    set.getString("nama"),
                    set.getString("tgl_bayar"),
                    set.getString("tgl_terakhir_bayar"),
                    set.getString("batas_pembayaran"),
                    set.getString("jumlah_bulan"),
                    set.getString("id_spp"),
                    set.getString("nominal_bayar"),
                    set.getString("jumlah_bayar"),
                    set.getString("kembalian")};

                tabmode.addRow(data);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Tampilkan Data Detail Pembayaran "+e);
        }
    }
    
    private void tabmode_petugas(){
        Object[] row = {"Id Petugas", "Username", "Password","Nama Petugas","Level"};
        tabmode = new DefaultTableModel(null, row);
        Table_Petugas.setModel(tabmode);
    }
    
    public void tampilkandata_formpetugas() {
        try {
            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            String sql = "Select * from tb_petugas";
            java.sql.Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(sql);
            while (set.next()) {
                String[] data = {set.getString("id_petugas"),
                    set.getString("username"),
                    set.getString("password"),
                    set.getString("nama_petugas"),
                    set.getString("level")};
                tabmode.addRow(data);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan Di Tampilkan Data Petugas, Detail " + e);
        }
    }
    
    private void save_petugas() {
        hapustabel();
        try {
            String sql = "Insert into tb_petugas values('" + txt_idpetugas.getText()
                    + "','" + txt_username.getText()
                    + "','" + txt_password.getText()
                    + "','" + txt_nama_petugas.getText()
                    + "','" + cbx_level_petugas.getSelectedItem()+ "');";
            java.sql.Connection conn = (java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Save Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Save Data"+ e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_petugas();
    }

    private void editdata_petugas() {
        hapustabel();
        try {
            String sql = "update tb_petugas set username='" + txt_username.getText()
                    + "',password='" + txt_password.getText()
                    + "',nama_petugas='" + txt_password.getText()
                    + "',level='" + cbx_level_petugas.getSelectedItem()
                    + "' WHERE id_petugas='" + txt_idpetugas.getText() + "'";

            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Edit Succesfull");
            panel.showNotification();
        } catch (Exception e) {
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Edit Data "+e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_petugas();
    }
    
    public void delete_data_petugas(){
        hapustabel();

        try{
            String sql = "DELETE FROM tb_petugas WHERE id_petugas = '"+txt_idpetugas.getText()+"'";

            java.sql.Connection conn=(java.sql.Connection)koneksi.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Delete Succesfull");
            panel.showNotification();
        }
        catch (Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Delete Data "+e);
            panel.showNotification();
        }
        tampilkandata_kelas();
        resetText_petugas();

    }
    
    public void search_data_petugas(){
       hapustabel();
      String key = txt_search_petugas.getText();
       try{
            java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
            String sql = "Select * From tb_petugas WHERE id_petugas LIKE '%"+key+"%' OR nama_petugas LIKE '%"+key+"%' OR username LIKE '%"+key+"%' OR level LIKE '%"+key+"%'";
            java.sql.Statement stat=conn.createStatement();
            ResultSet set=stat.executeQuery(sql);
            while (set.next()){
                String[] data={set.getString("id_petugas"),
                            set.getString("username"),
                            set.getString("password"),
                            set.getString("nama_petugas"),
                            set.getString("level")};
                tabmode.addRow(data);
                }

        }catch(Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Search Data "+e);
            panel.showNotification();
        }
    }
    
    private void table_mouse_klik_petugas(){
        try{
            txt_idpetugas.setEditable(false);
            int row = Table_Petugas.getSelectedRow();
            String table_klik=(Table_Petugas.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn=(java.sql.Connection)koneksi.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery
            ("select * from tb_petugas where id_petugas='"+table_klik+"'");
            if(sql.next()){
                String add1 = sql.getString("id_petugas");
                txt_idpetugas.setText(add1);
                String add2 = sql.getString("username");
                txt_username.setText(add2);
                String add3 = sql.getString("password");
                txt_password.setText(add3);
                String add4 = sql.getString("nama_petugas");
                txt_nama_petugas.setText(add4);
                String add5 = sql.getString("level");
                cbx_level_petugas.setSelectedItem(add5);
            }
        }catch (Exception e){
            Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Ada Kesalahan Di Table Mouse Click "+e);
            panel.showNotification();
        }
    }
    public void resetText_petugas() {
        hapustabel();
        txt_idpetugas.setText("");
        txt_username.setText("");
        txt_password.setText("");
        txt_nama_petugas.setText("");
        cbx_level_petugas.setSelectedIndex(-1);
        txt_idpetugas.setEditable(true);
        tampilkandata_formpetugas();
    }
    
    public void tambah_bulan_chooser() {
        try {
            String sql = "Select * from cek_pembayaran where nisn='"+txt_nisn.getText()+"'";
            Statement stat = (Statement) koneksi.koneksiDB().createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {

                Date currentDate = rs.getDate("tgl_terakhir_bayar");
                Calendar calendar = Calendar.getInstance();
                // Buat objek Calendar
                calendar.setTime(currentDate);

                // Tambahkan bulan ke tanggal saat ini
                int jumlahBulan = cbx_jumlah_bulan.getSelectedIndex() + 1; // Jumlah bulan yang ingin ditambahkan
                calendar.add(Calendar.MONTH, jumlahBulan);

                // Dapatkan tanggal setelah penambahan bulan
                Date newDate = calendar.getTime();
                choose_tgl_akhir_pmb.setDate(newDate);
            }
        } catch (Exception e) {
        }

    }
}
