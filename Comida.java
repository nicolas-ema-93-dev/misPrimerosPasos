package viborita;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Comida {
    private Point M;


    public Comida() {
        this.M = new Point(8,5);
    }

    public void generarNuevaPosicion(LinkedList<Point> cuerpoVibora) {
        Random random = new Random();
        boolean posicionOcupada;

        do {
            posicionOcupada = false; // Arrancamos suponiendo que el casillero está libre

            // 1. Generamos coordenadas al azar (ej: de 0 a 19)
            int newX = random.nextInt(20);
            int newY = random.nextInt(20);
            this.M = new Point(newX, newY);

            // 2. Recorremos TODO el cuerpo de la víbora para chequear si choca con alguno
            for (Point eslabon : cuerpoVibora) {
                if (eslabon.x == this.M.x && eslabon.y == this.M.y) {
                    posicionOcupada = true; // ¡Ups! Cayó arriba de la víbora
                    break; // No hace falta seguir buscando, ya sabemos que sirve para repetir
                }
            }

        } while (posicionOcupada); // Si estaba ocupada, el 'do-while' repite todo de nuevo
    }

    public Point getM() {
        return M;
    }
}
