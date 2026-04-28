package org.example;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        DBConnection db = new DBConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "Aa14942725"
        );

        Map<String, String> koloneTip = new LinkedHashMap<>();
        koloneTip.put("D_ID", "SERIAL PRIMARY KEY");
        koloneTip.put("D_EMRI", "VARCHAR(50)");
        koloneTip.put("D_EMAIL", "VARCHAR(100)");
        koloneTip.put("D_TEL", "VARCHAR(20)");

        createTable(db, koloneTip, "DEPARTMENT");

        Student student = new Student(null, "Bora", "bora@gmail.com", "2001-05-10", "0691111111", 95);

        insertStudent(db, student);

        getStudentById(db, 1);

        Student updatedStudent = new Student(5, "Ana", "ana@gmail.com", "2000-03-15", "0692222222", 80);

        updateStudentById(db, 5, updatedStudent);

        deleteStudentById(db, 1);

        dropTable(db, "DEPARTMENT");
    }

    public static void createTable(DBConnection db, Map<String, String> koloneTip, String tableName) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(tableName).append(" (");

        int count = 0;
        for (Map.Entry<String, String> entry : koloneTip.entrySet()) {
            sql.append(entry.getKey()).append(" ").append(entry.getValue());

            count++;
            if (count < koloneTip.size()) {
                sql.append(", ");
            }
        }

        sql.append(")");

        try (Connection con = db.connect();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql.toString());
            System.out.println("Table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable(DBConnection db, String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;

        try (Connection con = db.connect();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Table deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertStudent(DBConnection db, Student student) {
        String sql = "INSERT INTO STUDENT (EMRI, EMAIL, BIRTHDATE, NR_TELEFONIT, PIKE) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getEmri());
            ps.setString(2, student.getEmail());
            ps.setDate(3, Date.valueOf(student.getBirthdate()));
            ps.setString(4, student.getNrTelefonit());
            ps.setInt(5, student.getPike());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void getStudentById(DBConnection db, Integer id) {
        if (id == null) {
            System.out.println("ID cannot be null.");
            return;
        }

        String sql = "SELECT * FROM STUDENT WHERE ID = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("ID") + " - " +
                                rs.getString("EMRI") + " - " +
                                rs.getString("EMAIL") + " - " +
                                rs.getDate("BIRTHDATE") + " - " +
                                rs.getString("NR_TELEFONIT") + " - " +
                                rs.getInt("PIKE")
                );
            } else {
                System.out.println("Student with ID " + id + " does not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int updateStudentById(DBConnection db, Integer id, Student student) {
        if (id == null) {
            System.out.println("ID cannot be null.");
            return 0;
        }

        if (!studentExists(db, id)) {
            System.out.println("Student with ID " + id + " does not exist.");
            return 0;
        }

        String sql = "UPDATE STUDENT SET EMRI = ?, EMAIL = ?, BIRTHDATE = ?, NR_TELEFONIT = ?, PIKE = ? WHERE ID = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getEmri());
            ps.setString(2, student.getEmail());
            ps.setDate(3, Date.valueOf(student.getBirthdate()));
            ps.setString(4, student.getNrTelefonit());
            ps.setInt(5, student.getPike());
            ps.setInt(6, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteStudentById(DBConnection db, Integer id) {
        if (id == null) {
            System.out.println("ID cannot be null.");
            return 0;
        }

        if (!studentExists(db, id)) {
            System.out.println("Student with ID " + id + " does not exist.");
            return 0;
        }

        String sql = "DELETE FROM STUDENT WHERE ID = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean studentExists(DBConnection db, Integer id) {
        String sql = "SELECT ID FROM STUDENT WHERE ID = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}