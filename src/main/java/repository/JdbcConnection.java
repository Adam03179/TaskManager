package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 class JdbcConnection {

     static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager", "root", "onscreen");
    }
}
