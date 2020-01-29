package models;

import java.time.LocalDate;

public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private Sex sex;
    private int year;
    private int phoneNumber;
    private LocalDate dataOfBirth;
    private int houseNo;
    private String street;
    private String subCity;
    private String city;
    private String collegeId;
    private String departmentId;

    public Student(//TODO overload constructor if this isn't used by the filters
            String firstName,
            String lastName,
            String id,
            Sex sex,
            int year,
            LocalDate dataOfBirth,
            int phoneNumber,
            String city,
            String subCity,
            String street,
            int houseNo,
            String collegeId,
            String departmentId) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.year = year;
        this.phoneNumber = phoneNumber;
        this.dataOfBirth = dataOfBirth;
        this.houseNo = houseNo;
        this.street = street;
        this.subCity = subCity;
        this.city = city;
        this.collegeId = collegeId;
        this.departmentId = departmentId;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(LocalDate dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }



}
