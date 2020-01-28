package ui.customWidget;

import assistingclasses.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import models.Program;

public class ComboList {
    private VBox vBox;

    public ComboList() {

        ObservableList<String> collage = FXCollections.observableArrayList();
        collage.addAll("AAIT", "CNCS", "CBE", "CDS", "CEBS", "CHS", "CHLJC", "CLGS", "CSS", "CVMA", "CPVA");

        ObservableList<String> department = FXCollections.observableArrayList();
        department.addAll("SECE", "SCEE", "SMIE");

        ObservableList<Program> program = FXCollections.observableArrayList();
        program.addAll(Program.Under_Grad, Program.Grad, Program.Post_Grad);

        ObservableList<Integer> year = FXCollections.observableArrayList();
        year.addAll(1, 2, 3, 4, 5);

        ObservableList<String> section = FXCollections.observableArrayList();
        section.addAll("A", "B", "C", "D");

        vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setMinWidth(100);

        ComboBox<String> collages = new ComboBox<>(collage);
        ComboBox<String> departments = new ComboBox<>();
        ComboBox<Program> programs = new ComboBox<>();
        ComboBox<Integer> years = new ComboBox<>();
        ComboBox<String> sections = new ComboBox<>();
        collages.setPromptText("Collage");

        collages.valueProperty().addListener((observable, oldValue, newValue) -> {
            departments.setItems(Constants.getDepartmentsOfCollege(newValue));
            departments.setDisable(false);
        });
        departments.valueProperty().addListener((observable, oldValue, newValue) -> {
            programs.setItems(Constants.getProgramOfDepartment(newValue));
            programs.setDisable(false);
        });
        programs.valueProperty().addListener((observable, oldValue, newValue) -> {
            years.setItems(Constants.getYearsOfProgram(newValue));
            years.setDisable(false);
        });
        years.valueProperty().addListener((observable, oldValue, newValue) -> {
            sections.setItems(Constants.getSectionOfYear(newValue));
            sections.setDisable(false);
        });
        sections.valueProperty().addListener((observable, oldValue, newValue) -> {

        });

        departments.setPromptText("Department");
        departments.setDisable(true);
        programs.setPromptText("Program");
        programs.setDisable(true);
        years.setPromptText("Year");
        years.setDisable(true);
        sections.setPromptText("Section");
        sections.setDisable(true);

        vBox.getChildren().addAll(collages, departments, programs, years, sections);
    }

    public VBox getComboList() {
        return vBox;
    }

}
