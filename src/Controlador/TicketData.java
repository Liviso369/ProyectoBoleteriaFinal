/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JOptionPane;

public class TicketData {

    private Connection con;

    private SalaData sd;
    private PeliculaData pd;
    private ProyeccionData prod;
    private ButacaData bd;
    private ClienteData cd;

    public TicketData() {
        this.con = Conexion.getConexion();
        this.sd = new SalaData();
        this.pd = new PeliculaData();
        this.prod = new ProyeccionData();
        this.bd = new ButacaData();
        this.cd = new ClienteData();
    }

    public void guardarTicket(Ticket ticket) {

        String sql = "INSERT INTO ticket (idProyeccion, idButaca, idCliente, fechaCompra, horaCompra, formaPago, monto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, ticket.getProyeccion().getIdProyeccion());
            ps.setInt(2, ticket.getButaca().getIdButaca());
            ps.setInt(3, ticket.getCliente().getIdCliente());
            ps.setDate(4, java.sql.Date.valueOf(ticket.getFechaCompra()));
            ps.setTime(5, java.sql.Time.valueOf(ticket.getHoraCompra()));
            ps.setString(6, ticket.getFormaPago());
            ps.setDouble(7, ticket.getMonto());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                ticket.setIdTicket(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Ticket agregado");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-guardarTicket");
        }

    }
 
    public void BorrarTicket(Ticket ticket) {

        String sql = "DELETE FROM ticket WHERE idTicket = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ticket.getIdTicket());

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Ticket Borrado");

            } else {
                JOptionPane.showMessageDialog(null, "Ticket ya fue Borrado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-BorrarTicket");
        }
    }
          
    public List<Proyeccion> obtenerproyeccionXFechaActualOMayor() {

        List<Proyeccion> lista = new ArrayList<>();

        String sql = "SELECT * FROM proyeccion WHERE fechaProyeccion >= DATE(NOW())";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proyeccion proyeccion = new Proyeccion();

                proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                proyeccion.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                proyeccion.setPelicula(pd.buscarPeliculaXId(rs.getInt("idPelicula")));
                proyeccion.setFechaProyeccion(rs.getDate("fechaProyeccion").toLocalDate());
                proyeccion.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                proyeccion.setHorarioFin(rs.getTime("horarioFin").toLocalTime());
                lista.add(proyeccion);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-obtenerproyeccionXFechaActualXHoraActualOMayor");
        }

        return lista;
        //cargamos el combo
    }

    public List<Butaca> obtenerButacasLibres(Proyeccion proyeccion) {

        List<Butaca> lista = new ArrayList<>();

        String sql = "SELECT * FROM butaca WHERE idSala = ? AND idButaca NOT IN(SELECT  t.idButaca from butaca b, proyeccion p, sala s, ticket t WHERE t.idProyeccion=p.idProyeccion AND t.idButaca= b.idButaca AND p.idSala = s.idSala AND s.idSala=b.idSala and p.fechaProyeccion = ? AND p.horarioInicio = ?) AND estado = 1";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, proyeccion.getSala().getIdSala());
            ps.setDate(2, java.sql.Date.valueOf(proyeccion.getFechaProyeccion()));
            ps.setTime(3, java.sql.Time.valueOf(proyeccion.getHorarioInicio()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Butaca butaca = new Butaca();

                butaca.setIdButaca(rs.getInt("idButaca"));
                butaca.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                butaca.setFila(rs.getInt("fila"));
                butaca.setColumna(rs.getInt("columna"));
                butaca.setEstado(rs.getBoolean("estado"));

                lista.add(butaca);
            }

            ps.close();

            //cargamos el combo box de butaca
            //podria ir en butaca
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-obtenerButacasLibres");
        }
        return lista;
    }

    public List<Ticket> obtenerTicketsXFecha(LocalDate fecha) {

        List<Ticket> lista = new ArrayList<>();

        String sql = "SELECT * FROM ticket WHERE fechaCompra = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fecha));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("idTicket"));
                ticket.setProyeccion(prod.obtenerProyeccionXId(rs.getInt("idProyeccion")));
                ticket.setButaca(bd.obtenerButacaXId(rs.getInt("idButaca")));
                ticket.setCliente(cd.obtenerClienteXId(rs.getInt("idCliente")));
                ticket.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                ticket.setHoraCompra(rs.getTime("horaCompra").toLocalTime());
                ticket.setFormaPago(rs.getString("formaPago"));
                ticket.setMonto(rs.getDouble("monto"));
                lista.add(ticket);
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-obtenerTicketsXFecha");
        }

        return lista;
    }

    public List<Ticket> obtenerTicketsXPelicula(int idPelicula) {//obtener peliculas necesito
        List<Ticket> lista = new ArrayList<>();

        String sql = "SELECT * FROM ticket WHERE idProyeccion IN (SELECT pro.idProyeccion FROM proyeccion pro, pelicula p WHERE pro.idPelicula = p.idPelicula AND p.idPelicula = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPelicula);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("idTicket"));
                ticket.setProyeccion(prod.obtenerProyeccionXId(rs.getInt("idProyeccion")));
                ticket.setButaca(bd.obtenerButacaXId(rs.getInt("idButaca")));
                ticket.setCliente(cd.obtenerClienteXId(rs.getInt("idCliente")));
                ticket.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                ticket.setHoraCompra(rs.getTime("horaCompra").toLocalTime());
                ticket.setFormaPago(rs.getString("formaPago"));
                ticket.setMonto(rs.getDouble("monto"));
                lista.add(ticket);
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-obtenerTicketsXPelicula");
        }

        return lista;
    }

    public List<Cliente> obtenerClientesXFecha(LocalDate fecha) {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente WHERE idCliente IN(SELECT c.idCliente FROM cliente c, ticket t WHERE c.idCliente = t.idCliente AND t.fechaCompra = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fecha));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getLong("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEstado(rs.getBoolean("estado"));

                lista.add(cliente);
            }

            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "TicketData Sentencia SQL erronea/base de datos inactiva, error-obtenerClientesXFecha");
        }

        return lista;

    }

    public List<Ticket> BuscarXDni(long dni) {

        List<Ticket> lista = new ArrayList<>();

        String sql = "SELECT * FROM ticket WHERE idCliente IN(SELECT c.idCliente FROM cliente c, ticket t WHERE c.idCliente = t.idCliente AND c.dni = ?) ORDER BY fechaCompra ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, dni);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("idTicket"));
                ticket.setProyeccion(prod.obtenerProyeccionXId(rs.getInt("idProyeccion")));
                ticket.setButaca(bd.obtenerButacaXId(rs.getInt("idButaca")));
                ticket.setCliente(cd.obtenerClienteXId(rs.getInt("idCliente")));
                ticket.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                ticket.setHoraCompra(rs.getTime("horaCompra").toLocalTime());
                ticket.setFormaPago(rs.getString("formaPago"));
                ticket.setMonto(rs.getDouble("monto"));

                lista.add(ticket);
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-buscarClienteXDni");
        }

        return lista;
    }

    public Ticket BuscarTicketXClienteXProyeccionXButaca(int idCliente, int idProyeccion, int idButaca) {

        String sql = "SELECT * FROM ticket WHERE idCliente = ? AND idProyeccion = ? AND idButaca = ?";

        Ticket ticket = new Ticket();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idCliente);
            ps.setInt(2, idProyeccion);
            ps.setInt(3, idButaca);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ticket.setIdTicket(rs.getInt("idTicket"));
                ticket.setProyeccion(prod.obtenerProyeccionXId(rs.getInt("idProyeccion")));
                ticket.setButaca(bd.obtenerButacaXId(rs.getInt("idButaca")));
                ticket.setCliente(cd.obtenerClienteXId(rs.getInt("idCliente")));
                ticket.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                ticket.setHoraCompra(rs.getTime("horaCompra").toLocalTime());
                ticket.setFormaPago(rs.getString("formaPago"));
                ticket.setMonto(rs.getDouble("monto"));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL/base de datos inactiva, error-BuscarTicketXClienteXProyeccionXButac");
        }

        return ticket;
    }
}
