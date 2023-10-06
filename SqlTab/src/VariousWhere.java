import java.sql.*;

public class VariousWhere {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String selectLowPriceMeal = "SELECT * FROM Food WHERE PRICE < 15.00";
            ResultSet lowPriceMealsResult = statement.executeQuery(selectLowPriceMeal);
            System.out.println("Meals with price < €15:");
            printResultSet(lowPriceMealsResult);

            String selectVegetarianFood = "SELECT * FROM Food WHERE CATEGORY = 'VEGETARIAN'";
            ResultSet vegetarianFoodResult = statement.executeQuery(selectVegetarianFood);
            System.out.println("\nVegetarian Ingredients:");
            printResultSet(vegetarianFoodResult);

            String selectCalorieMeal = "SELECT * FROM Food WHERE CALORIES > 500";
            ResultSet calorieMeal = statement.executeQuery(selectCalorieMeal);
            System.out.println("\nMeals with more than 500 calories:");
            printResultSet(calorieMeal);

            String betweenMeals = "SELECT * FROM Food WHERE PRICE BETWEEN 15.0 AND 16.0";
            ResultSet betweenMealsResult = statement.executeQuery(betweenMeals);
            System.out.println("Meals with price between €15 and €16:");
            printResultSet(betweenMealsResult);

            String lactoseAndGluten = "SELECT * FROM Food WHERE LACTOSE_FREE = true AND GLUTEN_FREE = true";
            ResultSet lactoseAndGlutenResult = statement.executeQuery(lactoseAndGluten);
            System.out.println("\nLactose-free and Gluten-free Ingredients:");
            printResultSet(lactoseAndGlutenResult);

            String lessAndMorePriceCalories = "SELECT * FROM Food WHERE PRICE < 15.00 OR CALORIES > 600 ";
            ResultSet lessAndMorePriceCaloriesResult = statement.executeQuery(lessAndMorePriceCalories);
            System.out.println("\nMeals with price < €15 or calories > 600:");
            printResultSet(lessAndMorePriceCaloriesResult);

            String veganOrGluten = "SELECT * FROM Food WHERE VEGAN = TRUE OR GLUTEN_FREE = TRUE";
            ResultSet veganOrGlutenResult = statement.executeQuery(veganOrGluten);
            System.out.println("\nVegan or Gluten-free Ingredients:");
            printResultSet(veganOrGlutenResult);

            String pestoPastaMeals = "SELECT * FROM Food WHERE NAME = 'PESTO PASTA'";
            ResultSet pestoPastaMealsResult = statement.executeQuery(pestoPastaMeals);
            System.out.println("/n of pesto pasta:");
            printResultSet(pestoPastaMealsResult);

            String notLactoseIngredient = "SELECT * FROM Food WHERE LACTOSE_FREE = FALSE";
            ResultSet notLactoseIngredientResult = statement.executeQuery(notLactoseIngredient);
            System.out.println("n/ of non lactose ingredient:");
            printResultSet(notLactoseIngredientResult);

            String highPriceLowCalories = "SELECT * FROM Food WHERE PRICE > 20.00 OR CALORIES < 800";
            ResultSet highPriceLowCaloriesResult = statement.executeQuery(highPriceLowCalories);
            System.out.println("\nMeals with price > €20 or calories < 800:");
            printResultSet(highPriceLowCaloriesResult);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void printResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("NAME");
            double price = resultSet.getDouble("PRICE");
            int calories = resultSet.getInt("CALORIES");
            System.out.println("Meal: " + name + ", Price: €" + price + ", Calories: " + calories);
        }
    }
}
