package versionbeta;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author Limon
 */
/*
 * esta es la clase central para abrir la conexión con MySQL,
 * la idea es no repetir usuario, contraseña y URL en cada ventana
 */
public class ConexionMySQL {
//nombre de la base de datos que usa todo el proyecto
    private final String db = "eloteria_db2"; 
//este es eo URL local de MySQL,si se cambia de computadora, aquí suele ser lo primero que se revisa
    private final String url = "jdbc:mysql://localhost:3306/" + db;
// Usuario de MySQL configurado para el proyecto.
    private final String usuario = "root";
// Contraseña del ususario,dependiendo quien lo ejecute, la contraseña debeb de cambiar 
    private final String pass = "12345"; 
    
    public ConexionMySQL() {
    }
//aqui se intenta abrir la conexión y devuelve el objeto Connection para hacer consultas
    public Connection Conectar() {
        Connection enlace = null;
        try {
    //el driver para la conexion       
            Class.forName("com.mysql.cj.jdbc.Driver");
            enlace = DriverManager.getConnection(this.url, this.usuario, this.pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }                   
        return enlace;
    }
//este método es una plantilla de netbeans, no se usa en las ventanas actuales
    Connection conectar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}