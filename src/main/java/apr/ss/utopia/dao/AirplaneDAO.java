package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Airplane;
import apr.ss.utopia.entity.AirplaneType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO extends BaseDAO<Airplane> {

    public AirplaneDAO(Connection conn) {
        super(conn);
    }

    public Integer addAirplane(Airplane airplane) throws SQLException {
        return save("insert into " + Airplane.NAME + " (" + Airplane.TYPE + ") " + " values (?)", new Object[]{airplane.getType().getId()});
    }

    public void updateAirplane(Airplane airplane) throws SQLException {
        save("update " + Airplane.NAME + " set " + Airplane.TYPE + " = ? where " + Airplane.ID + " = ?", new Object[]{airplane.getType().getId(), airplane.getId()});
    }

    public void deleteAirplane(Airplane airplane) throws SQLException {
        save("delete from " + Airplane.NAME + " where " + Airplane.ID + " = ?", new Object[]{airplane.getId()});
    }

    public List<Airplane> readAllAirplane() throws ClassNotFoundException, SQLException {
        return read("select " +
                        Airplane.NAME + "." + Airplane.ID + " as " + Airplane.ID + ", " +
                        Airplane.TYPE + ", " +
                        AirplaneType.CAPACITY +
                        " from " + Airplane.NAME +
                        " join " + AirplaneType.NAME +
                        " on " + Airplane.NAME + "." + Airplane.TYPE +
                        " = " + AirplaneType.NAME + "." + AirplaneType.ID,
                new Object[]{});
    }

    public List<Airplane> readAirportsByCode(Airplane airplane) throws ClassNotFoundException, SQLException {
        return read("select * from " + Airplane.NAME + " where " + Airplane.ID + " = ?", new Object[]{airplane.getId()});
    }

    @Override
    public List<Airplane> extractData(ResultSet rs) throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();
        while (rs.next()) {
            Airplane a = new Airplane();
            AirplaneType at = new AirplaneType();
            at.setId(rs.getInt(Airplane.TYPE));
            at.setCapacity(rs.getInt(AirplaneType.CAPACITY));
            a.setId(rs.getInt(Airplane.ID));
            a.setType(at);
            airplanes.add(a);
        }
        return airplanes;
    }
}
