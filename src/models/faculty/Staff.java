package models.faculty;

import models.Sex;

import java.time.LocalDate;

abstract public class Staff {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private int phoneNumber;
//    private String address; TODO a composite column cannot be mapped to a column only its components
    private int HouseNo;
    private String street;
    private String subCity;
    private String city;
    private double salary;
    private String officeNumber;

    public Staff(String id, String firstName,
                 String lastName, LocalDate dateOfBirth,
                 Sex sex, int phoneNumber,
                 int houseNo, String street,
                 String subCity, String city,
                 double salary, String officeNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        HouseNo = houseNo;
        this.street = street;
        this.subCity = subCity;
        this.city = city;
        this.salary = salary;
        this.officeNumber = officeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(int houseNo) {
        HouseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getFullName() {
        return firstName + lastName;
    }
}
