package com.github.TannerLow.JavaRayTracer;

public class Sphere implements Hittable {
    private Vector3 center;
    private float radius;

    public Sphere(Vector3 center, float radius) {
        this.center = center;
        this.radius = Math.max(0, radius);
    }

    @Override
    public HitRecord hit(Ray ray, Interval t) {
        HitRecord record = new HitRecord();
        Vector3 originToCenter = center.subtract(ray.origin);
        float a = ray.direction.lengthSquared();
        float h = ray.direction.dot(originToCenter);
        float c = originToCenter.lengthSquared() - radius*radius;
        float discriminant = h*h - a*c;

        if(discriminant < 0) {
            record.isHit = false;
            return record;
        }

        float sqrtd = (float)Math.sqrt(discriminant);

        float root = (h - sqrtd) / a;
        if(!t.surrounds(root)) {
            root = (h + sqrtd) / a;
            if(!t.surrounds(root)) {
                record.isHit = false;
                return record;
            }
        }

        record.t = root;
        record.point = ray.at(record.t);
        Vector3 outwardNormal = record.point.subtract(center).divideInplace(radius);
        record.setFaceNormal(ray, outwardNormal);
        record.isHit = true;

        return record;
    }
}
