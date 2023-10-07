import java.sql.*;
public class MealsAndOrders {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nutrizione";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(url, user, password);

            // Numero di ordini per ogni pasto
            String query1 = "SELECT p.Nome AS NomePasto, COUNT(o.IDOrdine) AS NumeroOrdini " +
                    "FROM Pasti p " +
                    "LEFT JOIN Ordini o ON p.IDPasto = o.IDPasto " +
                    "GROUP BY p.IDPasto, p.Nome";

            // Ricavo totale per pasti con prezzo > €15
            String query2 = "SELECT SUM(p.Prezzo) AS RicavoTotale " +
                    "FROM Pasti p " +
                    "WHERE p.Prezzo > 15";

            // Numero di pasti con > 500 calorie
            String query3 = "SELECT COUNT(*) AS NumeroPasti " +
                    "FROM Pasti p " +
                    "WHERE p.Calorie > 500";

            // Totale prezzo per pasti con nome che contiene 'S'
            String query4 = "SELECT SUM(p.Prezzo) AS TotalePrezzo " +
                    "FROM Pasti p " +
                    "WHERE LOWER(p.Nome) LIKE '%s%'";

            // Numero di ordini per pasti ordinati prima di una data specifica
            String query5 = "SELECT p.Nome AS NomePasto, COUNT(o.IDOrdine) AS NumeroOrdini " +
                    "FROM Pasti p " +
                    "LEFT JOIN Ordini o ON p.IDPasto = o.IDPasto " +
                    "WHERE o.DataOrdine < '2023-10-01 18:00:00' " +
                    "GROUP BY p.IDPasto, p.Nome";

            // Esecuzione delle query e stampa dei risultati

            // Esegui la query 1
            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Numero di ordini per ogni pasto:");
            while (resultSet.next()) {
                String nomePasto = resultSet.getString("NomePasto");
                int numeroOrdini = resultSet.getInt("NumeroOrdini");
                System.out.println(nomePasto + ": " + numeroOrdini + " ordini");
            }

            // Chiudi il result set
            resultSet.close();

            // Esegui la query 2
            preparedStatement = connection.prepareStatement(query2);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            double ricavoTotale = resultSet.getDouble("RicavoTotale");
            System.out.println("Ricavo totale per pasti con prezzo > €15: €" + ricavoTotale);

            // Chiudi il result set
            resultSet.close();

            // Esegui la query 3
            preparedStatement = connection.prepareStatement(query3);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int numeroPasti = resultSet.getInt("NumeroPasti");
            System.out.println("Numero di pasti con > 500 calorie: " + numeroPasti);

            // Chiudi il result set
            resultSet.close();

            // Esegui la query 4
            preparedStatement = connection.prepareStatement(query4);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            double totalePrezzo = resultSet.getDouble("TotalePrezzo");
            System.out.println("Totale prezzo per pasti con nome che contiene 'S': €" + totalePrezzo);

            // Chiudi il result set
            resultSet.close();

            // Esegui la query 5
            preparedStatement = connection.prepareStatement(query5);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Numero di ordini per pasti ordinati prima di '2023-10-01 18:00:00':");
            while (resultSet.next()) {
                String nomePasto = resultSet.getString("NomePasto");
                int numeroOrdini = resultSet.getInt("NumeroOrdini");
                System.out.println(nomePasto + ": " + numeroOrdini + " ordini");
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
