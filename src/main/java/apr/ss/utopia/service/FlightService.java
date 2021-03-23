package apr.ss.utopia.service;

import apr.ss.utopia.dao.FlightDAO;
import apr.ss.utopia.dao.RouteDAO;
import apr.ss.utopia.entity.Flight;
import apr.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    Util util = new Util();

    public Flight createFlight(Flight f) {
        Connection conn;

        try {
            conn = util.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            f.setId(flightDAO.addFlight(f));
            conn.commit();
            return f;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Flight Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return f;
    }

    public List<Flight> fetchAllFlights() {
        Connection conn;
        try {
            conn = util.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            return flightDAO.readAllFlight();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    public boolean deleteFlightById(Flight f) {
        Connection conn;
        try {
            conn = util.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            boolean isSuccess = flightDAO.deleteFlight(f);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public Flight fetchFlightById(Flight flight) {
        Connection conn;

        try {
            conn = util.getConnection();
            FlightDAO airportDAO = new FlightDAO(conn);
            List<Flight> rl = airportDAO.readFlightsByCode(flight);
            return rl.get(0);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return new Flight();
    }

    public void updateFlight(Route route, Flight flight) {
        Connection conn;

        try {
            conn = util.getConnection();
            FlightDAO flightDAO = new FlightDAO(conn);
            RouteDAO routeDAO = new RouteDAO(conn);
            List<Route> rl = routeDAO.findRouteByRoute(route);
            if (rl.isEmpty())
                route.setId(routeDAO.addRoute(route));
            else
                route = rl.get(0);
            flight.setRoute(route);
            flightDAO.updateFlight(flight);
            conn.commit();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Flight Update Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }

    }
}
