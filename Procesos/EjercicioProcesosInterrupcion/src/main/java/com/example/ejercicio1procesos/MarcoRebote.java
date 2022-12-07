package com.example.ejercicio1procesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MarcoRebote extends JFrame {

    private final LaminaPelota lamina = new LaminaPelota();
    private Thread thread, thread2, thread3;
    private final JButton btnStart, btnStart2, btnStart3, bntDetener, btnDetener2, btnDetener3;

    public MarcoRebote(){

        setBounds(600,300,600,350);

        setTitle ("Rebotes");

        //Anyadimos la lamina en el centro
        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones=new JPanel();

        //Creamos los botones

        btnStart = new JButton("Hilo1");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });

        laminaBotones.add(btnStart);

        btnStart2 = new JButton("Hilo2");
        btnStart2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });

        laminaBotones.add(btnStart2);


        btnStart3 = new JButton("Hilo3");

        btnStart3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });

        laminaBotones.add(btnStart3);
        //Botones para detener

        bntDetener = new JButton("Detener");
        bntDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });
        laminaBotones.add(bntDetener);

        btnDetener2 = new JButton("Detener2");
        btnDetener2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });
        laminaBotones.add(btnDetener2);

        btnDetener3 = new JButton("Detener3");
        btnDetener3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });

        laminaBotones.add(btnDetener3);

        add(laminaBotones, BorderLayout.SOUTH);
    }

    public void comienza_el_juego (ActionEvent button)  {
        Pelota pelota=new Pelota();
        lamina.add(pelota);
        Runnable runnable = new PelotaHilos(pelota,lamina);
        if (button.getSource().equals(btnStart)){
            thread = new Thread(runnable);
            thread.start();
        }else if(button.getSource().equals(btnStart2)){
            thread2 = new Thread(runnable);
            thread2.start();
        } else if (button.getSource().equals(btnStart3)) {
            thread3 = new Thread(runnable);
            thread3.start();
        }


    }


    public void detener(ActionEvent button){
        //Problema que tan solo se detiene el ultimo thread creado.
        if (button.getSource().equals(bntDetener)){
            thread.interrupt();
        }else if(button.getSource().equals(btnDetener2)){
            thread2.interrupt();
        } else if (button.getSource().equals(btnDetener3)) {
            thread3.interrupt();
        }

    }


    //*********SEGUNDA CLASE ********************///
    static class LaminaPelota extends JPanel{
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
