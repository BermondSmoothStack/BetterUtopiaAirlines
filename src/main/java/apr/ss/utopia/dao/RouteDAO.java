package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Airport;
import apr.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends BaseDAO<Route> {

    public RouteDAO(Connection conn) {
        super(conn);
    }

    public Integer addRoute(Route route) throws SQLException {
        return save("insert into "
                        + Route.NAME + " (" +
                        Route.DEST_CODE + ", " +
                        Route.ORIGIN_CODE + ") " + " values (?, ?)",
                new Object[]{route.getDestinationAirport().getAirportCode(), route.getOriginAirport().getAirportCode()});
    }

    public void updateRoute(Route route) throws SQLException {
        save("update " + Route.NAME + " set " +
                        Route.DEST_CODE + " = ?, " +
                        Route.ORIGIN_CODE + " = ? " +
                        "where " + Route.ID + " = ?",
                new Object[]{route.getDestinationAirport(), route.getOriginAirport(), route.getId()});
    }

    public List<Route> findRouteByRoute(Route route) throws SQLException, ClassNotFoundException {
        return read("select * from " + Route.NAME +
                        " where " + Route.DEST_CODE + " = ?" +
                        " and " + Route.ORIGIN_CODE + " = ?",
                new Object[]{
                        route.getDestinationAirport().getAirportCode(),
                        route.getOriginAirport().getAirportCode()
                });
    }

    public void deleteRoute(Route route) throws SQLException {
        save("delete from " + Route.NAME + " where " + Route.ID + " = ?", new Object[]{route.getId()});
    }

    public List<Route> readAllRoute() throws ClassNotFoundException, SQLException {
        return read("select * from " + Route.NAME, new Object[]{});
    }

    public List<Route> readRoutesByCode(Route route) throws ClassNotFoundException, SQLException {
        return read("select * from " + Route.NAME + " where " + Route.ID + " = ?", new Object[]{route.getId()});
    }


    @Override
    public List<Route> extractData(ResultSet rs) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (rs.next()) {
            Route r = new Route();
            Airport destination = new Airport();
            Airport origin = new Airport();

            destination.setAirportCode(rs.getString(Route.DEST_CODE));
            origin.setAirportCode(rs.getString(Route.ORIGIN_CODE));

            r.setId(rs.getInt(Route.ID));
            r.setDestinationAirport(destination);
            r.setOriginAirport(origin);

            routes.add(r);
        }
        return routes;
    }
}
