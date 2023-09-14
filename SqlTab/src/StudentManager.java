import java.sql.*;
import java.util.ArrayList;

public class StudentManager {
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    public StudentManager(String jdbcUrl, String jdbcUser, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
    }

    public void createItalianStudentsView() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String createViewSql = "CREATE OR REPLACE VIEW italian_students AS " +
                    "SELECT first_name AS name, last_name AS surname FROM students WHERE country = 'Italy'";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createViewSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGermanStudentsView() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String createViewSql = "CREATE OR REPLACE VIEW german_students AS " +
                    "SELECT first_name AS name, last_name AS surname FROM students WHERE country = 'Germany'";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createViewSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Student> getItalianStudents() {
        ArrayList<Student> italianStudents = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String selectSql = "SELECT name, surname FROM italian_students";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    italianStudents.add(new Student(name, surname));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return italianStudents;
    }

    public ArrayList<Student> getGermanStudents() {
        ArrayList<Student> germanStudents = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            String selectSql = "SELECT name, surname FROM german_students";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    germanStudents.add(new Student(name, surname));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return germanStudents;
    }

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/newdb";
        String jdbcUsername = "developer";
        String jdbcPassword = "1122";

        StudentManager studentManager = new StudentManager(jdbcURL, jdbcUsername, jdbcPassword);

        studentManager.createItalianStudentsView();
        studentManager.createGermanStudentsView();

        ArrayList<Student> italianStudents = studentManager.getItalianStudents();
        ArrayList<Student> germanStudents = studentManager.getGermanStudents();

        System.out.println("Italian Students:");
        for (Student student : italianStudents) {
            System.out.println(student.getName() + " " + student.getSurname());
        }

        System.out.println("\nGerman Students:");
        for (Student student : germanStudents) {
            System.out.println(student.getName() + " " + student.getSurname());
        }
    }
}
