package viborita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TablaDePuntajes extends JPanel implements ActionListener {
    private final int ANCHO = 400;
    private final int ALTO = 400;
    private JButton salir;
    private Ventana ventanaPuntos;

            public TablaDePuntajes(Ventana ventana){
                 this.ventanaPuntos = ventana;

                 setPreferredSize(new Dimension(ANCHO, ALTO));
                 setBackground(Color.BLACK);
                 setLayout(null);

                 this.salir = new JButton("Salir");
                 this.salir.addActionListener(this);

                 add(salir);
                 salir.setBounds(100,310,200,40);


            }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD,36));
        g.drawString("Top five records: ", 60,30);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD,14));
        int y = 100;
        for(PuntajeRegistro r : BaseDeDatos.mostrarTopPuntajes()){
            g.drawString(" "+r.getNombre() +", "+ r.getPuntaje(),80,y );
            y = y+30;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == salir){
                ventanaPuntos.volverMenuPrincipal();
            }
    }
}
