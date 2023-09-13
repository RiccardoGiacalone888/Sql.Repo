import java.sql.*;

public class DataBaseDemo {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "developer";
            String password = "1122";

            conn = DriverManager.getConnection(url, user, password);

            try {
                Statement statement = conn.createStatement();
                String query = "SELECT * FROM students LIMIT 4";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");

                    System.out.println("Student ID: " + studentId);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("First Name: " + firstName);
                    System.out.println("-----------------------");
                }

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trovato " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore di connessione con il database " + e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("Errore durante la chiusura della connessione " + e.getMessage());
            }
        }
    }
}
