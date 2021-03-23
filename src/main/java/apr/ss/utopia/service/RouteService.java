package apr.ss.utopia.service;

import apr.ss.utopia.dao.RouteDAO;
import apr.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RouteService {

    Util util = new Util();

    public Route createRoute(Route route) {
        Connection conn;

        try {
            conn = util.getConnection();
            RouteDAO routeDAO = new RouteDAO(conn);
            List<Route> routes = routeDAO.findRouteByRoute(route);
            if (!routes.isEmpty())
                return routes.get(0);
            route.setId(routeDAO.addRoute(route));
            conn.commit();
            return route;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Route Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return route;
    }
}
