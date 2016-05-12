
package Aplicacion1;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import proyectoalgebra.ProyectoAlgebraMain;


public class menuAplicacionI extends javax.swing.JFrame {

    ArrayList <Vector> espacioVectorial = new ArrayList();
    boolean espacioDependiente;
    
    public menuAplicacionI() {
        initComponents();
    }
    
    public void Ld_Li(ArrayList <Vector> lista) {
        if (lista.get(0).getElementos().size() == 2) { // R2
            if (lista.size() == 1) {
                espacioDependiente = false;
                System.out.println("El espacio es linealmente independiente.");
            }
            if (lista.size() > 2) {
                espacioDependiente = true;
                System.out.println("El espacio es linealmente dependiente.");
            }
            else {
                SolucionLineal solucionemeEsta = new SolucionLineal(2);
                espacioDependiente = solucionemeEsta.solucion(lista);
            }
        }
        else { // R3
            if (lista.size() == 1) {
                espacioDependiente = false;
                System.out.println("El espacio es linealmente independiente.");
            }
            if (lista.size() > 3) {
                espacioDependiente = true;
                System.out.println("El espacio es linealmente dependiente.");
            }
            if (lista.size() == 3) {
                SolucionLineal solucionemeEsta = new SolucionLineal(3);
                espacioDependiente = solucionemeEsta.solucion(lista);
            }
            else { 
                boolean ceros = true;
                for (int i = 0; i < lista.size(); i++) {
                    ceros = true;
                    for (int j = 0; j < lista.get(0).getElementos().size(); j++) {
                        double numero = (double) lista.get(i).getElementos().get(j);
                        if (numero != 0) {
                            ceros = false;
                            break;
                        }
                    }
                    if (ceros == true) {
                        break;
                    }
                }   
                if (ceros == true) {
                    espacioDependiente = true;
                    System.out.println("Es linealmente dependiente.");
                }
                else {
                    espacioDependiente = gauss(lista);
                    if (espacioDependiente == true) {
                        System.out.println("Es linealmente dependiente.");
                    }
                    else {
                        System.out.println("Es linealmente independiente.");
                    }
                }
            }
        }
    }
    
    public String calcularBase() {
        boolean hayBase = false;
        ArrayList <Vector> espacio = new ArrayList();
        if (espacioVectorial.get(0).getElementos().size() == 2) { // Para R2
            for (int i = 0; i < espacioVectorial.size(); i++) {
                double elementoA = (double)espacioVectorial.get(i).getElementos().get(0);
                double elementoB = (double)espacioVectorial.get(i).getElementos().get(1);
                if (elementoA != 0 || elementoB != 0) {
                    espacio.add(espacioVectorial.get(i));
                    for (int j = i + 1; j < espacioVectorial.size(); j++) {
                        double elementoC = (double)espacioVectorial.get(j).getElementos().get(0);
                        double elementoD = (double)espacioVectorial.get(j).getElementos().get(1);
                        if (elementoC != 0 || elementoD != 0) {
                            espacio.add(espacioVectorial.get(j));
                            Ld_Li(espacio);
                            if (espacioDependiente == true) {
                                try {
                                    espacio.remove(j);
                                } catch (Exception E) {}
                                hayBase = false;
                            }
                            else {
                                hayBase = true;
                                break;
                            }
                        }
                    }
                }
                if (hayBase == true) {
                        break;
                }
                else {
                    espacio.clear();
                }
            }
            if (hayBase == true) {
                String base = "Base: {";
                for (int i = 0; i < espacio.size(); i++) {
                    base += " ( ";
                    for (int j = 0; j < espacio.get(i).getElementos().size(); j++) {
                        base += " " + espacio.get(i).getElementos().get(j) + " ";
                        if (j < (espacioVectorial.get(i).getElementos().size() - 1)) {
                            base += ",";
                        }
                    }
                    base += " ) ";
                }
                base += "}";
                System.out.println(base);
                return base;
            }
            else {
                return ("El espacio no posee base.");
            }
        }
        else { // Para R3
            for (int i = 0; i < espacioVectorial.size(); i++) {
                double elementoA = (double)espacioVectorial.get(i).getElementos().get(0);
                double elementoB = (double)espacioVectorial.get(i).getElementos().get(1);
                double elementoC = (double)espacioVectorial.get(i).getElementos().get(2);
                if (elementoA != 0 || elementoB != 0 || elementoC != 0) {
                    espacio.add(espacioVectorial.get(i));
                    for (int j = i + 1; j < espacioVectorial.size(); j++) {
                        double elementoD = (double)espacioVectorial.get(j).getElementos().get(0);
                        double elementoE = (double)espacioVectorial.get(j).getElementos().get(1);
                        double elementoF = (double)espacioVectorial.get(j).getElementos().get(2);
                        if (elementoD != 0 || elementoE != 0 || elementoF != 0) {
                            espacio.add(espacioVectorial.get(j));
                            Ld_Li(espacio);
                            if (espacioDependiente == true) {
                                try {
                                    espacio.remove(j);
                                } catch (Exception E) {}                                
                                hayBase = false;
                            }
                            else { 
                                if (espacio.size() == 3) {
                                    hayBase = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (hayBase == true) {
                        break;
                }
                else {
                    espacio.clear();
                }
            }
            if (hayBase == true) {
                String base = "Base: {";
                for (int i = 0; i < espacio.size(); i++) {
                    base += " ( ";
                    for (int j = 0; j < espacio.get(i).getElementos().size(); j++) {
                        base += " " + espacio.get(i).getElementos().get(j) + " ";
                        if (j < (espacioVectorial.get(i).getElementos().size() - 1)) {
                            base += ",";
                        }
                    }
                    base += " ) ";
                }
                base += "}";
                System.out.println(base);
                return base;
            }
            else {
                return ("El espacio no posee base.");
            }
        }
        
    }
    
    public boolean gauss(ArrayList <Vector> espacio){
        double [][] matriz = new double [3][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                matriz [i][j] = (double)espacio.get(j).getElementos().get(i);
            }
        }
        System.out.println(matriz[0][0]);
        System.out.println(matriz[1][0]);
        System.out.println(matriz[2][0]);
        System.out.println(matriz[0][1]);
        System.out.println(matriz[1][1]);
        System.out.println(matriz[2][1]);
        // Se empieza gauss
        for (int i = 1; i < 3; i++) {
            double valor = matriz[i][0];
            matriz [i][0] -= matriz[0][0] * (1 / matriz[0][0]) * valor;
            matriz [i][1] -= matriz[0][1] * (1 / matriz[0][0]) * valor;
        }
        System.out.println(matriz[0][0]);
        System.out.println(matriz[1][0]);
        System.out.println(matriz[2][0]);
        System.out.println(matriz[0][1]);
        System.out.println(matriz[1][1]);
        System.out.println(matriz[2][1]);
        
        if (matriz[1][0] == 0 && matriz[1][1] == 0 &&
            matriz[2][0] == 0 && matriz[2][1] == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        panelAlmacenar = new javax.swing.JPanel();
        vector00 = new javax.swing.JTextField();
        vector01 = new javax.swing.JTextField();
        vector02 = new javax.swing.JTextField();
        vector10 = new javax.swing.JTextField();
        vector11 = new javax.swing.JTextField();
        vector12 = new javax.swing.JTextField();
        vector20 = new javax.swing.JTextField();
        vector21 = new javax.swing.JTextField();
        vector22 = new javax.swing.JTextField();
        vector30 = new javax.swing.JTextField();
        vector31 = new javax.swing.JTextField();
        vector32 = new javax.swing.JTextField();
        vector40 = new javax.swing.JTextField();
        vector41 = new javax.swing.JTextField();
        vector42 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbR = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbNum = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        panelLineal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoVector = new javax.swing.JTextArea();
        labelLineal = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        panelBase = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoBaseVector = new javax.swing.JTextArea();
        labelBase = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        panelGen = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        tf2 = new javax.swing.JTextField();
        tf3 = new javax.swing.JTextField();
        tf4 = new javax.swing.JTextField();
        tf5 = new javax.swing.JTextField();
        tf6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf7 = new javax.swing.JTextField();
        tf8 = new javax.swing.JTextField();
        tf9 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel.setLayout(new java.awt.CardLayout());

        jButton1.setText("Almacenar Vectores");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Determinar si L.D o L.I");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Calcular la Base");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Gen {v1,v2}");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Regresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(150, 150, 150))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        Panel.add(panelMenu, "card2");

        jLabel1.setText("V1:");

        jLabel2.setText("V2:");

        jLabel3.setText("V3:");

        jLabel4.setText("V4:");

        jLabel5.setText("V5:");

        jButton6.setText("Regresar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setText("R:");

        cbR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3" }));
        cbR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRActionPerformed(evt);
            }
        });

        jLabel7.setText("Numero de Vectores:");

        cbNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        cbNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNumActionPerformed(evt);
            }
        });

        jButton7.setText("Almacenar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAlmacenarLayout = new javax.swing.GroupLayout(panelAlmacenar);
        panelAlmacenar.setLayout(panelAlmacenarLayout);
        panelAlmacenarLayout.setHorizontalGroup(
            panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlmacenarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(46, 46, 46)
                .addComponent(jButton6)
                .addContainerGap())
            .addGroup(panelAlmacenarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlmacenarLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbR, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAlmacenarLayout.createSequentialGroup()
                        .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(28, 28, 28)
                        .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAlmacenarLayout.createSequentialGroup()
                                .addComponent(vector40, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vector41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vector42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelAlmacenarLayout.createSequentialGroup()
                                    .addComponent(vector30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelAlmacenarLayout.createSequentialGroup()
                                    .addComponent(vector20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelAlmacenarLayout.createSequentialGroup()
                                    .addComponent(vector10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelAlmacenarLayout.createSequentialGroup()
                                    .addComponent(vector00, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector01, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(vector02, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1))))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panelAlmacenarLayout.setVerticalGroup(
            panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlmacenarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vector00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vector10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vector20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vector30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vector40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vector42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelAlmacenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel.add(panelAlmacenar, "card3");

        textoVector.setEditable(false);
        textoVector.setColumns(20);
        textoVector.setRows(5);
        jScrollPane1.setViewportView(textoVector);

        labelLineal.setText("jLabel8");

        jButton8.setText("Regresar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLinealLayout = new javax.swing.GroupLayout(panelLineal);
        panelLineal.setLayout(panelLinealLayout);
        panelLinealLayout.setHorizontalGroup(
            panelLinealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLinealLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelLinealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(labelLineal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLinealLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(151, 151, 151))
        );
        panelLinealLayout.setVerticalGroup(
            panelLinealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLinealLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(labelLineal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(41, 41, 41))
        );

        Panel.add(panelLineal, "card4");

        textoBaseVector.setEditable(false);
        textoBaseVector.setColumns(20);
        textoBaseVector.setRows(5);
        jScrollPane2.setViewportView(textoBaseVector);

        labelBase.setText("jLabel8");

        jButton9.setText("Regresar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBaseLayout = new javax.swing.GroupLayout(panelBase);
        panelBase.setLayout(panelBaseLayout);
        panelBaseLayout.setHorizontalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(labelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBaseLayout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(151, 151, 151))
        );
        panelBaseLayout.setVerticalGroup(
            panelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(labelBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(41, 41, 41))
        );

        Panel.add(panelBase, "card5");

        jLabel8.setText("Vectores del Gen:");

        jLabel9.setText("Vector 1:");

        jLabel10.setText("Vector 2:");

        jLabel11.setText("Vector a Probar:");

        tf8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf8ActionPerformed(evt);
            }
        });

        jButton10.setText("Pertenece???");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Regresar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGenLayout = new javax.swing.GroupLayout(panelGen);
        panelGen.setLayout(panelGenLayout);
        panelGenLayout.setHorizontalGroup(
            panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addGroup(panelGenLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton10)
                        .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(tf9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelGenLayout.createSequentialGroup()
                        .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGenLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGenLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf4))
                            .addComponent(tf7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGenLayout.createSequentialGroup()
                                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(tf5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(tf3)))
                            .addComponent(tf8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelGenLayout.setVerticalGroup(
            panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        Panel.add(panelGen, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ProyectoAlgebraMain main = new ProyectoAlgebraMain();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRActionPerformed
        cbNum.setSelectedIndex(cbNum.getSelectedIndex());
        int indice = cbR.getSelectedIndex();
        if (indice < 1) {
            vector02.setEnabled(false);
            vector12.setEnabled(false);
            vector22.setEnabled(false);
            vector32.setEnabled(false);
            vector42.setEnabled(false);
        }
        else {
            int i = cbNum.getSelectedIndex();
            vector02.setEnabled(true);
            vector12.setEnabled(false);
            vector22.setEnabled(false);
            vector32.setEnabled(false);
            vector42.setEnabled(false);
            if (i >= 1) {
            vector12.setEnabled(true);}
            if (i >= 2) {
            vector22.setEnabled(true);}
            if (i >= 3) {
            vector32.setEnabled(true);}
            if (i == 4) {
            vector42.setEnabled(true);}
        }
    }//GEN-LAST:event_cbRActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cbR.setSelectedIndex(0);
        vector00.setText("");
        vector01.setText("");
        vector02.setText("");
        vector10.setText("");
        vector11.setText("");
        vector12.setText("");
        vector20.setText("");
        vector21.setText("");
        vector22.setText("");
        vector30.setText("");
        vector31.setText("");
        vector32.setText("");
        vector40.setText("");
        vector41.setText("");
        vector42.setText("");
        Panel.removeAll();
        Panel.add(panelAlmacenar);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNumActionPerformed
        int indice = cbNum.getSelectedIndex();
        if (indice < 1) {
            vector10.setEnabled(false);
            vector11.setEnabled(false);
            vector12.setEnabled(false);
            vector20.setEnabled(false);
            vector21.setEnabled(false);
            vector22.setEnabled(false);
            vector30.setEnabled(false);
            vector31.setEnabled(false);
            vector32.setEnabled(false);
            vector40.setEnabled(false);
            vector41.setEnabled(false);
            vector42.setEnabled(false);
        }
        if (indice == 1) {
            vector10.setEnabled(true);
            vector11.setEnabled(true);
            vector12.setEnabled(true);
            vector20.setEnabled(false);
            vector21.setEnabled(false);
            vector22.setEnabled(false);
            vector30.setEnabled(false);
            vector31.setEnabled(false);
            vector32.setEnabled(false);
            vector40.setEnabled(false);
            vector41.setEnabled(false);
            vector42.setEnabled(false);
        }
        if (indice == 2) {
            vector10.setEnabled(true);
            vector11.setEnabled(true);
            vector12.setEnabled(true);
            vector20.setEnabled(true);
            vector21.setEnabled(true);
            vector22.setEnabled(true);
            vector30.setEnabled(false);
            vector31.setEnabled(false);
            vector32.setEnabled(false);
            vector40.setEnabled(false);
            vector41.setEnabled(false);
            vector42.setEnabled(false);
        }
        if (indice == 3) {
            vector10.setEnabled(true);
            vector11.setEnabled(true);
            vector12.setEnabled(true);
            vector20.setEnabled(true);
            vector21.setEnabled(true);
            vector22.setEnabled(true);
            vector30.setEnabled(true);
            vector31.setEnabled(true);
            vector32.setEnabled(true);
            vector40.setEnabled(false);
            vector41.setEnabled(false);
            vector42.setEnabled(false);
        }
        if (indice == 4) {
            vector10.setEnabled(true);
            vector11.setEnabled(true);
            vector12.setEnabled(true);
            vector20.setEnabled(true);
            vector21.setEnabled(true);
            vector22.setEnabled(true);
            vector30.setEnabled(true);
            vector31.setEnabled(true);
            vector32.setEnabled(true);
            vector40.setEnabled(true);
            vector41.setEnabled(true);
            vector42.setEnabled(true);
        }
        cbR.setSelectedIndex(cbR.getSelectedIndex());
    }//GEN-LAST:event_cbNumActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Panel.removeAll();
        Panel.add(panelMenu);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            espacioVectorial.clear();
            int indiceR = cbR.getSelectedIndex();
            int indiceNum = cbNum.getSelectedIndex();
            ArrayList elemV1 = new ArrayList();
            ArrayList elemV2 = new ArrayList();
            ArrayList elemV3 = new ArrayList();
            ArrayList elemV4 = new ArrayList();
            ArrayList elemV5 = new ArrayList();
            Vector vector1;
            if (indiceR < 1) { // Con R2
                System.out.println("R2");
                double elemento00 = Double.parseDouble(vector00.getText());
                double elemento01 = Double.parseDouble(vector01.getText());
                System.out.println(elemento00);
                System.out.println(elemento01);
                elemV1.add(elemento00);
                elemV1.add(elemento01);
                vector1 = new Vector(elemV1);
                espacioVectorial.add(vector1);
                if (indiceNum >= 1) {
                    double elemento10 = Double.parseDouble(vector10.getText());
                    double elemento11 = Double.parseDouble(vector11.getText());
                    elemV2.add(elemento10);
                    elemV2.add(elemento11);
                    Vector vector2 = new Vector(elemV2);
                    espacioVectorial.add(vector2);
                }
                if (indiceNum >= 2) {
                    double elemento20 = Double.parseDouble(vector20.getText());
                    double elemento21 = Double.parseDouble(vector21.getText());
                    elemV3.add(elemento20);
                    elemV3.add(elemento21);
                    Vector vector3 = new Vector(elemV3);
                    espacioVectorial.add(vector3);
                }
                if(indiceNum >= 3) {
                    double elemento30 = Double.parseDouble(vector30.getText());
                    double elemento31 = Double.parseDouble(vector31.getText());
                    elemV4.add(elemento30);
                    elemV4.add(elemento31);
                    Vector vector4 = new Vector(elemV4);
                    espacioVectorial.add(vector4);
                }
                if (indiceNum == 4) {
                    double elemento40 = Double.parseDouble(vector40.getText());
                    double elemento41 = Double.parseDouble(vector41.getText());
                    elemV5.add(elemento40);
                    elemV5.add(elemento41);
                    Vector vector5 = new Vector(elemV5);
                    espacioVectorial.add(vector5);
                }
            }
            else { // Con R3
                System.out.println("R3");
                double elemento00 = Double.parseDouble(vector00.getText());
                double elemento01 = Double.parseDouble(vector01.getText());
                double elemento02 = Double.parseDouble(vector02.getText());
                elemV1.add(elemento00);
                elemV1.add(elemento01);
                elemV1.add(elemento02);
                vector1 = new Vector(elemV1);
                espacioVectorial.add(vector1);
                if (indiceNum >= 1) {
                    double elemento10 = Double.parseDouble(vector10.getText());
                    double elemento11 = Double.parseDouble(vector11.getText());
                    double elemento12 = Double.parseDouble(vector12.getText());
                    elemV2.add(elemento10);
                    elemV2.add(elemento11);
                    elemV2.add(elemento12);
                    Vector vector2 = new Vector(elemV2);
                    espacioVectorial.add(vector2);
                }
                if (indiceNum >= 2) {
                    double elemento20 = Double.parseDouble(vector20.getText());
                    double elemento21 = Double.parseDouble(vector21.getText());
                    double elemento22 = Double.parseDouble(vector22.getText());
                    elemV3.add(elemento20);
                    elemV3.add(elemento21);
                    elemV3.add(elemento22);
                    Vector vector3 = new Vector(elemV3);
                    espacioVectorial.add(vector3);
                }
                if(indiceNum >= 3) {
                    double elemento30 = Double.parseDouble(vector30.getText());
                    double elemento31 = Double.parseDouble(vector31.getText());
                    double elemento32 = Double.parseDouble(vector32.getText());
                    elemV4.add(elemento30);
                    elemV4.add(elemento31);
                    elemV4.add(elemento32);
                    Vector vector4 = new Vector(elemV4);
                    espacioVectorial.add(vector4);
                }
                if (indiceNum == 4) {
                    double elemento40 = Double.parseDouble(vector40.getText());
                    double elemento41 = Double.parseDouble(vector41.getText());
                    double elemento42 = Double.parseDouble(vector42.getText());
                    elemV5.add(elemento40);
                    elemV5.add(elemento41);
                    elemV5.add(elemento42);
                    Vector vector5 = new Vector(elemV5);
                    espacioVectorial.add(vector5);
                }
            }
            JOptionPane.showMessageDialog(null, "Los vectores se han guardado.");
            Panel.removeAll();
            Panel.add(panelMenu);
            Panel.repaint();
            Panel.revalidate();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algun dato quedo vacio o no corresponde a un numero.");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Panel.removeAll();
        Panel.add(panelMenu);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (espacioVectorial.size() == 0) {
            JOptionPane.showMessageDialog(null, "Debe almacenar almenos un vector para ingresar a esta seccion.");
        }
        else {
            String espacio = "{";
            
            for (int i = 0; i < espacioVectorial.size(); i++) {
                espacio += " ( ";
                for (int j = 0; j < espacioVectorial.get(i).getElementos().size(); j++) {
                    espacio += " " + espacioVectorial.get(i).getElementos().get(j) + " ";
                    if (j < (espacioVectorial.get(i).getElementos().size() - 1)) {
                        espacio += ",";
                    }
                }
                espacio += " ) ";
                if (i < (espacioVectorial.size() - 1)) {
                    espacio += "\n ";
                }
            }
            espacio += "}";
            Ld_Li(espacioVectorial);
            if (espacioDependiente == true) {
                labelLineal.setText("El espacio es linealmente dependiente.");
            }
            else {
                labelLineal.setText("El espacio es linealmente independiente.");
            }
            textoVector.setText(espacio);
            Panel.removeAll();
            Panel.add(panelLineal);
            Panel.repaint();
            Panel.revalidate();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Panel.removeAll();
        Panel.add(panelMenu);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (espacioVectorial.size() == 0) {
            JOptionPane.showMessageDialog(null, "Debe almacenar almenos un vector para ingresar a esta seccion.");
        }
        else {
            String espacio = "{";
            
            for (int i = 0; i < espacioVectorial.size(); i++) {
                espacio += " ( ";
                for (int j = 0; j < espacioVectorial.get(i).getElementos().size(); j++) {
                    espacio += " " + espacioVectorial.get(i).getElementos().get(j) + " ";
                    if (j < (espacioVectorial.get(i).getElementos().size() - 1)) {
                        espacio += ",";
                    }
                }
                espacio += " ) ";
                if (i < (espacioVectorial.size() - 1)) {
                    espacio += "\n ";
                }
            }
            espacio += "}";
            textoBaseVector.setText(espacio);
            String resultado = calcularBase();
            labelBase.setText(resultado);
            Panel.removeAll();
            Panel.add(panelBase);
            Panel.repaint();
            Panel.revalidate();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Panel.removeAll();
        Panel.add(panelMenu);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
        tf8.setText("");
        tf9.setText("");
        Panel.removeAll();
        Panel.add(panelGen);
        Panel.repaint();
        Panel.revalidate();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tf8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            ArrayList <Vector> gen = new ArrayList();
            double elem1 = Double.parseDouble(tf1.getText());
            double elem2 = Double.parseDouble(tf2.getText());
            double elem3 = Double.parseDouble(tf3.getText());
            ArrayList elemV1 = new ArrayList();
            elemV1.add(elem1);
            elemV1.add(elem2);
            elemV1.add(elem3);
            Vector vector1 = new Vector(elemV1);
            gen.add(vector1);
            double elem4 = Double.parseDouble(tf4.getText());
            double elem5 = Double.parseDouble(tf5.getText());
            double elem6 = Double.parseDouble(tf6.getText());
            ArrayList elemV2 = new ArrayList();
            elemV2.add(elem4);
            elemV2.add(elem5);
            elemV2.add(elem6);
            Vector vector2 = new Vector(elemV2);
            gen.add(vector2);
            Ld_Li(gen);
            if (espacioDependiente == true) {
                JOptionPane.showMessageDialog(null, "Los vectores pertenecientes al gen no conforman un espacio L.I.");
            }
            else {
                double elem7 = Double.parseDouble(tf7.getText());
                double elem8 = Double.parseDouble(tf8.getText());
                double elem9 = Double.parseDouble(tf9.getText());
                ArrayList elemV3 = new ArrayList();
                elemV3.add(elem7);
                elemV3.add(elem8);
                elemV3.add(elem9);
                Vector vector3 = new Vector(elemV3);
                gen.add(vector3);
                Ld_Li(gen);
                if (espacioDependiente == true) {
                    JOptionPane.showMessageDialog(null, "El vector ( " + elem7 + " , " + elem8 + " , " + elem9 + " )" 
                            + " NO pertenece al gen de los vectores indicados.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "El vector ( " + elem7 + " , " + elem8 + " , " + elem9 + " )" 
                            + " SI pertenece al gen de los vectores indicados.");
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se dejo un espacio vacio o algun valor no corresponde a un numero.");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuAplicacionI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuAplicacionI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuAplicacionI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuAplicacionI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuAplicacionI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JComboBox<String> cbNum;
    private javax.swing.JComboBox<String> cbR;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelBase;
    private javax.swing.JLabel labelLineal;
    private javax.swing.JPanel panelAlmacenar;
    private javax.swing.JPanel panelBase;
    private javax.swing.JPanel panelGen;
    private javax.swing.JPanel panelLineal;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTextArea textoBaseVector;
    private javax.swing.JTextArea textoVector;
    private javax.swing.JTextField tf1;
    private javax.swing.JTextField tf2;
    private javax.swing.JTextField tf3;
    private javax.swing.JTextField tf4;
    private javax.swing.JTextField tf5;
    private javax.swing.JTextField tf6;
    private javax.swing.JTextField tf7;
    private javax.swing.JTextField tf8;
    private javax.swing.JTextField tf9;
    private javax.swing.JTextField vector00;
    private javax.swing.JTextField vector01;
    private javax.swing.JTextField vector02;
    private javax.swing.JTextField vector10;
    private javax.swing.JTextField vector11;
    private javax.swing.JTextField vector12;
    private javax.swing.JTextField vector20;
    private javax.swing.JTextField vector21;
    private javax.swing.JTextField vector22;
    private javax.swing.JTextField vector30;
    private javax.swing.JTextField vector31;
    private javax.swing.JTextField vector32;
    private javax.swing.JTextField vector40;
    private javax.swing.JTextField vector41;
    private javax.swing.JTextField vector42;
    // End of variables declaration//GEN-END:variables
}
