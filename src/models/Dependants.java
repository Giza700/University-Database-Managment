package models;

import java.time.LocalDate;

public class Dependants {
    private String name;
    private Sex sex;
    private LocalDate dateOfBirth;
    private String staffId;

    public Dependants(String name,
                      Sex sex,
                      LocalDate dateOfBirth,
                      String staffId) {
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
