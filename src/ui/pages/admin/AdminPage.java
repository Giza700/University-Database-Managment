package ui.pages.admin;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.*;
import ui.pages.customer.registrar.StudentWindow;


public class AdminPage {
    private Application AdminPage;

    public AdminPage() {
        AdminPage = new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {
                BorderPane window = new BorderPane();
                window.getStyleClass().add("mainBlack");
                window.getStylesheets().add("./ui/css/label.css");
                ToolBar toolBar = new ToolBar();
                new Registrar(window, toolBar);
                new Teacher(window, toolBar);
                new Student(window, toolBar);
                new SchoolAdmin(window, toolBar);

                MyButton registrarSection = new MyButton("Registrar", event -> {
                    new Registrar(window, toolBar);

                });
                MyButton teachersSection = new MyButton("Teachers", event -> {
                    new Teacher(window, toolBar);
                });
                MyButton studentSection = new MyButton("Student", event -> {
                    new Student(window, toolBar);
                });
                MyButton schoolAdminSection = new MyButton("SchoolAdmin", event -> {
                    new SchoolAdmin(window, toolBar);
                });
                ButtonList buttonList = new ButtonList(registrarSection,teachersSection,studentSection,schoolAdminSection);
                toolBar.getItems().add(buttonList.getHBox());

                Rectangle2D screen = Screen.getPrimary().getBounds();
                Scene newScene = new Scene(window, screen.getWidth(), screen.getHeight());
                primaryStage.setScene(newScene);
                primaryStage.setTitle("Search");
                primaryStage.show();
            }

        };
        try {
            AdminPage.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
