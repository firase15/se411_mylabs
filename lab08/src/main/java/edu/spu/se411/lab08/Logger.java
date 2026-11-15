package edu.spu.se411.lab08;

import org.slf4j.LoggerFactory;

public class Logger implements Observer {

    private static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(Logger.class);

    @Override
    public void update(Subject subject) {
        Sensor sensor = (Sensor) subject;

        System.out.printf("Logger: %s = %.2f%n",
                sensor.getName(), sensor.getReading());

        logger.info("{} = {}", sensor.getName(),
                sensor.getReading());
    }
}