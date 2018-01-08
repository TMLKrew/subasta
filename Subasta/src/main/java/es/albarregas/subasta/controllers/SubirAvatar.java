/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.controllers;

import es.albarregas.subasta.DAO.UsuarioDAO;
import es.albarregas.subasta.beans.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "SubirAvatar", urlPatterns = {"/subirAvatar"})
public class SubirAvatar extends HttpServlet {

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
        String directorio = null;
        Map map = System.getenv();
        Set keys = map.keySet();
        Iterator iterator = keys.iterator();
        boolean encontrado = false;
        
        while (iterator.hasNext() && !encontrado) {
            String key = (String) iterator.next();
            if (key.equals("CATALINA_HOME")) {
                directorio = (String) map.get(key);
                encontrado = true;
            }

        }
        
        directorio = directorio + "/webapps/Subasta/images/"+user.getId()+"/avatar/";
        

        File parent = new File(directorio);
        parent.mkdirs();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

// req es la HttpServletRequest que recibimos del formulario.
// Los items obtenidos serán cada uno de los campos del formulario,
// tanto campos normales como ficheros subidos.
        List items;
        try {
            items = upload.parseRequest(request);
            // Se recorren todos los items, que son de tipo FileItem
            for (Object item : items) {
                FileItem uploaded = (FileItem) item;
                

                // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
                // subido donde nos interese
                if (!uploaded.isFormField()) {
                    // No es campo de formulario, guardamos el fichero en algún sitio
                    File fichero = new File(parent, uploaded.getName());
                    try {
                        uploaded.write(fichero);                        
                        user.setAvatar(uploaded.getName());
                        UsuarioDAO.avatar(user);
                        sesion.setAttribute("info", user);
                    } catch (Exception ex) {
                        System.out.println("por aqui sale el error");
                        Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }

        //request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/jsp/profile.jsp");
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
