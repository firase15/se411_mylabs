package edu.psu.se411.lab07_polymorphism;




public class CarRentalBooking extends Booking {

    private double dailyRentalRate;
    private Integer rentalDays;

    public CarRentalBooking(int bookingId, String customerFullName,
                            String travelDate, String destinationCity,
                            double dailyRentalRate) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        if (!Double.isFinite(dailyRentalRate) || dailyRentalRate < 0) {
            throw new InvalidArgumentException(
                    "Daily rental rate must be a valid non-negative number");
        }

        this.dailyRentalRate = dailyRentalRate;
        this.rentalDays = null;
    }

    public void setRentalDays(int rentalDays) {

        if (rentalDays < GlobalConfig.MIN_RENTAL_DAYS
                || rentalDays > GlobalConfig.MAX_RENTAL_DAYS) {

            throw new InvalidArgumentException(
                    "Rental days must be between 1 and 30");
        }

        this.rentalDays = rentalDays;
    }

    @Override
    public double calculateTotalPrice() {

        if (rentalDays == null) {
            throw new MissingInformationException(
                    "Number of rental days has not been provided");
        }

        return dailyRentalRate * rentalDays;
    }
}
