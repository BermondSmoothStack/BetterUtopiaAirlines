package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport> {

    public AirportDAO(Connection conn) {
        super(conn);
    }

    public Integer addAirport(Airport airport) throws SQLException {
        return save("insert into " + Airport.NAME + " (" +
                        Airport.CODE + ", " +
                        Airport.CITY + ") " +
                        " values (?, ?)",
                new Object[]{
                        airport.getAirportCode(),
                        airport.getCity()
                });
    }

    public Boolean updateAirportCode(Airport airport) throws SQLException {
        return save("update " + Airport.NAME + " set " +
                        Airport.CITY + " = ? " +
                        "where " + Airport.CODE + " = ?",
                new Object[]{
                        airport.getCity(),
                        airport.getAirportCode()
                }) > 0;
    }

    public boolean deleteAirport(Airport airport) throws SQLException {
        return delete("delete from " + Airport.NAME + " where " + Airport.CODE + " = ?", new Object[]{airport.getAirportCode()});
    }

    public List<Airport> readAllAirport() throws ClassNotFoundException, SQLException {
        return read("select * from " + Airport.NAME, new Object[]{});
    }

    public List<Airport> readAirportsByCode(String code) throws ClassNotFoundException, SQLException {
        return read("select * from " + Airport.NAME + " where " + Airport.CODE + " = ?", new Object[]{code});
    }

    @Override
    public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Airport> airports = new ArrayList<>();
        while (rs.next()) {
            Airport a = new Airport();
            a.setAirportCode(rs.getString(Airport.CODE));
            a.setCity(rs.getString(Airport.CITY));
            airports.add(a);
        }

        return airports;
    }
}
