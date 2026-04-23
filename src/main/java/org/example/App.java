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
            System.out.println("-----Student-----");
            ResultSet resTable1 = statement1.executeQuery("SELECT * FROM STUDENT");
            while (resTable1.next()){
                System.out.println(resTable1.getInt("ID") + " - " + resTable1.getString("EMRI") + " - " + resTable1.getString("EMAIL") + " - " + resTable1.getDate("BIRTHDATE")+ " - " + resTable1.getString("NR_TELEFONIT")+ " - " + resTable1.getInt("PIKE"));
            }
            System.out.println("\n----KURSI-------");
            ResultSet resKursi = statement1.executeQuery("SELECT * FROM KURSI");
            while (resKursi.next()){
                System.out.println(resKursi.getInt("ID") + " - " + resKursi.getString("EMRI_KURSIT") + " - " + resKursi.getString("KOHEZGJATJA") + " - " + resKursi.getDate("CREATION_DATE")+ " - " + resKursi.getDate("UPDATE_DATE")+ " - " + resKursi.getString("PROGRAMMING_LANGUAGE"));
            }
            //new commit
            ResultSet resBridge = statement1.executeQuery("SELECT * FROM STUDENT_KURSI");
            while (resBridge.next()){
                System.out.println(resBridge.getInt("STUDENT_ID") + " - " + resBridge.getInt("KURSI_ID") + " - " + resBridge.getDate("REGISTRATION_DT"));
            }
            System.out.println("\n----------------------");
            ResultSet resStudent = statement1.executeQuery("SELECT * FROM STUDENT WHERE PIKE> 10");
            while (resStudent.next()){
                System.out.println(resStudent.getInt("ID") + " - " + resStudent.getString("EMRI") + " - " + resStudent.getString("EMAIL") + " - " + resStudent.getDate("BIRTHDATE")+ " - " + resStudent.getString("NR_TELEFONIT")+ " - " + resStudent.getInt("PIKE"));
            }

            int insertRow = statement1.executeUpdate("INSERT INTO STUDENT (EMRI, EMAIL, BIRTHDATE, NR_TELEFONIT, PIKE)" +
                    "VALUES ('ANNA', 'anna@gmail.com', '2000-05-10', '0691111111', 90)");
            System.out.println("\n" + insertRow + " Values inserted");

            int updatePike = statement1.executeUpdate("UPDATE STUDENT SET PIKE = 50 WHERE ID = 9");
            System.out.println("\n" + updatePike + " Value updated");

            int rowDelete = statement1.executeUpdate("DELETE FROM STUDENT WHERE ID = 33");
            System.out.println("\n" + rowDelete + " Row 33 deleted");

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
