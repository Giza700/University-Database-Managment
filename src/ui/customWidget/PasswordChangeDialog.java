package ui.customWidget;

import assistingclasses.ColumnValue;
import database.DataBaseManagement;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.Account.RegistrarAccount;
import ui.pages.customer.registrar.RegistrarPage;

public class PasswordChangeDialog {
    private Dialog<Pair<String, String>> loginDialog;

    public PasswordChangeDialog() {
        loginDialog = new Dialog<>();
        loginDialog.setTitle("Password Change");
        loginDialog.setHeaderText("Change Your password");

        Label messageLabel = new Label();
        messageLabel.setId("messageLabel");
        messageLabel.getStylesheets().add("ui/css/label.css");
        messageLabel.setMinWidth(300);
        Label userName = new Label("User Name");

        TextField userNameField = new TextField();
        userNameField.setPromptText("Mr.X");

        Label passWord = new Label("Old Password");
        TextField oldPassWordField = new PasswordField();
        oldPassWordField.setPromptText("Testing123");

        Label newPassWord = new Label("New Password");
        TextField newPassWordField = new PasswordField();
        newPassWordField.setPromptText("Retesting123");

        ButtonType change = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);

        loginDialog.getDialogPane().getButtonTypes().addAll(change, ButtonType.CANCEL);
        loginDialog.setResultConverter(param -> {
            //TODO Handle possible error situations
            if (param == change) {
                if (!userNameField.getText().equals("") && !oldPassWordField.getText().equals("") && !newPassWordField.getText().equals("")) {
                    DataBaseManagement.getInstance().openDataBase();
                    DataBaseManagement.getInstance().updateValueInTable("RegistrarAccount",
                            "username=\"" + userNameField.getText() + "\"",
                            new ColumnValue(newPassWordField.getText(), "password"));
                } else if (userNameField.getText().equals("") || newPassWordField.getText().equals("") || oldPassWordField.getText().equals("")) {
                    messageLabel.setText("Please fill in all the fields");
                }
                else if (DataBaseManagement.getInstance().
                        fetchColumnsFromUserName().stream().filter(account -> account.getUserName() == userNameField.getText() && account.getPassword() != oldPassWordField.getText()).toArray().length != 0) {
                    messageLabel.setText("Incorrect username and password combination");
                }

            }
            return null;
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 10, 10, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        GridPane.setConstraints(userName, 0, 0);
        GridPane.setConstraints(userNameField, 1, 0);
        GridPane.setConstraints(passWord, 0, 1);
        GridPane.setConstraints(oldPassWordField, 1, 1);
        GridPane.setConstraints(newPassWord, 0, 2);
        GridPane.setConstraints(newPassWordField, 1, 2);

        GridPane.setConstraints(messageLabel, 0, 3, 2, 2);

        gridPane.getChildren().addAll(userName, userNameField, passWord, oldPassWordField, newPassWord, newPassWordField, messageLabel);

        loginDialog.getDialogPane().setContent(gridPane);
        loginDialog.showAndWait();
    }
}
