/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.PeliculaData;
import Controlador.ProyeccionData;
import Controlador.SalaData;
import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Familia
 */
public class Pagina11 extends javax.swing.JPanel {

    private DefaultTableModel modelo;
    private JTableHeader th;

    private ProyeccionData prod;
    private PeliculaData pd;
    private SalaData sd;

    /**
     * Creates new form Pagina1
     */
    public Pagina11() {
        initComponents();
        this.modelo = new DefaultTableModel();
        personalizarTabla();

        this.prod = new ProyeccionData();
        this.pd = new PeliculaData();
        this.sd = new SalaData();

        cargarComboSala();
        cargarComboPelicula();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFecha.getDateEditor();
        editor.setEditable(false);
    }

    private void armarCabecera() {
        List<Object> columnas = new ArrayList<>();

        columnas.add("Sala");
        columnas.add("Pelicula");
        columnas.add("Fecha");
        columnas.add("HorarioInicio");
        columnas.add("HorarioFin");

        for (Object x : columnas) {

            modelo.addColumn(x);
        }

        jtProyeccion.setModel(modelo);
    }

    private void personalizarTabla() {

        armarCabecera();
        th = jtProyeccion.getTableHeader();
        Font fuente = new Font("Times New Roman", 0, 20);
        th.setFont(fuente);
        th.setBackground(new Color(249, 193, 205));
        jtProyeccion.setFont(new Font("Times New Roman", 0, 20));
        jtProyeccion.setRowHeight(30);
        jtProyeccion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void borrarFilas() {

        int a = modelo.getRowCount() - 1;

        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void limpiarCampos() {
        jdcFecha.setDate(null);
        jcbPelicula.setSelectedIndex(0);
        borrarFilas();
    }

    private void cargarComboSala() {
        for (Sala x : sd.obtenerSalas()) {

            jcbSala.addItem(x);
        }
    }

    private void cargarComboPelicula() {
        for (Pelicula x : pd.obtenerPeliculas()) {

            jcbPelicula.addItem(x);
        }
    }

    private void traerXFechaXSala() {

        Date input = jdcFecha.getDate();
        LocalDate fecha1 = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Sala sala1 = (Sala) jcbSala.getSelectedItem();

        if (prod.obtenerProyeccionXFechaXSala(fecha1, sala1.getIdSala()).isEmpty()) {

            JOptionPane.showMessageDialog(this, "No hay Proyecciones en esa fecha y en esa sala");

        } else {

            for (Proyeccion x : prod.obtenerProyeccionXFechaXSala(fecha1, sala1.getIdSala())) {

                modelo.addRow(new Object[]{x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad(), x.getPelicula().getNombre(), x.getFechaProyeccion(), x.getHorarioInicio(), x.getHorarioFin()});
            }

        }

    }

    private void traerXPelicula() {

        Pelicula pelicula = (Pelicula) jcbPelicula.getSelectedItem();

        if (prod.obtenerProyeccionesXPelicula(pelicula.getIdPelicula()).isEmpty()) {

            JOptionPane.showMessageDialog(this, "No hay Proyecciones en esa pelicula");

        } else {
            for (Proyeccion x : prod.obtenerProyeccionesXPelicula(pelicula.getIdPelicula())) {

                modelo.addRow(new Object[]{x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad(), x.getPelicula().getNombre(), x.getFechaProyeccion(), x.getHorarioInicio(), x.getHorarioFin()});

            }
        }
    }

    private String recuperaUbicacion() {
        int fila = jtProyeccion.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 0);

        char[] letras = texto.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                break;

            } else {
                texto1 += letras[i];
            }
        }

        return texto1;
    }

    private String recuperaLocalidad() {
        int fila = jtProyeccion.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 0);

        char[] letras = texto.toCharArray();

        String texto1 = "";

        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                texto1 = texto.substring(i + 1, letras.length);

                break;
            }
        }

        return texto1;
    }

    private String recuperaPelicula() {
        int fila = jtProyeccion.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 1);

        char[] letras = texto.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                break;

            } else {
                texto1 += letras[i];
            }
        }

        String nombre = texto1;

        return nombre;
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
        jbBuscar1 = new javax.swing.JButton();
        jbBuscar2 = new javax.swing.JButton();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jbActualizar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jcbPelicula = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProyeccion = new javax.swing.JTable();
        jcbSala = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Control Proyeccion");

        jbBuscar1.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar1.setText("BuscarXFechaXSala");
        jbBuscar1.setBorder(null);
        jbBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscar1ActionPerformed(evt);
            }
        });

        jbBuscar2.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar2.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar2.setText("BuscarXPelicula");
        jbBuscar2.setBorder(null);
        jbBuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscar2ActionPerformed(evt);
            }
        });

        jdcFecha.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jdcFecha.setPreferredSize(new java.awt.Dimension(230, 30));

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

        jcbPelicula.setBackground(new java.awt.Color(255, 255, 255));
        jcbPelicula.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbPelicula.setForeground(new java.awt.Color(0, 0, 0));
        jcbPelicula.setMaximumRowCount(3);
        jcbPelicula.setBorder(null);

        jtProyeccion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jtProyeccion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtProyeccion);

        jcbSala.setBackground(new java.awt.Color(255, 255, 255));
        jcbSala.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbSala.setForeground(new java.awt.Color(0, 0, 0));
        jcbSala.setMaximumRowCount(3);
        jcbSala.setBorder(null);

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel3))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(350, 350, 350)
                        .addComponent(jbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)
                        .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206)
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpContenedorLayout.createSequentialGroup()
                                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jcbSala, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbSala, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
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

    private void jbBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar1ActionPerformed
        if (jdcFecha.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Rellene todo los campos");
        } else {
            borrarFilas();
            traerXFechaXSala();
        }

    }//GEN-LAST:event_jbBuscar1ActionPerformed

    private void jbBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar2ActionPerformed
        borrarFilas();
        traerXPelicula();
    }//GEN-LAST:event_jbBuscar2ActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        int fila = jtProyeccion.getSelectedRow();

        if (fila != -1) {

            String ubicacion = recuperaUbicacion();
            String localidad = recuperaLocalidad();
            Sala sala = sd.buscarSalaXUbicacionXLocalidad(ubicacion, localidad);

            String nombre = recuperaPelicula();
            Pelicula pelicula = pd.buscarPeliculaXNombre(nombre);

            LocalDate fecha = LocalDate.parse(modelo.getValueAt(fila, 2).toString());

            LocalTime hora1 = LocalTime.parse(modelo.getValueAt(fila, 3).toString());
            LocalTime hora2 = LocalTime.parse(modelo.getValueAt(fila, 4).toString());

            Pagina10 p10 = new Pagina10(sala, pelicula, fecha, hora1, hora2);
            p10.setSize(1000, 530);
            p10.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p10, BorderLayout.CENTER);
            jpContenedor.revalidate();
            jpContenedor.repaint();
        } else {

            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }
    }//GEN-LAST:event_jbActualizarActionPerformed

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
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Pelicula> jcbPelicula;
    private javax.swing.JComboBox<Sala> jcbSala;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JTable jtProyeccion;
    // End of variables declaration//GEN-END:variables
}
