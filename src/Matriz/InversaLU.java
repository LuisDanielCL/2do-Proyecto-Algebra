/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Matriz;

import java.util.ArrayList;
import java.util.Arrays;

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
            System.out.println("--------------------");
            System.out.println("Vamos a encontrar la columna: " + i + " de la matriz inversa");
            System.out.println("Sacamos la columna: " + i + " de la matriz identidad");
            System.out.println("MatrizL * (z1,z2..zn) = columnaIdentidad");
            System.out.print(matrizL.toString()+ "  * (z1,z2...zn) = ");
            float[] columnaIdentidad = identidad.getColumna(i);
            String columna = "[";
            for (int m = 0; m < columnaIdentidad.length; m++){
                System.out.print(columnaIdentidad[m] + ",");
            }
            System.out.print("]" + "\n");
            System.out.println("Se resuelve el sistema de ecuaciones...");
            resolver.resolver(matrizL, matrizU, columnaIdentidad);
           
            float[] respuestas = resolver.getArrayY();
            System.out.println("Las respuestas son: ");
            for (int n = 0; n < respuestas.length; n++){
                System.out.println(respuestas[n]);
            }
            System.out.println("Ahora se calcula la columna de la matriz inversa con: U * columnaInversa = (z1,z2...zn)");
            System.out.print(matrizU.toString() + "   * (I^-1 " + i + "1... + " + "I^-1 " + i + "n = " );
            String respuesta = "[";
            for (int y = 0; y < respuestas.length; y++){
                respuesta += respuestas[y] + ",";
            }
            respuesta += "]" + "\n";
            System.out.print(respuesta);
            System.out.println("Se resuelve el nuevo sistema de ecuaciones");
            
            float[] respuestas2 = resolver.getArrayX();
            System.out.println("Las respuestas son: ");
            
            for (int k = 0; k < respuestas2.length;k++){
                System.out.println(respuestas2[k]);
                inversa.setElement(k, i, respuestas2[k]);
            }
        }
        System.out.println("La matriz inversa resultado es: ");
        System.out.println(inversa.toString());
        
    }catch (Exception e){
       System.out.println(e);
    }
}

public Matriz getInversa(){
    return inversa;
}


   
}
