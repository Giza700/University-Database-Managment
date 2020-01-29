package ui.pages.Student;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ui.customWidget.ButtonList;
import ui.customWidget.MyButton;
import ui.customWidget.PasswordChangeDialog;
import ui.pages.admin.Registrar;
import ui.pages.admin.SchoolAdmin;
import ui.pages.admin.Student;
import ui.pages.admin.Teacher;
import ui.pages.customer.registrar.StudentWindow;

import java.awt.*;

public class StudentPage {
    private Application StudentPage;
    private Stage parentStage;
    public StudentPage(Stage parentStage) {
        this.parentStage = parentStage;
        StudentPage = new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {

                BorderPane window = new BorderPane();
                window.getStyleClass().add("mainBlack");
                window.getStylesheets().add("./ui/css/label.css");
                ToolBar toolBar = new ToolBar();
              new CourseList(window, toolBar);
                new GradeReport(window, toolBar);
                //new Schedule(window, toolBar);


                MyButton CourseListSection = new MyButton("CourseList", event -> {
                   new CourseList(window, toolBar);

                });
                MyButton GradeReportSection = new MyButton("GradeReport", event -> {
                    new GradeReport(window, toolBar);
                });
                MyButton ScheduleSection = new MyButton("Schedule", event -> {
                    //new Schedule(window, toolBar);
                });





                ImageView imageView = new ImageView(getClass().getResource("/assets/back_arrow.png").toExternalForm());
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                imageView.setOnMouseClicked(event -> {
                    primaryStage.close();
                    parentStage.show();
                });

                ButtonList buttonList = new ButtonList(CourseListSection,GradeReportSection,ScheduleSection);
                Pane spaceHolder = new Pane();
                HBox.setHgrow(spaceHolder, Priority.ALWAYS);
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
            StudentPage.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
