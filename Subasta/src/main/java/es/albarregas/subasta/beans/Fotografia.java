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
public class Fotografia implements Serializable{

    public Fotografia() {
        super();
    }
    
    private int ifFotografia;
    private int idArticulo;
    private String fotografia;

    public int getIfFotografia() {
        return ifFotografia;
    }

    public void setIfFotografia(int ifFotografia) {
        this.ifFotografia = ifFotografia;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
    
    
}
