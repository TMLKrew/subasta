/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon
 * Este controlador se en carga de bloquear o desbloquear el acceso de los usuarios a la pagina web.
 * Se le pasa por parametro el id de usuario y si esta bloqueado o no
 */
@WebServlet(name = "BloquearUsuario", urlPatterns = {"/BloquearUsuario"})
public class BloquearUsuario extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        String acceso = request.getParameter("acceso");
        if (acceso.equals("n")){
            acceso = "s";
        } else
        if (acceso.equals("s")){
            acceso = "n";
        }
        try {
            UsuarioDAO.bloquear(acceso, id);
        } catch (SQLException ex) {
            Logger.getLogger(BloquearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/controlUsuarios").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
