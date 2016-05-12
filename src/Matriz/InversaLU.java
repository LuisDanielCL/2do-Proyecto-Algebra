/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Matriz;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author josemurillo
 */
public class InversaLU {
    Matriz matrizL;
    Matriz matrizU;
    Matriz matrizA;
    Matriz inversa;
    Matriz identidad;
    double[] respuestas;
    String pasoApaso = "";

   
public InversaLU(){
}

public void calcInversa (Matriz pmatrizA){
    FactorizacionLU flu = new FactorizacionLU();
    try{
        flu.factorizar(pmatrizA);
        matrizL = flu.getMatrizL();
        matrizU = flu.matrizU;
        matrizA = pmatrizA;
        inversa = pmatrizA;
        identidad = pmatrizA;
        identidad.identidad();
        ResolverSistemas resolver = new ResolverSistemas();

        for (int i = 0; i < inversa.largoColumna(); i++){
            pasoApaso += "--------------------" + "\n";
            pasoApaso += "Vamos a encontrar la columna: " + i + " de la matriz inversa" + "\n";
            pasoApaso +="Sacamos la columna: " + i + " de la matriz identidad" + "\n";
            pasoApaso += "MatrizL * (z1,z2..zn) = columnaIdentidad" + "\n";
            pasoApaso += matrizL.toString()+ "  * (z1,z2...zn) = ";
            pasoApaso += "[";
            float[] columnaIdentidad = identidad.getColumna(i);
            for (int m = 0; m < columnaIdentidad.length; m++){
                pasoApaso +=(columnaIdentidad[m] + ",");
            }
            pasoApaso += "]" + "\n";
            pasoApaso += "Se resuelve el sistema de ecuaciones..." + "\n";
            resolver.resolver(matrizL, matrizU, columnaIdentidad);
           
            float[] respuestas = resolver.getArrayY();
            pasoApaso += "Las respuestas son: " + "\n";
            for (int n = 0; n < respuestas.length; n++){
                pasoApaso +=respuestas[n] + "\n";
            }
            pasoApaso += "Ahora se calcula la columna de la matriz inversa con: U * columnaInversa = (z1,z2...zn)" + "\n";
            pasoApaso += matrizU.toString() + " * (I^-1 " + i + "1... + " + "I^-1 " + i + "n = " + "\n";
            String respuesta = "[";
            for (int y = 0; y < respuestas.length; y++){
                respuesta += respuestas[y] + ",";
            }
            respuesta += "]" + "\n";
            pasoApaso += respuesta;
            pasoApaso += "Se resuelve el nuevo sistema de ecuaciones" + "\n";
            
            float[] respuestas2 = resolver.getArrayX();
            pasoApaso += "Las respuestas son: ";
            
            for (int k = 0; k < respuestas2.length;k++){
                pasoApaso += respuestas2[k] + "\n";
                inversa.setElement(k, i, respuestas2[k]);
            }
        }
        pasoApaso += "La matriz inversa resultado es: " + "\n";
        pasoApaso += inversa.toString() + "\n";
        
    }catch (Exception e){
       JOptionPane.showMessageDialog(null, "La matriz inversa no se pudo calcular");
    }
}

public Matriz getInversa(){
    return inversa;
}

public String getPasoApaso(){
    return pasoApaso;
}


   
}
