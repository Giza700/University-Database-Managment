package ui.pages.teacher;

import assistingclasses.MyTableColumn;
import database.DataBaseManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Course;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;

public class Schedule {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<Course> searchResults;
    private String id = null;

    Schedule(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        // setWindowLeft();
        setWindowCenter();
        //setWindowRight();
    }
    public void setWindowTop(ToolBar toolBar) {
        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        TextField search = new TextField();
        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
                }
                //searchResults.setItem(DataBaseManagement.getInstance().fetchWithCondition(getComparingColumn(getSelectedCheckBox()), newValue))
        );

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        searchRow.getChildren().addAll(search);

        VBox searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));

        window.setTop(new VBox(toolBar, searchBar));

    }
    public void setWindowCenter() {
        searchResults = new MyTableView<>(
                new MyTableColumn("TeacherId", "teacherId"),
                new MyTableColumn("CourseId", "courseId"),
                new MyTableColumn("SectionId", "sectionId"),
                new MyTableColumn("Start Time", "startTime"),
                new MyTableColumn("End Time", "endTime"),
                new MyTableColumn("DepartmentId", "departmentId"),
                new MyTableColumn("CollegeId", "collegeId"),
                new MyTableColumn("Day Of The Week", "dayOfTheWeek"),
                new MyTableColumn("Class Room", "classRoom"),
                new MyTableColumn("Building Name", "buildingName"),
                new MyTableColumn("Building Number", "buildingNumber")
                );

        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromCourse("*"));
        } catch (NullPointerException e) {
            System.out.println("Empty Schedule List");
        }
        window.setCenter(searchResults.getTableView());

    }
}
