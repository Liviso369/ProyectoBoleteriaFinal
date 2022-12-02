package Modelo;

import java.time.*;
import java.util.Objects;

public class Proyeccion {

    private int idProyeccion;

    private Sala sala;

    private Pelicula pelicula;

    private LocalDate fechaProyeccion;

    private LocalTime horarioInicio;

    private LocalTime horarioFin;

    public Proyeccion(int idProyeccion, Sala sala, Pelicula pelicula, LocalDate fechaProyeccion, LocalTime horarioInicio, LocalTime horarioFin) {
        this.idProyeccion = idProyeccion;
        this.sala = sala;
        this.pelicula = pelicula;
        this.fechaProyeccion = fechaProyeccion;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
    }

    public Proyeccion(Sala sala, Pelicula pelicula, LocalDate fechaProyeccion, LocalTime horarioInicio, LocalTime horarioFin) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.fechaProyeccion = fechaProyeccion;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
    }

    public Proyeccion() {
    }

    public int getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(int idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFechaProyeccion() {
        return fechaProyeccion;
    }

    public void setFechaProyeccion(LocalDate fechaProyeccion) {
        this.fechaProyeccion = fechaProyeccion;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    @Override
    public String toString() {
        return pelicula + "/" + fechaProyeccion + "/" + horarioInicio + "/" + horarioFin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proyeccion other = (Proyeccion) obj;
        if (this.sala.getIdSala() != other.sala.getIdSala()) {
            return false;
        }
        if (!Objects.equals(this.fechaProyeccion, other.fechaProyeccion)) {
            return false;
        }
        if (!Objects.equals(this.horarioInicio, other.horarioInicio)) {
            return false;
        }
        return true;
    }
    
    

}
