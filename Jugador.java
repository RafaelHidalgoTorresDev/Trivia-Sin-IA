package TRIVIAL_SIN_IA;

import java.io.PrintWriter;

public class Jugador {
    String nombre;
    int puntos;
    String ultimaRespuesta;
    PrintWriter salida;

    public Jugador(String nombre, PrintWriter salida) {
        this.nombre = nombre;
        this.puntos = 0;
        this.ultimaRespuesta = "";
        this.salida = salida;
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }
}
