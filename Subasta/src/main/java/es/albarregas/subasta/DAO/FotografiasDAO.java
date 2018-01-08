/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Fotografia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class FotografiasDAO {

    public static void fotografia(String foto, int id) {
        String consulta = "INSERT INTO fotografias (idFotografia, idArticulo, fotografia)"
                + " VALUES (0,?,?)";
        PreparedStatement fotografia = null;        

        try {
            Connection conexion = ConnectionDAO.getConexionMySQL();
            
                fotografia = conexion.prepareStatement(consulta);               
                fotografia.setInt(1, id);              
                fotografia.setString(2,foto);                
                fotografia.executeUpdate();
                fotografia.close();

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();

        }
    }
    
    public static ArrayList<Fotografia> leerFotografias(int idArticulo) throws SQLException{
        ArrayList<Fotografia> fotografias = new ArrayList<Fotografia>();
        String consulta = "SELECT * FROM fotografias WHERE idArticulo = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idArticulo);
        
        ResultSet rst = stm.executeQuery();
        while(rst.next()){
            Fotografia fotografia = new Fotografia();
            fotografia.setIdArticulo(idArticulo);
            fotografia.setIfFotografia(rst.getInt("idFotografia"));
            fotografia.setFotografia(rst.getString("fotografia"));
            
            fotografias.add(fotografia);
        }
        return fotografias;
    }
}
