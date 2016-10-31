/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.sql.*;

/**
 *
 * @author S. Metzger
 */
public class QueryExample {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            // Driver is loaded - postgreSQL JDBC Driver needs to be
            // added under libraries for this to work
            Class.forName("org.postgresql.Driver");
            
            // A connection-object is created with host, port, databasename, username and password
            String host = "localhost";
            String port = "5432";
            String database = "test_database";
            String username = "test_user";
            String password = "test_password";
            
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            //could have also written this instead of the above:
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_database", "test_user", "test_password");
            
            // A statement-object is created. This is used to execute
            // queries and updates on the database.
            Statement stmt = conn.createStatement();

            //A ResultSet is created from an executed query 
            String sql = "SELECT * FROM Customer";
            ResultSet rs = stmt.executeQuery(sql);
            
            // Loop over the ResultSet and print the 2nd column:
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
            
            /// Close ResultSet, Statement and Connection when we're done.
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " + ex.getMessage());
            System.out.println("Remember to add postgreSQL JDBC Driver Library! (Use Add Library...)");
        } catch (SQLException ex) {
            System.out.println("SQL ERROR: " + ex.getMessage());
        }
    }
    
}
