package edu.spu.se411.lab08;

import java.util.ArrayList;
import java.util.List;

public class Sensor implements Subject, Cloneable {

    private String name;
    private double reading;
    private List<Observer> observers = new ArrayList<>();

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
        notifyObservers();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    @Override
    public Sensor clone() {
        try {
            Sensor cloned = (Sensor) super.clone();
            cloned.observers = new ArrayList<>();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}