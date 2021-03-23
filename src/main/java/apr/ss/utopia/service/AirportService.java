package apr.ss.utopia.service;

import apr.ss.utopia.dao.AirportDAO;
import apr.ss.utopia.entity.Airport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportService {
    Util util = new Util();

    public Airport createAirport(Airport airport) {
        Connection conn;

        try {
            conn = util.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            airportDAO.addAirport(airport);
            conn.commit();
            return airport;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Airport Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return airport;
    }

    public boolean updateAirport(Airport airport){
        Connection conn;

        try {
            conn = util.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            Boolean isSuccess = airportDAO.updateAirportCode(airport);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Airport Update Failed! Make sure you IATA already exists.");
            System.out.println(e);
        }
        return false;
    }

    public List<Airport> fetchAllAirports() {
        Connection conn;

        try {
            conn = util.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            return airportDAO.readAllAirport();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    public Airport fetchAirportByIATA(String code){
        Connection conn;

        try {
            conn = util.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            List<Airport> rl = airportDAO.readAirportsByCode(code);
            return rl.get(0);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return new Airport();
    }

    public boolean deleteAirportByCode(Airport a){
        Connection conn;
        try {
            conn = util.getConnection();
            AirportDAO airportDAO = new AirportDAO(conn);
            boolean isSuccess = airportDAO.deleteAirport(a);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }
}
