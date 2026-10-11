
package edu.psu.se411.lab07_polymorphism;

public class FlightBooking extends Booking {

    private double baseTicketPrice;
    private Double luggageWeight;

    public FlightBooking(int bookingId, String customerFullName,
                         String travelDate, String destinationCity,
                         double baseTicketPrice) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        if (baseTicketPrice < 0 || !Double.isFinite(baseTicketPrice)) {
            throw new InvalidArgumentException("Invalid base ticket price");
        }

        this.baseTicketPrice = baseTicketPrice;
        this.luggageWeight = null;
    }

    public void setLuggageWeight(double luggageWeight) {

        if (!Double.isFinite(luggageWeight)
                || luggageWeight < GlobalConfig.MIN_LUGGAGE_WEIGHT
                || luggageWeight > GlobalConfig.MAX_LUGGAGE_WEIGHT) {

            throw new InvalidArgumentException(
                    "Luggage weight must be between 0 and 40 kg");
        }

        this.luggageWeight = luggageWeight;
    }

    @Override
    public double calculateTotalPrice() {

        if (luggageWeight == null) {
            throw new MissingInformationException(
                    "Luggage weight has not been provided");
        }

        return baseTicketPrice
                + (luggageWeight * GlobalConfig.EXTRA_LUGGAGE_RATE);
    }
}
