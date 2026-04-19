package TRIVIAL_SIN_IA;

import java.io.BufferedReader;

public class HiloLectorCliente implements Runnable {
    BufferedReader entrada;

    public HiloLectorCliente(BufferedReader entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("FIN")) {
                    System.exit(0);
                }
                System.out.println(mensaje);
            }
        } catch (Exception e) {
        }
    }
}
