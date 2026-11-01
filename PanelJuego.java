package viborita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelJuego extends JPanel implements ActionListener {

    private final int ANCHO = 400;
    private final int ALTO = 400;
    private final int TAMAÑO_CASILLA = 20;
    Timer timer;
    private Viborita v;
    private Comida comida;
    private int contadorC;
    private ComidaEspecial CM;
    private int puntos;
    private Ventana ventanaPrincipal;

    public PanelJuego(Ventana ventana) {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);

        this.ventanaPrincipal = ventana;
        v = new Viborita();
        comida = new Comida();
        puntos = 0;
        this.contadorC = 0;
        CM = new ComidaEspecial();
        // Activamos el escuchador de teclado para moverla en tiempo real
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:  v.setDireccion('L'); break;
                    case KeyEvent.VK_RIGHT: v.setDireccion('R'); break;
                    case KeyEvent.VK_UP:    v.setDireccion('U'); break;
                    case KeyEvent.VK_DOWN:  v.setDireccion('D'); break;
                }
            }
        });

        // El Timer hace "tic" cada 130 milisegundos y actualiza el juego
        timer = new Timer(130, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujamos el cuerpo de la víbora recorriendo su lista de puntos
        g.setColor(Color.GREEN);
        for (Point p : v.getCuerpo()) {
            // Multiplicamos por el tamaño de la casilla para llevar las coordenadas a píxeles en pantalla
            g.fillRect(p.x * TAMAÑO_CASILLA, p.y * TAMAÑO_CASILLA, TAMAÑO_CASILLA - 2, TAMAÑO_CASILLA - 2);
        }
        //Dibujamos la comida
        g.setColor(Color.red);
        Point posComida = comida.getM();
        g.fillRect(posComida.x * TAMAÑO_CASILLA, posComida.y * TAMAÑO_CASILLA
        ,TAMAÑO_CASILLA-2,TAMAÑO_CASILLA-2);

        //dibujamos la comida especial;
        if(contadorC > 10) {
            g.setColor(Color.ORANGE);
            Point posComidaespe = CM.getM();
            g.fillRect(posComidaespe.x * TAMAÑO_CASILLA, posComidaespe.y * TAMAÑO_CASILLA
                    , TAMAÑO_CASILLA - 2, TAMAÑO_CASILLA - 2);
        }


        //dibujamos el puntaje
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Puntos: " + puntos, 280, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Este método se ejecuta en cada "tic" del reloj:
        // 1. Mover la víbora
        v.mover();
        // 2. Verificar si chocó o comió
        if (v.comer(comida)) {
            contadorC = contadorC+1;
            sumarPuntos();
            v.crecer();               // Se agranda
            comida.generarNuevaPosicion(v.getCuerpo());// Hacemos aparecer otra manzana en otro lado
        } else if(CM.getM()!= null&&v.comerEspe(CM)&&contadorC >= 10){
            sumarPuntosEspe();
            v.crecer();
            contadorC = 0;
            CM.generarNuevaPosicion(v.getCuerpo());
        }else if (v.autoColicion(v.getCuerpo())) {
            timer.stop();
            String nombreJugador = JOptionPane.showInputDialog(
                    null,
                    "¡Game over! Tu puntaje fue: " + puntos + "\nIngresa tu nombre: ",
                    "Guardar puntaje",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (nombreJugador != null && !nombreJugador.trim().isEmpty()){
                BaseDeDatos.guardarPuntaje(nombreJugador.trim(), puntos);
            }else {
                BaseDeDatos.guardarPuntaje("anonimo", puntos);
            }


            ventanaPrincipal.cambiarPantallaPuntos();
        }
        // 3. Volver a pintar la pantalla
        repaint();
    }

    public void sumarPuntos(){
       puntos = puntos +100;
    }

    public void sumarPuntosEspe(){
        puntos = puntos +150;
    }


    public void reiniciarVariables(){
        this.puntos=0;
        v = new Viborita();
        comida = new Comida();
        contadorC = 0;
        timer.start();
    }
}