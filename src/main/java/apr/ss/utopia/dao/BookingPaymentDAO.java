package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.BookingPayment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingPaymentDAO extends BaseDAO<BookingPayment> {

    public BookingPaymentDAO(Connection conn) {
        super(conn);
    }

    public void addBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("insert into (" +
                        BookingPayment.STRIPE + ", " +
                        BookingPayment.REFUNDED + ", " +
                        BookingPayment.BOOKING_ID +
                        ") " + BookingPayment.NAME + " values (?, ?, ?)",
                new Object[]{
                        bookingPayment.getStripe(),
                        bookingPayment.getRefunded(),
                        bookingPayment.getBooking().getId()
                });
    }

    public void updateBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("update " + BookingPayment.NAME + " set " +
                        BookingPayment.STRIPE + " = ?, " +
                        BookingPayment.REFUNDED + " = ?, " +
                        "where " + BookingPayment.BOOKING_ID + " = ?",
                new Object[]{
                        bookingPayment.getStripe(),
                        bookingPayment.getRefunded(),
                        bookingPayment.getBooking().getId(),
                });
    }

    public void deleteBookingPayment(BookingPayment bookingPayment) throws SQLException {
        save("delete from " + BookingPayment.NAME +
                        " where " + BookingPayment.BOOKING_ID + " = ?",
                new Object[]{bookingPayment.getBooking().getId()});
    }

    public List<BookingPayment> readAllBookingPayment() throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingPayment.NAME, new Object[]{});
    }

    public List<BookingPayment> readBookingPaymentByCode(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingPayment.NAME +
                        " where " + BookingPayment.BOOKING_ID + " = ?",
                new Object[]{bookingPayment.getBooking().getId()});
    }

    @Override
    public List<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<BookingPayment> bookingUsers = new ArrayList<>();
        while (rs.next()) {
            BookingPayment bp = new BookingPayment();
            Booking b = new Booking();

            b.setId(rs.getInt(BookingPayment.BOOKING_ID));

            bp.setBooking(b);
            bp.setRefunded(rs.getBoolean(BookingPayment.REFUNDED));
            bp.setStripe(rs.getString(BookingPayment.STRIPE));

            bookingUsers.add(bp);
        }
        return bookingUsers;
    }
}
