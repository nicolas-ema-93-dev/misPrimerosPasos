package viborita;

import java.awt.Point;
import java.util.LinkedList;

public class Viborita {
    // Usamos una lista de puntos (coordenadas x, y en la grilla)
    private LinkedList<Point> cuerpo;
    private char direccion; // 'R' (Derecha), 'L' (Izquierda), 'U' (Arriba), 'D' (Abajo)
    private Comida comida;
    public Viborita() {
        cuerpo = new LinkedList<>();
        // Arrancamos con un cuerpo de 3 eslabones de ejemplo
        cuerpo.add(new Point(5, 5)); // Cabeza
        cuerpo.add(new Point(4, 5)); // Cuerpo
        cuerpo.add(new Point(3, 5)); // Cola

        direccion = 'R'; // Arranca moviéndose a la derecha
        comida = comida;
    }

    public void mover() {
        // 1. Duplicamos la posición de la cabeza actual para crear la nueva cabeza
        Point cabezaActual = cuerpo.getFirst();
        Point nuevaCabeza = new Point(cabezaActual.x, cabezaActual.y);

        // 2. Desplazamos la nueva cabeza según la dirección actual
        switch (direccion) {
            case 'R': nuevaCabeza.x++; break;
            case 'L': nuevaCabeza.x--; break;
            case 'U': nuevaCabeza.y--; break;
            case 'D': nuevaCabeza.y++; break;
        }
        // --- 🌐 ACÁ AGREGAMOS LA MAGIA DE LOS BORDES INFINITOS ---
        int maxCasillas = 20; // Supongamos que tu tablero mide 20x20 casilleros

        // Si se pasa por la derecha (x >= 20), aparece en la izquierda (x = 0)
        if (nuevaCabeza.x >= maxCasillas) {
            nuevaCabeza.x = 0;
        }
        // Si se pasa por la izquierda (x < 0), aparece en la derecha (x = 19)
        else if (nuevaCabeza.x < 0) {
            nuevaCabeza.x = maxCasillas - 1;
        }

        // Si se pasa por abajo (y >= 20), aparece arriba (y = 0)
        if (nuevaCabeza.y >= maxCasillas) {
            nuevaCabeza.y = 0;
        }
        // Si se pasa por arriba (y < 0), aparece abajo (y = 19)
        else if (nuevaCabeza.y < 0) {
            nuevaCabeza.y = maxCasillas - 1;
        }
        // 3. Agregamos la nueva cabeza al principio de la lista
        cuerpo.addFirst(nuevaCabeza);

        // 4. Quitamos el último eslabón (para simular el movimiento, a menos que coma)
        cuerpo.removeLast();
    }

    public boolean autoColicion( LinkedList<Point> v){
        for(int i = 1; i < v.size();i++ ){
            if(v.getFirst().x == v.get(i).x && v.getFirst().y == v.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public void crecer() {
        // Si come, duplica el último eslabón para estirar el cuerpo
        Point cola = cuerpo.getLast();
        cuerpo.addLast(new Point(cola.x, cola.y));
    }

    // Getters y Setters para controlar la dirección desde el teclado
    public void setDireccion(char nuevaDir) {
        // Evitamos que gire 180 grados sobre su propio eje (ej: ir a la derecha y chocar yendo a la izquierda)
        if ((direccion == 'R' && nuevaDir != 'L') ||
                (direccion == 'L' && nuevaDir != 'R') ||
                (direccion == 'U' && nuevaDir != 'D') ||
                (direccion == 'D' && nuevaDir != 'U')) {
            direccion = nuevaDir;
        }
    }

    public boolean comer(Comida comida) {
        Point cabeza = cuerpo.getFirst();
        Point posComida = comida.getM();

        // Devuelve true solo si coincide tanto el ancho (X) como el alto (Y)
        return cabeza.x == posComida.x && cabeza.y == posComida.y;
    }

    public LinkedList<Point> getCuerpo() {
        return cuerpo;
    }

    public Point getCabeza() {
        return cuerpo.getFirst();
    }
}