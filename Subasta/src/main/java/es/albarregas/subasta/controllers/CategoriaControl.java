/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.ArticuloDAO;
import es.albarregas.subasta.beans.Categoria;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon
 * Este controlador se encarga de redirigir el flujo a una pagina donde se mostraran todos los articulos de una categoria.
 * Se le pasa por parametro el id de categoria.
 * El controlador comprueba que la hora inicial de los articulos sea menor que la actual y que la fecha fin sea mayor que la actual
 * Obtiene las categorias mediante el contexto de la aplicacion
 */
@WebServlet(name = "Categoria", urlPatterns = {"/Categoria"})
public class CategoriaControl extends HttpServlet {


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
        String nombre = request.getParameter("valor");
        Timestamp ultimo = Timestamp.valueOf(LocalDateTime.now());
        ServletContext ctx = request.getServletContext();
        ArrayList<Categoria> categorias = (ArrayList<Categoria>) ctx.getAttribute("categorias");
        for (Categoria cat : categorias){
            if (cat.getIdCategoria() == Integer.parseInt(nombre)){
                request.setAttribute("cats",cat);
            }
        }
        request.setAttribute("now",ultimo); 
        ArrayList articulos;
        try {
            articulos = ArticuloDAO.leerArticulos(Integer.parseInt(nombre));
            sesion.setAttribute("articulos", articulos);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("jsp/categoria.jsp").forward(request, response);
       
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
