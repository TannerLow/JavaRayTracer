package com.github.TannerLow.JavaRayTracer;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        int width = 800;
        int height = 600;

        JFrame frame = new JFrame("GPU Ray Tracer Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                image.setRGB(x, y, rgb((int)(255 * (double)x/(width-1)), (int)(255 * (double)y/(height-1)), 0));
            }
        }
        frame.add(new ImagePanel(image));
        frame.setVisible(true);
    }

    public static int rgb(int r, int g, int b) {
        int rgb = r << 16;
        rgb |= g << 8;
        rgb |= b;
        return rgb;
    }
}