package apr.ss.utopia.dao;

import apr.ss.utopia.entity.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends BaseDAO<Role> {

    public RoleDAO(Connection conn) {
        super(conn);
    }

    public void addRole(Role role) throws SQLException {
        save("insert into (" +
                        Role.ROLE_NAME + ") " +
                        Role.NAME + " values (?)",
                new Object[]{role.getName()});
    }

    public void updateRole(Role role) throws SQLException {
        save("update " + Role.NAME + " set " +
                        Role.ROLE_NAME + " = ? " +
                        "where " + Role.ID + " = ?",
                new Object[]{role.getName(), role.getId()});
    }

    public void deleteRole(Role role) throws SQLException {
        save("delete from " + Role.NAME + " where " + Role.ID + " = ?", new Object[]{role.getId()});
    }

    public List<Role> readAllRole() throws ClassNotFoundException, SQLException {
        return read("select * from " + Role.NAME, new Object[]{});
    }

    public List<Role> readRolesByCode(Role role) throws ClassNotFoundException, SQLException {
        return read("select * from " + Role.NAME + " where " + Role.ID + " = ?", new Object[]{role.getId()});
    }

    @Override
    public List<Role> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            Role r = new Role();

            r.setId(rs.getInt(Role.ID));
            r.setName(rs.getString(Role.ROLE_NAME));

            roles.add(r);
        }
        return roles;
    }
}
