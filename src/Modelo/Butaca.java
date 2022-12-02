package Modelo;


public class Butaca {

    private int idButaca;

    private Sala sala;

    private int fila;

    private int columna;
    
    private boolean estado;

    public Butaca(int idButaca, Sala sala, int fila, int columna, boolean estado) {
        this.idButaca = idButaca;
        this.sala = sala;
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public Butaca(Sala sala, int fila, int columna, boolean estado) {
        this.sala = sala;
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public Butaca() {
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Butaca: " + fila + "/" + columna;
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
        final Butaca other = (Butaca) obj;
        if (this.fila != other.fila) {
            return false;
        }
        if (this.columna != other.columna) {
            return false;
        }
        return true;
    }
    
    
}
