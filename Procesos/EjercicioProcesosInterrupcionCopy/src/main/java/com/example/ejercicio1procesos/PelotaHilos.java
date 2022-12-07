package com.example.ejercicio1procesos;

import java.awt.*;
import java.util.TreeMap;

public class PelotaHilos implements Runnable {

    private Pelota pelota;
    private Component componente;

    /**
     *
     * @param pelota
     * @param componente donde va a rebotar (la lamina)
     */
    public PelotaHilos(Pelota pelota, Component componente) {

        this.pelota = pelota;
        this.componente = componente;

    }

    public void run(){


        System.out.println("Estado del hilo al comienzo " + Thread.currentThread().isInterrupted());
//        while (!Thread.interrupted()){
//            pelota.moverPelota(componente.getBounds());
//            componente.paint(componente.getGraphics());
//        }
//
        while (!Thread.currentThread().isInterrupted()){
            pelota.moverPelota(componente.getBounds());
            componente.paint(componente.getGraphics());
        }

//        for (int i=1; i<=3000; i++){
//            pelota.moverPelota(componente.getBounds());
//            //pinta la lamina
//            componente.paint(componente.getGraphics());
//            try {
//                Thread.sleep(4);
//            }catch (InterruptedException io){
//                io.printStackTrace();
//
//            }
//
//        }
        System.out.println("Estado del hilo al final " + Thread.currentThread().isInterrupted());

    }



}
