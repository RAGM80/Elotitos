package intento; // Tu paquete actual

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author Limon
 */
public class ConexionMySQL {
    private final String db = "eloteria_db"; 
    private final String url = "jdbc:mysql://localhost:3306/" + db;
    private final String usuario = "root";
    private final String pass = ""; 
    
    public ConexionMySQL() {
    }
    
    public Connection Conectar() {
        Connection enlace = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            enlace = DriverManager.getConnection(this.url, this.usuario, this.pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }                   
        return enlace;
    }

    Connection conectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}