package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.BookingGuest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingGuestDAO extends BaseDAO<BookingGuest> {

    public BookingGuestDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBookings(BookingGuest bookingGuest) throws SQLException {
        save("insert into (" +
                        BookingGuest.BOOKING_ID + ", " +
                        BookingGuest.EMAIL + ", " +
                        BookingGuest.PHONE + ") " +
                        BookingGuest.NAME + " values (?, ?, ?)",
                new Object[]{bookingGuest.getBooking().getId(), bookingGuest.getEmail(), bookingGuest.getPhone()});
    }

    public void deleteFlightBookings(BookingGuest bookingGuest) throws SQLException {
        save("delete from " + BookingGuest.NAME +
                        " where " + BookingGuest.BOOKING_ID + " = ?",
                new Object[]{bookingGuest.getBooking().getId()});
    }

    public List<BookingGuest> readAllFlightBookings() throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingGuest.NAME, new Object[]{});
    }

    public List<BookingGuest> readFlightBookingsByCode(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingGuest.NAME +
                        " where " + BookingGuest.BOOKING_ID + " = ?",
                new Object[]{bookingGuest.getBooking().getId()});
    }

    @Override
    public List<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<BookingGuest> bookingGuests = new ArrayList<>();
        while (rs.next()) {
            BookingGuest bg = new BookingGuest();
            Booking b = new Booking();

            b.setId(rs.getInt(BookingGuest.BOOKING_ID));

            bg.setBooking(b);
            bg.setEmail(rs.getString(BookingGuest.EMAIL));
            bg.setPhone(rs.getString(BookingGuest.PHONE));

            bookingGuests.add(bg);
        }
        return bookingGuests;
    }
}
