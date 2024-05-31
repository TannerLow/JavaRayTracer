package com.github.TannerLow.JavaRayTracer;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class ImagePanel extends JPanel {
    private BufferedImage image;

    // Camera
    float focalLength = 1.0f;
    float viewportHeight;
    float viewportWidth;
    Vector3 cameraCenter = new Vector3(0,0,0);

    Vector3 viewportU;
    Vector3 viewportV;
    Vector3 pixelDeltaU;
    Vector3 pixelDeltaV;

    Vector3 viewportUpperLeft;
    Vector3 pixel00Location;

    public ImagePanel(BufferedImage image) {
        this.image = image;

        viewportHeight = 2.0f;
        viewportWidth = viewportHeight * ((float) image.getWidth() / image.getHeight());

        viewportU = new Vector3(viewportWidth, 0, 0);
        viewportV = new Vector3(0, -viewportHeight, 0);
        pixelDeltaU = viewportU.divide(image.getWidth());
        pixelDeltaV = viewportV.divide(image.getHeight());

        viewportUpperLeft = cameraCenter
                .subtract(new Vector3(0, 0, focalLength))
                .subtract(viewportU.divide(2))
                .subtract(viewportV.divide(2));
        pixel00Location = viewportUpperLeft.plus(pixelDeltaU.plus(pixelDeltaV).scale(0.5f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw the image, fitting it to the panel size
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        }
        return super.getPreferredSize();
    }

    public void drawPixel(int x, int y, Vector3 color) {
        image.setRGB(x, y, rgb((int)(color.x * 255) , (int)(color.y * 255), (int)(color.z * 255)));
    }

    public void drawPixel(int x, int y, int rgb) {
        image.setRGB(x, y, rgb);
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

    public void render() {
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Vector3 pixelCenter = pixel00Location.plus(pixelDeltaU.scale(x)).plus(pixelDeltaV.scale(y));
                Vector3 rayDirection = pixelCenter.subtract(cameraCenter);
                Ray ray = new Ray(cameraCenter, rayDirection);
                Vector3 color = getRayColor(ray);
                image.setRGB(x, y, rgbInt(color));
            }
        }
    }

    private Vector3 color1 = new Vector3(1, 1, 1);
    private Vector3 color2 = new Vector3(0.5f, 0.7f, 1.0f);
    public Vector3 getRayColor(Ray ray) {
        Vector3 unitDirection = ray.direction.unitVector();
        float a = 0.5f * (unitDirection.y + 1.0f);
        return color1.scale(1.0f - a).plus(color2.scale(a));
    }
}
