package Modelo;

import java.util.Objects;


public class Sala {

    private int idSala;

    private String ubicacion;

    private String localidad;

    private boolean estado;

    public Sala(int idSala, String ubicacion, String localidad, boolean estado) {
        this.idSala = idSala;
        this.ubicacion = ubicacion;
        this.localidad = localidad;
        this.estado = estado;
    }

    public Sala(String ubicacion, String localidad, boolean estado) {
        this.ubicacion = ubicacion;
        this.localidad = localidad;
        this.estado = estado;
    }


    public Sala() {
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return ubicacion + "/" + localidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Sala other = (Sala) obj;
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        return true;
    }

    
    
    

   

    
}
