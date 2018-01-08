/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class ArticuloDAO {

    public static ArrayList<Articulo> leerArticulos(int idCategoria) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        String consulta = "SELECT * FROM articulos WHERE idCategoria = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idCategoria);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Articulo art = new Articulo();
            art.setCategoria(rst.getInt("idCategoria"));
            art.setDescripcion(rst.getString("descripcion"));
            art.setDescripcionCorta(rst.getString("descripcionCorta"));
            art.setFechaInicio(rst.getTimestamp("fechaInicio"));
            art.setFechaFin(rst.getTimestamp("fechaFin"));
            art.setId(rst.getInt("idArticulo"));
            art.setImporteSalida(rst.getFloat("importeSalida"));

            art.setCliente(UsuarioDAO.leerUsuario(rst.getInt("idCliente")));
            art.setFotografias(FotografiasDAO.leerFotografias(rst.getInt("idArticulo")));
            art.setCaracteristicas(CaracyartDAO.leerCaracteristicas(art.getId()));
            art.setPujas(PujaDAO.leerPujas(art.getId()));
            
            articulos.add(art);
        }

        return articulos;
    }

    public static int abrirSubasta(Articulo art) {
        String consulta = "INSERT INTO articulos (idArticulo, descripcionCorta, descripcion, idCategoria, idCliente, fechaInicio, fechaFin, importeSalida)"
                + " VALUES (0,?,?,?,?,?,?,?)";
        PreparedStatement subasta = null;
        int id = 0;

        try {
            Connection conexion = ConnectionDAO.getConexionMySQL();
            subasta = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            subasta.setString(1, art.getDescripcionCorta());
            subasta.setString(2, art.getDescripcion());
            subasta.setInt(3, art.getCategoria());
            subasta.setInt(4, art.getCliente().getId());
            subasta.setTimestamp(5, (Timestamp) art.getFechaInicio());
            subasta.setTimestamp(6, art.getFechaFin());
            subasta.setFloat(7, art.getImporteSalida());
            subasta.executeUpdate();
            ResultSet idArticulo = subasta.getGeneratedKeys();
            
            while (idArticulo.next()) {
                id = idArticulo.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
        }
        return id;
    }
     public static ArrayList<Articulo> misArticulos(int idCliente) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        String consulta = "SELECT * FROM articulos WHERE idCliente = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idCliente);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            Articulo art = new Articulo();
            art.setCategoria(rst.getInt("idCategoria"));
            art.setDescripcion(rst.getString("descripcion"));
            art.setDescripcionCorta(rst.getString("descripcionCorta"));
            art.setFechaInicio(rst.getTimestamp("fechaInicio"));
            art.setFechaFin(rst.getTimestamp("fechaFin"));
            art.setId(rst.getInt("idArticulo"));
            art.setImporteSalida(rst.getFloat("importeSalida"));

            art.setCliente(UsuarioDAO.leerUsuario(rst.getInt("idCliente")));
            art.setFotografias(FotografiasDAO.leerFotografias(rst.getInt("idArticulo")));
            art.setCaracteristicas(CaracyartDAO.leerCaracteristicas(art.getId()));
            art.setPujas(PujaDAO.leerPujas(art.getId()));
            
            articulos.add(art);
        }

        return articulos;
    }
}
