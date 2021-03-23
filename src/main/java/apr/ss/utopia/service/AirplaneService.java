package apr.ss.utopia.service;


import apr.ss.utopia.dao.AirplaneDAO;
import apr.ss.utopia.dao.AirplaneTypeDAO;
import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.AirplaneType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AirplaneService {
    Util util = new Util();

    public Airplane createAirplane(Airplane airplane){
        Connection conn;

        try{
            conn = util.getConnection();
            AirplaneDAO airplaneDAO = new AirplaneDAO(conn);
            airplane.setId(airplaneDAO.addAirplane(airplane));
            conn.commit();
            return airplane;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List<AirplaneType> fetchAllAirplaneTypes() {
        Connection conn;

        try {
            conn = util.getConnection();
            AirplaneTypeDAO airplaneTypeDAO = new AirplaneTypeDAO(conn);
            return airplaneTypeDAO.readAllAirplaneType();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to get a list of Airplane Types.");
            System.out.println(e);
        }
        return null;
    }

    public List<Airplane> fetchAllAirplane() {
        Connection conn;

        try {
            conn = util.getConnection();
            AirplaneDAO airplaneDAO = new AirplaneDAO(conn);
            return airplaneDAO.readAllAirplane();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to get a list of Airplanes.");
            System.out.println(e);
        }
        return null;
    }
}
