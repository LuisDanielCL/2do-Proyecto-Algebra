/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matriz;

/**
 *
 * @author Jack
 */
public class ResolverSistemas {
    Matriz matrizL;
    Matriz matrizU;
    float[] arrayB;
    float[] arrayY;
    float[] arrayX;
    String pasoY = "";
    String pasoX = "";

    public ResolverSistemas() {
       
    }
    
    public void resolver(Matriz pMatrizL, Matriz pMatrizU, float[] pArrayB){
        matrizL = pMatrizL;
        matrizU = pMatrizU;
        arrayB = pArrayB;
        arrayY = new float[arrayB.length];
        arrayX = new float[arrayB.length];
        
        encontrarY();
        encontrarX();
    }
    
    public void encontrarY(){
        arrayY[0] = arrayB[0]/matrizL.getElement(0, 0);
        pasoY += "Y1 = "+arrayB[0]+" / "+matrizL.getElement(0, 0)+" = "+arrayY[0]+"\n";
        int Y = 2;
        for (int i = 1; i < arrayB.length; i++) {
            pasoY += "Y"+Y+" = (1 / "+matrizL.getElement(i, i)+") * ("+arrayB[i];
            Y++;
            float aux = arrayB[i];
            for (int j = 0; j < arrayB.length-1; j++) {
                aux -= matrizL.getElement(i, j)*arrayY[j];
                pasoY += " - "+matrizL.getElement(i, j)+" * "+arrayY[j];
            }
            arrayY[i] = 1/matrizL.getElement(i, i) * aux;
            pasoY += ") = "+arrayY[i]+"\n";
        }
    }
    
    public void encontrarX(){
        arrayX[arrayX.length-1] = arrayY[arrayY.length-1]/matrizU.getElement(arrayY.length-1, arrayY.length-1);
        pasoX += "X"+arrayX.length+" = "+arrayY[arrayY.length-1]+" / "+matrizU.getElement(arrayY.length-1, arrayY.length-1)+" = "+arrayX[arrayX.length-1]+"\n";
        int X = arrayX.length-1;
        for (int i = arrayY.length-2; i >= 0 ; i--) {
            pasoX += "X"+X+" = (1 / "+matrizU.getElement(i, i)+") * ("+arrayY[i];
            X--;
            float aux = arrayY[i];
            for (int j = arrayY.length-1; j > 0; j--) {
                aux -= matrizU.getElement(i, j)*arrayX[j];
                pasoX += " - "+matrizU.getElement(i, j)+" * "+arrayX[j];
            }
            arrayX[i] = 1/matrizU.getElement(i, i) * aux;
            pasoX += ") = "+arrayX[i]+"\n";
        }
    }

    public float[] getArrayY() {
        return arrayY;
    }

    public float[] getArrayX() {
        return arrayX;
    }

    public String getPasoY() {
        return pasoY;
    }

    public String getPasoX() {
        return pasoX;
    }
    
    
    
}
