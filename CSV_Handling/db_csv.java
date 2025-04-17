package org.example;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class db_csv {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";
        String csvFilePath = "employees.csv";

        String query = "SELECT id, name, department, salary FROM employees";

        try (
                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))
        ) {
            // Write CSV header
            String[] header = { "Employee ID", "Name", "Department", "Salary" };
            writer.writeNext(header);

            // Write rows from result set
            while (resultSet.next()) {
                String[] row = {
                        String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        String.valueOf(resultSet.getDouble("salary"))
                };
                writer.writeNext(row);
            }

            System.out.println("CSV file written successfully to: " + csvFilePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
