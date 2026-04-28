package org.example;

public class Student {
    private Integer id;
    private String emri;
    private String email;
    private String birthdate;
    private String nrTelefonit;
    private int pike;

    public Student(Integer id, String emri, String email, String birthdate, String nrTelefonit, int pike) {
        this.id = id;
        this.emri = emri;
        this.email = email;
        this.birthdate = birthdate;
        this.nrTelefonit = nrTelefonit;
        this.pike = pike;
    }

    public Integer getId() { return id; }
    public String getEmri() { return emri; }
    public String getEmail() { return email; }
    public String getBirthdate() { return birthdate; }
    public String getNrTelefonit() { return nrTelefonit; }
    public int getPike() { return pike; }
}