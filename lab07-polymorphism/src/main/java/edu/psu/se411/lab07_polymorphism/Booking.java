
package edu.psu.se411.lab07_polymorphism;

public abstract class Booking {

    private int bookingId;
    private String customerFullName;
    private String travelDate;
    private String destinationCity;

    public Booking(int bookingId, String customerFullName,
                   String travelDate, String destinationCity) {

        if (bookingId <= 0) {
            throw new InvalidArgumentException("Booking ID must be positive");
        }

        if (customerFullName == null || customerFullName.trim().isEmpty()) {
            throw new MissingInformationException("Customer full name is required");
        }

        if (travelDate == null || travelDate.trim().isEmpty()) {
            throw new MissingInformationException("Travel date is required");
        }

        if (destinationCity == null || destinationCity.trim().isEmpty()) {
            throw new MissingInformationException("Destination city is required");
        }

        this.bookingId = bookingId;
        this.customerFullName = customerFullName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public abstract double calculateTotalPrice();

    @Override
    public String toString() {
        return "Booking ID: " + bookingId
                + ", Customer: " + customerFullName
                + ", Travel Date: " + travelDate
                + ", Destination: " + destinationCity;
    }
}
