/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Caracteristica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class CaracteristicasDAO { 
    
    public static ArrayList<Caracteristica> leerCaracteristicas (int idCategoria) throws SQLException{
        ArrayList<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
        String consulta = "SELECT * FROM caracteristicas WHERE idCategoria = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idCategoria);
        ResultSet rst = stm.executeQuery();
        
        while(rst.next()){
            Caracteristica caracteristica = new Caracteristica();
            caracteristica.setDenoninacion(rst.getString("denominacion"));
            caracteristica.setIdCaracteristica(rst.getInt("idCaracteristica"));
            caracteristica.setIdCategoria(rst.getInt("idCategoria"));
            
            caracteristicas.add(caracteristica);
            
        }
        
        return caracteristicas;
        
    }
    
    public static void insertarCaracteristicas (String nombre, int idCategoria) throws SQLException{
        String consulta = "INSERT INTO caracteristicas (idCaracteristica, idCategoria, denominacion) VALUES (0,?,?)";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idCategoria);
        stm.setString(2, nombre);
        stm.executeUpdate();
    }

}
