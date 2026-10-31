package viborita;

import viborita.BaseDeDatos;

import javax.swing.*;

public class Ventana extends JFrame {
    private MenuPrincipal menuPrincipal;
    private PanelJuego panelJuego;
    private TablaDePuntajes records;
    private Viborita v;
    public Ventana() {
        setTitle("Viborita Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        // 1. Inicializamos los paneles
        // Le pasamos 'this' (esta ventana) al menú para que sepa a quién hablarle
        menuPrincipal = new MenuPrincipal(this);
        panelJuego = new PanelJuego(this);
        records = new TablaDePuntajes(this);
        // 2. Arrancamos mostrando el menú principal
        add(menuPrincipal);

        setVisible(true);
    }

    // Este es el método que ejecuta el cambio cuando tocan el botón
    public void cambiarPantallaJuego() {
        panelJuego.reiniciarVariables();
        remove(menuPrincipal); // Sacamos el menú
        add(panelJuego);     // Metemos el juego

        revalidate();        // Le avisamos al contenedor que recalculé los componentes
        repaint();           // Redibujamos la ventana

        panelJuego.requestFocusInWindow(); // Le damos el foco al juego para que reaccione al teclado


    }

    public void cambiarPantallaPuntos(){
        remove(panelJuego);
        add(records);

        revalidate();
        repaint();
    }

    public void volverMenuPrincipal(){
        remove(records);
        add(menuPrincipal);

        revalidate();
        repaint();
    }



    public static  void main(String[] args){
        BaseDeDatos.inicializarBD();
        new Ventana();
    }
}