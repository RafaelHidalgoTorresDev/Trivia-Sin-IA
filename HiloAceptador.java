package TRIVIAL_SIN_IA;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HiloAceptador implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Esperando jugadores... Escribe START para empezar.");

            while (!Servidor.juegoIniciado && Servidor.jugadores.size() < 10) {
                Socket socket = server.accept();

                if (Servidor.juegoIniciado) {
                    socket.close();
                    break;
                }

                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                salida.println("Dime tu nombre:");
                String nombre = entrada.readLine();

                Jugador nuevoJugador = new Jugador(nombre, salida);
                Servidor.jugadores.add(nuevoJugador);
                System.out.println("Nuevo jugador conectado: " + nombre);

                salida.println("Hola " + nombre + ". Espera a que el admin escriba START.");

                ClienteHandler handler = new ClienteHandler(socket, entrada, nuevoJugador);
                new Thread(handler).start();
            }
        } catch (Exception e) {
        }
    }
}
