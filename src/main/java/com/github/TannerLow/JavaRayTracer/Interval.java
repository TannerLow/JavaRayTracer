package com.github.TannerLow.JavaRayTracer;

public class Interval {
    public static Interval empty = new Interval();
    public static Interval universe = new Interval(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);

    public float min;
    public float max;

    public Interval() {
        min = Float.POSITIVE_INFINITY;
        max = Float.NEGATIVE_INFINITY;
    }

    public Interval(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float size() {
        return max - min;
    }

    public boolean contains(float x) {
        return min <= x && x <= max;
    }

    public boolean surrounds(float x) {
        return min < x && x < max;
    }
}
