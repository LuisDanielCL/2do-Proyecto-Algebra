/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matriz;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JACK
 */
public class Matriz {

    float[][] matriz ;   
    public  Matriz(int filas, int columnas) throws Exception { 
        if (((5<filas)||(filas<0)) || ((5<columnas) || (columnas<0))){
            throw new Exception ();
        }
        float[][] matrizTemp = new float[filas][columnas];
        matriz = matrizTemp;
    }
    
    public void agregarValor(int fila, int columna, float valor){
        matriz[fila][columna] = valor;
        
    }
    
    public int largoFila(){
        return matriz.length;
    }
    
    public int largoColumna(){
        return matriz[0].length;
    }
    
    public float getElement(int i, int j){
        return matriz[i][j];
    }
    
    public void setElement(int i, int j, float element){
        matriz[i][j] = element;
    }
    @Override
    public String toString(){
        String salida = "";
        for(int i=0; i<matriz.length; i++){
         salida += "|";
          for(int j=0; j<matriz[0].length; j++){
              salida += String.format("%.2f", matriz[i][j])+ "\t";
            }

              salida += "|\n";
          
          
        }
        return salida;
    }
    public void identidad(){
            // Llenar matriz identidad -------------------
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    if (i == j) {
                        matriz[i][j] = 1;
                    }else{
                        matriz[i][j] = 0;
                    }
                }
            }
    }
    public int getMasLargo() {
        int salida = 0;
        String comparar="";
        for(int i=0; i<matriz.length; i++){
         
          for(int j=0; j<matriz[0].length; j++){
              
              comparar = String.format("%.2f",matriz[i][j]);
              if (salida<comparar.length()){
                  salida = comparar.length();
              }
            }  
        }
        return salida;
    }

    void copiar(Matriz pMatriz) {
        for (int i=0; i<matriz.length; i++){
            for (int j=0; j<matriz[0].length; j++){
                matriz[i][j] = pMatriz.getElement(i, j);
            }
        }
    }
    
    float[] getColumna (int columna){
        float[] respuesta = new float[this.largoColumna()];
        for (int i = 0; i < this.largoColumna(); i++){
            respuesta[i] = this.getElement(i, columna);
        }
        return respuesta;
    }
}