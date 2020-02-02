package models;

//TODO similar to what I stated in Section class.
// The field values are retrieved from different tables
// for phone_list we retrieve from Department_Phone table and
// for officeNum_list we retrieve from Department_OfficeNumber table
// for course_list we retrieve from Department_Course table
// so we instantiate this class using the default constructor
// instead of parametrized constructor as we will not have the values at once
public class Department {
    private String departmentId;
    private String departmentName;
    private String buildingName;
    private int buildingNumber;
    private String collegeId;


    public Department(
            String departmentId,
            String departmentName,
            String buildingName,
            int buildingNumber,
            String collegeId) {

        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.buildingName = buildingName;
        this.buildingNumber = buildingNumber;
        this.collegeId = collegeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }
}
