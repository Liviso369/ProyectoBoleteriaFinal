/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Familia
 */
public class Menu extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Menu
     */
    private JMenuBar menuBar;

    private JMenu menu1, menu2, menu3, menu4, menu5, menu6;

    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6, menuItem7, menuItem8, menuItem9, menuItem10, menuItem11;

    private int xMouse;

    private int yMouse;

    public Menu() {
        initComponents();
        contruirMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpBackgroud = new javax.swing.JPanel();
        jpContenedorMenuBar = new javax.swing.JPanel();
        jpBarra = new javax.swing.JPanel();
        jpSalir = new javax.swing.JPanel();
        jlSalir = new javax.swing.JLabel();
        jpLogoBarra = new javax.swing.JPanel();
        jlLogoBarra = new javax.swing.JLabel();
        jpContenedor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setPreferredSize(new java.awt.Dimension(1000, 600));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedorMenuBar.setBackground(new java.awt.Color(11, 7, 17));
        jpContenedorMenuBar.setPreferredSize(new java.awt.Dimension(1000, 40));

        javax.swing.GroupLayout jpContenedorMenuBarLayout = new javax.swing.GroupLayout(jpContenedorMenuBar);
        jpContenedorMenuBar.setLayout(jpContenedorMenuBarLayout);
        jpContenedorMenuBarLayout.setHorizontalGroup(
            jpContenedorMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jpContenedorMenuBarLayout.setVerticalGroup(
            jpContenedorMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jpBackgroud.add(jpContenedorMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1000, -1));

        jpBarra.setBackground(new java.awt.Color(0, 0, 0));
        jpBarra.setPreferredSize(new java.awt.Dimension(1000, 40));
        jpBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpBarraMouseDragged(evt);
            }
        });
        jpBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpBarraMousePressed(evt);
            }
        });

        jpSalir.setBackground(new java.awt.Color(0, 0, 0));

        jlSalir.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jlSalir.setForeground(new java.awt.Color(255, 255, 255));
        jlSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlSalir.setText("X");
        jlSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlSalir.setPreferredSize(new java.awt.Dimension(30, 30));
        jlSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlSalirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpSalirLayout = new javax.swing.GroupLayout(jpSalir);
        jpSalir.setLayout(jpSalirLayout);
        jpSalirLayout.setHorizontalGroup(
            jpSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSalirLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpSalirLayout.setVerticalGroup(
            jpSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSalirLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpLogoBarra.setBackground(new java.awt.Color(0, 0, 0));

        jlLogoBarra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlLogoBarra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/icono.png"))); // NOI18N
        jlLogoBarra.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jpLogoBarraLayout = new javax.swing.GroupLayout(jpLogoBarra);
        jpLogoBarra.setLayout(jpLogoBarraLayout);
        jpLogoBarraLayout.setHorizontalGroup(
            jpLogoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLogoBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlLogoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpLogoBarraLayout.setVerticalGroup(
            jpLogoBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLogoBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlLogoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpBarraLayout = new javax.swing.GroupLayout(jpBarra);
        jpBarra.setLayout(jpBarraLayout);
        jpBarraLayout.setHorizontalGroup(
            jpBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBarraLayout.createSequentialGroup()
                .addComponent(jpLogoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(940, 940, 940)
                .addComponent(jpSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpBarraLayout.setVerticalGroup(
            jpBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpLogoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jpSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jpBackgroud.add(jpBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 30));

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/Logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BOLETERIA");

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(309, Short.MAX_VALUE))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabel2)))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jpBackgroud.add(jpContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1000, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBarraMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jpBarraMousePressed

    private void jpBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBarraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jpBarraMouseDragged

    private void jlSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jlSalirMouseClicked

    private void jlSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlSalirMouseEntered
        jpSalir.setBackground(Color.RED);
    }//GEN-LAST:event_jlSalirMouseEntered

    private void jlSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlSalirMouseExited
        jpSalir.setBackground(Color.BLACK);
    }//GEN-LAST:event_jlSalirMouseExited

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
        loadLookAndFeel();
    }

    private void contruirMenu() {
        menuBar = new JMenuBar();
        menuBar.setSize(1000, 43);
        menuBar.setLocation(0, 0);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(11, 7, 17)));
        jpContenedorMenuBar.removeAll();
        jpContenedorMenuBar.add(menuBar, BorderLayout.CENTER);
        jpContenedorMenuBar.revalidate();
        jpContenedorMenuBar.repaint();

        menu1 = new JMenu("Cliente");
        menuBar.add(menu1);
        menu1.setFont(new Font("Times New Roman", 0, 22));
        menu1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(167, 167, 167)));
        menu1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem1 = new JMenuItem("Formulario");
        menu1.add(menuItem1);
        menuItem1.setFont(new Font("Times New Roman", 0, 20));
        menuItem1.setBorder(null);
        menuItem1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem1.addActionListener(this);

        menuItem2 = new JMenuItem("Control");
        menu1.add(menuItem2);
        menuItem2.setFont(new Font("Times New Roman", 0, 20));
        menuItem2.setBorder(null);
        menuItem2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem2.addActionListener(this);

        //////////////////////////////////////////////////////
        menu2 = new JMenu("Ticket");
        menuBar.add(menu2);
        menu2.setFont(new Font("Times New Roman", 0, 22));
        menu2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(167, 167, 167)));
        menu2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem3 = new JMenuItem("Formulario");
        menu2.add(menuItem3);
        menuItem3.setFont(new Font("Times New Roman", 0, 20));
        menuItem3.setBorder(null);
        menuItem3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem3.addActionListener(this);

        menuItem4 = new JMenuItem("Control");
        menu2.add(menuItem4);
        menuItem4.setFont(new Font("Times New Roman", 0, 20));
        menuItem4.setBorder(null);
        menuItem4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem4.addActionListener(this);

        ////////////////////////////////////////////////////////// 
        menu3 = new JMenu("Proyeccion");
        menuBar.add(menu3);
        menu3.setFont(new Font("Times New Roman", 0, 22));
        menu3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(167, 167, 167)));
        menu3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        menuItem6 = new JMenuItem("Formulario");
        menu3.add(menuItem6);
        menuItem6.setFont(new Font("Times New Roman", 0, 20));
        menuItem6.setBorder(null);
        menuItem6.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem6.addActionListener(this);

        menuItem7 = new JMenuItem("Control");
        menu3.add(menuItem7);
        menuItem7.setFont(new Font("Times New Roman", 0, 20));
        menuItem7.setBorder(null);
        menuItem7.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem7.addActionListener(this);

        ////////////////////////////////////////////////////
        menu4 = new JMenu("Butaca");
        menuBar.add(menu4);
        menu4.setFont(new Font("Times New Roman", 0, 22));
        menu4.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(167, 167, 167)));
        menu4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem5 = new JMenuItem("Control");
        menu4.add(menuItem5);
        menuItem5.setFont(new Font("Times New Roman", 0, 20));
        menuItem5.setBorder(null);
        menuItem5.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem5.addActionListener(this);

        ////////////////////////////////////////////////////
        menu5 = new JMenu("Pelicula");
        menuBar.add(menu5);
        menu5.setFont(new Font("Times New Roman", 0, 22));
        menu5.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(167, 167, 167)));
        menu5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        menuItem8 = new JMenuItem("Formulario");
        menu5.add(menuItem8);
        menuItem8.setFont(new Font("Times New Roman", 0, 20));
        menuItem8.setBorder(null);
        menuItem8.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem8.addActionListener(this);

        menuItem9 = new JMenuItem("Control");
        menu5.add(menuItem9);
        menuItem9.setFont(new Font("Times New Roman", 0, 20));
        menuItem9.setBorder(null);
        menuItem9.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem9.addActionListener(this);

        ///////////////////////////////////////////////////////
        menu6 = new JMenu("Sala");
        menuBar.add(menu6);
        menu6.setFont(new Font("Times New Roman", 0, 22));
        menu6.setBorder(null);
        menu6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        menuItem10 = new JMenuItem("Formulario");
        menu6.add(menuItem10);
        menuItem10.setFont(new Font("Times New Roman", 0, 20));
        menuItem10.setBorder(null);
        menuItem10.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem10.addActionListener(this);

        menuItem11 = new JMenuItem("Control");
        menu6.add(menuItem11);
        menuItem11.setFont(new Font("Times New Roman", 0, 20));
        menuItem11.setBorder(null);
        menuItem11.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuItem11.addActionListener(this);

    }

    private static void loadLookAndFeel() {

        try {
            UIManager.put("MenuBar.background", new Color(11, 7, 17));
            UIManager.put("Menu.foreground", Color.WHITE);

            UIManager.put("Menu.selectionBackground", new Color(24, 22, 34));
            UIManager.put("Menu.selectionForeground", Color.WHITE);

            UIManager.put("MenuItem.background", new Color(11, 7, 17));
            UIManager.put("MenuItem.foreground", Color.WHITE);
            UIManager.put("MenuItem.selectionBackground", new Color(42, 38, 46));
            UIManager.put("MenuItem.selectionForeground", Color.WHITE);

            UIManager.put("PopupMenu.border", new LineBorder(new Color(11, 7, 17)));

            UIManager.setLookAndFeel(new MetalLookAndFeel());

        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "Error JMenuBar-Metodo loadLookAndFeel");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jlLogoBarra;
    private javax.swing.JLabel jlSalir;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpBarra;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JPanel jpContenedorMenuBar;
    private javax.swing.JPanel jpLogoBarra;
    private javax.swing.JPanel jpSalir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == menuItem1) {
            Pagina1 p1 = new Pagina1();
            p1.setSize(1000, 530);
            p1.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p1, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }

        if (e.getSource() == menuItem2) {
            Pagina2 p2 = new Pagina2();
            p2.setSize(1000, 530);
            p2.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p2, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }

        if (e.getSource() == menuItem3) {
            Pagina3 p3 = new Pagina3();
            p3.setSize(1000, 530);
            p3.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p3, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }

        if (e.getSource() == menuItem4) {
            Pagina4 p4 = new Pagina4();
            p4.setSize(1000, 530);
            p4.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p4, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }

        if (e.getSource() == menuItem5) {
            Pagina5 p5 = new Pagina5();
            p5.setSize(1000, 530);
            p5.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p5, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem8) {
            Pagina6 p6 = new Pagina6();
            p6.setSize(1000, 530);
            p6.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p6, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem9) {
            Pagina7 p7 = new Pagina7();
            p7.setSize(1000, 530);
            p7.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p7, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem10) {
            Pagina8 p8 = new Pagina8();
            p8.setSize(1000, 530);
            p8.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p8, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem11) {
            Pagina9 p9 = new Pagina9();
            p9.setSize(1000, 530);
            p9.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p9, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem6) {
            Pagina10 p10 = new Pagina10();
            p10.setSize(1000, 530);
            p10.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p10, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
        
        if (e.getSource() == menuItem7) {
            Pagina11 p11 = new Pagina11();
            p11.setSize(1000, 530);
            p11.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p11, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        }
    }
}
