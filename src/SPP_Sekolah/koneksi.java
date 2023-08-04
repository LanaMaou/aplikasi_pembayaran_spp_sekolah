package SPP_Sekolah;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection mysqlkonek;

    public static Connection koneksiDB() {
        if (mysqlkonek == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_spp";
                String user = "root";
                String pass = "";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlkonek = (Connection) DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Konek", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return mysqlkonek;
    }
}
