package com.github.TannerLow.JavaRayTracer;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;

import static com.github.TannerLow.JavaRayTracer.ImagePanel.rgb;

public class Window {
    JFrame frame;
    ImagePanel imagePanel;
    int width;
    int height;
    double aspectRatio;

    public Window(String title, double aspectRatio, int imageWidth) {
        this.aspectRatio = aspectRatio;
        width = imageWidth;
        height = (int) (1.0 / aspectRatio * imageWidth);

        frame = new JFrame("GPU Ray Tracer Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                image.setRGB(x, y, rgb((int)(255.999 * (double)x/(width-1)), (int)(255.999 * (double)y/(height-1)), 0));
            }
        }
        imagePanel = new ImagePanel(image);
        imagePanel.render();
        imagePanel.setSize(imagePanel.getPreferredSize());
        frame.add(imagePanel);
        frame.setVisible(true);
    }

    public void drawPixel(int x, int y, Vector3 color) {
        imagePanel.drawPixel(x, y, color);
    }

    public void drawPixel(int x, int y, int rgb) {
        imagePanel.drawPixel(x, y, rgb);
    }

    public void refreshImage() {
        imagePanel.repaint();
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }
}
