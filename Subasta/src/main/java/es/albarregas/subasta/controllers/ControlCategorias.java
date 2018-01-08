/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.CaracteristicasDAO;
import es.albarregas.subasta.DAO.CategoriasDAO;
import es.albarregas.subasta.beans.Categoria;
import es.albarregas.subasta.events.ApplicationStart;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon
 * Este controlador lo utiliza el administrador para insertar categorias y caracteristicas a la base de datos
 * Recibe por parametros el nombre de la categoria y el nombre de las distintas caracteristicas
 */
@WebServlet(name = "ControlCategorias", urlPatterns = {"/controlCategorias"})
public class ControlCategorias extends HttpServlet {

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
        String nombre = request.getParameter("Catnombre");
        ArrayList caracteristicas = new ArrayList();
        Enumeration<String> parametros = request.getParameterNames();
        while (parametros.hasMoreElements()) {
            String carac = parametros.nextElement();
            if (!carac.startsWith("Cat")) {

                String c = request.getParameter(carac);
                caracteristicas.add(c);
            }
        }
        try {
            int id = CategoriasDAO.insertarCategoria(nombre);
            for (Object car : caracteristicas) {
                CaracteristicasDAO.insertarCaracteristicas((String) car, id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Categoria> categorias = null;
        try {
            categorias = CategoriasDAO.categorias();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationStart.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServletContext ctx = request.getServletContext();
        ctx.setAttribute("categorias", categorias);

        request.getRequestDispatcher("/jsp/controlcategorias.jsp").forward(request, response);
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
