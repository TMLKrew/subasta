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

/**
 *
 * @author Simon
 * Este controlador se encarga de introducir las pujas de un articulo en la base de datos.
 * Recibe por parametros el id del articulo y el valor de la puja 
 */
@WebServlet(name = "Puja", urlPatterns = {"/Puja"})
public class PujaControl extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("articulo"));

        float puja = Float.parseFloat(request.getParameter("puja"));
        Puja pujaS = null;
        try {
            pujaS = PujaDAO.ultimaPuja(id);
        } catch (SQLException ex) {
            Logger.getLogger(PujaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (puja > pujaS.getValor()) {
            Usuario user = (Usuario) sesion.getAttribute("info");
            Timestamp fecha = Timestamp.valueOf(LocalDateTime.now());

            try {
                PujaDAO.puja(user.getId(), id, fecha, puja);
            } catch (SQLException ex) {
                Logger.getLogger(PujaControl.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        request.getRequestDispatcher("/articulo?art=" + id).forward(request, response);
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
