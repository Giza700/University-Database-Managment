package ui.pages.customer.registrar;

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
import models.Student;
import ui.Validation;
import ui.customWidget.*;

import javax.naming.directory.SearchResult;


public class StudentWindow {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<Student> searchResults;
    private String id = null;

    StudentWindow(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowLeft();
        setWindowCenter();
        setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {
        SearchTool searchTool = new SearchTool(Constants.STUDENT_INPUTS);
        searchTool.setOnSearch((observable, oldValue, newValue) -> {
            searchResults.setItem(DataBaseManagement.getInstance().fetchStudentWithCondition(Constants.getComparingColumn(searchTool.getSelectedRadioButton()), newValue));
        });
        window.setTop(new VBox(toolBar, searchTool.getSearchBar()));
    }

    private void setWindowRight() {

        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add new student",
                event -> onSubmitButtonClicked(),
                "Submit",
                Constants.STUDENT_INPUTS
        );
        addNew.getColleges().valueProperty().addListener((observable, oldValue, newValue) -> {
            addNew.getDepartment().setItems(DataBaseManagement.getInstance().fetchDepartmentWithCondition("collegeId", newValue));
            addNew.getProgram().setDisable(true);
            addNew.getProgram().setItems(null);
            addNew.getDepartment().setDisable(false);
        });
        addNew.getDepartment().valueProperty().addListener((observable, oldValue, newValue) -> {
            addNew.getProgram().setItems(DataBaseManagement.getInstance().fetchProgramWithCondition("departmentId", newValue));
            addNew.getProgram().setDisable(false);
        });

        editExisting = new Inputs("Edit student information",
                event -> onEditButtonClicked(),
                "Edit",
                Constants.STUDENT_INPUTS);

        editExisting.getColleges().valueProperty().addListener((observable, oldValue, newValue) -> {
            editExisting.getDepartment().setItems(DataBaseManagement.getInstance().fetchDepartmentWithCondition("collegeId", newValue));
        });

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

    private void setWindowLeft() {
        ComboList comboList = new ComboList();
        comboList.setUPComboList((observable, oldValue, newValue) -> {
                    comboList.getDepartments().setItems
                            (DataBaseManagement.getInstance().fetchDepartmentWithCondition("collegeId", newValue));
                    comboList.getDepartments().setDisable(false);
                    comboList.getPrograms().setDisable(true);
                    comboList.getYears().setDisable(true);
                    comboList.getSections().setDisable(true);

                    searchResults.setItem(DataBaseManagement.getInstance().fetchStudentWithCondition("collegeId", newValue));
                },
                (observable, oldValue, newValue) -> {
                    comboList.getPrograms().setItems
                            (DataBaseManagement.getInstance().fetchProgramWithCondition("departmentId", newValue));
                    comboList.getPrograms().setDisable(false);
                    comboList.getYears().setDisable(true);
                    comboList.getSections().setDisable(true);
                    searchResults.setItem(DataBaseManagement.getInstance().fetchStudentWithCondition
                            ("collegeId = '" + comboList.getCollages().getValue() + "' AND departmentId = '" + newValue + "'"));
                },
                (observable, oldValue, newValue) -> {
                    comboList.getYears().setItems(DataBaseManagement.getInstance().fetchYearsOfProgram("programId", newValue));
                    comboList.getYears().setDisable(false);
                    comboList.getSections().setDisable(true);
                    searchResults.setItem(DataBaseManagement.getInstance().fetchStudentWithCondition
                            ("collegeId = '" + comboList.getCollages().getValue() + "' AND departmentId = '" + comboList.getDepartments().getValue() +
                                    "' AND programId = '" + newValue + "'"));

                },
                (observable, oldValue, newValue) -> {
                    comboList.getSections().setItems(Constants.getSectionOfYear(newValue));
                    comboList.getSections().setDisable(false);
                    searchResults.setItem(DataBaseManagement.getInstance().fetchStudentWithCondition
                            ("collegeId = '" + comboList.getCollages().getValue() + "' AND departmentId = '" + comboList.getDepartments().getValue() +
                                    "' AND programId = '" + comboList.getPrograms().getValue() + "' AND year = " + newValue));
                },
                (observable, oldValue, newValue) -> {

                }, event -> {
                    comboList.getCollages().setValue(null);
                    comboList.getDepartments().setValue(null);
                    comboList.getDepartments().setDisable(true);
                    comboList.getPrograms().setValue(null);
                    comboList.getPrograms().setDisable(true);
                    comboList.getYears().setValue(null);
                    comboList.getYears().setDisable(true);
                    comboList.getSections().setValue(null);
                    comboList.getSections().setDisable(true);
                    searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudent("*"));
                });
        window.setLeft(comboList.getComboList());
    }

    private void setWindowCenter() {
        searchResults = new MyTableView<>(
                new MyTableColumn("First Name", "firstName"),
                new MyTableColumn("Last Name", "lastName"),
                new MyTableColumn("ID", "id"),
                new MyTableColumn("Sex", "sex"),
                new MyTableColumn("Year", "year"),
                new MyTableColumn("Phone Number", "phoneNumber"),
                new MyTableColumn("DOB", "dataOfBirth"),
                new MyTableColumn("City", "city"),
                new MyTableColumn("SubCity", "subCity"),
                new MyTableColumn("Street", "city"),
                new MyTableColumn("House No", "houseNo")
        );

        DataBaseManagement.getInstance().createTable("Student",
                new Column("firstName", "String", 15),
                new Column("lastName", "String", 15),
                new Column("id", "String", 15),
                new Column("sex", "String", 7),
                new Column("year", "Int", 15),
                new Column("dataOfBirth", "String", 15),
                new Column("phoneNumber", "Int", 15),
                new Column("city", "String", 10),
                new Column("subCity", "String", 10),
                new Column("street", "String", 10),
                new Column("houseNo", "Int", 10),
                new Column("collegeId", "String", 10),
                new Column("departmentId", "String", 10),
                new Column("programId", "String", 10)
        );

        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudent("*"));
        } catch (NullPointerException e) {
            searchResults.setItem(null);
        }
        window.setCenter(searchResults.getTableView());
    }

    private void onSubmitButtonClicked() {
        //TODO check if combo box values have been selected
        if (Validation.validateName(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[0])) != null ||
                Validation.validateName(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[1])) != null) {
            addNew.setMessage(Validation.validateName(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[0])));
        } else if (Validation.validateId(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[2])) != null)
            addNew.setMessage(Validation.validateId(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[2])));
        else if (Validation.validateYear(Integer.parseInt(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[4]))) != null)
            addNew.setMessage(Validation.validateYear(Integer.parseInt(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[4]))));
        else if (Validation.validateDob(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[5])) != null)
            addNew.setMessage(Validation.validateDob(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[5])));
        else if (Validation.validatePhone(Integer.parseInt(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[6]))) != null)
            addNew.setMessage(Validation.validatePhone(Integer.parseInt(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[6]))));
        else {
            DataBaseManagement.getInstance().insertDataIntoTable("Student",
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[0]), "firstName"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[1]), "lastName"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[2]), "id"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[3]), "sex"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[4]), "year"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[5]), "dataOfBirth"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[6]), "phoneNumber"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[7]), "city"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[8]), "subCity"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[9]), "street"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.STUDENT_INPUTS[10]), "houseNo"),
                    new ColumnValue(addNew.getColleges().getValue(), "collegeId"),
                    new ColumnValue(addNew.getDepartment().getValue(), "departmentId")
            );
        }
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudent("*"));
    }

    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("Student",
                "id=\"" + id + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[2]), "id"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[3]), "sex"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[4]), "year"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[5]), "dataOfBirth"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[6]), "phoneNumber"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[7]), "city"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[8]), "subCity"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[9]), "street"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[10]), "houseNo"),
                new ColumnValue<>(editExisting.getColleges().getValue(), "collegeId"),
                new ColumnValue<>(editExisting.getDepartment().getValue(), "departmentId")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudent("*"));
    }

    private void onLoadButtonClicked() {

        ObservableList<models.Student> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(student -> {
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[0], student.getFirstName());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[1], student.getLastName());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[2], student.getId());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[3], student.getSex().toString());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[4], Integer.toString(student.getYear()));
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[5], Constants.getLocalDateString(student.getDataOfBirth()));
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[6], Integer.toString(student.getPhoneNumber()));
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[7], student.getCity());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[8], student.getSubCity());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[9], student.getStreet());
            editExisting.setTextFieldValue(Constants.STUDENT_INPUTS[10], Integer.toString(student.getHouseNo()));
            editExisting.getColleges().setValue(student.getCollegeId());
            editExisting.getDepartment().setValue(student.getDepartmentId());
            editExisting.getProgram().setValue(student.getProgramId());
            editExisting.getDepartment().setDisable(false);
            editExisting.getProgram().setDisable(false);
            editExisting.getDepartment().setItems(DataBaseManagement.getInstance().fetchDepartmentWithCondition("collegeId", student.getCollegeId()));
            editExisting.getProgram().setItems(DataBaseManagement.getInstance().fetchProgramWithCondition("departmentId", student.getDepartmentId()));
            id = editExisting.getTextFieldValue(Constants.STUDENT_INPUTS[2]);
        });
    }

    private void onDeleteButtonClicked() {

        ObservableList<models.Student> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("Student",
                "id=\"" + account.getId() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudent("*"));
    }


}
