package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking> {

    public BookingDAO(Connection conn) {
        super(conn);
    }

    public Integer addBooking(Booking booking) throws SQLException {
        return save("insert into " + Booking.NAME + " (" +
                        Booking.ACTIVE + ", " +
                        Booking.CONFIRMATION_CODE + ") " +
                        " values (?, ?)",
                new Object[]{
                        booking.getActive(),
                        booking.getConfirmationCode()
                });
    }

    public boolean updateBooking(Booking booking) throws SQLException {
        return save("update " + Booking.NAME + " set " +
                        Booking.ACTIVE + " = ?, " +
                        Booking.CONFIRMATION_CODE + " = ? " +
                        "where " + Booking.ID + " = ?",
                new Object[]{booking.getActive(), booking.getConfirmationCode(), booking.getId()}) > 0;
    }

    public boolean deleteBookingByConfirmationCode(Booking booking) throws SQLException {
        return delete("delete from " + Booking.NAME + " where " + Booking.CONFIRMATION_CODE + " = ?", new Object[]{booking.getConfirmationCode()});
    }

    public List<Booking> readAllBooking() throws ClassNotFoundException, SQLException {
        return read("select * from " + Booking.NAME, new Object[]{});
    }

    public List<Booking> readBookingsByCode(Booking booking) throws ClassNotFoundException, SQLException {
        return read("select * from " + Booking.NAME + " where " + Booking.CONFIRMATION_CODE + " = ?", new Object[]{booking.getConfirmationCode()});
    }

    public List<Booking> findBookingByPassengerFlight(Flight flight, Passenger passenger) throws SQLException, ClassNotFoundException {
        return read ("select booking.id, is_active, confirmation_code from booking\n" +
                "join passenger on passenger.booking_id = booking.id\n" +
                "join flight_bookings on flight_bookings.booking_id = booking.id\n" +
                "where passenger.id = ? and flight_bookings.flight_id = ? ",new Object[]{passenger.getId(), flight.getId()});

    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Booking> bookings = new ArrayList<>();
        while (rs.next()) {
            Booking b = new Booking();
            b.setId(rs.getInt(Booking.ID));
            b.setActive(rs.getBoolean(Booking.ACTIVE));
            b.setConfirmationCode(rs.getString(Booking.CONFIRMATION_CODE));

            bookings.add(b);
        }
        return bookings;
    }
}
