package ui.pages;

import database.DataBaseManagement;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.LoginDialog;
import ui.pages.admin.AdminPage;


public class MainPage extends Application {
    //   Image image = new Image(getClass().getResource("class.jpeg").toExternalForm());

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        ToolBar toolBar = new ToolBar();

        Label openRegistrar = new Label("Registrar");
        openRegistrar.getStylesheets().add("ui/css/label.css");
        openRegistrar.setOnMouseClicked(event -> {
            new LoginDialog(primaryStage);
        });

        Label openAdmin = new Label("Admin");
        openAdmin.getStylesheets().add("ui/css/label.css");
        openAdmin.setOnMouseClicked(event -> {
            new AdminPage();
            primaryStage.close();
        });

        /*ImageView imageView = new ImageView(image);*/
        toolBar.getItems().addAll(openAdmin, openRegistrar);
        borderPane.setTop(toolBar);
        /*borderPane.setCenter(imageView);*/

        DataBaseManagement.getInstance().openDataBase();

        Rectangle2D screen = Screen.getPrimary().getBounds();
        Scene scene = new Scene(borderPane, screen.getWidth(), screen.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
