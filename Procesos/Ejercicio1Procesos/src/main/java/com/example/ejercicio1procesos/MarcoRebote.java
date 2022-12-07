package com.example.ejercicio1procesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MarcoRebote extends JFrame {

    private LaminaPelota lamina;

    public MarcoRebote(){

        setBounds(600,300,400,350);

        setTitle ("Rebotes");

        lamina=new LaminaPelota();

        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones=new JPanel();

        ponerBoton(laminaBotones, "Dale!", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                comienza_el_juego();
            }

        });


        ponerBoton(laminaBotones, "Salir", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                System.exit(0);
            }

        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    public void comienza_el_juego (){
        Pelota pelota=new Pelota();

        lamina.add(pelota);

        for (int i=1; i<=3000; i++){
            pelota.moverPelota(lamina.getBounds());
            lamina.paint(lamina.getGraphics());
        }
    }

    //Ponemos botones

    public void ponerBoton(Container c, String titulo, ActionListener oyente){
        JButton boton=new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    class LaminaPelota extends JPanel{
        private List<Pelota> pelotas=new ArrayList<Pelota>();

        //Añadimos pelota a la lámina
        public void add(Pelota b){
            pelotas.add(b);
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D)g;
            for(Pelota b: pelotas){
                g2.fill(b.getShape());
            }

        }

    }


}
