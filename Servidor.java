package TRIVIAL_SIN_IA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Servidor {
    public static ArrayList<Jugador> jugadores = new ArrayList<>();
    public static boolean juegoIniciado = false;

    public static void main(String[] args) {
        try {
            HiloAceptador aceptador = new HiloAceptador();
            Thread hiloAceptador = new Thread(aceptador);
            hiloAceptador.start();

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String comando = "";
            while (!comando.equals("START")) {
                comando = teclado.readLine();
            }

            juegoIniciado = true;
            System.out.println("El juego ha comenzado.");

            ArrayList<Pregunta> preguntas = new ArrayList<>();
            preguntas.add(new Pregunta("En Dark Souls, ¿Que anillo necesitas para caminar por el Abismo y luchar contra los Cuatro Reyes?", "a) Anillo del Abejorro", "b) Pacto de Artorias", "c) Anillo de Havel", "d) Anillo de favor y proteccion", "b"));
            preguntas.add(new Pregunta("¿En que nivel exacto ocurre el famoso bug de la pantalla de la muerte en Pac-Man?", "a) 99", "b) 128", "c) 255", "d) 256", "d"));
            preguntas.add(new Pregunta("En Zelda: Majora's Mask, ¿Cuantas mascaras coleccionables hay en total?", "a) 20", "b) 24", "c) 30", "d) 15", "b"));
            preguntas.add(new Pregunta("¿Cual es el verdadero nombre de Liquid Snake en Metal Gear Solid?", "a) David", "b) John", "c) Eli", "d) Jack", "c"));
            preguntas.add(new Pregunta("En Hollow Knight, ¿Como se llama el jefe final secreto del DLC Godmaster?", "a) El Destello", "b) Vasija Pura", "c) Destello Absoluto", "d) Rey Pesadilla Grimm", "c"));

            for (int i = 0; i < preguntas.size(); i++) {
                Pregunta p = preguntas.get(i);

                for (int j = 0; j < jugadores.size(); j++) {
                    jugadores.get(j).ultimaRespuesta = "";
                    jugadores.get(j).enviar("PREGUNTA " + (i + 1) + ": " + p.enunciado);
                    jugadores.get(j).enviar(p.opcionA);
                    jugadores.get(j).enviar(p.opcionB);
                    jugadores.get(j).enviar(p.opcionC);
                    jugadores.get(j).enviar(p.opcionD);
                    jugadores.get(j).enviar("Tienes 10 segundos. Escribe RESPUESTA y tu letra.");
                }

                Thread.sleep(10000);

                for (int j = 0; j < jugadores.size(); j++) {
                    Jugador jug = jugadores.get(j);
                    if (jug.ultimaRespuesta.equals(p.correcta)) {
                        jug.puntos++;
                    }
                }

                for (int j = 0; j < jugadores.size(); j++) {
                    Jugador jug = jugadores.get(j);
                    jug.enviar("--- RANKING ACTUAL ---");
                    for (int k = 0; k < jugadores.size(); k++) {
                        jug.enviar(jugadores.get(k).nombre + ": " + jugadores.get(k).puntos + " puntos");
                    }
                    jug.enviar("----------------------");
                }
            }

            System.out.println("--- RANKING FINAL ---");
            Jugador ganador = null;
            for (int j = 0; j < jugadores.size(); j++) {
                Jugador jug = jugadores.get(j);
                System.out.println(jug.nombre + ": " + jug.puntos + " puntos");

                if (ganador == null || jug.puntos > ganador.puntos) {
                    ganador = jug;
                }
            }

            if (ganador != null) {
                System.out.println("GANADOR: " + ganador.nombre);
            }

            for (int j = 0; j < jugadores.size(); j++) {
                Jugador jug = jugadores.get(j);
                jug.enviar("--- RANKING FINAL ---");
                for (int k = 0; k < jugadores.size(); k++) {
                    jug.enviar(jugadores.get(k).nombre + ": " + jugadores.get(k).puntos + " puntos");
                }
                if (ganador != null) {
                    jug.enviar("EL GANADOR ES: " + ganador.nombre);
                }
                jug.enviar("FIN");
            }

            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
