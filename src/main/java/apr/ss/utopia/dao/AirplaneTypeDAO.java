package apr.ss.utopia.dao;

import apr.ss.utopia.entity.AirplaneType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType> {

    public AirplaneTypeDAO(Connection conn) {
        super(conn);
    }

    public void addAirplaneType(AirplaneType airplaneType) throws SQLException {
        save("insert into (" + AirplaneType.CAPACITY + ") " + AirplaneType.NAME + " values (?)", new Object[]{airplaneType.getCapacity()});
    }

    public void updateAirplaneType(AirplaneType airplaneType) throws SQLException {
        save("update " + AirplaneType.NAME + " set " + AirplaneType.CAPACITY + " = ? where " + AirplaneType.ID + " = ?", new Object[]{airplaneType.getCapacity(), airplaneType.getId()});
    }

    public void deleteAirplaneType(AirplaneType airplaneType) throws SQLException {
        save("delete from " + AirplaneType.NAME + " where " + AirplaneType.ID + " = ?", new Object[]{airplaneType.getId()});
    }

    public List<AirplaneType> readAllAirplaneType() throws ClassNotFoundException, SQLException {
        return read("select * from " + AirplaneType.NAME, new Object[]{});
    }

    public List<AirplaneType> readAirplaneTypesByCode(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
        return read("select * from " + AirplaneType.NAME + " where " + AirplaneType.ID + " = ?", new Object[]{airplaneType.getId()});
    }


    @Override
    public List<AirplaneType> extractData(ResultSet rs) throws SQLException {
        List<AirplaneType> airplaneTypes = new ArrayList<>();
        while (rs.next()) {
            AirplaneType a = new AirplaneType();
            a.setId(rs.getInt(AirplaneType.ID));
            a.setCapacity(rs.getInt(AirplaneType.CAPACITY));
            airplaneTypes.add(a);
        }
        return airplaneTypes;
    }
}
