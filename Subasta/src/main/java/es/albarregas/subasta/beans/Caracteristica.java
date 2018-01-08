/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.beans;

import java.io.Serializable;

/**
 *
 * @author Simon
 */
public class Caracteristica implements Serializable{

    public Caracteristica() {
        super();
    }
    
    private int idCaracteristica;
    private int idCategoria;
    private String denominacion;

    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDenoninacion() {
        return denominacion;
    }

    public void setDenoninacion(String denoninacion) {
        this.denominacion = denoninacion;
    }
    
    
    
    
    
}
