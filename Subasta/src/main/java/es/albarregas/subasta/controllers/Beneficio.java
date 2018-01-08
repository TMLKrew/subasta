/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.PujaDAO;
import static es.albarregas.subasta.DAO.PujaDAO.puja;
import es.albarregas.subasta.beans.Puja;
import es.albarregas.subasta.models.CalcularBeneficio;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Este controlador redirige la pagina a una pagina donde se mostrara el beneficio obtenido por un articulo.
 * Para ello se le pasa como parametro el id del articulo. El controlador accedera a la ultima puja realizada para ese articulo y calculara
 * el beneficio.
 */
@WebServlet(name = "Beneficio", urlPatterns = {"/beneficio"})
public class Beneficio extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("art"));
        try {
            Puja puja = PujaDAO.ultimaPuja(id);
            request.setAttribute("beneficio", CalcularBeneficio.calcular(puja));
        } catch (SQLException ex) {
            Logger.getLogger(Beneficio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/jsp/beneficio.jsp?id="+id).forward(request, response);
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
