package apr.ss.utopia.service;


import apr.ss.utopia.dao.BookingDAO;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookingService {

    Util util = new Util();

    public Booking createBooking(Booking b) {
        Connection conn;
        try {
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            b.setId(bookingDAO.addBooking(b));
            conn.commit();
            return b;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Booking Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return b;
    }

    public Booking getBookingById(String code) {
        Connection conn;
        try {
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking b = new Booking();
            b.setConfirmationCode(code);
            List<Booking> bl = bookingDAO.readBookingsByCode(b);
            b = bl.isEmpty() ? null : bl.get(0);
            conn.commit();
            return b;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Booking Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteBookingByConfirmationCode(Booking booking) {
        Connection conn;
        try {
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            boolean isSuccess = bookingDAO.deleteBookingByConfirmationCode(booking);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public Boolean updateBookingStatus(Booking b) {
        Connection conn;
        try {
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            Boolean isSuccess = bookingDAO.updateBooking(b);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Booking Update Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return false;
    }

    public Boolean cancelBooking(Flight flight, Passenger passenger) {
        Connection conn;
        try {
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            List<Booking> bl = bookingDAO.findBookingByPassengerFlight(flight,passenger);
            if (null != bl && !bl.isEmpty()){
                Booking b = bl.get(0);
                b.setActive(false);
                Boolean isSuccess = bookingDAO.updateBooking(b);
                conn.commit();
                return isSuccess;
            } else {
                System.out.println("No booking found from this flight from you.");
            }
            return false;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Booking Update Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return false;
    }
}
