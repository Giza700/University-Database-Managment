package models;

public class Course {
    private String courseNumber;
    private String courseName;
    private String description;
    private double creditHour;

    public Course(String courseNumber,
                  String courseName,
                  String description,
                  double creditHour) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.description = description;
        this.creditHour = creditHour;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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

    public double getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(double creditHour) {
        this.creditHour = creditHour;
    }
}
