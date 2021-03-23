package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Booking;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.FlightBookings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingsDAO extends BaseDAO<FlightBookings> {

    public FlightBookingsDAO(Connection conn) {
        super(conn);
    }

    public Integer addFlightBookings(FlightBookings flightBookings) throws SQLException {
        return save("insert into "+ FlightBookings.NAME +  " ( "+
                        FlightBookings.FLIGHT_ID + ", " +
                        FlightBookings.BOOKING_ID + ") " +
                        " values (?, ?)",
                new Object[]{
                        flightBookings.getFlight().getId(),
                        flightBookings.getBooking().getId()
                });
    }

    public void deleteFlightBookings(FlightBookings flightBookings) throws SQLException {
        save("delete from " + FlightBookings.NAME +
                        " where " + FlightBookings.FLIGHT_ID + " = ? " +
                        "AND " + FlightBookings.BOOKING_ID + " = ?",
                new Object[]{flightBookings.getFlight().getId(), flightBookings.getBooking().getId()});
    }

    public List<FlightBookings> readAllFlightBookings() throws ClassNotFoundException, SQLException {
        return read("select * from " + FlightBookings.NAME, new Object[]{});
    }

    public List<FlightBookings> readFlightBookingsByCode(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
        return read("select * from " + FlightBookings.NAME +
                        " where " + FlightBookings.FLIGHT_ID + " = ?" +
                        " and " + FlightBookings.BOOKING_ID + " = ?",
                new Object[]{flightBookings.getFlight().getId(), flightBookings.getBooking().getId()});
    }

    @Override
    public List<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<FlightBookings> flightBookings = new ArrayList<>();
        while (rs.next()) {
            FlightBookings fb = new FlightBookings();
            Flight f = new Flight();
            Booking b = new Booking();

            f.setId(rs.getInt(FlightBookings.FLIGHT_ID));
            b.setId(rs.getInt(FlightBookings.BOOKING_ID));

            fb.setFlight(f);
            fb.setBooking(b);

            flightBookings.add(fb);
        }
        return flightBookings;
    }
}
