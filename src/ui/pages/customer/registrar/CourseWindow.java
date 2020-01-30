package ui.pages.customer.registrar;

import assistingclasses.Column;
import assistingclasses.ColumnValue;
import assistingclasses.Constants;
import assistingclasses.MyTableColumn;
import database.DataBaseManagement;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.faculty.Teacher;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;
import ui.customWidget.RadioButtonGrid;

public class CourseWindow {

    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<Teacher> searchResults;
    private String id = null;

    CourseWindow(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        window.setLeft(null);
        setWindowCenter();
        setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {
        TextField search = new TextField();

        RadioButtonGrid radioButtonGrid = new RadioButtonGrid();
        for (String string : Constants.COURSE_INPUTS) {
            radioButtonGrid.addRadioButton(string);
        }
        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            searchResults.setItem(DataBaseManagement.getInstance().fetchTeacherWithCondition(Constants.getComparingColumn(radioButtonGrid.getSelectedRadio()), newValue));
        });

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        searchRow.getChildren().addAll(search, radioButtonGrid.getRadioButtonGrid());

        VBox searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));

        window.setTop(new VBox(toolBar, searchBar));

    }

    private void setWindowRight() {
        //TODO Remove duplicate code in the windows

        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add new teacher", "Submit", event -> onSubmitButtonClicked(), Constants.COURSE_INPUTS
        );
        editExisting = new Inputs("Edit teacher information", "Edit", event -> onEditButtonClicked(), Constants.COURSE_INPUTS);

        VBox deleteAccount = new VBox(5);
        deleteAccount.setPadding(new Insets(10));

        Label label = new Label("Load or Remove Account");
        label.getStyleClass().add("title");
        label.getStylesheets().add("./ui/css/label.css");

        Button loadButton = new Button("Load");
        loadButton.setId("loadButton");
        loadButton.getStylesheets().add("./ui/css/label.css");
        loadButton.setOnAction(event -> onLoadButtonClicked());

        Button deleteButton = new Button("Delete");
        deleteButton.setId("deleteButton");
        deleteButton.getStylesheets().add("./ui/css/label.css");
        deleteButton.setOnAction(event -> onDeleteButtonClicked());


        deleteAccount.getChildren().addAll(label, loadButton, deleteButton);

        mainBox.getChildren().addAll(addNew.getGridPane(), new Separator(), editExisting.getGridPane(), new Separator(), deleteAccount);
        scrollPane.setContent(mainBox);
        scrollPane.setMinWidth(250);

        window.setRight(scrollPane);
    }

    private void setWindowCenter() {
        searchResults = new MyTableView<>(
                new MyTableColumn("First Name", "firstName"),
                new MyTableColumn("Last Name", "lastName"),
                new MyTableColumn("ID", "id"),
                new MyTableColumn("Sex", "sex")
        );

        DataBaseManagement.getInstance().createTable("Teacher",
                new Column("firstName", "String", 15),
                new Column("lastName", "String", 15),
                new Column("id", "String", 15),
                new Column("sex", "String", 7)
        );

        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
        } catch (NullPointerException e) {
            System.out.println("Empty StudentWindow List");
        }
        window.setCenter(searchResults.getTableView());

    }

    private void onSubmitButtonClicked() {

        DataBaseManagement.getInstance().insertDataIntoTable("Teacher",
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[0]), "firstName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[1]), "lastName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[2]), "id"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[3]), "sex")

                );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
    }

    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("Student",
                "id=\"" + id + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[2]), "id"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[3]), "sex")


                );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
    }

    private void onLoadButtonClicked() {

        ObservableList<Teacher> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(teacher -> {
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[0], teacher.getFirstName());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[1], teacher.getLastName());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[2], teacher.getId());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[3], teacher.getSex().toString());
            id = editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[2]);

        });
    }

    private void onDeleteButtonClicked() {

        ObservableList<Teacher> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("Teacher",
                "id=\"" + account.getId() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
    }


}
