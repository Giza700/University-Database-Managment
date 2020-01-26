package ui.pages;

import database.DataBaseManagement;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.pages.admin.AdminPage;
import ui.pages.customer.registrar.RegistrarPage;


public class MainPage extends Application {
    //   Image image = new Image(getClass().getResource("class.jpeg").toExternalForm());

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        ToolBar toolBar = new ToolBar();

        Label openRegistrar = new Label("Registrar");
        openRegistrar.getStylesheets().add("ui/css/label.css");
        openRegistrar.setOnMouseClicked(event -> {
            new RegistrarPage(primaryStage);
            primaryStage.close();
            //new LoginDialog(primaryStage);
        });

        Label openAdmin = new Label("Admin");
        openAdmin.getStylesheets().add("ui/css/label.css");
        openAdmin.setOnMouseClicked(event -> {
            new AdminPage();
            primaryStage.close();
        });

        Rectangle2D screen = Screen.getPrimary().getBounds();

        ImageView imageView = new ImageView(getClass().getResource("/assets/campus.jpeg").toExternalForm());
        ImageView imageView1 = new ImageView(getClass().getResource("/assets/campus1.jpeg").toExternalForm());
        ImageView imageView2 = new ImageView(getClass().getResource("/assets/class.jpeg").toExternalForm());
        ImageView imageView3 = new ImageView(getClass().getResource("/assets/AAU-Header_2.jpg").toExternalForm());
        /*imageView.setFitWidth(screen.getWidth());
        imageView.setFitHeight(screen.getHeight()-50);*/
        toolBar.getItems().addAll(openAdmin, openRegistrar);

        VBox vBox = new VBox(5,toolBar, imageView3);
        borderPane.setTop(vBox);
       /* borderPane.setCenter(imageView);
        borderPane.setLeft(imageView1);
        borderPane.setBottom(imageView2);*/

        DataBaseManagement.getInstance().openDataBase();
        Scene scene = new Scene(borderPane, screen.getWidth(), screen.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
