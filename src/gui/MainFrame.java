package gui;

import graph.Generator;
import graph.GraphReader;
import alg.Annealing;
import alg.CoolingSchedule;

import java.io.FileNotFoundException;
import java.util.Arrays;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;


public class MainFrame extends javax.swing.JFrame {

	
	
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
		setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Liczba wierzchołków grafu jednego przedzialu:"); //MARTA: bo to w sumie polowa liczby wierzcholkow grafu

       // jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Zakres wag krawędzi. Od:");

       // jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jTextField3ActionPerformed(evt);
                
            }
        });

        jLabel3.setText("do:");

      //  jTextField3.setText("jTextField3");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
                
            }
        });

        
        
        jToggleButton2.setText("Generuj graf i uruchom");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                	int ilosc_wierz = Integer.parseInt(jTextField1.getText());
                	int wag_min = Integer.parseInt(jTextField2.getText());
                	int wag_max = Integer.parseInt(jTextField3.getText());
                
	                
					//sprawdzanie
					if(ilosc_wierz < 1) {
						javax.swing.JOptionPane.showMessageDialog(getParent(), 
								"Liczba wierzcholkow jednego przedzialu grafu powinna wynosic co najmniej 1",
								"Blad",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(wag_min < 1) {
						javax.swing.JOptionPane.showMessageDialog(getParent(), 
								"Minimalna dozwolona waga krawedzi to 1",
								"Blad",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(wag_min >= wag_max) {
						javax.swing.JOptionPane.showMessageDialog(getParent(), 
								"Maksymalna waga krawedzi powinna byc wieksza od minimalnej",
								"Blad",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
	                
	                Generator.generateGraph(ilosc_wierz, wag_min, wag_max);
	                int[][] gg = null;
	                String [][] dg = null;
	                int [] results = null;
	                
					try {					
						//if()
						gg = GraphReader.readFile(ilosc_wierz, wag_min, wag_max);   //graf jako macierz
						dg = GraphReader.displayGraph(gg);                          //stringi z inf o grafie (tylko do wyswietlenia)
						
						//nie wiem jak to ?
						results =  Annealing.findSol(gg, CoolingSchedule.COOLING_LINEAR);
						//results =  Annealing.findSol(gg, CoolingSchedule.COOLING_GEOMETRICAL);
						//results =  Annealing.findSol(gg, CoolingSchedule.COOLING_LOGARITHMIC);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
	                
	                jTextPane1.append(Arrays.deepToString(dg));
	                
	                String res = Annealing.chooseRes(results);
	                
	                jTextPane2.append(res);
            }

            catch(NumberFormatException e) { //Boze
            	javax.swing.JOptionPane.showMessageDialog(getParent(), 
						"Zly format danych wejsciowych",
						"Blad",
						JOptionPane.ERROR_MESSAGE);
            }
        }
        });
        
        
        jRadioButton1.setText("Funkcja liniowa");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Funkcja geometryczna");

        jRadioButton3.setText("Funkcja logarytmiczna");
        
        
        
        

        jToggleButton1.setText("po co ten przycisk? :/");

        jScrollPane1.setViewportView(jTextPane1);

        jLabel5.setText("Rozwiązanie:");

        jScrollPane2.setViewportView(jTextPane2);

        
        
        jButton1.setText("Wykres");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1(evt);
               
                
            }
        });
        
        
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton2)
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(391, 391, 391))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel5)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton2)
                    .addComponent(jRadioButton3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addGap(38, 38, 38)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel4))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jButton1(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }          
    
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    } 

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
          
        
    }
    
    
    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextArea jTextPane1;
    private javax.swing.JTextArea jTextPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
                      
}
