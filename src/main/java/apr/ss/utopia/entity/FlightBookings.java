package apr.ss.utopia.entity;

import java.util.Objects;

public class FlightBookings {

    public static final String NAME = "flight_bookings";
    public static final String FLIGHT_ID = "flight_id";
    public static final String BOOKING_ID = "booking_id";

    private Flight flight;
    private Booking booking;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightBookings that = (FlightBookings) o;
        return flight.equals(that.flight) && booking.equals(that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight, booking);
    }
}
