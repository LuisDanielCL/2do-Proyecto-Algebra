/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion2;

import Matriz.AddMatriz;
import Matriz.AddMatriz3;
import Matriz.FactorizacionLU;
import Matriz.Matriz;
import Matriz.ResolverSistemas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectoalgebra.ProyectoAlgebraMain;

/**
 *
 * @author LuisDanielCL
 */
public class mainApp3 extends javax.swing.JFrame {

    /**
     * Creates new form mainApp2
     */
    
    public Matriz matriz;
    FactorizacionLU opereacion = new FactorizacionLU();
    ResolverSistemas resolvedor = new ResolverSistemas();
    float[] arrayB;
    public mainApp3(ProyectoAlgebraMain ventanaP) {
        initComponents();
        btnB.setEnabled(false);
    }
    
    public void actualizarMatriz(){
        TXTmatriz.setTabSize(matriz.getMasLargo());
        TXTmatriz.setText(matriz.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TXTmatriz = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTmatrizU = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        TXTmatrizL = new javax.swing.JTextArea();
        btnB = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtB = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtX = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtY = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTmatriz.setColumns(20);
        TXTmatriz.setRows(5);
        jScrollPane1.setViewportView(TXTmatriz);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 245, 141));

        jButton1.setText("Llenar matriz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jButton2.setText("Factorización LU");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 194, -1, -1));

        jLabel1.setText("Matriz U");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jLabel2.setText("Matriz L");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        TXTmatrizU.setColumns(20);
        TXTmatrizU.setRows(5);
        jScrollPane2.setViewportView(TXTmatrizU);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 245, 141));

        TXTmatrizL.setColumns(20);
        TXTmatrizL.setRows(5);
        jScrollPane3.setViewportView(TXTmatrizL);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 245, 141));

        btnB.setText("Determinar B");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });
        getContentPane().add(btnB, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        txtB.setColumns(20);
        txtB.setRows(5);
        jScrollPane4.setViewportView(txtB);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 50, 140));

        txtX.setColumns(20);
        txtX.setRows(5);
        jScrollPane5.setViewportView(txtX);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 80, 140));

        txtY.setColumns(20);
        txtY.setRows(5);
        jScrollPane6.setViewportView(txtY);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 80, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String sizeS = JOptionPane.showInputDialog("Digite el tamaño de la matriz, debe ser menor o igual a 5.");
            int size = Integer.parseInt(sizeS);
            if ((size > 5) || (size < 1)){
                JOptionPane.showMessageDialog(null,"Tamaño invalido");
            }else{
                AddMatriz3 agregarVentana = new AddMatriz3(size,size,this);
                agregarVentana.setVisible(true);
                this.setEnabled(false);
                arrayB = new float[size];
                btnB.setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
            opereacion.factorizar(matriz);
            TXTmatrizU.setText(opereacion.getMatrizU().toString());
            TXTmatrizL.setText(opereacion.getMatrizL().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
         
         resolvedor.resolver(opereacion.getMatrizL(), opereacion.getMatrizU(), arrayB);
         
         float[] arrayY = resolvedor.getArrayY();
         float[] arrayX = resolvedor.getArrayX();
         
         String StrY = "";
         for (int i = 0; i < arrayY.length; i++) {
             int num = i+1;
            StrY += "Y" + num + ": " + arrayY[i]+"\n";
        }
         txtY.setText(StrY);
         
         String StrX = "";
         for (int i = 0; i < arrayX.length; i++) {
             int num = i+1;
            StrX += "X" + num + ": " + arrayX[i]+"\n";
        }
         txtX.setText(StrX);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        String StrB = "";
        for (int i = 0; i < arrayB.length; i++) {
            int ec = i+1;
            String valor = JOptionPane.showInputDialog("Digite el valor para la ecuacion "+ ec);
            arrayB[i] = Integer.parseInt(valor);
            StrB += valor + "\n";
        }     
        txtB.setText(StrB);
    }//GEN-LAST:event_btnBActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TXTmatriz;
    private javax.swing.JTextArea TXTmatrizL;
    private javax.swing.JTextArea TXTmatrizU;
    private javax.swing.JButton btnB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea txtB;
    private javax.swing.JTextArea txtX;
    private javax.swing.JTextArea txtY;
    // End of variables declaration//GEN-END:variables
}

