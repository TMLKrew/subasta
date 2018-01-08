/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.subasta.models;

import es.albarregas.subasta.beans.Puja;

/**
 *
 * @author Simon
 */
public class CalcularBeneficio {

    public static float calcular(Puja puja) {
        float valor = puja.getValor();
        if (valor <= 50){
            valor-=10;
        }else if (valor <= 120){
            valor-=valor*0.15;
        } else {
            valor-=valor*0.21;
        }

        return valor;

    }

}
