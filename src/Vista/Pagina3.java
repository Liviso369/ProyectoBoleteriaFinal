/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ClienteData;
import Controlador.TicketData;
import Modelo.Butaca;
import Modelo.Cliente;
import Modelo.Proyeccion;
import Modelo.Sala;
import Modelo.Ticket;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Familia
 */
public class Pagina3 extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form Pagina1
     */
    private Cliente cliente;
    private Proyeccion proyeccion;
    private Butaca butaca;
    private String formaPago;
    private double monto;

    private TicketData td;
    private ClienteData cd;
    private String hora, min, seg, ampm;
    Calendar calendario;
    Thread h1;

    public Pagina3() {
        initComponents();
        this.td = new TicketData();
        this.cd = new ClienteData();
        h1 = new Thread(this);
        h1.start();
        jtfFecha.setText(LocalDate.now() + "");
        cargarComboPelicula();
        cargarComboForma();
        jbBorrar.setEnabled(false);
        jbBorrar.setBackground(new Color(117, 21, 41));
        UIManager.put("Button.disabledText", Color.BLACK);
    }

    public Pagina3(Cliente cliente, Proyeccion proyeccion, Butaca butaca, String formaPago, double monto) {
        initComponents();

        h1 = new Thread(this);
        h1.start();
        jtfFecha.setText(LocalDate.now() + "");

        this.cliente = cliente;
        this.proyeccion = proyeccion;
        this.butaca = butaca;
        this.formaPago = formaPago;
        this.monto = monto;
        this.td = new TicketData();
        this.cd = new ClienteData();

        cambiarPropiedadesElementosActualizar();

    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            jtfHora.setText(hora + ":" + min + ":" + seg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException error) {

            }
        }
    }

    private void calcula() {
        calendario = new GregorianCalendar();
        Date fechaHoraactual = new Date();
        calendario.setTime(fechaHoraactual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY);
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        min = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        seg = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    private void cargarComboPelicula() {
        int valor, valor1;
        jcbPelicula.removeAllItems();

        for (Proyeccion x : td.obtenerproyeccionXFechaActualOMayor()) {

            valor = x.getHorarioInicio().compareTo(LocalTime.now());
            valor1 = x.getFechaProyeccion().compareTo(LocalDate.now());

            if (valor1 == 1) {
                jcbPelicula.addItem(x);

            } else if (valor1 == 0) {
                if (valor == 1) {
                    jcbPelicula.addItem(x);
                }
            }
        }

    }

    private void BuscarSala() {

        Proyeccion proyeccion1 = (Proyeccion) jcbPelicula.getSelectedItem();

        Sala sala1 = proyeccion1.getSala();

        jtfSala.setText(proyeccion1.getSala().getUbicacion() + "/" + proyeccion1.getSala().getLocalidad());

    }

    private void cargarComboButaca() {
        Proyeccion proyeccion1 = (Proyeccion) jcbPelicula.getSelectedItem();
        jcbButaca.removeAllItems();

        for (Butaca x : td.obtenerButacasLibres(proyeccion1)) {

            jcbButaca.addItem(x);
        }
    }

    private void cargarComboForma() {

        List<String> lista = new ArrayList<>();
        lista.add("Tarjeta Debito");
        lista.add("Tarjeta Credito");
        lista.add("Mercado Pago");
        lista.add("Contado");
        jcbForma.removeAllItems();

        for (String x : lista) {

            jcbForma.addItem(x);
        }
    }

    private void limpiarCampos() {
        jtfDni.setText("");
        jtfCliente.setText("");
        jtfMonto.setText("");
        jcbPelicula.setSelectedIndex(0);
        jcbForma.setSelectedIndex(0);
    }

    private void cargarDatosAlActualizar() {

        jtfFecha.setText(LocalDate.now() + "");

        jtfDni.setText(cliente.getDni() + "");
        jtfCliente.setText(cliente.getNombre() + "/" + cliente.getApellido());
        jtfMonto.setText(Math.round(monto) + "");

        jcbPelicula.removeAllItems();
        jcbPelicula.addItem(proyeccion);

        BuscarSala();

        jcbButaca.removeAllItems();
        jcbButaca.addItem(butaca);

        jcbForma.removeAllItems();
        jcbForma.addItem(formaPago);

    }

    private void cambiarPropiedadesElementosActualizar() {
        jbGuardar.setEnabled(false);
        jbBuscar.setEnabled(false);

        jtfDni.setEnabled(false);
        jtfMonto.setEnabled(false);
        jtfCliente.setEnabled(false);
        jtfSala.setEnabled(false);

        jcbButaca.setEnabled(false);
        jcbPelicula.setEnabled(false);
        jcbForma.setEnabled(false);

        cargarDatosAlActualizar();

        jtfDni.setDisabledTextColor(Color.BLACK);
        jtfCliente.setDisabledTextColor(Color.BLACK);
        jtfMonto.setDisabledTextColor(Color.BLACK);
        jtfSala.setDisabledTextColor(Color.BLACK);
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("Button.disabledText", Color.BLACK);

        jbBuscar.setBackground(new Color(117, 21, 41));
        jbGuardar.setBackground(new Color(117, 21, 41));

    }

    private void cambiarPropiedadesElementosLimpiar() {
        jbGuardar.setEnabled(true);
        jbBuscar.setEnabled(true);

        jtfDni.setEnabled(true);
        jtfMonto.setEnabled(true);
        jtfCliente.setEnabled(true);
        jtfSala.setEnabled(true);

        jcbButaca.setEnabled(true);
        jcbPelicula.setEnabled(true);
        jcbForma.setEnabled(true);

        jbBuscar.setBackground(new Color(235, 42, 83));
        jbGuardar.setBackground(new Color(235, 42, 83));

        jbBorrar.setEnabled(false);
        jbBorrar.setBackground(new Color(117, 21, 41));
        UIManager.put("Button.disabledText", Color.BLACK);

        cargarComboPelicula();
        cargarComboForma();
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfHora = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfMonto = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jtfDni = new javax.swing.JTextField();
        jtfFecha = new javax.swing.JTextField();
        jbGuardar = new javax.swing.JButton();
        jtfCliente = new javax.swing.JTextField();
        jcbPelicula = new javax.swing.JComboBox<>();
        jcbForma = new javax.swing.JComboBox<>();
        jcbButaca = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jtfSala = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jpBackgroud.setBackground(new java.awt.Color(32, 30, 45));
        jpBackgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpContenedor.setBackground(new java.awt.Color(32, 30, 45));
        jpContenedor.setPreferredSize(new java.awt.Dimension(1000, 530));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Formulario Ticket");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dni:");

        jtfHora.setEditable(false);
        jtfHora.setBackground(new java.awt.Color(255, 255, 255));
        jtfHora.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfHora.setForeground(new java.awt.Color(0, 0, 0));
        jtfHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfHora.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cliente:");

        jtfMonto.setBackground(new java.awt.Color(255, 255, 255));
        jtfMonto.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfMonto.setForeground(new java.awt.Color(0, 0, 0));
        jtfMonto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));
        jtfMonto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfMontoFocusLost(evt);
            }
        });

        jbBuscar.setBackground(new java.awt.Color(235, 42, 83));
        jbBuscar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscar.setText("BuscarXDni");
        jbBuscar.setBorder(null);
        jbBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jbBorrar.setBackground(new java.awt.Color(235, 42, 83));
        jbBorrar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jbBorrar.setForeground(new java.awt.Color(255, 255, 255));
        jbBorrar.setText("Borrar");
        jbBorrar.setBorder(null);
        jbBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarActionPerformed(evt);
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

        jtfDni.setBackground(new java.awt.Color(255, 255, 255));
        jtfDni.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfDni.setForeground(new java.awt.Color(0, 0, 0));
        jtfDni.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));
        jtfDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDniFocusLost(evt);
            }
        });

        jtfFecha.setEditable(false);
        jtfFecha.setBackground(new java.awt.Color(255, 255, 255));
        jtfFecha.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfFecha.setForeground(new java.awt.Color(0, 0, 0));
        jtfFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));

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

        jtfCliente.setEditable(false);
        jtfCliente.setBackground(new java.awt.Color(255, 255, 255));
        jtfCliente.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfCliente.setForeground(new java.awt.Color(0, 0, 0));
        jtfCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));

        jcbPelicula.setBackground(new java.awt.Color(255, 255, 255));
        jcbPelicula.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbPelicula.setForeground(new java.awt.Color(0, 0, 0));
        jcbPelicula.setMaximumRowCount(5);
        jcbPelicula.setBorder(null);
        jcbPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPeliculaActionPerformed(evt);
            }
        });

        jcbForma.setBackground(new java.awt.Color(255, 255, 255));
        jcbForma.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbForma.setForeground(new java.awt.Color(0, 0, 0));
        jcbForma.setMaximumRowCount(20);
        jcbForma.setBorder(null);

        jcbButaca.setBackground(new java.awt.Color(255, 255, 255));
        jcbButaca.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jcbButaca.setForeground(new java.awt.Color(0, 0, 0));
        jcbButaca.setMaximumRowCount(20);
        jcbButaca.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pelicula:");

        jtfSala.setEditable(false);
        jtfSala.setBackground(new java.awt.Color(255, 255, 255));
        jtfSala.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        jtfSala.setForeground(new java.awt.Color(0, 0, 0));
        jtfSala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfSala.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 5, new java.awt.Color(255, 255, 255)));

        jSeparator1.setBackground(new java.awt.Color(235, 42, 83));

        javax.swing.GroupLayout jpContenedorLayout = new javax.swing.GroupLayout(jpContenedor);
        jpContenedor.setLayout(jpContenedorLayout);
        jpContenedorLayout.setHorizontalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(144, 144, 144)
                .addComponent(jtfHora, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpContenedorLayout.createSequentialGroup()
                                    .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6))
                                    .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jpContenedorLayout.createSequentialGroup()
                                            .addGap(42, 42, 42)
                                            .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jpContenedorLayout.createSequentialGroup()
                                            .addGap(38, 38, 38)
                                            .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpContenedorLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(91, 91, 91)
                                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpContenedorLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpContenedorLayout.createSequentialGroup()
                                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpContenedorLayout.createSequentialGroup()
                                        .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpContenedorLayout.createSequentialGroup()
                                        .addComponent(jtfSala, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(232, 232, 232)
                                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcbForma, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcbButaca, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))))
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jpContenedorLayout.setVerticalGroup(
            jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContenedorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContenedorLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(24, 24, 24)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSala, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbButaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        if (jtfDni.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Rellene el campo Dni para buscar un cliente");
            jtfDni.requestFocus();

        } else {
            Pattern pat = Pattern.compile("^\\d{8}$");
            Matcher mat = pat.matcher(jtfDni.getText());
            if (mat.matches()) {

                Long dni = Long.parseLong(jtfDni.getText());

                Cliente cliente1 = cd.buscarClienteXDni(dni);

                if (cliente1.getDni() != 0) {

                    jtfCliente.setText(cliente1.getNombre() + "/" + cliente1.getApellido());
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado");
                }
            }
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jcbPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPeliculaActionPerformed
        if (!(jcbPelicula.getSelectedItem() == null)) {
            BuscarSala();
            cargarComboButaca();
        }
    }//GEN-LAST:event_jcbPeliculaActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (jtfCliente.getText().equals("") || jtfMonto.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Rellene todos los campos");

        } else {

            Pattern pat = Pattern.compile("^\\d{8}$");
            Matcher mat = pat.matcher(jtfDni.getText());

            Pattern pat1 = Pattern.compile("^\\d+$");
            Matcher mat1 = pat1.matcher(jtfMonto.getText());

            if (mat.matches() && mat1.matches()) {
                Proyeccion proyeccion1 = (Proyeccion) jcbPelicula.getSelectedItem();
                Cliente cliente1 = cd.buscarClienteXDni(Integer.parseInt(jtfDni.getText()));
                Butaca butaca1 = (Butaca) jcbButaca.getSelectedItem();
                LocalDate fecha1 = LocalDate.parse(jtfFecha.getText());
                LocalTime tiempo1 = LocalTime.parse(jtfHora.getText());
                String formaPago1 = (String) jcbForma.getSelectedItem();
                double monto1 = Double.parseDouble(jtfMonto.getText());

                Ticket ticket = new Ticket(proyeccion1, butaca1, cliente1, fecha1, tiempo1, formaPago1, monto1);

                td.guardarTicket(ticket);
                cargarComboButaca();
            }
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        cambiarPropiedadesElementosLimpiar();
        limpiarCampos();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        Ticket ticket = td.BuscarTicketXClienteXProyeccionXButaca(cliente.getIdCliente(), proyeccion.getIdProyeccion(), butaca.getIdButaca());
        int id = ticket.getIdTicket();

        Proyeccion proyeccion1 = (Proyeccion) jcbPelicula.getSelectedItem();
        Cliente cliente1 = cd.buscarClienteXDni(Integer.parseInt(jtfDni.getText()));
        Butaca butaca1 = (Butaca) jcbButaca.getSelectedItem();
        LocalDate fecha1 = LocalDate.parse(jtfFecha.getText());
        LocalTime tiempo1 = LocalTime.parse(jtfHora.getText());
        String formaPago1 = (String) jcbForma.getSelectedItem();
        double monto1 = Double.parseDouble(jtfMonto.getText());

        Ticket ticket1 = new Ticket(id, proyeccion1, butaca1, cliente1, fecha1, tiempo1, formaPago1, monto1);

        td.BorrarTicket(ticket1);
    }//GEN-LAST:event_jbBorrarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        jpContenedor.setVisible(false);
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jtfDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDniFocusLost
        Pattern pat = Pattern.compile("^\\d{8}$");
        Matcher mat = pat.matcher(jtfDni.getText());
        if (!jtfDni.getText().equals("") && !mat.matches()) {
            JOptionPane.showMessageDialog(this, "Ingrese Dni con 8 cararacteres y solo numeros");
            jtfDni.requestFocus();
        }
    }//GEN-LAST:event_jtfDniFocusLost

    private void jtfMontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfMontoFocusLost
        Pattern pat = Pattern.compile("^\\d+$");
        Matcher mat = pat.matcher(jtfMonto.getText());
        if (!jtfMonto.getText().equals("") && !mat.matches()) {
            JOptionPane.showMessageDialog(this, "Ingrese Dni con 8 cararacteres y solo numeros");
            jtfMonto.requestFocus();
        }
    }//GEN-LAST:event_jtfMontoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Butaca> jcbButaca;
    private javax.swing.JComboBox<String> jcbForma;
    private javax.swing.JComboBox<Proyeccion> jcbPelicula;
    private javax.swing.JPanel jpBackgroud;
    private javax.swing.JPanel jpContenedor;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfDni;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfHora;
    private javax.swing.JTextField jtfMonto;
    private javax.swing.JTextField jtfSala;
    // End of variables declaration//GEN-END:variables
}
