package apr.ss.utopia.service;

import apr.ss.utopia.dao.SeatDAO;
import apr.ss.utopia.dao.SeatTypeDAO;
import apr.ss.utopia.entity.SeatType;
import apr.ss.utopia.entity.Seat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatService {
    Util util = new Util();

    public Seat createSeat(Seat seat){
        Connection conn;
        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            seat.setId(seatDAO.addSeat(seat));
            conn.commit();
            return seat;
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Seat Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }

    public List<SeatType> fetchAllSeatTypes() {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatTypeDAO seatDAO = new SeatTypeDAO(conn);
            return seatDAO.readAllSeatType();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    public SeatType fetchSeatTypeByName(String name) {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatTypeDAO seatTypeDAO = new SeatTypeDAO(conn);
            SeatType s = new SeatType();
            s.setName(name);
            List<SeatType> sl = seatTypeDAO.readSeatTypesByType(s);
            s = sl.isEmpty() ? null : sl.get(0);
            return s;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteSeat(Seat seat) {
        Connection conn;
        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            boolean isSuccess = seatDAO.deleteSeat(seat);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public Seat fetchSeatById(Seat seat) {
        Connection conn = null;

        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            Seat s = new Seat();
            s.setId(seat.getId());
            List<Seat> sl = seatDAO.readSeatsByCode(s);
            s = sl.isEmpty() ? null : sl.get(0);
            return s;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateSeat(Seat seat) {
        Connection conn;
        try {
            conn = util.getConnection();
            SeatDAO seatDAO = new SeatDAO(conn);
            seatDAO.updateSeat(seat);
            conn.commit();
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Seat Update Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
    }
}
