import java.sql.*;

public class OrdersAndPrice {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nutrizione";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(url, user, password);

            // Query 1: Prezzo massimo di un pasto con più di 500 calorie
            String query1 = "SELECT MAX(Prezzo) AS MaxPrezzo " +
                    "FROM Pasti " +
                    "WHERE Calorie > 500";
            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double maxPrice = resultSet.getDouble("MaxPrezzo");
                System.out.println("Prezzo massimo di un pasto con più di 500 calorie: €" + maxPrice);
            }

            // Query 2: Calorie medie dei pasti che costano più di 15 EUR
            String query2 = "SELECT AVG(Calorie) AS CalorieMedie " +
                    "FROM Pasti " +
                    "WHERE Prezzo > 15";
            preparedStatement = connection.prepareStatement(query2);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double avgCalories = resultSet.getDouble("CalorieMedie");
                System.out.println("Calorie medie dei pasti che costano più di 15 EUR: " + avgCalories);
            }

            // Query 3: Pasti con prezzo massimo usando HAVING e MAX()
            String query3 = "SELECT * " +
                    "FROM Pasti " +
                    "HAVING Prezzo = (SELECT MAX(Prezzo) FROM Pasti)";
            preparedStatement = connection.prepareStatement(query3);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Pasti con prezzo massimo:");
            while (resultSet.next()) {
                String nomePasto = resultSet.getString("Nome");
                double prezzoPasto = resultSet.getDouble("Prezzo");
                System.out.println(nomePasto + ": €" + prezzoPasto);
            }

            // Query 4: Pasti con prezzo inferiore al prezzo medio usando HAVING e AVG
            String query4 = "SELECT * " +
                    "FROM Pasti " +
                    "HAVING Prezzo < (SELECT AVG(Prezzo) FROM Pasti)";
            preparedStatement = connection.prepareStatement(query4);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Pasti con prezzo inferiore al prezzo medio:");
            while (resultSet.next()) {
                String nomePasto = resultSet.getString("Nome");
                double prezzoPasto = resultSet.getDouble("Prezzo");
                System.out.println(nomePasto + ": €" + prezzoPasto);
            }

            // Query 5: Pasti con prezzo inferiore al prezzo medio e più di 600 calorie
            String query5 = "SELECT * " +
                    "FROM Pasti " +
                    "WHERE Prezzo < (SELECT AVG(Prezzo) FROM Pasti) " +
                    "AND Calorie > 600";
            preparedStatement = connection.prepareStatement(query5);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Pasti con prezzo inferiore al prezzo medio e più di 600 calorie:");
            while (resultSet.next()) {
                String nomePasto = resultSet.getString("Nome");
                double prezzoPasto = resultSet.getDouble("Prezzo");
                int caloriePasto = resultSet.getInt("Calorie");
                System.out.println(nomePasto + ": €" + prezzoPasto + ", Calorie: " + caloriePasto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

