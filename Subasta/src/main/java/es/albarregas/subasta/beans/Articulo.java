/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Simon
 */
public class Articulo implements Serializable {

    public Articulo() {
        super();
    }

    private int categoria;
    private int id;
    private String descripcionCorta;
    private String descripcion;
    private Usuario cliente;
    private float importeSalida;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private ArrayList<Caracyart> caracteristicas;
    private ArrayList<Fotografia> fotografias;
    private ArrayList<Puja> pujas;

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public float getImporteSalida() {
        return importeSalida;
    }

    public void setImporteSalida(float importeSalida) {
        this.importeSalida = importeSalida;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Caracyart> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Caracyart> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Fotografia> getFotografias() {
        return fotografias;
    }

    public void setFotografias(ArrayList<Fotografia> fotografias) {
        this.fotografias = fotografias;
    }

    public ArrayList<Puja> getPujas() {
        return pujas;
    }

    public void setPujas(ArrayList<Puja> pujas) {
        this.pujas = pujas;
    }

}
