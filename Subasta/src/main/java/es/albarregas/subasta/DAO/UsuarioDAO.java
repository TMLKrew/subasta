/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.DAO;

import es.albarregas.subasta.beans.Usuario;
import es.albarregas.subasta.models.Encriptar;
import es.albarregas.subasta.models.ValidarRegistro;
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
public class UsuarioDAO {

    public static Usuario leerUsuario(int idUsuario) throws SQLException {
        Usuario user = new Usuario();
        String consulta = "SELECT * FROM usuarios INNER JOIN clientes WHERE idUsuario = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setInt(1, idUsuario);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            user.setId(idUsuario);
            user.setNombre(rst.getString("nombre"));
            user.setApellido1(rst.getString("apellido1"));
            user.setApellido2(rst.getString("apellido2"));
            user.setAvatar(rst.getString("avatar"));
        }

        return user;
    }

    public static boolean registro(Usuario user) throws SQLException {

        String consulta = "INSERT INTO usuarios(idUsuario, email, password)"
                + " VALUES (0,?,?)";
        String consulta2 = "INSERT INTO clientes(idCliente, nombre, apellido1, apellido2, nif, direccion, telefono)"
                + " VALUES (?,?,?,?,?,?,?)";
        int usuarios = 0;
        int idUser = 0;
        int clientes = 0;
        boolean error = false;
        PreparedStatement usuario = null;
        PreparedStatement cliente = null;
        Statement fKey = null;
        try {
            user.setPassword(Encriptar.encriptar(user.getPassword()));
            if (ValidarRegistro.validarRegistro(user)) {

                Connection conexion = ConnectionDAO.getConexionMySQL();
                fKey = conexion.createStatement();
                fKey.executeQuery("SET FOREIGN_KEY_CHECKS=0");
                usuario = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
                usuario.setString(1, user.getEmail());
                usuario.setString(2, user.getPassword());
                usuarios = usuario.executeUpdate();
                ResultSet idUsuario = usuario.getGeneratedKeys();
                while (idUsuario.next()) {
                    idUser = idUsuario.getInt(1);
                }

                cliente = conexion.prepareStatement(consulta2);
                cliente.setInt(1, idUser);
                cliente.setString(2, user.getNombre());
                cliente.setString(3, user.getApellido1());
                cliente.setString(4, user.getApellido2());
                cliente.setString(5, user.getNif());
                cliente.setString(6, user.getDireccion());
                cliente.setString(7, user.getTelefono());
                clientes = cliente.executeUpdate();
                fKey.executeQuery("SET FOREIGN_KEY_CHECKS=1");
                cliente.close();
                usuario.close();
                fKey.close();
            } else {
                error = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
            error = true;
        }
        return error;

    }

    public static boolean email(String email) {
        String consulta = "SELECT email FROM usuarios WHERE email = ?";
        boolean registrado = false;
        Connection conexion = ConnectionDAO.getConexionMySQL();
        try {
            PreparedStatement stm = conexion.prepareStatement(consulta);
            stm.setString(1, email);
            ResultSet rst = stm.executeQuery();
            while (rst.next()) {
                if (email.equals(rst.getString("email"))) {
                    registrado = true;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
        }
        return registrado;
    }

    public static boolean login(String user, String pass, Timestamp ultimo) throws Exception {
        boolean acceso = false;
        int id = 0;
        String sentencia = "SELECT idUsuario,email,password,bloqueado FROM usuarios";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        try {
            Statement stm = conexion.createStatement();
            ResultSet rst = stm.executeQuery(sentencia);
            while (rst.next()) {
                if (rst.getString("email").equals(user) && Encriptar.desencriptar(rst.getString("password")).equals(pass)) {
                    acceso = true;
                    id = rst.getInt("idUsuario");
                    if (rst.getString("bloqueado").equals("s")) {
                        acceso = false;
                    }
                }
            }
            sentencia = "UPDATE usuarios SET ultimoAcceso = ? WHERE idUsuario = ?";
            PreparedStatement pstm = conexion.prepareStatement(sentencia);
            pstm.setTimestamp(1, ultimo);
            pstm.setInt(2, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();

        }
        return acceso;
    }

    public static boolean perfil(Usuario user) {

        Connection conexion = ConnectionDAO.getConexionMySQL();
        int id = 0;
        boolean error = false;
        try {
            PreparedStatement stm = conexion.prepareStatement("SELECT idUsuario FROM usuarios WHERE email = ?");
            stm.setString(1, user.getEmail());
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                id = rst.getInt("idUsuario");
            }
            PreparedStatement stm3 = conexion.prepareStatement("UPDATE clientes SET nombre = ?, apellido1 = ?, apellido2 = ?, nif = ?, direccion = ?, telefono = ? WHERE idCliente = ?");
            stm3.setString(1, user.getNombre());
            stm3.setString(2, user.getApellido1());
            stm3.setString(3, user.getApellido2());
            stm3.setString(4, user.getNif());
            stm3.setString(5, user.getDireccion());
            stm3.setString(6, user.getTelefono());
            stm3.setInt(7, id);
            stm3.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
            error = true;
        }
        return error;
    }

    public static boolean password(Usuario user) {
        Connection conexion = ConnectionDAO.getConexionMySQL();
        int id = 0;
        boolean error = false;
        try {
            PreparedStatement stm = conexion.prepareStatement("UPDATE usuarios SET password = ? WHERE email = ?");
            stm.setString(1, user.getPassword());
            stm.setString(2, user.getEmail());
            int rst = stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
            error = true;
        }
        return error;
    }

    public static void avatar(Usuario user) {

        Connection conexion = ConnectionDAO.getConexionMySQL();
        int id = 0;
        try {
            PreparedStatement stm = conexion.prepareStatement("SELECT idUsuario FROM usuarios WHERE email = ?");
            stm.setString(1, user.getEmail());
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                id = rst.getInt("idUsuario");
            }
            PreparedStatement stm2 = conexion.prepareStatement("UPDATE `clientes` SET avatar = ? WHERE idCliente = ?");
            stm2.setString(1, user.getAvatar());
            stm2.setInt(2, id);
            stm2.executeUpdate();
            stm.close();
            stm2.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();

        }

    }

    public static Usuario info(String email) throws SQLException {
        Usuario user = new Usuario();
        String consulta = "SELECT * FROM usuarios INNER JOIN clientes ON usuarios.idUsuario = clientes.idCliente WHERE email = ?";
        PreparedStatement info = null;
        try {
            Connection conexion = ConnectionDAO.getConexionMySQL();
            info = conexion.prepareStatement(consulta);
            info.setString(1, email);
            ResultSet rst = info.executeQuery();
            while (rst.next()) {
                user.setId(rst.getInt("idUsuario"));
                user.setEmail(rst.getString("email"));
                user.setPassword(rst.getString("password"));
                user.setNombre(rst.getString("nombre"));
                user.setApellido1(rst.getString("apellido1"));
                user.setApellido2(rst.getString("apellido2"));
                user.setNif(rst.getString("nif"));
                user.setDireccion(rst.getString("direccion"));
                user.setTelefono(rst.getString("telefono"));
                user.setAvatar(rst.getString("avatar"));
                user.setTipo(rst.getString("tipoAcceso"));
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la sentencia");
            ex.printStackTrace();
        }
        return user;

    }

    public static ArrayList<Usuario> usuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String consulta = "SELECT * FROM usuarios inner join clientes on usuarios.idUsuario = clientes.idCliente";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        Statement stm = conexion.createStatement();
        ResultSet rst = stm.executeQuery(consulta);
        while (rst.next()) {
            if (rst.getString("tipoAcceso").equals("u")) {
                Usuario user = new Usuario();
                user.setNombre(rst.getString("nombre"));
                user.setApellido1(rst.getString("apellido1"));
                user.setApellido2(rst.getString("apellido2"));
                user.setEmail(rst.getString("email"));
                user.setId(rst.getInt("idUsuario"));
                user.setBloqueado(rst.getString("bloqueado"));
                usuarios.add(user);
            }
        }
        return usuarios;

    }
    
    public static void bloquear(String acceso, int id) throws SQLException{
        String consulta = "UPDATE usuarios SET bloqueado=?  WHERE idUsuario = ?";
        Connection conexion = ConnectionDAO.getConexionMySQL();
        PreparedStatement stm = conexion.prepareStatement(consulta);
        stm.setString(1, acceso);
        stm.setInt(2, id);
        
        stm.executeUpdate();
    }
}
