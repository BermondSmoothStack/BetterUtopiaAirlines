package apr.ss.utopia.service;


import apr.ss.utopia.dao.BookingDAO;
import apr.ss.utopia.dao.PassengerDAO;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PassengerService {

    Util util = new Util();

    public Passenger createPassenger(Passenger p) {
        Connection conn;

        try {
            conn = util.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            p.setId(passengerDAO.addPassenger(p));
            conn.commit();
            return p;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Passenger Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return p;
    }

    public Passenger getPassengerById(Integer id) {
        Connection conn;
        try {
            conn = util.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            Passenger p = new Passenger();
            p.setId(id);
            List<Passenger> pl = passengerDAO.readPassengersById(p);
            p = pl.isEmpty() ? null : pl.get(0);
            conn.commit();
            return p;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Passenger Lookup Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }

    public boolean deletePassengerById(Passenger passenger) {
        Connection conn;
        try {
            conn = util.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            boolean isSuccess = passengerDAO.deletePassenger(passenger);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public void updateTicket(Booking booking, Passenger passenger) throws SQLException {
        Connection conn;
        try {
            conn = util.getConnection();
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);

            if (null == booking.getId())
                booking.setId(bookingDAO.addBooking(booking));
            passenger.setBooking(booking);
            passengerDAO.updatePassenger(passenger);

            conn.commit();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Passenger Lookup Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }

    }
}
