package com.example.ejercicio1procesos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DibujarPelota extends JPanel {
    private List<Pelota> pelotas;

    public DibujarPelota(LayoutManager layout, boolean isDoubleBuffered, List<Pelota> pelotas) {
        super(layout, isDoubleBuffered);
        this.pelotas = pelotas;
    }

    public void add(Pelota b) {
        pelotas.add(b);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Pelota b : pelotas) {
            g2.fill(b.getShape());
        }

    }


}