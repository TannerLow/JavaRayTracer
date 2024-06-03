package com.github.TannerLow.JavaRayTracer;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;

import static com.github.TannerLow.JavaRayTracer.ImagePanel.rgb;

public class Window {
    JFrame frame;
    ImagePanel imagePanel;

    public Window(String title, double aspectRatio, int imageWidth) {

        HittableList world = new HittableList();
        world.add(new Sphere(new Vector3(0, 0, -1), 0.5f));
        world.add(new Sphere(new Vector3(0, -100.5f, -1), 100));

        Camera camera = new Camera();
        camera.aspectRatio = aspectRatio;
        camera.imageWidth = imageWidth;
        camera.initialize();
        imagePanel = new ImagePanel(camera, world);
        imagePanel.update();

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(camera.getImageWidth(), camera.getImageHeight());
        frame.setLocationRelativeTo(null);

        imagePanel.setSize(imagePanel.getPreferredSize());
        frame.add(imagePanel);
        frame.setVisible(true);
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
