package viborita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class MenuPrincipal extends JPanel implements ActionListener {
    private final int ANCHO = 400;
    private final int ALTO = 400;
    private JButton botonIniciar;
    private Ventana ventanaPrincipal;

    public MenuPrincipal(Ventana ventana){
        this.ventanaPrincipal = ventana;

        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setLayout(null);


        //Activo el escuchador de clicks
        this.botonIniciar = new JButton("Iniciar partida");
        botonIniciar.addActionListener(this);

        add(botonIniciar);
        botonIniciar.setBounds(100,310,200,40);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Viborita Game ", 60, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonIniciar){

            ventanaPrincipal.cambiarPantallaJuego();

        }
    }
}
