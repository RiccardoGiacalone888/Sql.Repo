import java.sql.*;
public class pKeys {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Query 1: Trova il nome di tutti i pasti che contengono aglio.
            String query1 = "SELECT DISTINCT M.NAME " +
                    "FROM MEAL M " +
                    "INNER JOIN INGREDIENT I ON M.MEAL_ID = I.MEAL_ID " +
                    "WHERE I.NAME = 'garlic'";
            executeAndPrintQuery(connection, query1, "Pasti con aglio:");

            // Query 2: Trova il numero di pasti con ingredienti vegetariani.
            String query2 = "SELECT COUNT(DISTINCT M.MEAL_ID) " +
                    "FROM MEAL M " +
                    "INNER JOIN INGREDIENT I ON M.MEAL_ID = I.MEAL_ID " +
                    "WHERE I.IS_VEGETARIAN = 1";
            executeAndPrintQuery(connection, query2, "Numero di pasti vegetariani:");

            // Query 3: Trova i nomi dei pasti con almeno 1 ingrediente senza glutine e almeno 1 ingrediente vegano.
            String query3 = "SELECT DISTINCT M.NAME " +
                    "FROM MEAL M " +
                    "INNER JOIN INGREDIENT I ON M.MEAL_ID = I.MEAL_ID " +
                    "WHERE (I.IS_GLUTEN_FREE = 1 AND I.IS_VEGAN = 1)";
            executeAndPrintQuery(connection, query3, "Pasti con ingredienti senza glutine e vegani:");

            // Query 4: Trova la media del numero di ingredienti per pasto.
            String query4 = "SELECT AVG(ingredient_count) " +
                    "FROM (SELECT M.MEAL_ID, COUNT(I.INGREDIENT_ID) AS ingredient_count " +
                    "      FROM MEAL M " +
                    "      LEFT JOIN INGREDIENT I ON M.MEAL_ID = I.MEAL_ID " +
                    "      GROUP BY M.MEAL_ID) AS T";
            executeAndPrintQuery(connection, query4, "Media del numero di ingredienti per pasto:");

            // Query 5: Trova i nomi dei pasti con più di 3 ingredienti.
            String query5 = "SELECT M.NAME " +
                    "FROM MEAL M " +
                    "LEFT JOIN INGREDIENT I ON M.MEAL_ID = I.MEAL_ID " +
                    "GROUP BY M.NAME " +
                    "HAVING COUNT(I.INGREDIENT_ID) > 3";
            executeAndPrintQuery(connection, query5, "Pasti con più di 3 ingredienti:");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeAndPrintQuery(Connection connection, String query, String message) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(message);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            System.out.println();
        }
    }
}
