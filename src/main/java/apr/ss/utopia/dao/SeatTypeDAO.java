package apr.ss.utopia.dao;

import apr.ss.utopia.entity.SeatType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatTypeDAO extends BaseDAO<SeatType> {
    public SeatTypeDAO(Connection conn) {
        super(conn);
    }

    public void addSeatType(SeatType seat) throws SQLException {
        save("insert into (" +
                        SeatType.TYPE_NAME + ") " +
                        SeatType.NAME + " values (?)",
                new Object[]{seat.getName()});
    }

    public void updateSeatType(SeatType seat) throws SQLException {
        save("update " + SeatType.NAME + " set " +
                        SeatType.TYPE_NAME + " = ? " +
                        "where " + SeatType.ID + " = ?",
                new Object[]{seat.getName(), seat.getId()});
    }

    public void deleteSeatType(SeatType seat) throws SQLException {
        save("delete from " + SeatType.NAME + " where " + SeatType.ID + " = ?", new Object[]{seat.getId()});
    }

    public List<SeatType> readAllSeatType() throws ClassNotFoundException, SQLException {
        return read("select * from " + SeatType.NAME, new Object[]{});
    }

    public List<SeatType> readSeatTypesByType(SeatType seat) throws ClassNotFoundException, SQLException {
        return read("select * from " + SeatType.NAME + " where " + SeatType.TYPE_NAME + " = ?", new Object[]{seat.getName()});
    }

    @Override
    public List<SeatType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<SeatType> seats = new ArrayList<>();
        while (rs.next()) {
            SeatType st = new SeatType();

            st.setId(rs.getInt(SeatType.ID));
            st.setName(rs.getString(SeatType.TYPE_NAME));

            seats.add(st);
        }
        return seats;
    }

}
