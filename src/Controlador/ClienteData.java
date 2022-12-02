/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Conexion;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ClienteData {

    private Connection con;

    public ClienteData() {
        this.con = Conexion.getConexion();
    }

    public void guardarCliente(Cliente cliente) {

        boolean validar = false;

        for (Cliente x : obtenerClientes()) {

            if (cliente.equals(x)) {// valida de que en base de datos no haya otro cliente con mismo dni

                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Existe otro cliente con el mismo DNI");

        } else {

            String sql = "INSERT INTO cliente (dni, nombre, apellido, estado) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setLong(1, cliente.getDni());
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setBoolean(4, cliente.isEstado());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    cliente.setIdCliente(rs.getInt(1));

                    JOptionPane.showMessageDialog(null, "Cliente agregado");
                }
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-guardarCliente");
            }

        }

    }

    public void actualizarCliente(Cliente cliente) {

        boolean validar = false;

        for (Cliente x : obtenerClientes()) {

            if (cliente.equals(x) && cliente.getIdCliente() != x.getIdCliente()) { // valida de que en base de datos no haya otro cliente igual pero con distinto id

                validar = true;
            } else if (cliente.equals(x) && cliente.getNombre().equals(x.getNombre()) && cliente.getApellido().equals(x.getApellido()) && cliente.isEstado() == x.isEstado()) {
// valida que si deje todo los datos igual, le avisa que existe el cliente con esos mismos datos
                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Cliente existente");

        } else {

            String sql = "UPDATE cliente SET dni = ?, nombre = ? ,apellido = ?, estado = ? WHERE idCliente = ?";

            try {
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setLong(1, cliente.getDni());
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setBoolean(4, cliente.isEstado());
                ps.setInt(5, cliente.getIdCliente());

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente modificado");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-actualizarCliente");
            }

        }

    }

    public Cliente buscarClienteXDni(long dni) {

        String sql = "SELECT * FROM cliente WHERE dni = ?";
        Cliente cliente = new Cliente();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getLong("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-buscarClienteXDni");
        }

        return cliente;

    }

    public List<Cliente> obtenerClientesXApellido(String apellido) {


        String sql = "SELECT * FROM cliente WHERE apellido = ?";

        List<Cliente> lista = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, apellido);

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

            JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-buscarClienteXDni");
        }

        return lista;
    }

    public Cliente obtenerClienteXId(int idCliente) {

        String sql = "SELECT * FROM cliente WHERE idCliente = ?";

        Cliente cliente = new Cliente();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getLong("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEstado(rs.getBoolean("estado"));
            } 

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL/base de datos inactiva, error-BuscarClienteXId");
        }

        return cliente;

    }

    public List<Cliente> obtenerClientes() {

        String sql = "SELECT * FROM cliente";

        List<Cliente> lista = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

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

            JOptionPane.showMessageDialog(null, "ClienteData Sentencia SQL erronea/base de datos inactiva, error-obtenerClientes");
        }

        return lista;
    }

}
