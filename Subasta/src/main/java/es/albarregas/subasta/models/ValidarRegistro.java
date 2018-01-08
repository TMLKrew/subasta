/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.models;

import es.albarregas.subasta.beans.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Simon
 */
public class ValidarRegistro {

  

    /**
     *
     * @param user
     * @return boolean En este método validaremos los campos introducidos en el
     * formulario de registro si en algún campo hay algun error, devolveremos un
     * atributo booleano
     */
    public static boolean validarRegistro(Usuario user) {
        boolean valido = true;
        //Comprobamos que los campos no puedan superar el tamaño establecido en la base de datos
        if (user.getEmail().length() > 100              
                || user.getNombre().length() > 20
                || user.getApellido1().length() > 20
                || user.getApellido2().length() > 20
                || user.getNif().length() > 9
                || user.getDireccion().length() > 45
                || user.getTelefono().length() > 9) {
            valido = false;
            return valido;
        }
        //Comprobamos que el email sea correcto
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            valido = false;
            return valido;
        }
        //Comprobamos que el DNI sea correcto
        pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        matcher = pattern.matcher(user.getNif());
        if (!matcher.matches()) {
            valido = false;
            return valido;
        }
        pattern = Pattern.compile("^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,19}[\\s]*)+$");
        matcher = pattern.matcher(user.getNombre());
        if (!matcher.matches()){
             valido = false;
            return valido;
        }
        matcher = pattern.matcher(user.getApellido1());
          if (!matcher.matches()){
             valido = false;
            return valido;
        }
        matcher = pattern.matcher(user.getApellido2());
          if (!matcher.matches()){
             valido = false;
            return valido;
        }   
        pattern = Pattern.compile("\\d{9}");
        matcher = pattern.matcher(user.getTelefono());
          if (!matcher.matches()){
              valido = false;
              return valido;
          }
        return valido;
    }
}
