package apr.ss.utopia.entity;

import java.util.Objects;

public class BookingUser {

    public static final String NAME = "booking_agent";
    public static final String USER_ID = "agent_id";
    public static final String BOOKING_ID = "booking_id";

    private Booking booking;
    private User user;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingUser that = (BookingUser) o;
        return booking.equals(that.booking) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking, user);
    }
}
