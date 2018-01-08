/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.PujaDAO;
import es.albarregas.subasta.beans.Puja;
import es.albarregas.subasta.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon
 * Este controlador dirige el flujo de la aplicacion hacia una pagina donde se visualizaran todas las pujas de un usuario.
 * 
 */
@WebServlet(name = "MisPujas", urlPatterns = {"/MisPujas"})
public class MisPujas extends HttpServlet {

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
        Usuario user = (Usuario) sesion.getAttribute("info");
        try {
            ArrayList<Puja> pujas = PujaDAO.misPujas(user.getId());
            request.setAttribute("misPujas", pujas);

        } catch (SQLException ex) {
            Logger.getLogger(MisPujas.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/jsp/mispujas.jsp").forward(request, response);
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
