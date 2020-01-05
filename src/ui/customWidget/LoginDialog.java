package ui.customWidget;

import database.DataBaseManagement;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.Account.RegistrarAccount;
import ui.pages.customer.registrar.RegistrarPage;

public class LoginDialog {
    private Dialog<Pair<String, String>> loginDialog;

    public LoginDialog(Stage parentPrimaryStage) {
        loginDialog = new Dialog<>();
        loginDialog.setTitle("Login");
        loginDialog.setHeaderText("Login into your account");

        //loginDialog.setGraphic();

        Label messageLabel = new Label();
        messageLabel.setId("messageLabel");
        messageLabel.getStylesheets().add("ui/css/label.css");
        messageLabel.setMinWidth(300);
        Label userName = new Label("User Name");

        TextField userNameField = new TextField();
        userNameField.setPromptText("User Name");

        Label passWord = new Label("Password");
        TextField passWordField = new PasswordField();
        passWordField.setPromptText("PassWord");

        ButtonType login = new ButtonType("Log In", ButtonBar.ButtonData.OK_DONE);

        loginDialog.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);
        loginDialog.setResultConverter(param -> {
            if (param == login) {
                boolean accountMatches = false;

                if (!userNameField.getText().equals("") && !passWordField.getText().equals("")) {
                    try {
                        DataBaseManagement.getInstance().openDataBase();
                        ObservableList<RegistrarAccount> userNames = DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*");
                        for (RegistrarAccount accounts : userNames) {
                            if (accounts.getUserName().equals(userNameField.getText()) && accounts.getPassword().equals(passWordField.getText())) {
                                accountMatches = true;
                                break;
                            }
                        }
                        if (accountMatches) {
                            new RegistrarPage();
                            loginDialog.close();
                            parentPrimaryStage.close();
                        }
                    } catch (Exception exception) {
                        messageLabel.setText("Please Ask your Admin to register you first");
                    }
                } else {
                    messageLabel.setText("Please fill both the fields");
                    System.out.println("dfghbnm");
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
        GridPane.setConstraints(passWordField, 1, 1);

        GridPane.setConstraints(messageLabel, 0, 2, 2, 2);

        gridPane.getChildren().addAll(userName, userNameField, passWord, passWordField, messageLabel);

        loginDialog.getDialogPane().setContent(gridPane);
        loginDialog.showAndWait();
    }
}
