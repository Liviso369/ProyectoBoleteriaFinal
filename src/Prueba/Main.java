/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import Controlador.*;
import Modelo.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    static ClienteData cd = new ClienteData();
    static Cliente cliente = null;

    static ButacaData bd = new ButacaData();
    static Butaca butaca = null;

    static SalaData sd = new SalaData();
    static Sala sala = null;

    static PeliculaData pd = new PeliculaData();
    static Pelicula pelicula = null;

    static ProyeccionData prod = new ProyeccionData();
    static Proyeccion proyeccion = null;

    static TicketData td = new TicketData();
    static Ticket ticket = null;

    public static void main(String[] args) {
//        guardarCliente();
//        actualizarCliente();
//        buscarClienteXDni();
//        obtenerClientesXApellido();
//        obtenerClienteXId();
//        obtenerClientes();
/////////////////////////////////////////////////
//        guardarButaca();
//        actualizarButaca();
//        obtenerButacasXSala();
//        obtenerButacasXId();
//        BuscarButacaXFilaXColumnaXSala();
/////////////////////////////////////////////////
//        guardarTicket();
//        BorrarTicket();
//        obtenerproyeccionXFechaActualXHoraActualOMayor();
//        obtenerButacasLibres();
//        obtenerTicketsXFecha();
//        obtenerTicketsXPelicula();
//        obtenerClientesXFecha();
//////////////////////////////////////////////////
//        guardarSala();
//        obtenerSalas();
//        actualizarSala();
//        buscarSalaXId();
//        buscarSalaXUbicacionXLocalidad();
//        obtenerSalasXLocalidad();
//////////////////////////////////////////////////
//        guardarPelicula();
//        obtenerPelicula();
//        actualizarPelicula();
//        buscarPeliculaXId();
//        buscarPeliculaXNombre();
//        obtenerPeliculasXGenero();
//////////////////////////////////////////////////
//        guardarProyeccion();
//        obtenerProyeccion();
//        obtenerProyeccionXId();
//        obtenerProyeccionXPeliculaXSalaXHoraXfecha();
//        obtenerIdPeliculaXSalaXHora();
//        obtenerProyeccionesXPelicula();
//        obtenerProyeccionXFechaXSala();
//        BorrarProyeccion();
//        obtenerIdSalaXPelicula();
    }

    public static void guardarCliente() {

        cliente = new Cliente(32444555, "Rodolfo", "Suarez", true);

        cd.guardarCliente(cliente);
    }

    public static void actualizarCliente() {
        for (Cliente x : cd.obtenerClientes()) {

            cliente = x;
        }

        cliente.setNombre("Tomas");

        cd.actualizarCliente(cliente);

    }

    public static void buscarClienteXDni() {

        cliente = cd.buscarClienteXDni(32444555);

        System.out.println(cliente.getNombre() + "/" + cliente.getApellido() + "/" + cliente.getDni());

    }

    public static void obtenerClientesXApellido() {
        for (Cliente x : cd.obtenerClientesXApellido("Suarez")) {
            System.out.println(x.getNombre() + "/" + x.getApellido() + "/" + x.getDni());
        }
    }

    public static void obtenerClienteXId() {

        for (Cliente x : cd.obtenerClientes()) {

            cliente = x;
        }
        cliente = cd.obtenerClienteXId(cliente.getIdCliente());
        System.out.println(cliente.getNombre() + "/" + cliente.getApellido() + "/" + cliente.getDni());
    }

    public static void obtenerClientes() {

        for (Cliente x : cd.obtenerClientes()) {

            System.out.println(x.getDni() + "/" + x.getNombre() + "/" + x.getApellido());
        }
    }

    /////////////////////////////////////////////////////////////////
    public static void guardarButaca() {

        sala = sd.buscarSalaXId(1);

        butaca = new Butaca(sala, 0, 3, true);

        bd.guardarButaca(butaca);
    }

    public static void actualizarButaca() {

        sala = sd.buscarSalaXId(1);

        butaca = new Butaca(6, sala, 1, 3, true);

        bd.actualizarButaca(butaca);

    }

    public static void obtenerButacasXSala() {

        for (Butaca x : bd.obtenerButacasXSala(1)) {

            System.out.println(x.getFila() + "/" + x.getColumna() + "/" + x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad());

        }
    }

    public static void obtenerButacasXId() {

        butaca = bd.obtenerButacaXId(1);
        System.out.println(butaca.getFila() + "/" + butaca.getColumna() + "/" + butaca.getSala().getUbicacion() + "/" + butaca.getSala().getLocalidad());
    }

    public static void BuscarButacaXFilaXColumnaXSala() {
        butaca = bd.BuscarButacaXFilaXColumnaXSala(0, 0, 1);
        System.out.println(butaca.getIdButaca() + "/" + butaca.getFila() + "/" + butaca.getColumna() + "/" + butaca.getSala().getUbicacion() + "/" + butaca.getSala().getLocalidad());
    }

    /////////////////////////////////////////////////////////////////
    public static void guardarTicket() {

        proyeccion = prod.obtenerProyeccionXId(1);//robocop sala1 san luis

        butaca = bd.obtenerButacaXId(3);

        cliente = cd.obtenerClienteXId(1);

        ticket = new Ticket(proyeccion, butaca, cliente, LocalDate.of(2022, 12, 2), LocalTime.of(15, 34, 00), "Mercado Pago", 800);

        td.guardarTicket(ticket);
    }

    public static void BorrarTicket() {

        proyeccion = prod.obtenerProyeccionXId(1);

        butaca = bd.obtenerButacaXId(1);

        cliente = cd.obtenerClienteXId(1);//le cambio de cliente

        ticket = new Ticket(1, proyeccion, butaca, cliente, LocalDate.of(2022, 12, 01), LocalTime.of(15, 00, 00), "Mercado Pago", 800);

        td.BorrarTicket(ticket);
    }

    public static void obtenerproyeccionXFechaActualXHoraActualOMayor() {

        for (Proyeccion x : td.obtenerproyeccionXFechaActualOMayor()) {

            System.out.println(x.getPelicula().getNombre() + "/" + x.getSala().getUbicacion() + "/" + x.getFechaProyeccion() + "/" + x.getHorarioFin());
        }

    }

    public static void obtenerButacasLibres() {

        proyeccion = prod.obtenerProyeccionXId(1);

        for (Butaca x : td.obtenerButacasLibres(proyeccion)) {

            System.out.println(x.getFila() + "/" + x.getColumna() + "/" + x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad());

        }
    }

    public static void obtenerTicketsXFecha() {

        for (Ticket x : td.obtenerTicketsXFecha(LocalDate.of(2022, 12, 02))) {

            System.out.println(x.getCliente().getNombre() + "/" + x.getProyeccion().getPelicula().getNombre() + "/" + x.getProyeccion().getSala().getUbicacion() + "/" + x.getButaca().getFila() + "/" + x.getButaca().getColumna() + "/" + x.getFechaCompra());
        }

    }

    public static void obtenerTicketsXPelicula() {

        for (Ticket x : td.obtenerTicketsXPelicula(5)) {

            System.out.println(x.getCliente().getNombre() + "/" + x.getProyeccion().getPelicula().getNombre() + "/" + x.getProyeccion().getSala().getUbicacion() + "/" + x.getButaca().getFila() + "/" + x.getButaca().getColumna() + "/" + x.getFechaCompra());

        }
    }

    public static void obtenerClientesXFecha() {
        for (Cliente x : td.obtenerClientesXFecha(LocalDate.of(2022, 12, 02))) {
            System.out.println(x.getNombre() + "/" + x.getApellido() + "/" + x.getDni());
        }
    }

    //////////////////////////////////////////////////////////////////
    public static void guardarSala() {
        sala = new Sala("Sala 4", "Villa Mercedes", true);
        sd.guardarSala(sala);
    }

    public static void obtenerSalas() {
        for (Sala x : sd.obtenerSalas()) {
            System.out.println(x);
        }
    }

    public static void actualizarSala() {
        for (Sala x : sd.obtenerSalas()) {
            sala = x;
        }

        sala.setUbicacion("Sala 5");
        sala.setLocalidad("San Luis");
        sd.actualizarSala(sala);
    }

    public static void buscarSalaXId() {
        sala = sd.buscarSalaXId(1);

        System.out.println(sala.getUbicacion() + "/" + sala.getLocalidad());
    }

    public static void buscarSalaXUbicacionXLocalidad() {
        sala = sd.buscarSalaXUbicacionXLocalidad("Sala 2", "San Luis");

        System.out.println(sala.getIdSala() + "/" + sala.getUbicacion() + "/" + sala.getLocalidad());
    }

    public static void obtenerSalasXLocalidad() {
        for (Sala x : sd.obtenerSalasXLocalidad("San Luis")) {

            System.out.println(x.getIdSala() + "/" + x.getUbicacion() + "/" + x.getLocalidad());
        }
    }

    //////////////////////////////////////////////////////////////////
    public static void guardarPelicula() {
        pelicula = new Pelicula("Spiderman 2", "Ciencia Ficcion", true);
        pd.guardarPelicula(pelicula);
    }

    public static void obtenerPelicula() {
        for (Pelicula x : pd.obtenerPeliculas()) {
            System.out.println(x);
        }
    }

    public static void actualizarPelicula() {
        for (Pelicula x : pd.obtenerPeliculas()) {
            pelicula = x;
        }

        pelicula.setNombre("Spiderman 3");
        pd.actualizarPeliculas(pelicula);
    }

    public static void buscarPeliculaXId() {
        pelicula = pd.buscarPeliculaXId(1);

        System.out.println(pelicula.getNombre());
    }

    public static void buscarPeliculaXNombre() {

        pelicula = pd.buscarPeliculaXNombre("Black Adam");

        System.out.println(pelicula.getNombre() + "/" + pelicula.getGenero());
    }

    public static void obtenerPeliculasXGenero() {

        for (Pelicula x : pd.obtenerPeliculasXGenero("Acción")) {

            System.out.println(x.getNombre() + "/" + x.getGenero());

        }
    }

    //////////////////////////////////////////////////////////////////
    public static void guardarProyeccion() {

        sala = sd.buscarSalaXId(1);
        pelicula = pd.buscarPeliculaXId(1);

        proyeccion = new Proyeccion(sala, pelicula, LocalDate.of(2022, 11, 30), LocalTime.of(16, 00), LocalTime.of(19, 30));

        prod.guardarProyeccion(proyeccion);
    }

    public static void obtenerProyeccion() {

        for (Proyeccion x : prod.obtenerProyeccion()) {
            System.out.println(x);
        }
    }

    public static void obtenerProyeccionXId() {
        proyeccion = prod.obtenerProyeccionXId(3);

        System.out.println(proyeccion.getPelicula().getNombre() + "/" + proyeccion.getSala().getUbicacion() + "/" + proyeccion.getSala().getLocalidad() + "/" + proyeccion.getFechaProyeccion() + "/" + proyeccion.getHorarioInicio());
    }

    public static void obtenerProyeccionXPeliculaXSalaXHoraXfecha() {

        proyeccion = prod.obtenerProyeccionXPeliculaXSalaXHoraXfecha(4, 1, LocalTime.of(16, 00, 00), LocalDate.of(2022, 12, 02));

        System.out.println(proyeccion.getPelicula().getNombre() + "/" + proyeccion.getSala().getUbicacion() + "/" + proyeccion.getSala().getLocalidad() + "/" + proyeccion.getFechaProyeccion() + "/" + proyeccion.getHorarioInicio());

    }

    public static void obtenerIdPeliculaXSalaXHora() {

        for (Integer x : prod.obtenerIdPeliculaXSalaXHora(1, LocalTime.of(20, 00, 00))) {

            pelicula = pd.buscarPeliculaXId(x);

            System.out.println(pelicula.getNombre() + "/" + pelicula.getGenero() );
        }

    }

    public static void obtenerProyeccionesXPelicula() {

        for (Proyeccion x : prod.obtenerProyeccionesXPelicula(2)) {

            System.out.println(x.getPelicula().getNombre() + "/" + x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad() + "/" + x.getFechaProyeccion() + "/" + x.getHorarioInicio());
        }
    }

    public static void obtenerProyeccionXFechaXSala() {

        for (Proyeccion x : prod.obtenerProyeccionXFechaXSala(LocalDate.of(2022, 12, 02), 1)) {
            System.out.println(x.getPelicula().getNombre() + "/" + x.getSala().getUbicacion() + "/" + x.getSala().getLocalidad() + "/" + x.getFechaProyeccion() + "/" + x.getHorarioInicio());
        }
    }

    public static void BorrarProyeccion() {
        for (Proyeccion x : prod.obtenerProyeccion()) {
            proyeccion = x;
        }

        prod.BorrarProyeccion(proyeccion);
    }

    public static void obtenerIdSalaXPelicula() {

        for (Integer x : prod.obtenerIdSalaXPelicula(1)) {

            Sala sala = sd.buscarSalaXId(x);
            
             System.out.println(sala.getUbicacion() + "/" + sala.getLocalidad() );
        }
    }
}
