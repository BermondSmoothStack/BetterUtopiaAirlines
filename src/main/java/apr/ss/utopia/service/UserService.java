package apr.ss.utopia.service;

import apr.ss.utopia.dao.UserDAO;
import apr.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    Util util = new Util();

    public User createUser(User u) {
        Connection conn;

        try {
            conn = util.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            u.setId(userDAO.addUser(u));
            conn.commit();
            return u;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("User Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return u;
    }

    public boolean updateUser(User u){
        Connection conn;

        try {
            conn = util.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            Boolean isSuccess = userDAO.updateUser(u);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("User Creation Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUserById(User user) {
        Connection conn;
        try {
            conn = util.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            boolean isSuccess = userDAO.deleteUser(user);
            conn.commit();
            return isSuccess;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public User getUserById(Integer id) {
        Connection conn;
        try {
            conn = util.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            User user = new User();
            user.setId(id);
            List<User> pl = userDAO.readUsersByCode(user);
            user = pl.isEmpty() ? null : pl.get(0);
            conn.commit();
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Passenger Lookup Failed! Make sure you entered the correct information.");
            System.out.println(e);
        }
        return null;
    }
}
