package ui.pages.teacher;

import assistingclasses.Column;
import assistingclasses.ColumnValue;
import assistingclasses.Constants;
import assistingclasses.MyTableColumn;
import database.DataBaseManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Program;
import ui.customWidget.*;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;

public class Course {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<models.Course> searchResults;
    private String id = null;

    Course(BorderPane borderPane, ToolBar toolBar) {
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
                new MyTableColumn("Course Number", "courseNumber"),
                new MyTableColumn("Course Name", "courseName"),
                new MyTableColumn("description", "description")

        );

        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromCourse("*"));
        } catch (NullPointerException e) {
            System.out.println("Empty StudentWindow List");
        }
        window.setCenter(searchResults.getTableView());

    }

}