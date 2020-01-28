package ui.pages.admin;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.*;
import ui.pages.customer.registrar.StudentWindow;


public class AdminPage {
    private Application AdminPage;
    private Stage parentStage;

    public AdminPage(Stage parentStage) {
        this.parentStage = parentStage;
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
                ImageView imageView = new ImageView(getClass().getResource("/assets/back_arrow.png").toExternalForm());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                imageView.setOnMouseClicked(event -> {
                    primaryStage.close();
                    parentStage.show();
                });
                ButtonList buttonList = new ButtonList(registrarSection,teachersSection,studentSection,schoolAdminSection);
                Pane spaceHolder = new Pane();
                HBox.setHgrow(spaceHolder,Priority.ALWAYS);
                Button changePassword = new Button("Change Password");
                changePassword.setId("transparentButton");
                changePassword.getStyleClass().add("./ui/css/label.css");
                changePassword.setOnAction(event -> {
                    new PasswordChangeDialog();
                });

                toolBar.getItems().addAll(imageView, buttonList.getHBox(),spaceHolder,changePassword);


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
