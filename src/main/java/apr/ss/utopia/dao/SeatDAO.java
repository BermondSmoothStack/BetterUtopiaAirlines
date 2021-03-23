package apr.ss.utopia.dao;

import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO extends BaseDAO<Seat> {

    public SeatDAO(Connection conn) {
        super(conn);
    }

    public Integer addSeat(Seat seat) throws SQLException {
        return save("insert into " +
                        Seat.NAME + " (" +
                        Seat.TYPE + ", " +
                        Seat.AIRPLANE + ", " +
                        Seat.CAPACITY + ", " +
                        Seat.RESERVED +
                        ") " + " values (?,?,?,?)",
                new Object[]{
                        seat.getType().getId(),
                        seat.getAirplaneType().getId(),
                        seat.getCapacity(),
                        seat.getReserved(),
                });
    }

    public void updateSeat(Seat seat) throws SQLException {
        save("update " + Seat.NAME + " set " +
                        Seat.TYPE + " = ?," +
                        Seat.AIRPLANE + " = ?, " +
                        Seat.CAPACITY + " = ?, " +
                        Seat.RESERVED + " = ? " +
                        "where " + Seat.ID + " = ?",
                new Object[]{
                        seat.getType().getId(),
                        seat.getAirplaneType().getId(),
                        seat.getCapacity(),
                        seat.getReserved(),
                        seat.getId()
                });
    }

    public boolean deleteSeat(Seat seat) throws SQLException {
        return delete("delete from " + Seat.NAME + " where " + Seat.ID + " = ?", new Object[]{seat.getId()});
    }

    public List<Seat> readAllSeat() throws ClassNotFoundException, SQLException {
        return read("select * from " + Seat.NAME, new Object[]{});
    }

    public List<Seat> readSeatsByCode(Seat seat) throws ClassNotFoundException, SQLException {
        return read("select " +
                Seat.NAME + "." + Seat.ID + ", " +
                Seat.CAPACITY + ", " +
                Seat.RESERVED + ", " +
                Seat.AIRPLANE + ", " +
                SeatType.TYPE_NAME + ", " +
                Seat.TYPE +
                " from " + Seat.NAME +
                " join seat_type on seats.seat_type = seat_type.id"+
                " where "+ Seat.NAME +"." + Seat.ID + " = ?", new Object[]{seat.getId()});
    }

    @Override
    public List<Seat> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Seat> seats = new ArrayList<>();
        while (rs.next()) {
            Seat s = new Seat();
            SeatType st = new SeatType();

            st.setName(rs.getString(SeatType.TYPE_NAME));

            s.setId(rs.getInt(Seat.ID));
            s.setCapacity(rs.getInt(Seat.CAPACITY));
            s.setReserved(rs.getInt(Seat.RESERVED));
            s.setType(st);

            seats.add(s);
        }
        return seats;
    }
}
