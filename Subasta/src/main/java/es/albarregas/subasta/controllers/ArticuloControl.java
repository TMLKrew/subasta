/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.PujaDAO;
import es.albarregas.subasta.beans.Articulo;
import java.io.IOException;
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
 * Este controlador se encarga redigir la pagina a un determinado articulo, para ello se le pasa como parametro el id del articulo.
 */
@WebServlet(name = "ArticuloControl", urlPatterns = {"/articulo"})
public class ArticuloControl extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        int id;
        id = Integer.parseInt(request.getParameter("art"));
        
        ArrayList<Articulo> articulos = (ArrayList<Articulo>) sesion.getAttribute("articulos");
        for (Articulo art : articulos){
            if (art.getId()==id){
                try {
                    art.setPujas(PujaDAO.leerPujas(id));
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("art", art);
            }
        }
        request.getRequestDispatcher("jsp/articulo.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
