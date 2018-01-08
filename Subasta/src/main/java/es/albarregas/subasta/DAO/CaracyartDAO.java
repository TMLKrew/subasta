/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Articulo;
import es.albarregas.subasta.beans.Caracyart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class CaracyartDAO {
       public static void caracteristicas(Caracyart cya) throws SQLException {
        String consulta = "INSERT INTO caracyart (id, idArticulo, idCaracteristica, valor)"
                + " VALUES (0,?,?,?)";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, cya.getIdArticulo());
        stm.setInt(2, cya.getIdCaracteristica());
        stm.setString(3, cya.getValor());
        stm.executeUpdate();
       }   
       
       public static ArrayList<Caracyart> leerCaracteristicas(int idArticulo) throws SQLException{
           ArrayList<Caracyart> cyas = new ArrayList<Caracyart>();
           String consulta = "SELECT * FROM caracyart WHERE idArticulo = ?";
           Connection conexion = ConnectionDAO.getConexionMySQL();
           PreparedStatement stm = conexion.prepareStatement(consulta);
           stm.setInt(1, idArticulo);
           
           ResultSet rst = stm.executeQuery();
           
           while(rst.next()){
               Caracyart cya = new Caracyart();
               cya.setId(rst.getInt("id"));
               cya.setIdArticulo(idArticulo);
               cya.setIdCaracteristica(rst.getInt("idCaracteristica"));
               cya.setValor(rst.getString("valor"));
               
               cyas.add(cya);
           }
           return cyas;
       }
}
