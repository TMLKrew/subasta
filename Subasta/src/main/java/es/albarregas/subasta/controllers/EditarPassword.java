/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.UsuarioDAO;
import es.albarregas.subasta.beans.Usuario;
import es.albarregas.subasta.models.Encriptar;
import java.io.IOException;
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
 * Este controlador se utiliza para cambiar la password de un usuario
 * Recibe tres parametros, la password anterior y dos veces la password nueva.
 * Comprueba que la password anterior es valida y que las dos nuevas son iguales.
 */
@WebServlet(name = "EditarPassword", urlPatterns = {"/EditarPassword"})
public class EditarPassword extends HttpServlet {

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
        String pass1, pass2, pass3;
        pass1 = request.getParameter("password");
        pass2 = request.getParameter("password1");
        pass3 = request.getParameter("password2");
        try {
            if (Encriptar.desencriptar(user.getPassword()).equals(pass1)) {
                System.out.println("Hola");
                if (pass2.equals(pass3)) {
                    System.out.println("hola");
                    user.setPassword(Encriptar.encriptar(pass2));
                    if (!UsuarioDAO.password(user)) {
                        sesion.setAttribute("info", user);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
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
