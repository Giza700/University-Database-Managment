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
<<<<<<< HEAD
                TextField search = new TextField();
                RadioButtonGrid radioButtonGrid = new RadioButtonGrid(
                        Constants.REGISTRAR_INPUTS[0],
                        Constants.REGISTRAR_INPUTS[1],
                        Constants.REGISTRAR_INPUTS[2],
                        Constants.REGISTRAR_INPUTS[3],
                        Constants.REGISTRAR_INPUTS[4]
                );

                search.setMinWidth(400);
                search.setPromptText("Name");


                HBox searchRow = new HBox();
                searchRow.setSpacing(5);
                searchRow.getChildren().addAll(search, radioButtonGrid.getRadioButtonGrid());

                VBox searchBar = new VBox();
                searchBar.setPadding(new Insets(10, 5, 2, 10));
                searchBar.getChildren().addAll(searchRow, new Separator());

                window = new BorderPane();
                ToolBar toolbar = new ToolBar();
                toolbar.getItems().add(new ButtonList(
                        new MyButton("Registrar", event -> setAddNew()),
                        new MyButton("Teacher", event -> {
                            window.setCenter(new Label("Teacher Page"));
                            window.setRight(null);
                        }),
                        new MyButton("StudentWindow", event -> {
                            window.setCenter(new Label("StudentWindow Page"));
                            window.setRight(null);
                        })
                ).getHBox());
                /*window.setLeft(setWindowLeft());*/
                VBox vBox = new VBox(toolbar, searchBar);
                window.setTop(vBox);
=======
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
>>>>>>> 90b59175c04ef1b08a9c358a87d52a661f3fe85b

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
