package com.github.TannerLow.JavaRayTracer;

public class HitRecord {
    public Vector3 point;
    public Vector3 normal;
    public float t;
    public boolean isHit;
    public boolean frontFace;

    public void setFaceNormal(Ray ray, Vector3 outwardNormal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outwardNormal` is assumed to have unit length.

        frontFace = ray.direction.dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.negate();
    }
}
