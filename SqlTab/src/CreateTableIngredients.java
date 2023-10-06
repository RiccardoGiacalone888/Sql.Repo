import java.sql.*;

public class CreateTableIngredients {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String createTableSQL =
                    "CREATE TABLE Food (" +
                            "NAME VARCHAR(255)," +
                            "PRICE DOUBLE," +
                            "CALORIES INT," +
                            "GLUTEN_FREE BOOLEAN);";
            statement.execute(createTableSQL);
            System.out.println("Tabella Food Creata.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
