import java.sql.*;
import java.util.ArrayList;

public class StudentsNameDemo {
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
                String query = "SELECT first_name, last_name FROM students";
                ResultSet resultSet = statement.executeQuery(query);

                ArrayList<String> surnames = new ArrayList<>();
                int count = 0;
                while (resultSet.next() && count <4) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_Name");
                    System.out.println("Students " + firstName);
                    surnames.add(lastName);
                    count++;
                }
                resultSet.close();
                System.out.println("Surnames:");
                for (String surname : surnames) {
                    System.out.println(surname);
                }
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






