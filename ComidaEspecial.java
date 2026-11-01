package viborita;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ComidaEspecial  {
    private Point me;


    public ComidaEspecial(){
        Random random = new Random();
        int x = random.nextInt(0,19);
        int y = random.nextInt(0,19);
        this.me = new Point(x,y);
    }

    public void generarNuevaPosicion(LinkedList<Point> cuerpoVibora) {
        Random random = new Random();
        boolean posicionOcupada;

        do {
            posicionOcupada = false; // Arrancamos suponiendo que el casillero está libre

            // 1. Generamos coordenadas al azar (ej: de 0 a 19)
            int newX = random.nextInt(1,19);
            int newY = random.nextInt(1,19);
            this.me = new Point(newX, newY);

            // 2. Recorremos TODO el cuerpo de la víbora para chequear si choca con alguno
            for (Point eslabon : cuerpoVibora) {
                if (eslabon.x == this.me.x && eslabon.y == this.me.y) {
                    posicionOcupada = true; // ¡Ups! Cayó arriba de la víbora
                    break; // No hace falta seguir buscando, ya sabemos que sirve para repetir
                }
            }

        } while (posicionOcupada); // Si estaba ocupada, el 'do-while' repite todo de nuevo
    }



    public Point getM() {
        return me;
    }

}
