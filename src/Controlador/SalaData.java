/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SalaData {

    Connection con;

    public SalaData() {
        this.con = Conexion.getConexion();
    }

    public void guardarSala(Sala sala) {

        boolean validar = false;

        for (Sala x : obtenerSalas()) {

            if (sala.equals(x)) {// valida de que en base de datos no haya otra sala

                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Existe otra sala con el misma ubicacion-localidad");

        } else {
            String sql = "INSERT INTO sala (ubicacion,localidad,estado) VALUES (?,?,?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, sala.getUbicacion());
                ps.setString(2, sala.getLocalidad());
                ps.setBoolean(3, sala.isEstado());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();//recupero las llaves el ultimo insertado es la primera llave
                if (rs.next()) {

                    sala.setIdSala(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Sala agregada");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-guardarsala");
            }
        }

    }

    public List<Sala> obtenerSalas() {
        ArrayList<Sala> listaTemp = new ArrayList<>();
        String sql = "SELECT * FROM sala WHERE estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();//select
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setIdSala(rs.getInt("idSala"));
                sala.setUbicacion(rs.getString("ubicacion"));
                sala.setLocalidad(rs.getString("localidad"));
                sala.setEstado(rs.getBoolean("estado"));

                listaTemp.add(sala);
            }
            ps.close(); //cerra la coneccion      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-obtenerSalas");
        }
        return listaTemp;
    }

    public void actualizarSala(Sala sala) {

        boolean validar = false;

        for (Sala x : obtenerSalas()) {

            if (sala.equals(x) && sala.getIdSala() != x.getIdSala()) { // valida de que en base de datos no haya otro sala igual pero con distinto id

                validar = true;
            } else if (sala.equals(x) && sala.isEstado() == x.isEstado()) {
// valida que si deje todo los datos igual, le avisa que existe una sala con esos mismos datos
                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Sala existente");

        } else {
            String sql = "UPDATE sala set ubicacion=?, localidad=?, estado=? WHERE idSala=?;";

            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, sala.getUbicacion());
                ps.setString(2, sala.getLocalidad());
                ps.setBoolean(3, sala.isEstado());
                ps.setInt(4, sala.getIdSala());

                if (ps.executeUpdate() > 0) {//cantidad de filas afectadas

                    JOptionPane.showMessageDialog(null, "Sala modificada");
                }

                ps.close();

            } catch (SQLException ex) {//error en la sentencia
                JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-actualizarSala");
            }

        }

    }

    public Sala buscarSalaXId(int id) {
        String sql = "SELECT * FROM sala WHERE idSala = ?";
        Sala sala = new Sala();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sala.setIdSala(rs.getInt("idSala"));
                sala.setUbicacion(rs.getString("ubicacion"));
                sala.setLocalidad(rs.getString("localidad"));
                sala.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-buscarSalaXId");

        }
        return sala;
    }

    public Sala buscarSalaXUbicacionXLocalidad(String ubicacion, String localidad) {
        String sql = "SELECT * FROM sala WHERE ubicacion = ? AND localidad = ?";
        Sala sala = new Sala();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ubicacion);
            ps.setString(2, localidad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sala.setIdSala(rs.getInt("idSala"));
                sala.setUbicacion(rs.getString("ubicacion"));
                sala.setLocalidad(rs.getString("localidad"));
                sala.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-buscarSalaXUbicacionXLocalidad");

        }
        return sala;
    }

    public List<Sala> obtenerSalasXLocalidad(String localidad) {
        ArrayList<Sala> listTemp = new ArrayList<>();

        String sql = "SELECT * FROM sala WHERE localidad = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, localidad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setIdSala(rs.getInt("idSala"));
                sala.setUbicacion(rs.getString("ubicacion"));
                sala.setLocalidad(rs.getString("localidad"));
                sala.setEstado(rs.getBoolean("estado"));
                listTemp.add(sala);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SalaData Sentencia SQL/base de datos inactiva, error-buscarSalaXLocalidad");
        }
        return listTemp;
    }

}
