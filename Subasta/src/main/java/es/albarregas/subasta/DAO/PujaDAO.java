/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Puja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class PujaDAO {
    public static void puja(int cliente, int articulo, Timestamp fecha, float importe) throws SQLException{
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm;
        stm = conexion.prepareStatement("INSERT INTO pujas(idCliente, idArticulo, fecha, importe) VALUES (?,?,?,?)");
        stm.setInt(1, cliente);
        stm.setInt(2, articulo);
        stm.setTimestamp(3, fecha);
        stm.setFloat(4, importe);
        stm.executeUpdate();
        
    }
    
    public static ArrayList<Puja> leerPujas (int idArticulo) throws SQLException{
        
        ArrayList<Puja> pujas = new ArrayList<Puja>();
        String consulta = "SELECT * FROM pujas WHERE idArticulo = ? ORDER BY importe DESC";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idArticulo);
        
        ResultSet rst = stm.executeQuery();
        while(rst.next()){
            Puja puja = new Puja();
            puja.setIdArticulo(idArticulo);
            puja.setFecha(rst.getTimestamp("fecha"));
            puja.setIdCliente(rst.getInt("idCliente"));
            puja.setValor(rst.getFloat("importe"));
            
            pujas.add(puja);
        }
        
        return pujas;
    }
    
    public static ArrayList<Puja> misPujas (int idCliente) throws SQLException{
        String consulta = "SELECT * FROM pujas WHERE idCliente = ?";
        ArrayList<Puja> pujas =  new ArrayList<Puja>();
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idCliente);
        
        ResultSet rst = stm.executeQuery();
        
        while(rst.next()){
            Puja puja = new Puja();
            
            puja.setIdArticulo(rst.getInt("idArticulo"));
            puja.setFecha(rst.getTimestamp("fecha"));
            puja.setValor(rst.getFloat("importe"));
            puja.setIdCliente(idCliente);
            
            pujas.add(puja);
        }
        return pujas;
    }
    
    public static Puja ultimaPuja(int idArticulo) throws SQLException{
        String consulta = "SELECT * FROM pujas WHERE fecha = (SELECT MAX(fecha) FROM pujas) AND idArticulo = ?";
        Puja puja = new Puja();
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idArticulo);
        
        ResultSet rst = stm.executeQuery();
        
        while(rst.next()){
            puja.setFecha(rst.getTimestamp("fecha"));
            puja.setIdArticulo(idArticulo);
            puja.setIdCliente(rst.getInt("idCliente"));
            puja.setValor(rst.getFloat("importe"));
        }
        
        return puja;
    }
}
