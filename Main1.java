package exam;

import java.sql.*;

public class Main1 {
    private static final String QUERY1 = "create table users(id int auto_increment primary key, name varchar(60), email varchar(60) unique, password varchar(60));";
    private static final String QUERY2 = "create table messages(id int auto_increment primary key, user_id int, message varchar(500), foreign key(user_id) references users(id));";
    private static final String QUERY3 = "create table items(id int auto_increment primary key, name varchar(40), description varchar(500), price decimal(7, 2));";
    private static final String QUERY4 = "create table orders(id int auto_increment primary key, description varchar (500), created date, user_id int, foreign key(user_id) references users(id));";
    private static final String QUERY5 = "create table items_orders(id int auto_increment primary key, item_id int, order_id int, foreign key(item_id) references items(id), foreign key(order_id) references orders(id));";


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam2_ex?useSSL=false&characterEncoding=utf8",
                "root", "coderslab")) {
            PreparedStatement QUERY1SQLStatement = conn.prepareStatement(QUERY1);
            QUERY1SQLStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn1 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam2_ex?useSSL=false&characterEncoding=utf8",
                "root", "coderslab")) {
            PreparedStatement QUERY2SQLStatement = conn1.prepareStatement(QUERY2);
            QUERY2SQLStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn2 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam2_ex?useSSL=false&characterEncoding=utf8",
                "root", "coderslab")) {
            PreparedStatement QUERY3SQLStatement = conn2.prepareStatement(QUERY3);
            QUERY3SQLStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn3 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam2_ex?useSSL=false&characterEncoding=utf8",
                "root", "coderslab")) {
            PreparedStatement QUERY4SQLStatement = conn3.prepareStatement(QUERY4);
            QUERY4SQLStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn4 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/exam2_ex?useSSL=false&characterEncoding=utf8",
                "root", "coderslab")) {
            PreparedStatement QUERY5SQLStatement = conn4.prepareStatement(QUERY5);
            QUERY5SQLStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


