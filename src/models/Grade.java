package models;
//TODO Grade is a weak entity, no primary key attributes of its own
public class Grade {
    private String letterGrade;
    private int numericGrade;
    private String courseNumber;
    private String studentId;
    private String teacherId;

    public Grade(String letterGrade, int numericGrade, String courseNumber,
                 String studentId, String teacherId) {
        this.letterGrade = letterGrade;
        this.numericGrade = numericGrade;
        this.courseNumber = courseNumber;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public int getNumericGrade() {
        return numericGrade;
    }

    public void setNumericGrade(int numericGrade) {
        this.numericGrade = numericGrade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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
