import java.sql.*;

public class DatabaseOperation {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ingredienti";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            //Creazione tabella Cheap Meals.
            String createTableCheapMeals = "CREATE TABLE CHEAP_MEALS AS " +
                    "SELECT * FROM Food <15.00";
            statement.execute(createTableCheapMeals);
            //Inserimento ingradienti vegani in una nuova tabella Vegan Ingredient.
            String createTableIngredientVegan= "INSERT INTO VEGAN_INGREDIENT (NAME, CATEGORY) " +
                    "SELECT NAME, CATEGORY FROM Food WHERE VEGAN=1 ";
            statement.execute(createTableIngredientVegan);
            //creazione vista con pasti superiori i 15£.
            String createVistaFroMeals = "CREATE VIEW EXPANSIVE_MEAL" +
                    "SELECT * FROM Food WHERE PRICE > 15.00";
            statement.execute(createVistaFroMeals);
            //creazione vista per ingredienti senza lattosio.
            String freeLactoseView = "CREATE VIEW LACTOSE_FREE" +
                    "SELECT * FROM Food WHERE LACTOSE_FREE = 1";
            statement.execute(freeLactoseView);
            //creazione tabella pasti con calorie superiori a 650.
            String createTableHighCalories = "CREATE TABLE HIGH_CALORIE_MEAL AS" +
                    "SELECT * FROM Food WHERE CALORIES > 650";
            statement.execute(createTableHighCalories);

            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
