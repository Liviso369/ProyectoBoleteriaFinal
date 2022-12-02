package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static String url = "jdbc:mysql://localhost/bdboleteria";
    private static String usuario = "root";
    private static String password = "";
    private static Conexion conexion = null;

    private Conexion() {//unica conexion
        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error al cargar Driver");///establecemos los driver de mariadb
        }
    }

    public static Connection getConexion() {//se establece la conexion a la base de datos
        Connection conn = null;
        if (conexion == null) {
            conexion = new Conexion();
        }

        try {
            conn = DriverManager.getConnection(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + usuario + "&password=" + password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error de Conexion");
        }

        return conn;
    }

}
