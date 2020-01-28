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
import models.Program;
import models.faculty.Teacher;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;
import ui.customWidget.RadioButtonGrid;

public class TeacherWindow {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<Teacher> searchResults;
    private String id = null;

    TeacherWindow(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowLeft();
        setWindowCenter();
        setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {
        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        TextField search = new TextField();

        RadioButtonGrid radioButtonGrid = new RadioButtonGrid(
                Constants.TEACHER_INPUTS[0],
                Constants.TEACHER_INPUTS[1],
                Constants.TEACHER_INPUTS[2],
                Constants.TEACHER_INPUTS[3],
                Constants.TEACHER_INPUTS[4],
                Constants.TEACHER_INPUTS[5],
                Constants.TEACHER_INPUTS[6],
                Constants.TEACHER_INPUTS[7],
                Constants.TEACHER_INPUTS[8],
                Constants.TEACHER_INPUTS[9],
                Constants.TEACHER_INPUTS[10],
                Constants.TEACHER_INPUTS[11],
                Constants.TEACHER_INPUTS[12]
        );

        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            searchResults.setItem(DataBaseManagement.getInstance().fetchTeacherWithCondition(getComparingColumn(radioButtonGrid.getSelectedRadio()), newValue));
        });

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        searchRow.getChildren().addAll(search, radioButtonGrid.getRadioButtonGrid());

        VBox searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));

        window.setTop(new VBox(toolBar, searchBar));

    }

    private void setWindowRight() {

        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add new teacher", "Submit", event -> onSubmitButtonClicked(), Constants.TEACHER_INPUTS
        );
        editExisting = new Inputs("Edit teacher information", "Edit", event -> onEditButtonClicked(), Constants.TEACHER_INPUTS);

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
        ObservableList<String> collage = FXCollections.observableArrayList();
        collage.addAll("AAIT", "CNCS", "CBE", "CDS", "CEBS", "CHS", "CHLJC", "CLGS", "CSS", "CVMA", "CPVA");

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        ObservableList<Program> program = FXCollections.observableArrayList();
        program.addAll(Program.Under_Grad, Program.Grad, Program.Post_Grad);

        ObservableList<Integer> year = FXCollections.observableArrayList();
        year.addAll(1, 2, 3, 4, 5);

        ObservableList<String> section = FXCollections.observableArrayList();
        section.addAll("A", "B", "C", "D");

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setMinWidth(100);

        ComboBox<String> collages = new ComboBox<>(collage);
        collages.setPromptText("Collage");

        ComboBox<String> departments = new ComboBox<>(department);
        departments.setPromptText("Department");

        ComboBox<Program> programs = new ComboBox<>(program);
        programs.setPromptText("Program");

        ComboBox<Integer> years = new ComboBox<>(year);
        years.setPromptText("Year");

        ComboBox<String> sections = new ComboBox<>(section);
        sections.setPromptText("Section");


        vBox.getChildren().addAll(collages, programs, departments, years, sections);
        window.setLeft(vBox);
    }

    private void setWindowCenter() {
        searchResults = new MyTableView<>(
                new MyTableColumn("First Name", "firstName"),
                new MyTableColumn("Last Name", "lastName"),
                new MyTableColumn("ID", "id"),
                new MyTableColumn("Sex", "sex"),
                new MyTableColumn("Phone Number", "dataOfBirth"),
                new MyTableColumn("DOB", "phoneNumber"),
                new MyTableColumn("City", "city"),
                new MyTableColumn("SubCity", "subCity"),
                new MyTableColumn("Street", "street"),
                new MyTableColumn("House No", "houseNo"),
                new MyTableColumn("Salary", "salary"),
                new MyTableColumn("Office Number", "officeNumber"),
                new MyTableColumn("Rank", "rank")
        );

        DataBaseManagement.getInstance().createTable("Teacher",
                new Column("firstName", "String", 15),
                new Column("lastName", "String", 15),
                new Column("id", "String", 15),
                new Column("sex", "String", 7),
                new Column("dataOfBirth", "String", 15),
                new Column("phoneNumber", "Int", 15),
                new Column("city", "String", 10),
                new Column("subCity", "String", 10),
                new Column("street", "String", 10),
                new Column("houseNo", "Int", 10),
                new Column("salary", "Double", 10),
                new Column("officeNumber", "String", 10),
                new Column("rank", "String", 10)
        );

        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
        } catch (NullPointerException e) {
            System.out.println("Empty StudentWindow List");
        }
        window.setCenter(searchResults.getTableView());

    }

    public String getComparingColumn(int i) {
        if (i == 1) return "lastName";
        else if (i == 2) return "id";
        else if (i == 3) return "sex";
        else if (i == 4) return "dateOfBirth";
        else if (i == 5) return "phoneNumber";
        else if (i == 6) return "city";
        else if (i == 7) return "subCity";
        else if (i == 8) return "street";
        else if (i == 9) return "houseNo";
        else if (i == 10) return "salary";
        else if (i == 11) return "officeNumber";
        else if (i == 12) return "rank";
        else return "firstName";
    }

    private void onSubmitButtonClicked() {

        DataBaseManagement.getInstance().insertDataIntoTable("Teacher",
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[0]), "firstName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[1]), "lastName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[2]), "id"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[3]), "sex"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[4]), "dataOfBirth"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[5]), "phoneNumber"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[6]), "city"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[7]), "subCity"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[8]), "street"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[9]), "houseNo"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[10]), "salary"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[11]), "officeNumber"),
                new ColumnValue(addNew.getTextFieldValue(Constants.TEACHER_INPUTS[12]), "rank")
        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacher("*"));
    }

    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("Student",
                "id=\"" + id + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[2]), "id"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[3]), "sex"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[4]), "dataOfBirth"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[5]), "phoneNumber"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[6]), "city"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[7]), "subCity"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[8]), "street"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[9]), "houseNo"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[10]), "salary"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[11]), "officeNumber"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[12]), "rank")

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
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[4], Constants.getLocalDateString(teacher.getDateOfBirth()));
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[5], Integer.toString(teacher.getPhoneNumber()));
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[6], teacher.getCity());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[7], teacher.getSubCity());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[8], teacher.getStreet());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[9], Integer.toString(teacher.getHouseNo()));
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[10], Double.toString(teacher.getSalary()));
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[11], teacher.getOfficeNumber());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[12], teacher.getRank());
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
