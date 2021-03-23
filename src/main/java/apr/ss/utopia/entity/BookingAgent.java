package apr.ss.utopia.entity;

import java.util.Objects;

public class BookingAgent {

    public static final String NAME = "booking_agent";
    public static final String AGENT_ID = "agent_id";
    public static final String BOOKING_ID = "booking_id";

    private Booking booking;
    private User agent;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return agent;
    }

    public void setUser(User agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingAgent that = (BookingAgent) o;
        return booking.equals(that.booking) && agent.equals(that.agent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking, agent);
    }
}
