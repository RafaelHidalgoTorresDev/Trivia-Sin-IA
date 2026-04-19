package TRIVIAL_SIN_IA;

import java.net.Socket;
import java.io.BufferedReader;

public class ClienteHandler implements Runnable {
    Socket socket;
    BufferedReader entrada;
    Jugador jugador;

    public ClienteHandler(Socket socket, BufferedReader entrada, Jugador jugador) {
        this.socket = socket;
        this.entrada = entrada;
        this.jugador = jugador;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.startsWith("RESPUESTA ")) {
                    String[] partes = mensaje.split(" ");
                    if (partes.length == 2) {
                        jugador.ultimaRespuesta = partes[1].toLowerCase();
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
