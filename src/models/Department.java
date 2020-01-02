package models;

import javafx.collections.ObservableList;

import java.util.List;

//TODO similar to what I stated in Section class.
// The field values are retrieved from different tables
// for phone_list we retrieve from Department_Phone table and
// for officeNum_list we retrieve from Department_OfficeNumber table
// for course_list we retrieve from Department_Course table
// so we instantiate this class using the default constructor
// instead of parametrized constructor as we will not have the values at once
public class Department {
    private String departmentCode;
    private String departmentName;
    private String buildingName;
    private int buildingNumber;
    private ObservableList<Integer> officePhone;
    private ObservableList<Integer> officeNumber;
    private ObservableList<Course> coursesList;

    public Department(
            String departmentCode,
            String departmentName,
            String buildingName,
            int buildingNumber
            /*ObservableList<Integer> officePhone,
            ObservableList<Integer> officeNumber,
            ObservableList<Course> coursesList*/) {

        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.buildingName = buildingName;
        this.buildingNumber = buildingNumber;
      /*  this.officePhone = officePhone;
        this.officeNumber = officeNumber;
        this.coursesList = coursesList;*/
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public ObservableList<Integer> getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(ObservableList<Integer> officePhone) {
        this.officePhone = officePhone;
    }

    public ObservableList<Integer> getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(ObservableList<Integer> officeNumber) {
        this.officeNumber = officeNumber;
    }

    public ObservableList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ObservableList<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
