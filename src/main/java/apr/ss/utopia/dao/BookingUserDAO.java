package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.BookingUser;
import apr.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingUserDAO extends BaseDAO<BookingUser>{

    public BookingUserDAO(Connection conn) {
        super(conn);
    }

    public void addBookingUser(BookingUser bookingUser) throws SQLException {
        save("insert into (" + BookingUser.USER_ID + ", " + BookingUser.BOOKING_ID + ") " + BookingUser.NAME + " values (?, ?)", new Object[]{bookingUser.getUser().getId(), bookingUser.getBooking().getId()});
    }

    public void deleteBookingUser(BookingUser bookingUser) throws SQLException {
        save("delete from " + BookingUser.NAME +
                        " where " + BookingUser.USER_ID + " = ? " +
                        "AND " + BookingUser.BOOKING_ID + " = ?",
                new Object[]{bookingUser.getUser().getId(), bookingUser.getBooking().getId()});
    }

    public List<BookingUser> readAllBookingUser() throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingUser.NAME, new Object[]{});
    }

    public List<BookingUser> readBookingUserByCode(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
        return read("select * from " + BookingUser.NAME +
                        " where " + BookingUser.USER_ID + " = ?" +
                        " and " + BookingUser.BOOKING_ID + " = ?",
                new Object[]{bookingUser.getUser().getId(), bookingUser.getBooking().getId()});
    }

    @Override
    public List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<BookingUser> bookingUsers = new ArrayList<>();
        while (rs.next()) {
            BookingUser ba = new BookingUser();
            User u = new User();
            Booking b = new Booking();

            u.setId(rs.getInt(BookingUser.USER_ID));
            b.setId(rs.getInt(BookingUser.BOOKING_ID));

            ba.setUser(u);
            ba.setBooking(b);

            bookingUsers.add(ba);
        }
        return bookingUsers;
    }
}
