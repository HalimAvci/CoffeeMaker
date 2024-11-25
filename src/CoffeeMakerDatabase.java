import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class CoffeeMakerDatabase {
    private Connection connection;

    public CoffeeMakerDatabase(){
        try {
            // Establish a connection to the MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SE3313_20070006009?user=root&password=41135061528");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method to insert data into the database
    public void insertData(int cups, Date currentDate){
        try {
            // SQL query to insert data into the 'cups_history' table
            String query = "INSERT INTO cups_history (cups, date, monthly_total) VALUES (?, ?, ?)";
            // Use try-with-resources to manage the PreparedStatement and automatically close it
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                // Extract month and year from the current table
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);

                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                // Set parameters in the PreparedStatement
                preparedStatement.setInt(1, cups);
                preparedStatement.setDate(2, new java.sql.Date(currentDate.getTime()));
                preparedStatement.setInt(3, getMonthlyTotal(month, year) + cups);
                // Execute the SQL update
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method to get the total cups for a specific month and year from the database
    private int getMonthlyTotal(int month, int year){
        try {
            // SQL query to get the sum of cups for a specific month and year
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT SUM(cups) AS total FROM cups_history WHERE MONTH(date) =? AND YEAR(date) =?"
            );
                // Set parameters in the PreparedStatement
                preparedStatement.setInt(1, month);
                preparedStatement.setInt(2, year);
                // Execute the SQL query
                ResultSet resultSet = preparedStatement.executeQuery();
                // If there is a result, return the total cups; otherwise, return 0
                if (resultSet.next()){
                    return resultSet.getInt("total");
                }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
