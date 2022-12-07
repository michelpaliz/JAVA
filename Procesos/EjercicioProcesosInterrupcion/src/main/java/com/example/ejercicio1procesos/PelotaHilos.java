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

        while (!Thread.currentThread().isInterrupted()){
            pelota.moverPelota(componente.getBounds());
            componente.paint(componente.getGraphics());
        }

        try{
           Thread.sleep(4);
        }catch (InterruptedException e){

            e.printStackTrace();
        }

        System.out.println("Estado del hilo al final " + Thread.currentThread().isInterrupted());

    }



}
