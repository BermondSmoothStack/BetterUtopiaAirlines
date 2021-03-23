package apr.ss.utopia.entity;

import java.util.Objects;

public class BookingPayment {

    public static final String NAME = "booking_payment";
    public static final String BOOKING_ID = "booking_id";
    public static final String STRIPE = "stripe_id";
    public static final String REFUNDED = "refunded";

    private Booking booking;
    private String stripe;
    private Boolean refunded;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStripe() {
        return stripe;
    }

    public void setStripe(String stripe) {
        this.stripe = stripe;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingPayment that = (BookingPayment) o;
        return booking.equals(that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking);
    }
}
