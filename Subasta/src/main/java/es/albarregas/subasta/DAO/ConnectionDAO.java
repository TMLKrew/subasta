package es.albarregas.subasta.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionDAO {
    public static Connection conexion = null;
    public static Connection getConexionMySQL() {
        if (conexion == null) {
            try {
                Context contextoInicial = new InitialContext();
                DataSource ds = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/poolMySQL");
                conexion = ds.getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            } 
        }
        return conexion;
    }

    public static void closeConnection() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión a la BD");
            e.printStackTrace();
        }
    }

}
