package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.faculty.Teacher;

//TODO when constructing object of Section class
// Step 1: Retrieve the fields sectionCode and year from the Section table
// Step 2: Retrieve courseList and teachersList from Section_Course table and Section_Teacher table
// So we obviously will need to use the setter methods because we don't have all the fields at once
// to pass to the constructor... that's why I have commented out the constructor
public class Section {

    private char sectionCode;
    private int year;

    private ObservableList<Course> courseList;
    private ObservableList<Teacher> teachersList;

    public Section(
            char sectionCode,
            int year
            /*ObservableList<Course> courseList,
            ObservableList<Teacher> teachersList*/) {
        this.sectionCode = sectionCode;
        this.year = year;
      /*  this.courseList = courseList;
        this.teachersList = teachersList;*/
    }

    public char getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(char sectionCode) {
        this.sectionCode = sectionCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ObservableList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ObservableList<Course> courseList) {
        this.courseList = courseList;
    }

    public ObservableList<Teacher> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(ObservableList<Teacher> teachersList) {
        this.teachersList = teachersList;
    }
}
