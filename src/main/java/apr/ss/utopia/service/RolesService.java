package apr.ss.utopia.service;

import apr.ss.utopia.dao.RoleDAO;
import apr.ss.utopia.entity.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesService {
    Util util = new Util();

    public List<Role> fetchAllRoles() {
        Connection conn = null;

        try {
            conn = util.getConnection();
            RoleDAO roleDAO = new RoleDAO(conn);
            return roleDAO.readAllRole();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

}
