import java.sql.*;

public class Customer{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/clienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Creazione della tabella "Customers" con una chiave primaria auto-incrementante
            String createTableQuery = "CREATE TABLE Customers (" +
                    "customer_id INT NOT NULL AUTO_INCREMENT," +
                    "first_name VARCHAR(255) NOT NULL," +
                    "last_name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (customer_id))";
            statement.executeUpdate(createTableQuery);

            System.out.println("Tabella Customers creata con successo.");

            // Inserimento di alcuni clienti
            String insertCustomerQuery1 = "INSERT INTO Customers (first_name, last_name, email) " +
                    "VALUES ('John', 'Doe', 'john.doe@example.com')";
            statement.executeUpdate(insertCustomerQuery1);

            String insertCustomerQuery2 = "INSERT INTO Customers (first_name, last_name, email) " +
                    "VALUES ('Jane', 'Smith', 'jane.smith@example.com')";
            statement.executeUpdate(insertCustomerQuery2);

            System.out.println("Clienti inseriti con successo.");

            // Aggiornamento di un cliente con WHERE che fa riferimento all'ID
            String updateCustomerQuery = "UPDATE Customers " +
                    "SET email = 'new_email@example.com' " +
                    "WHERE customer_id = 1";
            statement.executeUpdate(updateCustomerQuery);

            System.out.println("Cliente aggiornato con successo.");

            // Eliminazione di un cliente con WHERE che fa riferimento all'ID
            String deleteCustomerQuery = "DELETE FROM Customers WHERE customer_id = 1";
            statement.executeUpdate(deleteCustomerQuery);

            System.out.println("Cliente eliminato con successo.");

            // Eliminazione di tutti i clienti utilizzando l'istruzione TRUNCATE
            String truncateCustomersQuery = "TRUNCATE TABLE Customers";
            statement.executeUpdate(truncateCustomersQuery);

            System.out.println("Tutti i clienti eliminati con successo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
