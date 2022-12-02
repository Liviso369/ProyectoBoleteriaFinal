/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ClienteData;
import Controlador.TicketData;
import Modelo.Cliente;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Familia
 */
public class Pagina2 extends javax.swing.JPanel {

    /**
     * Creates new form Pagina1
     */
    private DefaultTableModel modelo;
    private JTableHeader th;
    private ClienteData cd;
    private Cliente cliente;
    private TicketData td;

    public Pagina2() {
        initComponents();
        this.modelo = new DefaultTableModel();
        personalizarTabla();
        this.cd = new ClienteData();
        this.td = new TicketData();

        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFecha.getDateEditor();
        editor.setEditable(false);
    }

    private void armarCabecera() {
        List<Object> columnas = new ArrayList<>();

        columnas.add("Dni");
        columnas.add("Nombre");
        columnas.add("Apellido");
        columnas.add("Estado");

        for (Object x : columnas) {

            modelo.addColumn(x);

        }

        jtCliente.setModel(modelo);
    }

    private void personalizarTabla() {

        armarCabecera();
        th = jtCliente.getTableHeader();
        Font fuente = new Font("Times New Roman", 0, 20);
        th.setFont(fuente);
        th.setBackground(new Color(249,193,205));
        jtCliente.setFont(new Font("Times New Roman", 0, 20));
        jtCliente.setRowHeight(30);
        jtCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void borrarFilas() {

        int a = modelo.getRowCount() - 1;

        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void TraerXDni() {
        Long dni = Long.parseLong(jtfDni.getText());

        cliente = cd.buscarClienteXDni(dni);

        if (cliente.getDni() != 0) {

            modelo.addRow(new Object[]{cliente.getDni(), cliente.getNombre(), cliente.getApellido(), cliente.isEstado()});
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }

    }

    private void TraerXApellido() {
        String apellido = jtfApellido.getText();

        if (cd.obtenerClientesXApellido(apellido).isEmpty()) {

            JOptionPane.showMessageDialog(this, "Cliente no encontrado");

        } else {

            for (Cliente x : cd.obtenerClientesXApellido(apellido)) {

                modelo.addRow(new Object[]{x.getDni(), x.getNombre(), x.getApellido(), x.isEstado()});
            }

        }

    }

    private void TraerXFecha() {

        if (jdcFecha.getDate() == null) {

            JOptionPane.showMessageDialog(null, "Rellene el campo fecha");
            jdcFecha.requestFocus();

        } else {
            Date input = jdcFecha.getDate();
            LocalDate fecha = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (td.obtenerClientesXFecha(fecha).isEmpty()) {

                JOptionPane.showMessageDialog(this, "Cliente no encontrado");

            } else {

                for (Cliente x : td.obtenerClientesXFecha(fecha)) {

                    modelo.addRow(new Object[]{x.getDni(), x.getNombre(), x.getApellido(), x.isEstado()});
                }

            }
        }

    }

    private void limpiarCampos() {

        jtfDni.setText("");
        jtfApellido.setText("");
        jdcFecha.setDate(null);
        borrarFilas();

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
        jtfDni = new javax.swing.JTextField();
        jbBuscar1 = new javax.swing.JButton();
        jbBuscar3 = new javax.swing.JButton();
        jbBuscar2 = new javax.swing.JButton();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jtfApellido = new javax.swing.JTextField();
        jbActualizar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Control Cliente");

        jtfDni.setBackground(new java.awt.Color(255, 255, 255));
        jtfDni.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfDni.setForeground(new java.awt.Color(0, 0, 0));
        jtfDni.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));
        jtfDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDniFocusLost(evt);
            }
        });

        jbBuscar1.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar1.setText("BuscarXDni");
        jbBuscar1.setBorder(null);
        jbBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscar1ActionPerformed(evt);
            }
        });

        jbBuscar3.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar3.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar3.setText("AsistieronXFecha");
        jbBuscar3.setBorder(null);
        jbBuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscar3ActionPerformed(evt);
            }
        });

        jbBuscar2.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar2.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar2.setText("BuscarXApellido");
        jbBuscar2.setBorder(null);
        jbBuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscar2ActionPerformed(evt);
            }
        });

        jdcFecha.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jdcFecha.setPreferredSize(new java.awt.Dimension(230, 30));

        jtfApellido.setBackground(new java.awt.Color(255, 255, 255));
        jtfApellido.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfApellido.setForeground(new java.awt.Color(0, 0, 0));
        jtfApellido.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));
        jtfApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfApellidoFocusLost(evt);
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

        jtCliente = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtCliente);

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(jbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(jbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211)
                .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210)
                .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jtfDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDniFocusLost
        Pattern pat = Pattern.compile("^\\d{8}$");
        Matcher mat = pat.matcher(jtfDni.getText());
        if (!jtfDni.getText().equals("") && !mat.matches()) {
            JOptionPane.showMessageDialog(this, "Ingrese Dni con 8 cararacteres y solo numeros");
            jtfDni.requestFocus();
        }
    }//GEN-LAST:event_jtfDniFocusLost

    private void jbBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar1ActionPerformed

        if (jtfDni.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Rellene el campo Dni");
            jtfDni.requestFocus();

        } else {
            Pattern pat = Pattern.compile("^\\d{8}$");
            Matcher mat = pat.matcher(jtfDni.getText());
            if (mat.matches()) {
                borrarFilas();
                TraerXDni();
            }
        }
    }//GEN-LAST:event_jbBuscar1ActionPerformed

    private void jbBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar3ActionPerformed
        borrarFilas();
        TraerXFecha();
    }//GEN-LAST:event_jbBuscar3ActionPerformed

    private void jbBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar2ActionPerformed
        if (jtfApellido.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Rellene el campo Apellido");
            jtfApellido.requestFocus();

        } else {

            Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ]+\\s?$");
            Matcher mat = pat.matcher(jtfApellido.getText());
            if (mat.matches()) {
                borrarFilas();
                TraerXApellido();
            }

        }
    }//GEN-LAST:event_jbBuscar2ActionPerformed

    private void jtfApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfApellidoFocusLost
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ]+\\s?$");
        Matcher mat = pat.matcher(jtfApellido.getText());
        if (!jtfApellido.getText().equals("") && !mat.matches()) {
            JOptionPane.showMessageDialog(this, "Ingrese un solo apellido con solo letras");
            jtfApellido.requestFocus();
        }
    }//GEN-LAST:event_jtfApellidoFocusLost

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        int fila = jtCliente.getSelectedRow();

        if (fila != -1) {
            long dni = Long.parseLong(modelo.getValueAt(fila, 0).toString());
            String nombre = (String) modelo.getValueAt(fila, 1);
            String apellido = (String) modelo.getValueAt(fila, 2);
            Boolean estado = Boolean.parseBoolean(modelo.getValueAt(fila, 3).toString());

            Pagina1 p1 = new Pagina1(dni, nombre, apellido, estado);
            p1.setSize(1000, 530);
            p1.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p1, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();

        } else {

            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        jpContenedor.setVisible(false);
    }//GEN-LAST:event_jbSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbBuscar1;
    private javax.swing.JButton jbBuscar2;
    private javax.swing.JButton jbBuscar3;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfDni;
    // End of variables declaration//GEN-END:variables
}
