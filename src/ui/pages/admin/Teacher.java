package ui.pages.admin;

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
import models.Account.Account;
import models.Account.TeacherAccount;
import ui.Validation;
import ui.customWidget.RadioButtonGrid;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;
import ui.customWidget.SearchTool;

public class Teacher {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<TeacherAccount> searchResults;
    private String userName = null;

    Teacher(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowCenter();
        setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {
        SearchTool searchTool = new SearchTool(Constants.TEACHER_INPUTS);
        searchTool.setOnSearch((observable, oldValue, newValue) -> {
            searchResults.setItem(DataBaseManagement.getInstance().fetchTeacherAccountWithCondition(
                    Constants.getComparingColumn(searchTool.getSelectedRadioButton()), newValue));
        });
        window.setTop(new VBox(toolBar, searchTool.getSearchBar()));
    }


    private void setWindowRight() {
        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add new Teacher ", "Submit", event -> onSubmitButtonClicked(), Constants.SCHOOLADMIN_INPUTS);
        editExisting = new Inputs("Edit Teacher  Information", "Edit", event -> onEditButtonClicked(), Constants.SCHOOLADMIN_INPUTS);
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
                new MyTableColumn("Email", "email"),
                new MyTableColumn("User Name", "userName"),
                new MyTableColumn("Password", "password")
        );
        DataBaseManagement.getInstance().createTable("TeacherAccount",
                new Column("firstName", "String", 15),
                new Column("lastName", "String", 15),
                new Column("Email", "String", 15),
                new Column("User Name", "String", 15),
                new Column("Password", "String", 15)
        );


        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacherAccount("*"));
        } catch (NullPointerException e) {
            System.out.println("Empty TeacherAccount List");
        }
        window.setCenter(searchResults.getTableView());

    }

    private void onSubmitButtonClicked() {

        if (Validation.validateName(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[0])) != null ||
                Validation.validateName(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[1])) != null) {
            addNew.setMessage(Validation.validateName(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[0])));
        } else if (Validation.validateuserName(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[3])) != null) {
            addNew.setMessage(Validation.validateuserName(addNew.getTextFieldValue((Constants.REGISTRAR_INPUTS[3]))));
        }/*else if (Validation.validatePassword(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[4]) !=null)){
            addNew.setMessage(Validation.validatePassword(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[4])));
        }*/ else {
            DataBaseManagement.getInstance().insertDataIntoTable("TeacherAccount",
                    new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[0]), "firstName"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[1]), "lastName"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[2]), "email"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3]), "username"),
                    new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4]), "password")
            );
        }
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacherAccount("*"));


    }

    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("TeacherAccount",
                "username=\"" + userName + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[2]), "email"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[3]), "username"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[4]), "password")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacherAccount("*"));
    }

    private void onLoadButtonClicked() {
        ObservableList<TeacherAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(TeacherAccount -> {
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[0], TeacherAccount.getFirstName());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[1], TeacherAccount.getLastName());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[2], TeacherAccount.getEmail());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[3], TeacherAccount.getUserName());
            editExisting.setTextFieldValue(Constants.TEACHER_INPUTS[4], TeacherAccount.getPassword());
            userName = editExisting.getTextFieldValue(Constants.TEACHER_INPUTS[3]);
        });

    }

    private void onDeleteButtonClicked() {
        ObservableList<TeacherAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("TeacherAccount",
                "username=\"" + account.getUserName() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromTeacherAccount("*"));

    }

}


