package ui.pages.teacher;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.ButtonList;
import ui.customWidget.MyButton;



public class TeacherPage {
    private Application teacherPage;

   /* public void showLoginDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner()
    }*/

    public TeacherPage(Stage parentStage) {
        teacherPage = new Application() {

            @Override
            public void start(Stage primaryStage) throws Exception {
                BorderPane window = new BorderPane();
                window.getStyleClass().add("mainWhite");
                window.getStylesheets().add("./ui/css/label.css");
                ToolBar toolBar = new ToolBar();
                new Course(window, toolBar);

                MyButton courseSection = new MyButton("Course", event -> {
                    new Course(window, toolBar);
                });
                MyButton gradeSection = new MyButton("Grade", event -> {
                    window.setCenter(new Label("Teacher Page"));
                    window.setRight(null);
                    new Grade(window, toolBar);
                });
                MyButton scheduleSection = new MyButton("Schedule", event -> {
                    window.setCenter(new Label("StudentWindow Page"));
                    window.setRight(null);
                    new Schedule(window, toolBar);
                });
                ImageView imageView = new ImageView(getClass().getResource("/assets/back_arrow.png").toExternalForm());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                imageView.setOnMouseClicked(event -> {
                    primaryStage.close();
                    parentStage.show();
                });
                ButtonList buttonList = new ButtonList(courseSection, gradeSection, scheduleSection);
                toolBar.getItems().add(buttonList.getHBox());

                Rectangle2D screen = Screen.getPrimary().getBounds();
                Scene newScene = new Scene(window, screen.getWidth(), screen.getHeight());
                primaryStage.setScene(newScene);
                primaryStage.setTitle("Search");
                primaryStage.show();
            }
        };
        try {
            teacherPage.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

