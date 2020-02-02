package ui.pages.Student;

import assistingclasses.Column;
import assistingclasses.Constants;
import assistingclasses.MyTableColumn;
import database.DataBaseManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Account.RegistrarAccount;
import models.Account.SchoolAdminAccount;
import ui.customWidget.Inputs;
import ui.customWidget.MyTableView;
import ui.customWidget.RadioButtonGrid;

public class GradeReport {
    private BorderPane window;
    private Inputs addNew;
    private Inputs editExisting;
    private MyTableView<SchoolAdminAccount> searchResults;
    private String userName = null;
    GradeReport(BorderPane borderPane, ToolBar toolBar) {
        window = borderPane;
        setWindowTop(toolBar);
        setWindowCenter();
        //setWindowRight();
    }

    private void setWindowTop(ToolBar toolBar) {

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");
        TextField search = new TextField();

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        //searchRow.getChildren().addAll(search, radioButtonGrid.getRadioButtonGrid());

        VBox searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));

        window.setTop(new VBox(toolBar, searchBar));

    }
    private void setWindowCenter() {
        searchResults = new MyTableView<>(
                new MyTableColumn("First Name", "firstName"),
                new MyTableColumn("Last Name", "lastName"),
                new MyTableColumn("Email", "email"),
                new MyTableColumn("User Name", "userName"),
                new MyTableColumn("Password", "password")
        );

        DataBaseManagement.getInstance().createTable("SchoolAdminAccount",// use ur normalized table form
                new Column("firstName", "String", 15),
                new Column("lastName", "String", 15),
                new Column("Email", "String", 15),
                new Column("User Name", "String", 15),
                new Column("Password", "String", 15)
        );
        try {
            searchResults.setItem(DataBaseManagement.getInstance().fetchColumnsFromSchoolAdminAccount("*")); // remember
        } catch (NullPointerException e) {
            System.out.println("Empty SchoolAdminAccount List");
        }
        window.setCenter(searchResults.getTableView());



    }


    public static String getComparingColumn(int i) {
        if (i == 1) return "lastName";
        else if (i == 2) return "User Name";
        else if (i == 3) return "Password";
        else return "firstName";

    }




}
