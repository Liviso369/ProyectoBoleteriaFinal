/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Butaca;
import Modelo.Conexion;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ButacaData {

    private Connection con;
    private SalaData sd;

    public ButacaData() {
        this.con = Conexion.getConexion();
        this.sd = new SalaData();
    }

    public void guardarButaca(Butaca butaca) {

        boolean validar = false;

        //validar si una butaca esta ya en el sistema
        for (Butaca x : obtenerButacasXSala(butaca.getSala().getIdSala())) {

            if (butaca.equals(x)) {
                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "butaca existente");
        } else {

            String sql = "INSERT INTO butaca (idSala,fila,columna,estado) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setInt(1, butaca.getSala().getIdSala());
                ps.setInt(2, butaca.getFila());
                ps.setInt(3, butaca.getColumna());
                ps.setBoolean(4, butaca.isEstado());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    butaca.setIdButaca(rs.getInt(1));

                    JOptionPane.showMessageDialog(null, "butaca agregada");
                }
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL erronea/base de datos inactiva, error-guardarButaca");
            }
        }

    }

    public void actualizarButaca(Butaca butaca) {

        boolean validar = false;

        //validar si una butaca esta ya en el sistema
        for (Butaca x : obtenerButacasXSala(butaca.getSala().getIdSala())) {

            if (butaca.getFila() == x.getFila() && butaca.getColumna() == x.getColumna() && butaca.isEstado() == x.isEstado()) {
                validar = true;
            } else if (butaca.equals(x) && butaca.getIdButaca() != x.getIdButaca()) {
                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "butaca existente");
        } else {

            String sql = "UPDATE butaca SET fila = ?, columna = ?, estado = ? WHERE idButaca = ?";

            try {
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, butaca.getFila());
                ps.setInt(2, butaca.getColumna());
                ps.setBoolean(3, butaca.isEstado());
                ps.setInt(4, butaca.getIdButaca());

                if (ps.executeUpdate() > 0) {

                    JOptionPane.showMessageDialog(null, "butaca modificada");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL erronea/base de datos inactiva, error-actualizarButaca");
            }

        }
    }

    public List<Butaca> obtenerButacasXSala(int idSala) {

        String sql = "SELECT * FROM butaca WHERE idSala = ? ORDER BY fila ";

        List<Butaca> lista = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSala);

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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL erronea/base de datos inactiva, obtenerButacasXSala");
        }

        return lista;
    }

    public Butaca obtenerButacaXId(int idButaca) {
        String sql = "SELECT * FROM butaca WHERE idButaca = ?";

        Butaca butaca = new Butaca();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idButaca);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                butaca.setIdButaca(rs.getInt("idButaca"));
                butaca.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                butaca.setFila(rs.getInt("fila"));
                butaca.setColumna(rs.getInt("columna"));
                butaca.setEstado(rs.getBoolean("estado"));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL/base de datos inactiva, error-BuscarButacaXId");
        }

        return butaca;

    }

    public Butaca BuscarButacaXFilaXColumnaXSala(int fila, int columna, int idSala) {
        String sql = "SELECT * FROM butaca WHERE fila = ? AND columna = ? AND idSala = ?";

        Butaca butaca = new Butaca();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, fila);
            ps.setInt(2, columna);
            ps.setInt(3, idSala);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                butaca.setIdButaca(rs.getInt("idButaca"));
                butaca.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                butaca.setFila(rs.getInt("fila"));
                butaca.setColumna(rs.getInt("columna"));
                butaca.setEstado(rs.getBoolean("estado"));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ButacaData Sentencia SQL/base de datos inactiva, error-BuscarButacaXFilaXColumnaXSala");
        }

        return butaca;

    }

}
