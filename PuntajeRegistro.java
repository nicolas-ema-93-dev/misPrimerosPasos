package viborita;

public class PuntajeRegistro {
    private String nombre;
    private int puntaje;

    public PuntajeRegistro(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }
}
