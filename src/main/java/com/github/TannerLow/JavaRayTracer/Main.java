package com.github.TannerLow.JavaRayTracer;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        Window window = new Window("GPU Ray Tracer Demo", 16.0 / 9.0, 1000);
        System.out.println(window.getWidth());
        System.out.println(window.getHeight());
    }
}