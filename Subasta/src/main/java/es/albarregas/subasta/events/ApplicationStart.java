/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.events;

import es.albarregas.subasta.DAO.CategoriasDAO;
import es.albarregas.subasta.beans.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Simon
 */
@WebListener 
public class ApplicationStart implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ArrayList<Categoria> categorias = null;
        try {
            categorias = CategoriasDAO.categorias();
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationStart.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("categorias", categorias);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Se ha finalizado la aplicación");
    }
    
}
