package apr.ss.utopia.service;


import apr.ss.utopia.dao.BookingDAO;
import apr.ss.utopia.dao.FlightDAO;
import apr.ss.utopia.dao.PassengerDAO;
import apr.ss.utopia.dao.SeatDAO;
import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Passenger;
import apr.ss.utopia.entity.Seat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class TicketService {

    Util util = new Util();

    public Booking createTicket(Passenger passenger, Seat seat){
        Connection conn;

        try{
            conn = util.getConnection();
            BookingDAO bookingDAO = new BookingDAO(conn);
            PassengerDAO passengerDAO = new PassengerDAO(conn);
            SeatDAO seatDAO = new SeatDAO(conn);

            Booking booking = newBooking();
            Integer bookingId = bookingDAO.addBooking(booking);
            booking.setId(bookingId);

            passenger.setBooking(booking);
            passengerDAO.addPassenger(passenger);

            seatDAO.updateSeat(seat);

            conn.commit();
            return booking;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Booking newBooking(){
        Booking booking = new Booking();
        String confirmationCode = UUID.randomUUID().toString().replace("-", "");
        booking.setConfirmationCode(confirmationCode);
        booking.setActive(true);
        return booking;
    }
}
