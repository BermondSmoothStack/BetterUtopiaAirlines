package apr.ss.utopia.service;

import apr.ss.utopia.dao.*;
import apr.ss.utopia.entity.*;

import java.sql.Connection;
import java.sql.SQLException;

public class FlightBookingService {

    Util util = new Util();

    public Boolean createTicket(Flight flight, Booking booking, Passenger passenger) throws SQLException{
        Connection conn;

        try {
            conn = util.getConnection();
            FlightBookingsDAO flightBookingsDAO = new FlightBookingsDAO(conn);
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            BookingDAO bookingDAO = new BookingDAO(conn);

            if (null == booking.getId())
                booking.setId(bookingDAO.addBooking(booking));
            if (null != passenger) {
                passenger.setBooking(booking);
                passenger.setId(passengerDAO.addPassenger(passenger));
            }

            FlightBookings fb = new FlightBookings();
            fb.setBooking(booking);
            fb.setFlight(flight);
            flightBookingsDAO.addFlightBookings(fb);
            conn.commit();
            return true;

        } catch ( ClassNotFoundException e) {
            System.out.println("Ticket Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }



}
