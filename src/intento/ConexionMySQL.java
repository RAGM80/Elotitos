package intento;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionMySQL {

    private final String db = "eloteria_db";
    private final String url = "jdbc:mysql://localhost:3306/" + db + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String usuario = "root";
    private final String pass = "12345";

    public Connection Conectar() {
        Connection enlace = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            enlace = DriverManager.getConnection(url, usuario, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al conectar la base de datos:\n" + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return enlace;
    }
}