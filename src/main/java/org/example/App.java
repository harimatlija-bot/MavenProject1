package org.example;

import java.sql.*;
import java.util.Properties;

public class App {
    public static void main( String[] args ) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        final Properties props = new Properties();
        String user = "postgres";
        String password = "Aa14942725";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Statement statement1 = con.createStatement();
            ResultSet resTable1 = statement1.executeQuery("SELECT * FROM STUDENT");
            while (resTable1.next()){
                System.out.println(resTable1.getInt("ID") + " - " + resTable1.getString("EMRI") + " - " + resTable1.getString("EMAIL") + " - " + resTable1.getDate("BIRTHDATE")+ " - " + resTable1.getString("NR_TELEFONIT")+ " - " + resTable1.getInt("PIKE"));
            }
            ResultSet resKursi = statement1.executeQuery("SELECT * FROM KURSI");
            while (resKursi.next()){
                System.out.println("\n" + resKursi.getInt("ID") + " - " + resKursi.getString("EMRI_KURSIT") + " - " + resKursi.getString("KOHEZGJATJA") + " - " + resKursi.getDate("CREATION_DATE")+ " - " + resKursi.getDate("UPDATE_DATE")+ " - " + resKursi.getString("PROGRAMMING_LANGUAGE"));
            }

            ResultSet resBridge = statement1.executeQuery("SELECT * FROM STUDENT_KURSI");
            while (resBridge.next()){
                System.out.println(resBridge.getInt("STUDENT_ID") + " - " + resBridge.getInt("KURSI_ID") + " - " + resBridge.getDate("REGISTRATION_DT"));
            }
        } catch(SQLException e) {
        e.printStackTrace();
        }
    }
}
