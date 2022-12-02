/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.*;
import Modelo.*;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.*;
import java.util.*;
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
public class Pagina4 extends javax.swing.JPanel {

    private DefaultTableModel modelo;
    private JTableHeader th;

    private TicketData td;
    private PeliculaData pd;
    private ClienteData cd;
    private SalaData sd;
    private ProyeccionData prod;
    private ButacaData bd;

    private Pelicula pelicula;
    private Cliente cliente;
    private Sala sala;
    private Proyeccion proyeccion;
    private Butaca butaca;

    /**
     * Creates new form Pagina1
     */
    public Pagina4() {
        initComponents();
        this.modelo = new DefaultTableModel();
        personalizarTabla();

        this.pd = new PeliculaData();
        this.td = new TicketData();
        this.cd = new ClienteData();
        this.sd = new SalaData();
        this.prod = new ProyeccionData();
        this.bd = new ButacaData();
        cargarComboPelicula();

        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFecha.getDateEditor();
        editor.setEditable(false);

    }

    private void armarCabecera() {
        List<Object> columnas = new ArrayList<>();

        columnas.add("Cliente");
        columnas.add("Pelicula");
        columnas.add("Sala");
        columnas.add("Butaca");
        columnas.add("Fecha");
        columnas.add("Hora");
        columnas.add("FormaPago");
        columnas.add("Precio");

        for (Object x : columnas) {

            modelo.addColumn(x);
        }

        jtTicket.setModel(modelo);
    }

    private void personalizarTabla() {

        armarCabecera();
        th = jtTicket.getTableHeader();
        Font fuente = new Font("Times New Roman", 0, 20);
        th.setFont(fuente);
        th.setBackground(new Color(249, 193, 205));
        jtTicket.setFont(new Font("Times New Roman", 0, 20));
        jtTicket.setRowHeight(30);
        jtTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void borrarFilas() {

        int a = modelo.getRowCount() - 1;

        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargarComboPelicula() {
        for (Pelicula x : pd.obtenerPeliculas()) {

            jcbPelicula.addItem(x);
        }
    }

    private void TraerXDni() {

        if (jtfDni.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Rellene el campo Dni");
            jtfDni.requestFocus();

        } else {
            Long dni = Long.parseLong(jtfDni.getText());

            if (td.BuscarXDni(dni).isEmpty()) {

                JOptionPane.showMessageDialog(null, "No hay ticket con ese DNI");

            } else {

                for (Ticket x : td.BuscarXDni(dni)) {

                    modelo.addRow(new Object[]{x.getCliente().getDni() + "/" + x.getCliente().getNombre() + "/"
                        + x.getCliente().getApellido(), x.getProyeccion().getPelicula().getNombre() + "/" + x.getProyeccion().getHorarioInicio() + "/" + x.getProyeccion().getFechaProyeccion(), x.getProyeccion().getSala().getUbicacion() + "/"
                        + x.getProyeccion().getSala().getLocalidad(), x.getButaca().getFila() + "/"
                        + x.getButaca().getColumna(), x.getFechaCompra(), x.getHoraCompra(), x.getFormaPago(), Math.round(x.getMonto())});
                }

            }

        }
    }

    private void TraerXpelicula() {

        pelicula = (Pelicula) jcbPelicula.getSelectedItem();

        if (td.obtenerTicketsXPelicula(pelicula.getIdPelicula()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ticket en esa pelicula");
        } else {

            for (Ticket x : td.obtenerTicketsXPelicula(pelicula.getIdPelicula())) {

                modelo.addRow(new Object[]{x.getCliente().getDni() + "/" + x.getCliente().getNombre() + "/"
                    + x.getCliente().getApellido(), x.getProyeccion().getPelicula().getNombre() + "/" + x.getProyeccion().getHorarioInicio() + "/" + x.getProyeccion().getFechaProyeccion(), x.getProyeccion().getSala().getUbicacion() + "/"
                    + x.getProyeccion().getSala().getLocalidad(), x.getButaca().getFila() + "/"
                    + x.getButaca().getColumna(), x.getFechaCompra(), x.getHoraCompra(), x.getFormaPago(), Math.round(x.getMonto())});
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

            if (td.obtenerTicketsXFecha(fecha).isEmpty()) {

                JOptionPane.showMessageDialog(null, "No hay ticket en esa fecha");

            } else {

                for (Ticket x : td.obtenerTicketsXFecha(fecha)) {

                    modelo.addRow(new Object[]{x.getCliente().getDni() + "/" + x.getCliente().getNombre() + "/"
                        + x.getCliente().getApellido(), x.getProyeccion().getPelicula().getNombre() + "/" + x.getProyeccion().getHorarioInicio() + "/" + x.getProyeccion().getFechaProyeccion(), x.getProyeccion().getSala().getUbicacion() + "/"
                        + x.getProyeccion().getSala().getLocalidad(), x.getButaca().getFila() + "/"
                        + x.getButaca().getColumna(), x.getFechaCompra(), x.getHoraCompra(), x.getFormaPago(), Math.round(x.getMonto())});
                }

            }

        }
    }

    private void limpiarCampos() {
        jtfDni.setText("");
        jcbPelicula.setSelectedIndex(0);
        jdcFecha.setDate(null);
        borrarFilas();
    }

    private long recuperaDni() {
        int fila = jtTicket.getSelectedRow();

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

        long dni = Long.parseLong(texto1);

        return dni;
    }

    private String recuperaUbicacion() {
        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 2);

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
        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 2);

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

    private int recuperarFila() {

        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 3);

        char[] letras = texto.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                break;

            } else {
                texto1 += letras[i];
            }
        }

        int fila1 = Integer.parseInt(texto1);

        return fila1;
    }

    private int recuperarColumna() {

        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 3);

        char[] letras = texto.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                texto1 = texto.substring(i + 1, letras.length);

                break;
            }
        }

        int columna = Integer.parseInt(texto1);

        return columna;
    }

    private String recuperarhora() {

        int validar = 0;

        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 1);

        char[] letras = texto.toCharArray();

        String texto1 = "";
        for (int i = 0; i < letras.length; i++) {

            if (letras[i] == '/') {

                validar++;
            }

            if (validar == 1) {

                texto1 += letras[i + 1];
            }
        }

        char[] letras1 = texto1.toCharArray();
        String texto2 = "";

        for (int i = 0; i < letras1.length; i++) {

            if (letras1[i] == '/') {

                break;

            } else {
                texto2 += letras1[i];
            }
        }

        String hora = texto2;

        return hora;
    }

    private String recuperaPelicula() {
        int fila = jtTicket.getSelectedRow();

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

    private String recuperarFecha() {

        int validar = 0;

        int fila = jtTicket.getSelectedRow();

        String texto = (String) modelo.getValueAt(fila, 1);

        char[] letras = texto.toCharArray();

        String texto1 = "";

        for (int i = 0; i < letras.length; i++) {

            if (validar == 2) {
                texto1 = texto.substring(i, letras.length);

                break;

            } else if (letras[i] == '/') {
                validar++;
            }
        }

        String fecha = texto1;

        System.out.println(fecha);

        return fecha;

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
        jbActualizar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jcbPelicula = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTicket = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Control Ticket");

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
        jbBuscar3.setText("BuscarXFecha");
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

        jtTicket = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jtTicket.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtTicket);

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(jbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(jbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212)
                .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206)
                .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Pattern pat = Pattern.compile("^\\d{8}$");
        Matcher mat = pat.matcher(jtfDni.getText());

        if (mat.matches()) {
            borrarFilas();
            TraerXDni();
        } else if (jtfDni.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Rellene el campo Dni");
            jtfDni.requestFocus();
        }
    }//GEN-LAST:event_jbBuscar1ActionPerformed

    private void jbBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar3ActionPerformed
        borrarFilas();
        TraerXFecha();
    }//GEN-LAST:event_jbBuscar3ActionPerformed

    private void jbBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscar2ActionPerformed
        borrarFilas();
        TraerXpelicula();
    }//GEN-LAST:event_jbBuscar2ActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        int fila = jtTicket.getSelectedRow();

        if (fila != -1) {

            cliente = cd.buscarClienteXDni(recuperaDni());

            String nombre = recuperaPelicula();

            pelicula = pd.buscarPeliculaXNombre(nombre);

            String ubicacion = recuperaUbicacion();
            String localidad = recuperaLocalidad();
            sala = sd.buscarSalaXUbicacionXLocalidad(ubicacion, localidad);

            LocalTime hora = LocalTime.parse(recuperarhora());

            LocalDate fecha = LocalDate.parse(recuperarFecha());

            proyeccion = prod.obtenerProyeccionXPeliculaXSalaXHoraXfecha(pelicula.getIdPelicula(), sala.getIdSala(), hora, fecha);

            int fila1 = recuperarFila();
            int columna = recuperarColumna();
            butaca = bd.BuscarButacaXFilaXColumnaXSala(fila1, columna, sala.getIdSala());

            String formaPago = (String) modelo.getValueAt(fila, 6);

            double monto = Double.parseDouble(modelo.getValueAt(fila, 7).toString());

            Pagina3 p3 = new Pagina3(cliente, proyeccion, butaca, formaPago, monto);
            p3.setSize(1000, 530);
            p3.setLocation(0, 0);

            jpContenedor.removeAll();
            jpContenedor.add(p3, BorderLayout.CENTER);
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
    private javax.swing.JComboBox<Pelicula> jcbPelicula;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JTable jtTicket;
    private javax.swing.JTextField jtfDni;
    // End of variables declaration//GEN-END:variables
}
