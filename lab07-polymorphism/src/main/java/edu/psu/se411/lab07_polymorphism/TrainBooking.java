
package edu.psu.se411.lab07_polymorphism;

public class TrainBooking extends Booking {

    private SeatClass seatClass;
    private Double distanceKm;

    public TrainBooking(int bookingId, String customerFullName,
                        String travelDate, String destinationCity,
                        SeatClass seatClass) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        if (seatClass == null) {
            throw new MissingInformationException(
                    "Train seat class is required");
        }

        this.seatClass = seatClass;
        this.distanceKm = null;
    }

    public void setDistanceKm(double distanceKm) {

        if (!Double.isFinite(distanceKm)
                || distanceKm < GlobalConfig.MIN_TRAIN_DISTANCE
                || distanceKm > GlobalConfig.MAX_TRAIN_DISTANCE) {

            throw new InvalidArgumentException(
                    "Train distance must be between 1 and 2000 km");
        }

        this.distanceKm = distanceKm;
    }

    @Override
    public double calculateTotalPrice() {

        if (distanceKm == null) {
            throw new MissingInformationException(
                    "Train distance has not been provided");
        }

        if (seatClass == SeatClass.STANDARD) {
            return distanceKm * GlobalConfig.TRAIN_STANDARD_RATE;
        } else {
            return distanceKm * GlobalConfig.TRAIN_FIRST_CLASS_RATE;
        }
    }
}
