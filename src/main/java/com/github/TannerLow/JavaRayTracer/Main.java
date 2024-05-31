package com.github.TannerLow.JavaRayTracer;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        Window window = new Window("GPU Ray Tracer Demo", 16.0 / 9.0, 1000);
        System.out.println(window.getWidth());
        System.out.println(window.getHeight());

//        int fps = 2;
//        long frameTime = 1000/fps;
//        int rgb = 0;
//        long startTime = System.currentTimeMillis();
//        while(true) {
//            if(System.currentTimeMillis() - startTime >= frameTime) {
//                for (int y = 0; y < window.getHeight(); y++) {
//                    for (int x = 0; x < window.getWidth(); x++) {
//                        window.drawPixel(x, y, rgb);
//                        rgb++;
//                        rgb %= 1 << 24;
//                    }
//                }
//                window.refreshImage();
//                startTime = System.currentTimeMillis();
//            }
//        }

//        JFrame frame = new JFrame("GPU Ray Tracer Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(width, height);
//        frame.setLocationRelativeTo(null);
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for(int y = 0; y < image.getHeight(); y++) {
//            for(int x = 0; x < image.getWidth(); x++) {
//                image.setRGB(x, y, rgb((int)(255 * (double)x/(width-1)), (int)(255 * (double)y/(height-1)), 0));
//            }
//        }
//        frame.add(new ImagePanel(image));
//        frame.setVisible(true);
    }
}