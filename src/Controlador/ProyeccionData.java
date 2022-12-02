/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Proyeccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProyeccionData {

    private Connection con;
    SalaData sd;
    PeliculaData pd;

    public ProyeccionData() {
        this.con = Conexion.getConexion();
        sd = new SalaData();
        pd = new PeliculaData();

    }

    public void guardarProyeccion(Proyeccion proyeccion) {
        boolean validar = false;

        for (Proyeccion x : obtenerProyeccion()) {

            if (proyeccion.equals(x)) {// valida de que en base de datos no haya otro proyeccion

                validar = true;
            }
        }

        if (validar) {

            JOptionPane.showMessageDialog(null, "Existe otra proyeccion con el misma sala-fecha-hora");

        } else {
            String sql = "INSERT INTO proyeccion (idSala, idPelicula, fechaProyeccion, horarioInicio, horarioFin) VALUES (?,?,?,?,?)";

            try {

                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, proyeccion.getSala().getIdSala());
                ps.setInt(2, proyeccion.getPelicula().getIdPelicula());
                ps.setDate(3, Date.valueOf(proyeccion.getFechaProyeccion()));
                ps.setTime(4, Time.valueOf(proyeccion.getHorarioInicio()));
                ps.setTime(5, Time.valueOf(proyeccion.getHorarioFin()));

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    proyeccion.setIdProyeccion(rs.getInt(1));

                    JOptionPane.showMessageDialog(null, "Proyeccion agregada");
                }
                ps.close();
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-guardarProyeccion");
            }

        }

    }

    public List<Proyeccion> obtenerProyeccion() {

        ArrayList<Proyeccion> listTemp = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion";
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

                listTemp.add(proyeccion);

            }
            ps.close(); //cerra la coneccion      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerProyeccion");
        }
        return listTemp;

    }

    public Proyeccion obtenerProyeccionXId(int idProyeccion) {

        String sql = "SELECT * FROM proyeccion WHERE idProyeccion = ?";

        Proyeccion proyeccion = new Proyeccion();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idProyeccion);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                proyeccion.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                proyeccion.setPelicula(pd.buscarPeliculaXId(rs.getInt("idPelicula")));
                proyeccion.setFechaProyeccion(rs.getDate("fechaProyeccion").toLocalDate());
                proyeccion.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                proyeccion.setHorarioFin(rs.getTime("horarioFin").toLocalTime());
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerProyeccionXIdroyeccion");
        }

        return proyeccion;

    }

    public Proyeccion obtenerProyeccionXPeliculaXSalaXHoraXfecha(int idpelicula, int idSala, LocalTime hora, LocalDate fecha) {

        String sql = "SELECT * FROM proyeccion WHERE idPelicula = ? AND idSala = ? AND horarioInicio = ? AND fechaProyeccion = ?";

        Proyeccion proyeccion = new Proyeccion();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idpelicula);
            ps.setInt(2, idSala);
            ps.setTime(3, java.sql.Time.valueOf(hora));
            ps.setDate(4, java.sql.Date.valueOf(fecha));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                proyeccion.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                proyeccion.setPelicula(pd.buscarPeliculaXId(rs.getInt("idPelicula")));
                proyeccion.setFechaProyeccion(rs.getDate("fechaProyeccion").toLocalDate());
                proyeccion.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                proyeccion.setHorarioFin(rs.getTime("horarioFin").toLocalTime());
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerProyeccionXPeliculaXSala");
        }

        return proyeccion;
    }

    public List<Integer> obtenerIdPeliculaXSalaXHora(int idSala, LocalTime hora) {

        ArrayList<Integer> listTemp = new ArrayList<>();

        String sql = "SELECT DISTINCT idPelicula FROM proyeccion WHERE idSala = ? AND horarioInicio = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idSala);
            ps.setTime(2, java.sql.Time.valueOf(hora));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idPelicula");
                listTemp.add(id);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error- obtenerIdPeliculaXSalaXHora");
        }

        return listTemp;
    }

    public List<Proyeccion> obtenerProyeccionesXPelicula(int idPelicula) {

        ArrayList<Proyeccion> listTemp = new ArrayList<>();

        String sql = "SELECT * FROM proyeccion WHERE idPelicula = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idPelicula);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyeccion proyeccion = new Proyeccion();
                proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                proyeccion.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                proyeccion.setPelicula(pd.buscarPeliculaXId(rs.getInt("idPelicula")));
                proyeccion.setFechaProyeccion(rs.getDate("fechaProyeccion").toLocalDate());
                proyeccion.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                proyeccion.setHorarioFin(rs.getTime("horarioFin").toLocalTime());

                listTemp.add(proyeccion);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerProyeccionesXPelicula");
        }
        return listTemp;

    }

    public List<Proyeccion> obtenerProyeccionXFechaXSala(LocalDate fecha, int idSala) {
        ArrayList<Proyeccion> listTemp = new ArrayList<>();
        String sql = "SELECT * FROM proyeccion WHERE fechaProyeccion = ? AND idSala = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            ps.setInt(2, idSala);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Proyeccion proyeccion = new Proyeccion();
                proyeccion.setIdProyeccion(rs.getInt("idProyeccion"));
                proyeccion.setSala(sd.buscarSalaXId(rs.getInt("idSala")));
                proyeccion.setPelicula(pd.buscarPeliculaXId(rs.getInt("idPelicula")));
                proyeccion.setFechaProyeccion(rs.getDate("fechaProyeccion").toLocalDate());
                proyeccion.setHorarioInicio(rs.getTime("horarioInicio").toLocalTime());
                proyeccion.setHorarioFin(rs.getTime("horarioFin").toLocalTime());
                listTemp.add(proyeccion);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerProyeccionXFechaXSala");
        }

        return listTemp;
    }

    public void BorrarProyeccion(Proyeccion proyeccion) {

        String sql = "DELETE FROM proyeccion WHERE idproyeccion = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, proyeccion.getIdProyeccion());

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Proyeccion Borrada");

            } else {
                JOptionPane.showMessageDialog(null, "Proyeccion ya fue Borrada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-BorrarProyeccion");
        }
    }

    public List<Integer> obtenerIdSalaXPelicula(int idPelicula) {

        ArrayList<Integer> listTemp = new ArrayList<>();

        String sql = "SELECT DISTINCT idSala FROM proyeccion WHERE idPelicula = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idPelicula);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idSala");

                listTemp.add(id);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ProyeccionData base de datos inactiva, error-obtenerIdSalaXPelicula");
        }
        return listTemp;

    }

}
