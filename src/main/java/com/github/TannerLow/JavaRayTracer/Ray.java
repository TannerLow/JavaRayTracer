package com.github.TannerLow.JavaRayTracer;

public class Ray {
    Vector3 origin;
    Vector3 direction;

    public Ray() {

    }

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    Vector3 at(float t) {
        return origin.plus(direction.scale(t));
    }
}
