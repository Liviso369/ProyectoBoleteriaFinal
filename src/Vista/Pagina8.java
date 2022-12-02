/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.SalaData;
import Modelo.Sala;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

/**
 *
 * @author Familia
 */
public class Pagina8 extends javax.swing.JPanel {

    /**
     * Creates new form Pagina1
     */
    private SpinnerNumberModel modelNumero;

    private SalaData sd;

    private String ubicacion;
    private String localidad;
    private boolean estado;

    private int id;

    public Pagina8() {
        initComponents();

        this.modelNumero = new SpinnerNumberModel();

        this.sd = new SalaData();

        jbActualizar.setEnabled(false);
        jbActualizar.setBackground(new Color(117, 21, 41));
        UIManager.put("Button.disabledText", Color.BLACK);
        jcbEstado.setEnabled(false);

        validarSpinner();
        cargarCombo();
    }

    public Pagina8(String ubicacion, String localidad, boolean estado) {
        initComponents();

        this.modelNumero = new SpinnerNumberModel();
        this.sd = new SalaData();

        this.ubicacion = ubicacion;
        this.localidad = localidad;
        this.estado = estado;

        Sala sala = sd.buscarSalaXUbicacionXLocalidad(ubicacion, localidad);
        this.id = sala.getIdSala();

        validarSpinner();
        cargarCombo();

        cambiarPropiedadesElementosActualizar();
    }

    private void limpiarCampos() {
        jsNumero.setValue(1);
        jcbLocalidad.setSelectedIndex(0);
        jcbEstado.setSelected(true);
    }

    private void cargarCombo() {

        List<String> lista = new ArrayList<>();

        lista.add("San Luis");
        lista.add("Villa Mercedes");

        for (String x : lista) {

            jcbLocalidad.addItem(x);

        }

    }

    private void validarSpinner() {

        modelNumero.setMaximum(10);
        modelNumero.setMinimum(1);
        modelNumero.setValue(1);

        jsNumero.setModel(modelNumero);

        jsNumero.setEditor(new JSpinner.DefaultEditor(jsNumero));

    }

    private void cargarDatosAlActualizar() {
        jsNumero.setValue(recuperarUbicacion());
        jcbLocalidad.setSelectedItem(localidad);
        jcbEstado.setSelected(estado);
    }

    private void cambiarPropiedadesElementosActualizar() {
        jbGuardar.setEnabled(false);
        jcbEstado.setEnabled(true);
        cargarDatosAlActualizar();
        UIManager.put("Button.disabledText", Color.BLACK);
        jbGuardar.setBackground(new Color(117, 21, 41));
    }

    private void cambiarPropiedadesElementosLimpiar() {
        jbGuardar.setEnabled(true);
        jcbEstado.setEnabled(true);
        jbActualizar.setEnabled(false);
        jcbEstado.setEnabled(false);
        jcbEstado.setSelected(true);
        UIManager.put("Button.disabledText", Color.BLACK);
        jbActualizar.setBackground(new Color(117, 21, 41));
        jbGuardar.setBackground(new Color(235, 42, 83));
    }

    private int recuperarUbicacion() {
        char[] letras = ubicacion.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == ' ') {

                break;

            } else {
                texto1 = ubicacion.substring(i + 2, letras.length);
            }
        }

        int numero = Integer.parseInt(texto1);

        return numero;
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
        jpContenedor = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JCheckBox();
        jbGuardar = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jtfUbicacion = new javax.swing.JTextField();
        jcbLocalidad = new javax.swing.JComboBox<>();
        jsNumero = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));
        jpContenedor.setPreferredSize(new java.awt.Dimension(1000, 530));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario Sala");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ubicacion:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Localidad:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Estado:");

        jcbEstado.setBackground(new java.awt.Color(32, 30, 45));
        jcbEstado.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jcbEstado.setForeground(new java.awt.Color(255, 255, 255));
        jcbEstado.setSelected(true);
        jcbEstado.setBorder(null);
        jcbEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jcbEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jbGuardar.setBackground(new java.awt.Color(235, 42, 83));
        jbGuardar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jbGuardar.setText("Guardar");
        jbGuardar.setBorder(null);
        jbGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        jbActualizar.setBackground(new java.awt.Color(235, 42, 83));
        jbActualizar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbActualizar.setForeground(new java.awt.Color(255, 255, 255));
        jbActualizar.setText("Actualizar");
        jbActualizar.setBorder(null);
        jbActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        jbLimpiar.setBackground(new java.awt.Color(235, 42, 83));
        jbLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        jbLimpiar.setText("Limpiar");
        jbLimpiar.setBorder(null);
        jbLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarActionPerformed(evt);
            }
        });

        jbSalir.setBackground(new java.awt.Color(235, 42, 83));
        jbSalir.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbSalir.setText("Salir");
        jbSalir.setBorder(null);
        jbSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jtfUbicacion.setEditable(false);
        jtfUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        jtfUbicacion.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfUbicacion.setForeground(new java.awt.Color(0, 0, 0));
        jtfUbicacion.setText("Sala");
        jtfUbicacion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));

        jcbLocalidad.setBackground(new java.awt.Color(255, 255, 255));
        jcbLocalidad.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbLocalidad.setForeground(new java.awt.Color(0, 0, 0));
        jcbLocalidad.setMaximumRowCount(3);
        jcbLocalidad.setBorder(null);

        jsNumero.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jLabel3))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel5)
                        .addGap(47, 47, 47)
                        .addComponent(jtfUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jsNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(51, 51, 51)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpContenedorLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jtfUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(jsNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(94, 94, 94)
                                .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpContenedorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)))
                        .addGap(128, 128, 128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContenedorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );

        jpBackgroud.add(jpContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 530));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/Logo.png"))); // NOI18N
        jpBackgroud.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 172, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BOLETERIA");
        jpBackgroud.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 221, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBackgroud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        String ubicacion1 = jtfUbicacion.getText() + " " + jsNumero.getValue();

        String localidad1 = (String) jcbLocalidad.getSelectedItem();

        boolean estado1 = jcbEstado.isSelected();

        Sala sala1 = new Sala(ubicacion1, localidad1, estado1);

        sd.guardarSala(sala1);
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        limpiarCampos();
        cambiarPropiedadesElementosLimpiar();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        jpContenedor.setVisible(false);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed

        String ubicacion1 = jtfUbicacion.getText() + " " + jsNumero.getValue();

        String localidad1 = (String) jcbLocalidad.getSelectedItem();

        boolean estado1 = jcbEstado.isSelected();

        Sala sala1 = new Sala(id, ubicacion1, localidad1, estado1);

        sd.actualizarSala(sala1);

    }//GEN-LAST:event_jbActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JCheckBox jcbEstado;
    private javax.swing.JComboBox<String> jcbLocalidad;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JSpinner jsNumero;
    private javax.swing.JTextField jtfUbicacion;
    // End of variables declaration//GEN-END:variables
}