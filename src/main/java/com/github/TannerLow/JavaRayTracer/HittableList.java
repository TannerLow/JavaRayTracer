package com.github.TannerLow.JavaRayTracer;

import java.util.ArrayList;
import java.util.List;

public class HittableList implements Hittable {
    public List<Hittable> objects;

    public HittableList() {
        objects = new ArrayList<>();
    }

    public HittableList(Hittable object) {
        objects = new ArrayList<>();
        add(object);
    }

    public void clear() {
        objects.clear();
    }

    public void add(Hittable object) {
        objects.add(object);
    }

    @Override
    public HitRecord hit(Ray ray, Interval t) {
        HitRecord record = new HitRecord();
        boolean hitAnything = false;
        float closestSoFar = t.max;

        for(Hittable object : objects) {
            HitRecord tempRecord = object.hit(ray, new Interval(t.min, closestSoFar));
            if(tempRecord.isHit) {
                hitAnything = true;
                closestSoFar = tempRecord.t;
                record = tempRecord;
            }
        }

        record.isHit = hitAnything;

        return record;
    }
}
