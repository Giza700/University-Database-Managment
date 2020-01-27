package ui.pages.customer.registrar;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.*;


public class RegistrarPage {

    private Application registrarPage;
    private Stage parentStage;

    public RegistrarPage(Stage parentStage) {
        this.parentStage = parentStage;
        registrarPage = new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {

                BorderPane window = new BorderPane();
                window.getStyleClass().add("mainBlack");
                window.getStylesheets().add("./ui/css/label.css");
                ToolBar toolBar = new ToolBar();
                new StudentWindow(window, toolBar);

                MyButton studentSection = new MyButton("Students", event -> {
                    new StudentWindow(window, toolBar);
                });
                MyButton teachersSection = new MyButton("Teachers", event -> {
                    new TeacherWindow(window,toolBar);
                });
                MyButton courseSection = new MyButton("Course", event -> {
                    window.setCenter(new Label("StudentWindow Page"));
                    window.setRight(null);
                });
                ImageView imageView = new ImageView(getClass().getResource("/assets/back_arrow.png").toExternalForm());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                imageView.setOnMouseClicked(event -> {
                    primaryStage.close();
                    parentStage.show();
                });
                ButtonList buttonList = new ButtonList(studentSection, teachersSection, courseSection);
                toolBar.getItems().addAll(imageView, buttonList.getHBox());

                Rectangle2D screen = Screen.getPrimary().getBounds();
                Scene newScene = new Scene(window, screen.getWidth(), screen.getHeight());
                primaryStage.setScene(newScene);
                primaryStage.setTitle("Search");
                primaryStage.show();
            }

        };
        try {
            registrarPage.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
