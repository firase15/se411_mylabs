
package edu.psu.se411.lab07_polymorphism;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger =
            LoggerFactory.getLogger(App.class);

    // Polymorphism: accepts any type of Booking
    public static double computeTotalPrice(Booking booking) {
        if (booking == null) {
            throw new InvalidArgumentException(
                    "Booking cannot be null");
        }

        return booking.calculateTotalPrice();
    }

    public static void main(String[] args) {

        logger.info("Application is starting...");

        try {

            System.out.println("=== TRAVEL AGENCY BOOKING SYSTEM ===");

            // 1. Create a flight booking
            FlightBooking flight = new FlightBooking(
                    101,
                    "Ahmed Ali",
                    "2026-11-10",
                    "Dubai",
                    800.0
            );

            flight.setLuggageWeight(20.0);

            // 2. Create a standard train booking
            TrainBooking trainStandard = new TrainBooking(
                    102,
                    "Sara Mohammed",
                    "2026-11-12",
                    "Jeddah",
                    SeatClass.STANDARD
            );

            trainStandard.setDistanceKm(500.0);

            // 3. Create a first-class train booking
            TrainBooking trainFirstClass = new TrainBooking(
                    103,
                    "Khalid Omar",
                    "2026-11-15",
                    "Dammam",
                    SeatClass.FIRST_CLASS
            );

            trainFirstClass.setDistanceKm(300.0);

            // 4. Create a car rental booking
            CarRentalBooking car = new CarRentalBooking(
                    104,
                    "Noura Hassan",
                    "2026-11-18",
                    "Riyadh",
                    150.0
            );

            car.setRentalDays(5);

            // 5. Demonstrate polymorphism
            List<Booking> bookings = new ArrayList<>();

            bookings.add(flight);
            bookings.add(trainStandard);
            bookings.add(trainFirstClass);
            bookings.add(car);

            System.out.println("\n=== VALID BOOKINGS ===");

            for (Booking booking : bookings) {

                System.out.println("\n" + booking);

                double totalPrice = computeTotalPrice(booking);

                System.out.printf(
                        "Total Price: %.2f SAR%n",
                        totalPrice
                );
            }

            // 6. Test missing information exceptions
            System.out.println("\n=== MISSING INFORMATION TESTS ===");

            testMissingFlightInformation();
            testMissingTrainInformation();
            testMissingCarInformation();

            // 7. Test invalid argument exceptions
            System.out.println("\n=== INVALID ARGUMENT TESTS ===");

            testInvalidFlightWeight();
            testInvalidTrainDistance();
            testInvalidRentalDays();

            // 8. Test valid boundary values
            System.out.println("\n=== BOUNDARY VALUE TESTS ===");

            testBoundaryValues();

        } catch (Exception e) {

            System.out.println(
                    "Unexpected error: " + e.getMessage()
            );

            logger.error(
                    "Unexpected exception in main",
                    e
            );

        } finally {

            logger.info("Application is stopping...");

            System.out.println(
                    "\n=== APPLICATION FINISHED ==="
            );
        }
    }

    private static void testMissingFlightInformation() {

        try {

            FlightBooking flight = new FlightBooking(
                    201, "Test Flight", "2026-12-01",
                    "Dubai", 500.0
            );

            computeTotalPrice(flight);

        } catch (MissingInformationException e) {

            System.out.println(
                    "Flight missing information: " + e.getMessage()
            );

            logger.error("Flight missing information", e);
        }
    }

    private static void testMissingTrainInformation() {

        try {

            TrainBooking train = new TrainBooking(
                    202, "Test Train", "2026-12-02",
                    "Jeddah", SeatClass.STANDARD
            );

            computeTotalPrice(train);

        } catch (MissingInformationException e) {

            System.out.println(
                    "Train missing information: " + e.getMessage()
            );

            logger.error("Train missing information", e);
        }
    }

    private static void testMissingCarInformation() {

        try {

            CarRentalBooking car = new CarRentalBooking(
                    203, "Test Car", "2026-12-03",
                    "Riyadh", 100.0
            );

            computeTotalPrice(car);

        } catch (MissingInformationException e) {

            System.out.println(
                    "Car missing information: " + e.getMessage()
            );

            logger.error("Car missing information", e);
        }
    }

    private static void testInvalidFlightWeight() {

        try {

            FlightBooking flight = new FlightBooking(
                    301, "Invalid Flight", "2026-12-04",
                    "Dubai", 500.0
            );

            flight.setLuggageWeight(45.0);

        } catch (InvalidArgumentException e) {

            System.out.println(
                    "Invalid flight weight: " + e.getMessage()
            );

            logger.error("Invalid flight weight", e);
        }
    }

    private static void testInvalidTrainDistance() {

        try {

            TrainBooking train = new TrainBooking(
                    302, "Invalid Train", "2026-12-05",
                    "Jeddah", SeatClass.FIRST_CLASS
            );

            train.setDistanceKm(2500.0);

        } catch (InvalidArgumentException e) {

            System.out.println(
                    "Invalid train distance: " + e.getMessage()
            );

            logger.error("Invalid train distance", e);
        }
    }

    private static void testInvalidRentalDays() {

        try {

            CarRentalBooking car = new CarRentalBooking(
                    303, "Invalid Car", "2026-12-06",
                    "Riyadh", 120.0
            );

            car.setRentalDays(35);

        } catch (InvalidArgumentException e) {

            System.out.println(
                    "Invalid rental days: " + e.getMessage()
            );

            logger.error("Invalid rental days", e);
        }
    }

    private static void testBoundaryValues() {

        FlightBooking flight = new FlightBooking(
                401, "Boundary Flight", "2026-12-07",
                "Dubai", 400.0
        );

        flight.setLuggageWeight(0.0);

        System.out.println(
                "Flight with 0 kg luggage: "
                        + computeTotalPrice(flight)
        );

        flight.setLuggageWeight(40.0);

        System.out.println(
                "Flight with 40 kg luggage: "
                        + computeTotalPrice(flight)
        );

        TrainBooking train = new TrainBooking(
                402, "Boundary Train", "2026-12-08",
                "Jeddah", SeatClass.STANDARD
        );

        train.setDistanceKm(1.0);

        System.out.println(
                "Train with 1 km distance: "
                        + computeTotalPrice(train)
        );

        train.setDistanceKm(2000.0);

        System.out.println(
                "Train with 2000 km distance: "
                        + computeTotalPrice(train)
        );

        CarRentalBooking car = new CarRentalBooking(
                403, "Boundary Car", "2026-12-09",
                "Riyadh", 100.0
        );

        car.setRentalDays(1);

        System.out.println(
                "Car rental for 1 day: "
                        + computeTotalPrice(car)
        );

        car.setRentalDays(30);

        System.out.println(
                "Car rental for 30 days: "
                        + computeTotalPrice(car)
        );
    }
}
