import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataTimestamp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/birthday";
        String user = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(url, user, password);
            //Concat unisce le colonne e le assegna ad un risultato.
            String query1 = "SELECT CONCAT(NAME, BIRTHDAY_DATE) AS CONCATENATED_COLUMNS FROM COMPLEANNO";
            //Lower converte i caratteri in minuscolo.
            String query2 = "SELECT LOWER(NAME) AS LOWERCASE_COLUMN FROM COMPLEANNO";
            //Upper converte i caratteri in maiuscolo.
            String query3 = "SELECT UPPER(NAME) AS UPPERCASE_COLUMN FROM COMPLEANNO";
            //Trim rimuove gli spazi vuoti.
            String query4 = "SELECT TRIM(BIRTHDAY_DATE) AS TRIMMED_COLUMN FROM COMPLEANNO";
            //Lenght restituisce la lungehzza della colonna in caratteri.
            String query5 = "SELECT LENGTH(NAME) AS COLUMN_LENGTH FROM COMPLEANNO";
            //Substring estrae una sottostringa da una colonna iniziando adlla posizione 1 con una lunghezza 3.
            String query6 = "SELECT SUBSTRING(BIRTHDAY_DATE, 1, 3) AS SUBSTRING_COLUMN FROM COMPLEANNO";
            //Round arrotonda il numero della colonna ad un numero specifico di posizioni decimali.
            String query7 = "SELECT ROUND(3.14159265, 2) AS ROUNDED_COLUMN FROM COMPLEANNO";
            //Data_Add aggiunge un intervello alla data nella colonna.
            String query8 = "SELECT DATE_ADD(BIRTHDAY_DATE, INTERVAL 5 DAY) AS NEXT_WEEK, " +
                    "DATE_ADD(NEXT_WEEK, INTERVAL 11 MONTH) AS BIRTHDAY_DATE FROM COMPLEANNO";
            //Data_sub sottrae un intervello.
            String query9 = "SELECT DATE_SUB(BIRTHDAY_DATE, INTERVAL 10 DAY) AS MINUS_10_DAYS FROM COMPLEANNO";
            //Format formatta la colonna .
            String query10 = "SELECT FORMAT(BIRTHDAY_DATE, 'dd/MM/yyyy') AS FORMATTED_DATE FROM COMPLEANNO";

            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String result = resultSet.getString("CONCATENATED_COLUMNS");
                System.out.println("CONCATENATED_COLUMNS: " + result);
            }

            // Esegui le altre query in modo simile

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

