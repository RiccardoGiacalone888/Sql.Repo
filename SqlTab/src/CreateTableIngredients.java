import java.sql.*;
public class CreateTableIngredients {
    public static void main(String[] args) {
        String url= "jdbc:mysql://localhost:3306/ingredients";
        String user= "root";
        String password= "Eleonora1992Riccardo1988ZeldaMalu!";

        try {
            Connection connection=DriverManager.getConnection(url,user,password);
            Statement statement=connection.createStatement();
            String createTableSQL=
                    "CREATE TABLE Ingredient (" +
                            "IngredientId INT PRIMARY KEY," +
                            "IngredientName VARCHAR(255)," +
                            "Category VARCHAR(50)," +
                            "ExpirationDate DATE," +
                            "Quantity INT," +
                            "UnitOfMeasure VARCHAR(20));";
            statement.execute(createTableSQL);
            System.out.println("Tabella Ingredient Creata.");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
