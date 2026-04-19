package TRIVIAL_SIN_IA;

public class Pregunta {
    String enunciado;
    String opcionA;
    String opcionB;
    String opcionC;
    String opcionD;
    String correcta;

    public Pregunta(String enunciado, String a, String b, String c, String d, String correcta) {
        this.enunciado = enunciado;
        this.opcionA = a;
        this.opcionB = b;
        this.opcionC = c;
        this.opcionD = d;
        this.correcta = correcta;
    }
}