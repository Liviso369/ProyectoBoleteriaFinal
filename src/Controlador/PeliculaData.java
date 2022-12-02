/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PeliculaData {

    Connection con;

    public PeliculaData() {
        this.con = Conexion.getConexion();
    }

    public void guardarPelicula(Pelicula pelicula) {

        boolean validar = false;

        for (Pelicula x : obtenerPeliculas()) {

            if (pelicula.equals(x) || pelicula.getNombre().equalsIgnoreCase(x.getNombre()) ) {// valida de que en base de datos no haya otra pelicula

                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Existe otra pelicula con el mismo nombre");

        } else {
            String sql = "INSERT INTO pelicula (nombre, genero,estado) VALUES (?, ?, ?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, pelicula.getNombre());
                ps.setString(2, pelicula.getGenero());
                ps.setBoolean(3, pelicula.isEstado());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();//recupero las llaves el ultimo insertado es la primera llave
                if (rs.next()) {

                    pelicula.setIdPelicula(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "pelicula agregada");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "peliculaData Sentencia SQL/base de datos inactiva, error-guardarpelicula");
            }
        }
    }

    public List<Pelicula> obtenerPeliculas() {
        List<Pelicula> listTemp = new ArrayList<>();
        String sql = "SELECT * FROM pelicula WHERE estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstado(rs.getBoolean("estado"));

                listTemp.add(pelicula);

            }
            ps.close(); //cerra la coneccion      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PeliculaData Sentencia SQL/base de datos inactiva, error-obtenerPeliculas");
        }
        return listTemp;
    }

    public void actualizarPeliculas(Pelicula pelicula) {
        boolean validar = false;

        for (Pelicula x : obtenerPeliculas()) {

            if ((pelicula.equals(x) || pelicula.getNombre().equalsIgnoreCase(x.getNombre())) && pelicula.getIdPelicula() != x.getIdPelicula()) { // valida de que en base de datos no haya otro pelicula igual pero con distinto id

                validar = true;
            } else if (pelicula.equals(x) && pelicula.getGenero().equals(x.getGenero()) && pelicula.isEstado() == x.isEstado()) {
// valida que si deje todo los datos igual, le avisa que existe la pelicula con esos mismos datos
                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Pelicula existente");

        } else {

            String sql = "UPDATE pelicula set nombre = ?, genero = ?, estado = ? WHERE idPelicula = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, pelicula.getNombre());
                ps.setString(2, pelicula.getGenero());
                ps.setBoolean(3, pelicula.isEstado());
                ps.setInt(4, pelicula.getIdPelicula());

                if (ps.executeUpdate() > 0) {//cantidad de filas afectadas

                    JOptionPane.showMessageDialog(null, "Pelicula modificada");
                }

                ps.close();

            } catch (SQLException ex) {//error en la sentencia
                JOptionPane.showMessageDialog(null, "PeliculaData Sentencia SQL/base de datos inactiva, error-actualizarPelicula");
            }

        }
    }

    public Pelicula buscarPeliculaXId(int idPelicula) {
        String sql = "SELECT * FROM pelicula WHERE idPelicula = ?";
        Pelicula pelicula = new Pelicula();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "PeliculaData Sentencia SQL/base de datos inactiva, error-buscarPeliculaXId");

        }
        return pelicula;
    }

    public Pelicula buscarPeliculaXNombre(String nombre) {
        String sql = "SELECT * FROM pelicula WHERE nombre = ?";
        Pelicula pelicula = new Pelicula();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "PeliculaData Sentencia SQL/base de datos inactiva, error-buscarPeliculaxNombre");

        }
        return pelicula;
    }

    public List<Pelicula> obtenerPeliculasXGenero(String genero) {
        List<Pelicula> listTemp = new ArrayList<>();

        String sql = "SELECT * FROM pelicula WHERE genero = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, genero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setEstado(rs.getBoolean("estado"));
                listTemp.add(pelicula);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PeliculaData Sentencia SQL/base de datos inactiva, error-obtenerPeliculasXGenero");
        }
        return listTemp;
    }

}
