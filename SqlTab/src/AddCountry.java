import java.sql.*;

public class AddCountry {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "developer";
            String password = "1122";

            conn = DriverManager.getConnection(url, user, password);
            try {
                Statement alterStatement = conn.createStatement();
                String alterTableSql = "ALTER TABLE students ADD COLUMN country VARCHAR(30) ";
                alterStatement.executeUpdate(alterTableSql);

                String updateItalySql = "UPDATE students SET country = 'Italy' WHERE student_id IN (1, 2)";
                String updateGermanySql = "UPDATE students SET country = 'Germany' WHERE student_id IN (3, 4)";
                Statement updateStatement = conn.createStatement();
                updateStatement.executeUpdate(updateItalySql);
                updateStatement.executeUpdate(updateGermanySql);

                System.out.println("La colonna 'country' è stata aggiunta e popolata con successo.");
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
