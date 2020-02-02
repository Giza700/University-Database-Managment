package ui.pages.teacher;

import assistingclasses.Column;
import assistingclasses.ColumnValue;
import assistingclasses.Constants;
import assistingclasses.MyTableColumn;
import database.DataBaseManagement;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Validation;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;

public class Grade {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<models.Grade> searchResults;
    private String studentId = null;
    private String courseNumber = null;
    private String teacherId = null;
    private String numericGrade = null;

    Grade(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        //setWindowTop(toolBar);
        setWindowCenter();
        setWindowRight();
    }

    public void setWindowCenter() {

        searchResults = new MyTableView<>(
                new MyTableColumn("courseNumber", "courseNumber"),
                new MyTableColumn("studentId", "studentId"),
                new MyTableColumn("numericGrade", "numericGrade"),
                new MyTableColumn("letterGrade", "letterGrade")
                // new MyTableColumn("teacherId", "teacherId")
        );
        DataBaseManagement.getInstance().createTable("Grades",
                new Column("letterGrade", "String", 15),
                new Column("numericGrade", "int", 15),
                new Column("courseNumber", "String", 15),
                new Column("studentId", "String", 15),
                new Column("teacherId", "String", 15)
        );
        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromGrade("*"));
        } catch (NullPointerException e) {
            System.out.println("No Grade Available");
        }
        window.setCenter(searchResults.getTableView());


    }

    private void setWindowRight() {
        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add student grade", "Submit", event -> onSubmitButtonClicked(), Constants.GRADE_INPUTS
        );
        editExisting = new Inputs("Edit student grade", "Edit", event -> onEditButtonClicked(), Constants.GRADE_INPUTS[0]);

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

    public static String getComparingColumn(int i) {
        if (i == 0) return "letterGrade";
        else if (i == 1) return "numericGrade";
        else if (i == 2) return "studentId";
        else return "teacherId";

    }

    public String calculateGrade(String gradeString) {
        double grade = Double.parseDouble(gradeString);
        if (grade >= 90) {
            return Constants.LETTER_GRADES[0];
        } else if (grade >= 83 && grade < 90) {
            return Constants.LETTER_GRADES[1];
        } else if (grade >= 80 && grade < 83) {
            return Constants.LETTER_GRADES[2];
        } else if (grade >= 75 && grade < 80) {
            return Constants.LETTER_GRADES[3];
        } else if (grade >= 68 && grade < 75) {
            return Constants.LETTER_GRADES[4];
        } else if (grade >= 65 && grade < 68) {
            return Constants.LETTER_GRADES[5];
        } else if (grade >= 60 && grade < 65) {
            return Constants.LETTER_GRADES[6];
        } else if (grade >= 50 && grade < 60) {
            return Constants.LETTER_GRADES[7];
        } else if (grade >= 45 && grade < 50) {
            return Constants.LETTER_GRADES[8];
        } else if (grade >= 40 && grade < 45) {
            return Constants.LETTER_GRADES[9];
        } else if (grade < 40) {
            return Constants.LETTER_GRADES[10];
        } else {
            return "Invalid input entered";
        }
    }

    private void onSubmitButtonClicked() {

        /*if (Validation.validateLetterGrade(addNew.getTextFieldValue(Constants.GRADE_INPUTS[0])) != null ||
                Validation.validateLetterGrade(addNew.getTextFieldValue(Constants.GRADE_INPUTS[1])) != null) {
            addNew.setMessage(Validation.validateLetterGrade(addNew.getTextFieldValue(Constants.GRADE_INPUTS[0])));
        }*/

        //else {
        String letterGrade = calculateGrade(addNew.getTextFieldValue(Constants.GRADE_INPUTS[0]));

        DataBaseManagement.getInstance().insertDataIntoTable("Grades",
                new ColumnValue(letterGrade, "letterGrade"),
                new ColumnValue(addNew.getTextFieldValue(Constants.GRADE_INPUTS[0]), "numericGrade"),
                new ColumnValue(addNew.getTextFieldValue(Constants.GRADE_INPUTS[1]), "courseNumber"),
                new ColumnValue(addNew.getTextFieldValue(Constants.GRADE_INPUTS[2]), "studentId"),
                new ColumnValue(addNew.getTextFieldValue(Constants.GRADE_INPUTS[3]), "teacherId")
        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromGrade("*"));
        //}

    }

    private void onEditButtonClicked() {
        //studentId = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[2]);
        //courseNumber = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[1]);
        //teacherId = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[3]);
        String letterGrade = calculateGrade(editExisting.getTextFieldValue(Constants.GRADE_INPUTS[0]));

        DataBaseManagement.getInstance().updateValueInTable("Grades",
                "studentId=\"" + studentId + "\"" /*+  "courseNumber=\"" + courseNumber + "\"" +  "teacherId=\"" + teacherId + "\""*/,
                new ColumnValue<>(letterGrade, "letterGrade"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.GRADE_INPUTS[0]), "numericGrade")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromGrade("*"));
    }

    private void onLoadButtonClicked() {
        ObservableList<models.Grade> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(grade -> {
            editExisting.setTextFieldValue(Constants.GRADE_INPUTS[0],Integer.toString(grade.getNumericGrade()));
            studentId = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[2]);
            //courseNumber = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[1]);
            //teacherId = editExisting.getTextFieldValue(Constants.GRADE_INPUTS[3]);
        });

    }

    private void onDeleteButtonClicked() {
        ObservableList<models.Grade> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(grade -> DataBaseManagement.getInstance().deleteRowFromTable("Grades",
                "studentId=\"" + grade.getStudentId() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromGrade("*"));

    }
}
