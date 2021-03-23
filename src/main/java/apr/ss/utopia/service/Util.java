package apr.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = System.getenv("sqlhost");
    private final String username = System.getenv("sqlusername");
    private final String password = System.getenv("sqlpassword");

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }

}
