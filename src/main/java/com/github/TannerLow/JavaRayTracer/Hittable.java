package com.github.TannerLow.JavaRayTracer;

public interface Hittable {
    HitRecord hit(Ray ray, Interval t);
}
