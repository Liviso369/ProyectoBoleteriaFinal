package Modelo;

import java.time.*;

public class Ticket {

    private int idTicket;

    private Proyeccion proyeccion;

    private Butaca butaca;

    private Cliente cliente;

    private LocalDate fechaCompra;
    
    private LocalTime horaCompra;

    private String formaPago;

    private double monto;
    

    public Ticket(int idTicket, Proyeccion proyeccion, Butaca butaca, Cliente cliente, LocalDate fechaCompra, LocalTime horaCompra, String formaPago, double monto) {
        this.idTicket = idTicket;
        this.proyeccion = proyeccion;
        this.butaca = butaca;
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
        this.formaPago = formaPago;
        this.monto = monto;
    }

    public Ticket(Proyeccion proyeccion, Butaca butaca, Cliente cliente, LocalDate fechaCompra, LocalTime horaCompra, String formaPago, double monto) {
        this.proyeccion = proyeccion;
        this.butaca = butaca;
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
        this.formaPago = formaPago;
        this.monto = monto;
    }

    public Ticket() {
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalTime getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(LocalTime horaCompra) {
        this.horaCompra = horaCompra;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return proyeccion + "/" + butaca + "/" + cliente + "/" + fechaCompra + "/" + horaCompra + "/" + formaPago + "/" + monto + '}';
    }

    
}
