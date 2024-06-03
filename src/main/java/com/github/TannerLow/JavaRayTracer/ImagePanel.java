package com.github.TannerLow.JavaRayTracer;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class ImagePanel extends JPanel {
    private Camera camera;
    private Hittable world;

    public ImagePanel(Camera camera, Hittable world) {
        this.camera = camera;
        this.world = world;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image, fitting it to the panel size
        g.drawImage(camera.render(world), 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(camera.getImageWidth(), camera.getImageHeight());
    }

    public static int rgb(int r, int g, int b) {
        int rgb = r << 16;
        rgb |= g << 8;
        rgb |= b;
        return rgb;
    }

    public static int rgbInt(Vector3 color) {
        int rgb = (int)(color.x * 255) << 16;
        rgb |= (int)(color.y * 255) << 8;
        rgb |= (int)(color.z * 255);
        return rgb;
    }

    public void update() {
        camera.render(world);
    }
}
