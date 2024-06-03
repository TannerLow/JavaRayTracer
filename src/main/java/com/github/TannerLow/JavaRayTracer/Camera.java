package com.github.TannerLow.JavaRayTracer;

import java.awt.image.BufferedImage;

public class Camera {
    public double aspectRatio = 16.0 / 9.0;
    public int imageWidth = 1000;
    private int imageHeight;

    private BufferedImage image;
    private Vector3 cameraCenter;
    private Vector3 pixelDeltaU;
    private Vector3 pixelDeltaV;
    private Vector3 pixel00Location;

    BufferedImage render(Hittable world) {
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Vector3 pixelCenter = pixel00Location.plus(pixelDeltaU.scale(x)).plus(pixelDeltaV.scale(y));
                Vector3 rayDirection = pixelCenter.subtract(cameraCenter);
                Ray ray = new Ray(cameraCenter, rayDirection);
                Vector3 color = getRayColor(ray, world);
                image.setRGB(x, y, rgbInt(color));
            }
        }
        return image;
    }

    public void initialize() {
        imageHeight = (int)(imageWidth / aspectRatio);
        imageHeight = (imageHeight < 1) ? 1 : imageHeight;

        cameraCenter = new Vector3(0,0,0);

        float focalLength = 1.0f;
        float viewportHeight = 2.0f;
        float viewportWidth = viewportHeight * ((float) imageWidth / imageHeight);

        Vector3 viewportU = new Vector3(viewportWidth, 0, 0);
        Vector3 viewportV = new Vector3(0, -viewportHeight, 0);
        pixelDeltaU = viewportU.divide(imageWidth);
        pixelDeltaV = viewportV.divide(imageHeight);

        Vector3 viewportUpperLeft = cameraCenter
                .subtract(new Vector3(0, 0, focalLength))
                .subtract(viewportU.divide(2))
                .subtract(viewportV.divide(2));
        pixel00Location = viewportUpperLeft.plus(pixelDeltaU.plus(pixelDeltaV).scale(0.5f));

        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    private Vector3 color1 = new Vector3(1, 1, 1);
    private Vector3 color2 = new Vector3(0.5f, 0.7f, 1.0f);
    Vector3 getRayColor(Ray ray, Hittable world) {
        HitRecord record = world.hit(ray, new Interval(0, Float.POSITIVE_INFINITY));
        if(record.isHit) {
            return record.normal.addInplace(color1).scaleInplace(0.5f);
        }

        Vector3 unitDirection = ray.direction.unitVector();
        float a = 0.5f * (unitDirection.y + 1.0f);
        return color1.scale(1.0f - a).plus(color2.scale(a));
    }

    public static int rgbInt(Vector3 color) {
        int rgb = (int)(color.x * 255) << 16;
        rgb |= (int)(color.y * 255) << 8;
        rgb |= (int)(color.z * 255);
        return rgb;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}
