package pl.coderslab.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() throws SQLException {

    return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/warsztat2_ex?useSSL=false&characterEncoding=utf8",
            "root", "coderslab");
    }
}
