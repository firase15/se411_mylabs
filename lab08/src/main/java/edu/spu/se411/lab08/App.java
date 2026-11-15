package edu.spu.se411.lab08;

import java.util.Random;

public class App {

    public static void main(String[] args) {

        Sensor temp = new Sensor("Temperature");
        Sensor humidity = new Sensor("Humidity");

        Dashboard dashboard = new Dashboard();
        Logger logger = new Logger();

        temp.register(dashboard);
        temp.register(logger);

        humidity.register(dashboard);
        humidity.register(logger);

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Sensor clonedSensor = temp.clone();
        clonedSensor.register(new Dashboard());
        clonedSensor.setReading(30);
    }
}