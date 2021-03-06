package models;

public class Course {
    private String courseCode;
    private String courseName;
    private String description;
    private int creditHour;

    public Course(String courseCode,
                  String courseName,
                  String description,
                  int creditHour) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.creditHour = creditHour;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }
}
