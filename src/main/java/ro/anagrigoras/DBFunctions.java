package ro.anagrigoras;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBFunctions {

    private Connection con;

    public Connection getCon() {
        return con;
    }

    public Connection connect_to_db() {
        con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", "postgres", "postgres");
            if (con != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }


}