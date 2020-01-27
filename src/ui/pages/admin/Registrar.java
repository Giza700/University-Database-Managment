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
import models.Account.RegistrarAccount;
import ui.customWidget.CheckBoxGrid;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;

public class Registrar {

    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<RegistrarAccount> searchResults;
    private String userName = null;

    Registrar(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowCenter();
        setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        CheckBoxGrid checkBox = new CheckBoxGrid(
                Constants.REGISTRAR_INPUTS[0],
                Constants.REGISTRAR_INPUTS[1],
                Constants.REGISTRAR_INPUTS[2],
                Constants.REGISTRAR_INPUTS[3],
                Constants.REGISTRAR_INPUTS[4]
        );
        TextField search = new TextField();
        search.setMinWidth(400);
        search.setPromptText("Search");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
                }
                /*searchResults.setItem(DataBaseManagement.getInstance().fetchWithCondition(getComparingColumn(getSelectedCheckBox()), newValue))*/
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
        addNew = new Inputs("Add new Registrar", "Submit", event -> onSubmitButtonClicked(), Constants.REGISTRAR_INPUTS
        );
        editExisting = new Inputs("Edit Registrar information", "Edit", event -> onEditButtonClicked(), Constants.REGISTRAR_INPUTS);

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
        /*if ((addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[0]).isEmpty() ||
                addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[1]).isEmpty() ||
                addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[3]).isEmpty() ||
                addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[4]).isEmpty())) {
            addNew.setMessage("Please fill in all fields");
        } else if (!Account.validateEmail(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[2]))) {
            addNew.setMessage("Invalid Email");
        } else if (!Account.validatePassword(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[4]))) {
            addNew.setMessage("Invalid Password. Your password needs to be longer than 7 characters and contain at least one letter(upper and lowercase) and number");
        } else {
            addNew.setMessage("");

            DataBaseManagement.getInstance().createTable("RegistrarAccount",
                    new Column("firstName", "String", 15),
                    new Column("lastName", "String", 15),
                    new Column("email", "String", 15),
                    new Column("username", "String", 15),
                    new Column("password", "String", 15)
            );
            try {
                searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*"));
            } catch (NullPointerException e) {
                System.out.println("Empty Registrar List");
            }
            window.setCenter(searchResults.getTableView());

        }*/

    }
    public String getComparingColumn(int i) {
        if (i == 1) return "lastName";
        else if (i == 2) return "email";
        else if (i == 3) return "username";
        else if (i == 4) return "password";
        else return "firstName";
    }
    private void onSubmitButtonClicked() {
        DataBaseManagement.getInstance().insertDataIntoTable("RegistrarAccount",
                new ColumnValue(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[0]), "firstName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[1]), "lastName"),
                new ColumnValue(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[2]), "email"),
                new ColumnValue(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[3]), "username"),
                new ColumnValue(addNew.getTextFieldValue(Constants.REGISTRAR_INPUTS[4]), "password")
        );
                searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*"));



    }
    private void onEditButtonClicked() {
        DataBaseManagement.getInstance().updateValueInTable("RegistrarAccount",
                "username=\"" + userName + "\"",
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[0]), "firstName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[1]), "lastName"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[2]), "email"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[3]), "username"),
                new ColumnValue<>(editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[4]), "password")

        );
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*"));
    }
    private void onLoadButtonClicked() {
        ObservableList<RegistrarAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(registrarAccount -> {
            editExisting.setTextFieldValue(Constants.REGISTRAR_INPUTS[0], registrarAccount.getFirstName());
            editExisting.setTextFieldValue(Constants.REGISTRAR_INPUTS[1], registrarAccount.getLastName());
            editExisting.setTextFieldValue(Constants.REGISTRAR_INPUTS[2], registrarAccount.getEmail());
            editExisting.setTextFieldValue(Constants.REGISTRAR_INPUTS[3], registrarAccount.getUserName());
            editExisting.setTextFieldValue(Constants.REGISTRAR_INPUTS[4], registrarAccount.getPassword());
            userName = editExisting.getTextFieldValue(Constants.REGISTRAR_INPUTS[3]);
        });

    }

    private void onDeleteButtonClicked() {
        ObservableList<RegistrarAccount> selected = searchResults.getSelectionModels().getSelectedItems();
        selected.forEach(account -> DataBaseManagement.getInstance().deleteRowFromTable("RegistrarAccount",
                "username=\"" + account.getUserName() + "\""));
        searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*"));

    }


    }
