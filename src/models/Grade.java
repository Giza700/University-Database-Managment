package models;
//TODO Grade is a weak entity, no primary key attributes of its own
public class Grade {
    private String grade;
    private String courseId;
    private String studentId;
    private String teacherId;

    public Grade(String grade,
                 String courseId,
                 String studentId,
                 String teacherId) {
        this.grade = grade;
        this.courseId = courseId;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
