package edu.spu.se411.lab08;

public class Dashboard implements Observer {

    @Override
    public void update(Subject subject) {
        Sensor sensor = (Sensor) subject;
        System.out.printf("Dashboard: %s = %.2f%n",
                sensor.getName(), sensor.getReading());
    }
}