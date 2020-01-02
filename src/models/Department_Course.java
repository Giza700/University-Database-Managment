package models;
//TODO both the fields are foreign keys... they refer a row in their respective tables
// We can use this class when doing query of Department_Course table
public class Department_Course {
    private String courseId;
    private String departmentId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
