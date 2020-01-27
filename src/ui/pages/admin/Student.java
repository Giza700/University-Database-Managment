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

import models.Account.StudentAccount;
import ui.customWidget.CheckBoxGrid;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;

public class Student{
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<StudentAccount> searchResults;
    private String userName = null;

    Student(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowCenter();
        setWindowRight();
    }
    private void setWindowTop(ToolBar toolBar) {

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        CheckBoxGrid checkBox = new CheckBoxGrid(
                Constants.STUDENTACCOUNT_INPUTS[0],
                Constants.STUDENTACCOUNT_INPUTS[1],
                Constants.STUDENTACCOUNT_INPUTS[2],
                Constants.STUDENTACCOUNT_INPUTS[3],
                Constants.STUDENTACCOUNT_INPUTS[4]
        );
        TextField search = new TextField();
        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
                }
        );

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        searchRow.getChildren().addAll(search, checkBox.getCheckBoxGrid());

        VBox searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));

        window.setTop(new VBox(toolBar, searchBar));

    }


    private void setWindowRight() {
        ScrollPane scrollPane = new ScrollPane();
        VBox mainBox = new VBox(5);
        addNew = new Inputs("Add new Student ", "Submit", event -> onSubmitButtonClicked(), Constants.STUDENTACCOUNT_INPUTS);
        editExisting = new Inputs("Edit Student  Information", "Edit", event -> onEditButtonClicked(), Constants.STUDENTACCOUNT_INPUTS);
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
      /*  if ((addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[0]).isEmpty() ||
                addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[1]).isEmpty() ||
                addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[3]).isEmpty() ||
                addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[4]).isEmpty())) {
            addNew.setMessage("Please fill in all fields");
        } else if (!Account.validateEmail(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[2]))) {
            addNew.setMessage("Invalid Email");
        } else if (!Account.validatePassword(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[4]))) {
            addNew.setMessage("Invalid Password. Your password needs to be longer than 7 characters and contain at least one letter(upper and lowercase) and number");
        } else {
            addNew.setMessage("");

            DataBaseManagement.getInstance().createTable("StudentAccount",
                    new Column("firstName", "String", 15),
                    new Column("lastName", "String", 15),
                    new Column("email", "String", 15),
                    new Column("username", "String", 15),
                    new Column("password", "String", 15)
            );
            try {
                searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudentAccount("*"));
            } catch (NullPointerException e) {
                System.out.println("Empty SchoolAdmin List");
            }
            window.setCenter(searchResults.getTableView());

        }*/

    }


    private void onSubmitButtonClicked() {
        DataBaseManagement.getInstance().insertDataIntoTable("StudentAccount",
                new ColumnValue(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[0]), "firstName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[1]), "lastName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[2]), "email"),
                new ColumnValue(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[3]), "username"),
                new ColumnValue(addNew.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[4]), "password")
        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudentAccount("*"));



    }
    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("TeacherAccount",
                "username=\"" + userName + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[2]), "email"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[3]), "username"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[4]), "password")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudentAccount("*"));
    }
    private void onLoadButtonClicked() {
        ObservableList<StudentAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(StudentAccount -> {
            editExisting.setTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[0], StudentAccount.getFirstName());
            editExisting.setTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[1], StudentAccount.getLastName());
            editExisting.setTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[2], StudentAccount.getEmail());
            editExisting.setTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[3], StudentAccount.getUserName());
            editExisting.setTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[4], StudentAccount.getPassword());
            userName = editExisting.getTextFieldValue(Constants.STUDENTACCOUNT_INPUTS[3]);
        });

    }

    private void onDeleteButtonClicked() {
        ObservableList<StudentAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("StudentAccount",
                "username=\"" + account.getUserName() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromStudentAccount("*"));

    }

}


