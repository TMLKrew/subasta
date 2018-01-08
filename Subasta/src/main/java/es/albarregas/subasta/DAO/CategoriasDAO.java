/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class CategoriasDAO {

    public static ArrayList<Categoria> categorias() throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        String consulta = "SELECT * FROM categorias";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        Statement stm = conexion.createStatement();
        ResultSet rst = stm.executeQuery(consulta);

        while (rst.next()) {
            Categoria cat = new Categoria();
            cat.setDenominacion(rst.getString("denominacion"));
            cat.setIdCategoria(rst.getInt("idCategoria"));
            cat.setImagen(rst.getString("imagen"));

            cat.setCaracteristicas(CaracteristicasDAO.leerCaracteristicas(cat.getIdCategoria()));

            categorias.add(cat);
        }

        return categorias;
    }

    public static int insertarCategoria(String nombre) throws SQLException {
        int id = 0;
        String consulta = "INSERT INTO categorias (idCategoria, denominacion) VALUES (0,?)";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, nombre);
        stm.executeUpdate();
        ResultSet idCat = stm.getGeneratedKeys();
        while (idCat.next()) {
            id = idCat.getInt(1);
        }
        return id;
    }

}
