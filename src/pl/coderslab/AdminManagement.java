package pl.coderslab;

import pl.coderslab.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminManagement {

    public static void main(String[] args) {

        int select = 0;
        Scanner scan = new Scanner(System.in);

        while (select != 5) {
            System.out.println("Wybierz jeden z programów (1-5): \n" +
                    "1 - Zarządzanie użytkownikami \n" +
                    "2 - Zarządzanie zadaniami \n" +
                    "3 - Zarządzanie grupami \n" +
                    "4 - Przypisywanie zadań \n" +
                    "5 - Wyjście");

            select = scan.nextInt();
            scan.nextLine();

            if (select == 5) {
                return;
            }

            try (Connection conn = DbConnection.getConnection()) {


                while (select < 6 && select > 0) {
                    if (select == 1) {

                        System.out.println("Lista wszystkich użytkowników: \n");

                        User[] users = User.loadAllUsers(conn);
                        for (User singleUser : users) {
                            System.out.println(singleUser);
                        }
                        System.out.println();

                        System.out.println("Wybierz opcję: \n" +
                                "    add – dodanie użytkownika,\n" +
                                "    edit – edycja użytkownika,\n" +
                                "    delete – usunięcie użytkownika,\n" +
                                "    quit – zakończenie programu.\n");

                        String select1 = scan.nextLine();

                        if (select1.equals("add")) {
                            System.out.println("Podaj: nazwę użytkownika, email, haslo (każdą daną zatwierdź wciskając enter)");
                            String username = scan.nextLine();
                            String email = scan.nextLine();
                            String password = scan.nextLine();

                            System.out.println("Wybierz id grupy do której ma dołączyć użytkownik:");

                            UserGroup[] userGroups = UserGroup.loadAllUserGroups(conn);
                            for (UserGroup item : userGroups) {
                                System.out.println(item);
                            }
                            int groupId = scan.nextInt();
                            scan.nextLine();
                            UserGroup userGroup = UserGroup.loadUserGroupById(conn, groupId);
                            User user = new User(username, email, password, userGroup);
                            user.saveToDB(conn);
                            System.out.println("Użytkownik " + username + " dodany do bazy +\n");

                        } else if (select1.equals("quit")) {
                            select = 0;

                        } else if (select1.equals("edit")) {

                            System.out.println("Podaj id użytkownika, którego chesz edytować:");

                            int userId = scan.nextInt();
                            scan.nextLine();

                            System.out.println("Podaj: nazwę użytkownika, email, haslo (każdą daną zatwierdź wciskając enter)");
                            String username = scan.nextLine();
                            String email = scan.nextLine();
                            String password = scan.nextLine();

                            System.out.println("Wybierz id grupy do której ma dołączyć użytkownik:");

                            UserGroup[] userGroups = UserGroup.loadAllUserGroups(conn);
                            for (UserGroup item : userGroups) {
                                System.out.println(item);
                            }
                            int groupId = scan.nextInt();
                            scan.nextLine();

                            User user = User.loadUserById(conn, userId);
                            user.setUsername(username);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setUserGroup(UserGroup.loadUserGroupById(conn, groupId));
                            user.saveToDB(conn);
                            System.out.println("Pomyślnie edytowano użytkownika o id: " + userId + "\n");

                        } else if (select1.equals("delete")) {

                            System.out.println("Podaj id użytkownika, którego chcesz usunąć:");
                            int userId = scan.nextInt();
                            scan.nextLine();
                            User user = User.loadUserById(conn, userId);
                            user.delete(conn);
                            System.out.println("Użytkownik o id: " + userId + " został pomyślnie usunięty z bazy. \n");
                        }
                    } else if (select == 2) {

                        System.out.println("Lista wszystkich zadań: \n");

                        Exercise[] exercises = Exercise.loadAllExercises(conn);
                        for (Exercise singleExercise : exercises) {
                            System.out.println(singleExercise);
                        }
                        System.out.println();

                        System.out.println("Wybierz opcję: \n" +
                                "    add – dodanie zadania,\n" +
                                "    edit – edycja zadania,\n" +
                                "    delete – usunięcie zadania,\n" +
                                "    quit – zakończenie programu.\n");

                        String select1 = scan.nextLine();

                        if (select1.equals("add")) {
                            System.out.println("Podaj: nazwę zadania, a następnie opis zadania (każdą daną zatwierdź wciskając enter)");
                            String title = scan.nextLine();
                            String description = scan.nextLine();
                            Exercise exercise = new Exercise(title, description);
                            exercise.saveToDB(conn);
                            System.out.println("Zadanie " + title + " dodane do bazy +\n");

                        } else if (select1.equals("quit")) {
                            select = 0;

                        } else if (select1.equals("edit")) {

                            System.out.println("Podaj id zadania, które chesz edytować:");

                            int exerciseId = scan.nextInt();
                            scan.nextLine();

                            System.out.println("Podaj: nazwę zadania, a następnie opis zadania(bez polskich znaków) (każdą daną zatwierdź wciskając enter)");
                            String title = scan.nextLine();
                            String description = scan.nextLine();

                            Exercise exercise = Exercise.loadExerciseById(conn, exerciseId);
                            exercise.setTitle(title);
                            exercise.setDescription(description);
                            exercise.saveToDB(conn);
                            System.out.println("Pomyślnie edytowano zadanie o id: " + exerciseId + "\n");

                        } else if (select1.equals("delete")) {

                            System.out.println("Podaj id zadania, które chcesz usunąć:");
                            int exerciseId = scan.nextInt();
                            scan.nextLine();
                            Exercise exercise = Exercise.loadExerciseById(conn, exerciseId);
                            exercise.delete(conn);
                            System.out.println("Zadanie o id: " + exerciseId + " zostało pomyślnie usunięte z bazy. \n");
                        }
                    } else if (select == 3) {

                        System.out.println("Lista wszystkich grup: \n");

                        UserGroup[] groups = UserGroup.loadAllUserGroups(conn);
                        for (UserGroup singleGroup : groups) {
                            System.out.println(singleGroup);
                        }
                        System.out.println();

                        System.out.println("Wybierz opcję: \n" +
                                "    add – dodanie grupy,\n" +
                                "    edit – edycja grupy,\n" +
                                "    delete – usunięcie grupy,\n" +
                                "    quit – zakończenie programu.\n");

                        String select1 = scan.nextLine();

                        if (select1.equals("add")) {
                            System.out.println("Podaj nazwę grupy: ");
                            String name = scan.nextLine();
                            UserGroup group = new UserGroup(name);
                            group.saveToDB(conn);
                            System.out.println("Grupa " + name + " dodana do bazy. \n");

                        } else if (select1.equals("quit")) {
                            select = 0;

                        } else if (select1.equals("edit")) {

                            System.out.println("Podaj id grupy którą chesz edytować:");

                            int groupId = scan.nextInt();
                            scan.nextLine();

                            System.out.println("Podaj nazwę grupy: ");
                            String name = scan.nextLine();

                            UserGroup group = UserGroup.loadUserGroupById(conn, groupId);
                            group.setName(name);
                            group.saveToDB(conn);
                            System.out.println("Pomyślnie edytowano grupę o id: " + groupId + "\n");

                        } else if (select1.equals("delete")) {

                            System.out.println("Podaj id grupy którą chcesz usunąć:");
                            int groupId = scan.nextInt();
                            scan.nextLine();
                            UserGroup group = UserGroup.loadUserGroupById(conn, groupId);
                            group.delete(conn);
                            System.out.println("Grupa o id: " + groupId + " została pomyślnie usunięta z bazy. \n");
                        }
                    } else if (select == 4) {

                        System.out.println("Wybierz jedną z opcji: \n" +
                                "    add – przypisywanie zadań do użytkowników,\n" +
                                "    view – przeglądanie rozwiązań danego użytkownika,\n" +
                                "    quit – zakończenie programu.\n");

                        String select1 = scan.nextLine();

                        if (select1.equals("add")) {

                            System.out.println("Lista wszystkich użytkowników: \n");

                            User[] users = User.loadAllUsers(conn);
                            for (User singleUser : users) {
                                System.out.println(singleUser);
                            }
                            System.out.println();

                            System.out.println("Podaj id użytkownika: ");

                            int userId = scan.nextInt();
                            scan.nextLine();

                            System.out.println("Lista wszystkich zadań: \n");

                            Exercise[] exercises = Exercise.loadAllExercises(conn);
                            for (Exercise singleExercise : exercises) {
                                System.out.println(singleExercise);
                            }
                            System.out.println();

                            System.out.println("Podaj id zadania: ");

                            int exerciseId = scan.nextInt();
                            scan.nextLine();


                            Solution solution = new Solution(Exercise.loadExerciseById(conn, exerciseId), User.loadUserById(conn, userId));
                            solution.saveToDB(conn);
                            System.out.println("Pomyślnie powiązano zadanie o id: " + exerciseId + " z użytkownikiem o id: " + userId + "\n");

                        } else if (select1.equals("view")) {
                            System.out.println("Podaj id użytkownika: ");

                            int userId = scan.nextInt();
                            scan.nextLine();

                            System.out.println("Rozwiązania użytkownika o id: " + userId + "\n");
                            Solution[] solutions = Solution.loadAllByUserId(conn, userId);
                            for (Solution singleSolution : solutions) {
                                System.out.println(singleSolution);
                            }
                            System.out.println();
                        } else if (select1.equals("quit")) {
                            select = 0;

                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Błąd połączenia z bazą");
            }
        }
    }
}
