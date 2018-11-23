package pl.coderslab;

import pl.coderslab.utils.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        User user = new User("Karolo", "emjdsa@example.com", "password", new UserGroup("grupa"));

        try (Connection conn = DbConnection.getConnection()) {

           // user.saveToDB(conn);

//           User user1 = User.loadUserById(conn, 3);
//            System.out.println(user1);
//            user1.setEmail("sjadhja@wp.pl");
//            user1.saveToDB(conn);

       //     User[] users = User.loadAllUsers(conn);
         //   for (User userElement : users) {
          //      System.out.println(userElement);
            User user4 = User.loadUserById(conn, 4);
            user4.delete(conn);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
