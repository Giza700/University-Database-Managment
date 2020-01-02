package ui.pages.customer;

import database.DataBaseManagement;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Account.RegistrarAccount;
import ui.pages.customer.registrar.RegistrarPage;

public class LogIn {
    private Application login;

    public LogIn() {
        login = new Application() {
            Label messageLabel = new Label();

            @Override
            public void start(Stage primaryStage) throws Exception {
                messageLabel.setId("messageLabel");
                messageLabel.getStylesheets().add("ui/css/label.css");
                messageLabel.setMinWidth(300);
                Label userName = new Label("User Name");

                TextField userNameField = new TextField();
                userNameField.setPromptText("User Name");

                Label passWord = new Label("Password");
                TextField passWordField = new PasswordField();
                passWordField.setPromptText("PassWord");

                Button login = new Button("Log In");
                login.setOnAction(e -> {
                    boolean accountMatches = false;
                    String usernameinput = userNameField.getText();
                    String passwordinput = passWordField.getText();

                    if (usernameinput != null && passwordinput != null) {
                        try {
                            DataBaseManagement.getInstance().openDataBase();
                            ObservableList<RegistrarAccount> userNames = DataBaseManagement.getInstance().fetchColumnsFromRegistrarAccount("*");
                            for (RegistrarAccount accounts : userNames) {
                                if (accounts.getUserName().equals(usernameinput) && accounts.getPassword().equals(passwordinput)) {
                                    accountMatches = true;
                                    break;
                                }
                            }
                            if (accountMatches){
                                new RegistrarPage();
                                primaryStage.close();
                            }
                        } catch (Exception exception) {
                            messageLabel.setText("Please Ask your Admin to register you first");
                        }
                    } else {
                        messageLabel.setText("Please fill both the fields");
                    }

                });
                HBox hBox = new HBox();
                hBox.setPadding(new Insets(0, 0, 0, 140));
                hBox.getChildren().add(login);


                GridPane gridPane = new GridPane();
                gridPane.setPadding(new Insets(30, 10, 10, 20));
                gridPane.setHgap(10);
                gridPane.setVgap(10);


                GridPane.setConstraints(userName, 0, 0);
                GridPane.setConstraints(userNameField, 1, 0);
                GridPane.setConstraints(passWord, 0, 1);
                GridPane.setConstraints(passWordField, 1, 1);
                GridPane.setConstraints(hBox, 1, 2);
                GridPane.setConstraints(messageLabel, 0, 4, 2, 2);


                gridPane.getChildren().addAll(userName, userNameField, passWord, passWordField, hBox, messageLabel);

                Scene scene = new Scene(gridPane, 300, 200);
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            }
        };
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Application getLogin() {
        return login;
    }
}
