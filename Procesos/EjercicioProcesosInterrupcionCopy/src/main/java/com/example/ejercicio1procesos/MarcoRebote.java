package com.example.ejercicio1procesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MarcoRebote extends JFrame {

    private final LaminaPelota lamina = new LaminaPelota();
    private Thread thread;

    public MarcoRebote(){

        setBounds(600,300,400,350);

        setTitle ("Rebotes");

        //Anyadimos la lamina en el centro
        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones=new JPanel();
        //Dibujar el boton EMPEZAR
        ponerBoton(laminaBotones, "Dale!", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                comienza_el_juego();
            }

        });

        //Dibujar el boton SALIR
        ponerBoton(laminaBotones, "Salir", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                System.exit(0);
            }

        });

        //Dibuja el boton de detener
        ponerBoton(laminaBotones, "Detener", new ActionListener(){

            public void actionPerformed(ActionEvent evento){
                detener();
            }

        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    public void comienza_el_juego ()  {
        Pelota pelota=new Pelota();
        lamina.add(pelota);

        //*********TEORIA*************//
        //La pelota tendra 3000 mil movimientos
        //Java es un programa monotarea no permite mas de 1 ejecutandose a la vez.
        //Se necesita que termine primero el primer hilo para despues comenzar el 2 hilo.
        //Esto pasa cuando quieres salir del programa no te dejara cerrar hasta que no termine la animacion.
//        for (int i=1; i<=3000; i++){
//            pelota.moverPelota(lamina.getBounds());
//            //pinta la lamina
//            lamina.paint(lamina.getGraphics());
//            try {
//                Thread.sleep(4);
//            }catch (InterruptedException io){
//                io.printStackTrace();
//            }
//
//        }
        //Convertir ahora el programa en Multitarea - es decir que mas de 1 hilo se ejecuten al mismo tiempo

        Runnable runnable = new PelotaHilos(pelota,lamina);
        thread = new Thread(runnable);
        thread.start();

    }

    public void detener(){
        thread.interrupt();

    }


    //Ponemos botones
    public void ponerBoton(Container c, String titulo, ActionListener oyente){
        JButton boton=new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }


    //*********SEGUNDA CLASE ********************///
    class LaminaPelota extends JPanel{
        private final List<Pelota> pelotas=new ArrayList<>();

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
