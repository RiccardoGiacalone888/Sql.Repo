import java.sql.*;

public class joins {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/fiveJoins";
        String username = "root";
        String password = "Eleonora1992Riccardo1988ZeldaMalu!";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement statement = connection.createStatement();

            // Inner Join
            String innerJoinSQL = "SELECT * FROM table1 INNER JOIN table2 ON table1.id = table2.id";
            ResultSet innerJoinResult = statement.executeQuery(innerJoinSQL);
            while (innerJoinResult.next()) {
                int id = innerJoinResult.getInt("id");
                String name = innerJoinResult.getString("name");
                String description = innerJoinResult.getString("description");
                System.out.println("Inner Join Result: ID = " + id + ", Name = " + name + ", Description = " + description);
            }
            innerJoinResult.close();

            // Left Join
            String leftJoinSQL = "SELECT * FROM table1 LEFT JOIN table2 ON table1.id = table2.id";
            ResultSet leftJoinResult = statement.executeQuery(leftJoinSQL);
            while (leftJoinResult.next()) {
                int id = leftJoinResult.getInt("id");
                String name = leftJoinResult.getString("name");
                String description = leftJoinResult.getString("description");
                System.out.println("Left Join Result: ID = " + id + ", Name = " + name + ", Description = " + description);
            }
            leftJoinResult.close();

            // Right Join
            String rightJoinSQL = "SELECT * FROM table1 RIGHT JOIN table2 ON table1.id = table2.id";
            ResultSet rightJoinResult = statement.executeQuery(rightJoinSQL);
            while (rightJoinResult.next()) {
                int id = rightJoinResult.getInt("id");
                String name = rightJoinResult.getString("name");
                String description = rightJoinResult.getString("description");
                System.out.println("Right Join Result: ID = " + id + ", Name = " + name + ", Description = " + description);
            }
            rightJoinResult.close();

            // Full Outer Join
            String fullOuterJoinSQL = "SELECT * FROM table1 LEFT JOIN table2 ON table1.id = table2.id "
                    + "UNION "
                    + "SELECT * FROM table1 RIGHT JOIN table2 ON table1.id = table2.id";
            ResultSet fullOuterJoinResult = statement.executeQuery(fullOuterJoinSQL);
            while (fullOuterJoinResult.next()) {
                int id = fullOuterJoinResult.getInt("id");
                String name = fullOuterJoinResult.getString("name");
                String description = fullOuterJoinResult.getString("description");
                System.out.println("Full Outer Join Result: ID = " + id + ", Name = " + name + ", Description = " + description);
            }
            fullOuterJoinResult.close();

            // Self Join
            String selfJoinSQL = "SELECT a.name AS name1, b.name AS name2 FROM table1 a, table1 b WHERE a.id = b.id";
            ResultSet selfJoinResult = statement.executeQuery(selfJoinSQL);
            while (selfJoinResult.next()) {
                String name1 = selfJoinResult.getString("name1");
                String name2 = selfJoinResult.getString("name2");
                System.out.println("Self Join Result: Name1 = " + name1 + ", Name2 = " + name2);
            }
            selfJoinResult.close();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



