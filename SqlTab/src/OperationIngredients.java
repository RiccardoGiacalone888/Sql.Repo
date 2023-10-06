import java.sql.*;
public class OperationIngredients {

        public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // 1. Insert statements per inserire gli ingredienti
            String[] ingredients = {"Eggs", "Bread", "Beef Patty", "Lettuce", "Yoghurt"};

            for (String ingredient : ingredients) {
                String insertSQL = "INSERT INTO Food (NAME) VALUES ('" + ingredient + "')";
                statement.execute(insertSQL);
                System.out.println("Inserito: " + ingredient);
            }

            // 2. Select statement per selezionare il NAME di tutti gli ingredienti
            String selectNamesSQL = "SELECT NAME FROM Food";
            ResultSet namesResultSet = statement.executeQuery(selectNamesSQL);

            while (namesResultSet.next()) {
                String ingredientName = namesResultSet.getString("NAME");
                System.out.println("Nome: " + ingredientName);
            }

            // 3. Select statement per selezionare il NAME e il PRICE degli ingredienti
            String selectNameAndPriceSQL = "SELECT NAME, PRICE FROM Food";
            ResultSet nameAndPriceResultSet = statement.executeQuery(selectNameAndPriceSQL);

            while (nameAndPriceResultSet.next()) {
                String ingredientName = nameAndPriceResultSet.getString("NAME");
                double price = nameAndPriceResultSet.getDouble("PRICE");
                System.out.println("Nome: " + ingredientName + ", Prezzo: " + price);
            }

            // 4. Select statement per selezionare tutte le colonne degli ingredienti, ordinati per NAME
            String selectAllOrderedByNameSQL = "SELECT * FROM Food ORDER BY NAME";
            ResultSet allOrderedByNameResultSet = statement.executeQuery(selectAllOrderedByNameSQL);

            while (allOrderedByNameResultSet.next()) {
                String ingredientName = allOrderedByNameResultSet.getString("NAME");
                double price = allOrderedByNameResultSet.getDouble("PRICE");
                int calories = allOrderedByNameResultSet.getInt("CALORIES");
                System.out.println("Nome: " + ingredientName + ", Prezzo: " + price + ", Calorie: " + calories);
            }

            // 5. Select statement per selezionare tutte le colonne degli ingredienti, ordinati per "GLUTEN_FREE" con gli elementi "true" in cima
            String selectAllOrderedByGlutenFreeSQL = "SELECT * FROM Food ORDER BY GLUTEN_FREE DESC";
            ResultSet allOrderedByGlutenFreeResultSet = statement.executeQuery(selectAllOrderedByGlutenFreeSQL);

            while (allOrderedByGlutenFreeResultSet.next()) {
                String ingredientName = allOrderedByGlutenFreeResultSet.getString("NAME");
                double price = allOrderedByGlutenFreeResultSet.getDouble("PRICE");
                int calories = allOrderedByGlutenFreeResultSet.getInt("CALORIES");
                boolean glutenFree = allOrderedByGlutenFreeResultSet.getBoolean("GLUTEN_FREE");
                System.out.println("Nome: " + ingredientName + ", Prezzo: " + price + ", Calorie: " + calories + ", Senza glutine: " + glutenFree);
            }

            connection.setAutoCommit(true);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

