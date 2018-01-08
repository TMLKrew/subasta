/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.UsuarioDAO;
import es.albarregas.subasta.beans.Usuario;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Simon
 * Este controlador se encarga de login, registro y comprobar que el correo del usuario no este en la base de datos.
 */
@WebServlet(name = "Registro", urlPatterns = {"/registro"})
public class Registro extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String salida = "index";
        Usuario usuario = null;
        boolean error = false;
        /**
         *  Si recibe el parametro login. El controlador accedera a la base de datos para comprobar que los datos sean correctos.
         *  
         */
        if (request.getParameter("login") != null) {
            Timestamp ultimo = Timestamp.valueOf(LocalDateTime.now());
            String user = request.getParameter("emailLogin");
            String pass = request.getParameter("passLogin");

            try {
                if (!UsuarioDAO.login(user, pass, ultimo)) {
                    salida = "error";
                } else {
                    usuario = UsuarioDAO.info(user);
                    sesion.setAttribute("info", usuario);
                }
            } catch (Exception ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (salida.equals("error")) {
                response.getWriter().write(salida);
            } else if (usuario.getTipo().equals("a")) {
                salida = "admin";
                response.getWriter().write(salida);
            } else {
                response.getWriter().write(salida);
            }
        }
        /**
         * Si recibe el parametro registrar, introduce los datos de un usuario en la base de datos
         */
        if (request.getParameter("registrar") != null) {
            Usuario user = new Usuario();
            try {
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (UsuarioDAO.email(request.getParameter("email"))) {
                    error = true;
                } else if (UsuarioDAO.registro(user)) {
                    error = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
            String valor = String.valueOf(error);
            response.getWriter().write(valor);
        }
        /**
         *  Si recibe el parametro comprobar accede a la base de datos para verificar que el correo no se encuentra 
         */
        if (request.getParameter("comprobar") != null) {
            if (UsuarioDAO.email(request.getParameter("email"))) {
                error = true;
            }
            String valor = String.valueOf(error);
            response.getWriter().write(valor);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
