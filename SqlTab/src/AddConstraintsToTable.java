import java.sql.*;
public class AddConstraintsToTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Aggiungi il vincolo NOT NULL alla colonna "NAME"
            String sqlNotNull = "ALTER TABLE food MODIFY COLUMN NAME VARCHAR(255) NOT NULL";
            statement.executeUpdate(sqlNotNull);
            System.out.println("Vincolo NOT NULL aggiunto alla colonna NAME");

            // Aggiungi il vincolo UNIQUE alla colonna "CATEGORY"
            String sqlUnique = "ALTER TABLE food ADD CONSTRAINT UC_CATEGORY UNIQUE (CATEGORY)";
            statement.executeUpdate(sqlUnique);
            System.out.println("Vincolo UNIQUE aggiunto alla colonna CATEGORY");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
