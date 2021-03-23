package apr.ss.utopia.dao;

import apr.ss.utopia.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDAO extends BaseDAO<Flight> {

    public FlightDAO(Connection conn) {
        super(conn);
    }

    public Integer addFlight(Flight flight) throws SQLException {
        return save("insert into " + Flight.NAME + " (" +
                        Flight.ROUTE + ", " +
                        Flight.AIRPLANE + ", " +
                        Flight.DEPARTURE + ", " +
                        Flight.RESERVED_SEATS + "," +
                        Flight.DURATION + "," +
                        Flight.SEAT_PRICE + ")" +
                        " values (?,?,?,?,?,?)",
                new Object[]{
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        0,
                        flight.getDuration(),
                        flight.getSeatPrice()
                });
    }

    public void updateFlight(Flight flight) throws SQLException {
        save("update " + Flight.NAME + " set " +
                        Flight.ROUTE + " = ?, " +
                        Flight.AIRPLANE + " = ?, " +
                        Flight.DEPARTURE + " = ?, " +
                        Flight.DURATION + " = ?, " +
                        Flight.RESERVED_SEATS + " = ?, " +
                        Flight.SEAT_PRICE + " = ? " +
                        "where " + Flight.ID + " = ?",
                new Object[]{
                        flight.getRoute().getId(),
                        flight.getAirplane().getId(),
                        flight.getDepartureTime(),
                        flight.getDuration(),
                        0,
                        flight.getSeatPrice(),
                        flight.getId()
                });
    }

    public boolean deleteFlight(Flight flight) throws SQLException {
        return delete("delete from " + Flight.NAME + " where " + Flight.ID + " = ?", new Object[]{flight.getId()});
    }

    public List<Flight> readAllFlight() throws ClassNotFoundException, SQLException {
        return readVerbose(
                "select \n" +
                        "airplane.id as airplane_id,\n" +
                        "seat_type.id as seat_type_id,\n"+
                        "seats.id as seat_id,\n" +
                        "name as seat_name,\n" +
                        "slots,\n" +
                        "seats.reserved_seats,\n" +
                        "flight.id as flight_id, \n" +
                        "origin.iata_id as origin_id, \n" +
                        "origin.city as origin_city, \n" +
                        "destination.iata_id as destination_id, \n" +
                        "destination.city as destination_city, \n" +
                        "departure_time, \n" +
                        "seat_price, \n" +
                        "duration_mins, \n" +
                        "airplane.type_id as airplane_type \n" +
                        "from \n" +
                        "(select iata_id, \n" +
                        "city, route.id as route_id, \n" +
                        "flight.id as flight_id \n" +
                        "from flight \n" +
                        "join route on flight.route_id = route.id \n" +
                        "join airport on route.origin_id = airport.iata_id) as origin \n" +
                        "join \n" +
                        "(select iata_id, \n" +
                        "city, route.id as route_id \n" +
                        "from \n" +
                        "flight \n" +
                        "join route on flight.route_id = route.id \n" +
                        "join airport on route.destination_id = airport.iata_id) as destination \n" +
                        "on origin.route_id = destination.route_id \n" +
                        "left join flight on flight.id = origin.flight_id \n" +
                        "left join airplane on flight.airplane_id = airplane.id\n" +
                        "left join airplane_type on airplane.type_id = airplane_type.id\n" +
                        "left join seats on seats.airplane_type = airplane_type.id\n" +
                        "left join seat_type on seats.seat_type = seat_type.id", new Integer[]{});
    }

    public List<Flight> readFlightsByCode(Flight flight) throws ClassNotFoundException, SQLException {
        return readVerbose(
                "select \n" +
                        "seat_type.id as seat_type_id,\n" +
                        "seats.id as seat_id,\n" +
                        "name as seat_name,\n" +
                        "slots,\n" +
                        "seats.reserved_seats,\n" +
                        "flight.id as flight_id, \n" +
                        "origin.iata_id as origin_id, \n" +
                        "origin.city as origin_city, \n" +
                        "destination.iata_id as destination_id, \n" +
                        "destination.city as destination_city, \n" +
                        "departure_time, \n" +
                        "seat_price, \n" +
                        "duration_mins, \n" +
                        "airplane.type_id as airplane_type \n" +
                        "from \n" +
                        "(select iata_id, \n" +
                        "city, route.id as route_id, \n" +
                        "flight.id as flight_id \n" +
                        "from flight \n" +
                        "join route on flight.route_id = route.id \n" +
                        "join airport on route.origin_id = airport.iata_id) as origin \n" +
                        "join \n" +
                        "(select iata_id, \n" +
                        "city, route.id as route_id \n" +
                        "from \n" +
                        "flight \n" +
                        "join route on flight.route_id = route.id \n" +
                        "join airport on route.destination_id = airport.iata_id) as destination \n" +
                        "on origin.route_id = destination.route_id \n" +
                        "left join flight on flight.id = origin.flight_id \n" +
                        "left join airplane on flight.airplane_id = airplane.id\n" +
                        "left join airplane_type on airplane.type_id = airplane_type.id\n" +
                        "left join seats on seats.airplane_type = airplane_type.id\n" +
                        "left join seat_type on seats.seat_type = seat_type.id\n" +
                        "where flight.id = ?",
                new Integer[]{flight.getId()}
        );
    }

    public List<Flight> readVerbose(String sql, Integer[] vals) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int count = 1;
        for (Integer o : vals) {
            pstmt.setInt(count, o);
            count++;
        }
        ResultSet rs = pstmt.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {

            Flight f = new Flight();
            Airplane a = new Airplane();
            AirplaneType at = new AirplaneType();
            Route r = new Route();
            Airport o = new Airport();
            Airport d = new Airport();
            Seat s = new Seat();
            SeatType st = new SeatType();
            List<Seat> sl = new ArrayList<>();

            st.setId(rs.getInt("seat_type_id"));
            st.setName(rs.getString("seat_name"));
            s.setId(rs.getInt("seat_id"));
            s.setCapacity(rs.getInt("slots"));
            s.setReserved(rs.getInt("reserved_seats"));

            s.setType(st);
            s.setAirplaneType(at);

            o.setAirportCode(rs.getString("origin_id"));
            o.setCity(rs.getString("origin_city"));

            d.setAirportCode(rs.getString("destination_id"));
            d.setCity(rs.getString("destination_city"));

            r.setOriginAirport(o);
            r.setDestinationAirport(d);

            at.setId(rs.getInt("airplane_type"));
            a.setId(rs.getInt("airplane_id"));
            a.setType(at);

            f.setId(rs.getInt("flight_id"));

            f.setRoute(r);
            f.setAirplane(a);
            f.setDuration(rs.getInt(Flight.DURATION));
            f.setDepartureTime(rs.getTimestamp(Flight.DEPARTURE).toLocalDateTime());
            f.setSeatPrice(rs.getFloat(Flight.SEAT_PRICE));

            Flight flightExist = null;
            if (!flights.isEmpty()) {
                List<Flight> flightList = flights.stream().filter(fd -> f.getId().equals(fd.getId())).collect(Collectors.toList());
                flightExist = !flightList.isEmpty() ? flightList.get(0) : null;
            }

            if (null != flightExist) {
                sl = flightExist.getSeats();
            }
            sl.add(s);
            f.setSeats(sl);
            flights.add(f);
        }
        return flights;
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight f = new Flight();
            Airplane a = new Airplane();
            Route r = new Route();

            a.setId(rs.getInt(Flight.AIRPLANE));
            r.setId(rs.getInt(Flight.ROUTE));

            f.setId(rs.getInt(Flight.ID));
            f.setAirplane(a);
            f.setRoute(r);
            f.setDuration(rs.getInt(Flight.DURATION));
            f.setDepartureTime(rs.getTimestamp(Flight.DEPARTURE).toLocalDateTime());
            f.setSeatPrice(rs.getFloat(Flight.SEAT_PRICE));

            flights.add(f);
        }

        return flights;
    }
}
