package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.BookingAgent;
import apr.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingAgentDAO extends BaseDAO<BookingAgent> {

    public BookingAgentDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBookings(BookingAgent bookingAgent) throws SQLException {
        save("insert into (" +
                        BookingAgent.AGENT_ID + ", " +
                        BookingAgent.BOOKING_ID + ") " +
                        BookingAgent.NAME + " values (?, ?)",
                new Object[]{
                        bookingAgent.getUser().getId(),
                        bookingAgent.getBooking().getId()
                });
    }

    public void deleteFlightBookings(BookingAgent bookingAgent) throws SQLException {
        save("delete from " + BookingAgent.NAME +
                        " where " + BookingAgent.AGENT_ID + " = ? " +
                        "AND " + BookingAgent.BOOKING_ID + " = ?",
                new Object[]{
                        bookingAgent.getUser().getId(),
                        bookingAgent.getBooking().getId()
                });
    }

    public List<BookingAgent> readAllFlightBookings() throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingAgent.NAME, new Object[]{});
    }

    public List<BookingAgent> readFlightBookingsByCode(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingAgent.NAME +
                        " where " + BookingAgent.AGENT_ID + " = ?" +
                        " and " + BookingAgent.BOOKING_ID + " = ?",
                new Object[]{bookingAgent.getUser().getId(), bookingAgent.getBooking().getId()});
    }

    @Override
    public List<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<BookingAgent> bookingAgents = new ArrayList<>();
        while (rs.next()) {
            BookingAgent ba = new BookingAgent();
            User u = new User();
            Booking b = new Booking();

            u.setId(rs.getInt(BookingAgent.AGENT_ID));
            b.setId(rs.getInt(BookingAgent.BOOKING_ID));

            ba.setUser(u);
            ba.setBooking(b);

            bookingAgents.add(ba);
        }
        return bookingAgents;
    }
}
