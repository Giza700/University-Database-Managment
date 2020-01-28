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
import models.Account.SchoolAdminAccount;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;
import ui.customWidget.RadioButtonGrid;

public class SchoolAdmin {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<SchoolAdminAccount> searchResults;
    private String userName = null;


    SchoolAdmin (BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowCenter();
        setWindowRight();
    }
    private void setWindowTop(ToolBar toolBar) {

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        RadioButtonGrid radioButtonGrid = new RadioButtonGrid(
                Constants.SCHOOLADMIN_INPUTS[0],
                Constants.SCHOOLADMIN_INPUTS[1],
                Constants.SCHOOLADMIN_INPUTS[2],
                Constants.SCHOOLADMIN_INPUTS[3],
                Constants.SCHOOLADMIN_INPUTS[4]
        );
        TextField search = new TextField();
        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
                }
        );

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
        addNew = new Inputs("Add new SchoolAdmin", "Submit", event -> onSubmitButtonClicked(), Constants.SCHOOLADMIN_INPUTS);
        editExisting = new Inputs("Edit SchoolAdmin  Information", "Edit", event -> onEditButtonClicked(), Constants.SCHOOLADMIN_INPUTS);
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
        /*if ((addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[0]).isEmpty() ||
                addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[1]).isEmpty() ||
                addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3]).isEmpty() ||
                addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4]).isEmpty())) {
            addNew.setMessage("Please fill in all fields");
        } else if (!Account.validateEmail(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[2]))) {
            addNew.setMessage("Invalid Email");
        } else if (!Account.validatePassword(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4]))) {
            addNew.setMessage("Invalid Password. Your password needs to be longer than 7 characters and contain at least one letter(upper and lowercase) and number");
        } else {
            addNew.setMessage("");

            DataBaseManagement.getInstance().createTable("SchoolAdminAccount",
                    new Column("firstName", "String", 15),
                    new Column("lastName", "String", 15),
                    new Column("email", "String", 15),
                    new Column("username", "String", 15),
                    new Column("password", "String", 15)
            );
            try {
                searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromSchoolAdminAccount("*"));
            } catch (NullPointerException e) {
                System.out.println("Empty SchoolAdmin List");
            }
            window.setCenter(searchResults.getTableView());

        }*/

    }


    private void onSubmitButtonClicked() {
        DataBaseManagement.getInstance().insertDataIntoTable("SchoolAdminAccount",
                new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[0]), "firstName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[1]), "lastName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[2]), "email"),
                new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3]), "username"),
                new ColumnValue(addNew.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4]), "password")
        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromSchoolAdminAccount("*"));



    }
    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("SchoolAdminAccount",
                "username=\"" + userName + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[2]), "email"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3]), "username"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4]), "password")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromSchoolAdminAccount("*"));
    }
    private void onLoadButtonClicked() {
        ObservableList<SchoolAdminAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(SchoolAdminAccount -> {
            editExisting.setTextFieldValue(Constants.SCHOOLADMIN_INPUTS[0], SchoolAdminAccount.getFirstName());
            editExisting.setTextFieldValue(Constants.SCHOOLADMIN_INPUTS[1], SchoolAdminAccount.getLastName());
            editExisting.setTextFieldValue(Constants.SCHOOLADMIN_INPUTS[2], SchoolAdminAccount.getEmail());
            editExisting.setTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3], SchoolAdminAccount.getUserName());
            editExisting.setTextFieldValue(Constants.SCHOOLADMIN_INPUTS[4], SchoolAdminAccount.getPassword());
            userName = editExisting.getTextFieldValue(Constants.SCHOOLADMIN_INPUTS[3]);
        });

    }

    private void onDeleteButtonClicked() {
        ObservableList<SchoolAdminAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("SchoolAdminAccount",
                "username=\"" + account.getUserName() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromSchoolAdminAccount("*"));

    }





}
