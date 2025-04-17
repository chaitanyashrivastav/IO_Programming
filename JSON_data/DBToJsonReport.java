package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.sql.*;

public class DBToJsonReport {
    public static void main(String[] args) throws Exception {
        // 1. Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "username", "password");

        // 2. Execute a SQL query
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, name, age FROM employees");

        // 3. Convert ResultSet to JSON array
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            ObjectNode row = mapper.createObjectNode();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getString(i));
            }
            jsonArray.add(row);
        }

        // 4. Write JSON to file
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/employee_report.json"), jsonArray);

        System.out.println("JSON report generated: employee_report.json");

        // 5. Cleanup
        rs.close();
        stmt.close();
        conn.close();
    }
}
