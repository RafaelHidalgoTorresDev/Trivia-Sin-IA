package TRIVIAL_SIN_IA;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            HiloLectorCliente lector = new HiloLectorCliente(entrada);
            new Thread(lector).start();

            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);
            }
        } catch (Exception e) {
            System.out.println("Se ha perdido la conexion con el servidor.");
        }
    }
}
