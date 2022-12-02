package Modelo;

import java.util.Objects;


public class Pelicula {

    private int idPelicula;

    private String nombre;
    
    private String genero;

    private boolean estado;

    public Pelicula(int idPelicula, String nombre, String genero, boolean estado) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.genero = genero;
        this.estado = estado;
    }

    public Pelicula(String nombre, String genero, boolean estado) {
        this.nombre = nombre;
        this.genero = genero;
        this.estado = estado;
    }

    public Pelicula() {
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return  nombre;
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
        final Pelicula other = (Pelicula) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
   
}
