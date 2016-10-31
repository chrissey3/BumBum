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
public class CreateTableExample {
    
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
            String database = "postgres";
            String username = "postgres";
            String password = "lerager1";
            
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            //could have also written this instead of the above:
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_database", "test_user", "test_password");
            
            // A statement-object is created. This is used to execute
            // queries and updates on the database.
            Statement stmt = conn.createStatement();
            
            // This string contains SQL code to create a new table.
            String sql_createTable =
                    "CREATE TABLE Customer(" +
                    "   id        SERIAL   PRIMARY KEY      NOT NULL," +
                    "   firstname CHAR(50)                  NOT NULL," +
                    "   lastname  CHAR(50)                  NOT NULL," +
                    "   city      CHAR(50)                  NOT NULL," +
                    "   phone     CHAR(50)                  NOT NULL," +
                    "   email     CHAR(50)                  NOT NULL" +
                    ");";
            
            // execute the above SQL code
            stmt.executeUpdate(sql_createTable);
            
            // This string contains SQL code to insert some rows into the
            // previously created table.
            String sql_insertRows = 
                    "INSERT INTO Customer(firstname, lastname, city, phone, email)" +
                    "VALUES " +
                    "('Lars', 'Larsen', 'Roskilde', '12341295', 'lars@larsen.dk')," +
                    "('Peter', 'Pedersen', 'Naestved', '65432392', 'peter@petersen.dk')," +
                    "('Anne', 'Hansen', 'Slagelse', '95392743', 'anne@hansen.dk')," +
                    "('Mette', 'Joergensen', 'Koebenhavn', '89590923', 'mette@joergensen.dk');";
            
            // execute the above SQL code
            stmt.executeUpdate(sql_insertRows);
            
            // Close Statement and Connection when we're done.
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
