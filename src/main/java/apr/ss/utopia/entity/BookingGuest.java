package apr.ss.utopia.entity;

import java.util.Objects;

public class BookingGuest {

    public static final String NAME = "booking_guest";
    public static final String BOOKING_ID = "booking_id";
    public static final String EMAIL = "contact_email";
    public static final String PHONE = "contact_phone";

    private Booking booking;
    private String email;
    private String phone;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingGuest that = (BookingGuest) o;
        return booking.equals(that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking);
    }
}
