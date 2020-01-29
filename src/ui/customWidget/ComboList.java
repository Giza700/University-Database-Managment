package ui.customWidget;

import assistingclasses.Constants;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import models.Program;


public class ComboList {
    private VBox vBox;
    private ComboBox<String> collages;
    private ComboBox<String> departments;
    private ComboBox<Program> programs;
    private ComboBox<Integer> years;
    private ComboBox<String> sections;

    public void setUPComboList(ChangeListener<String> onCollegeSelected,
                     ChangeListener<String> onDepartmentSelected,
                     ChangeListener<Program> onProgramSelected,
                     ChangeListener<Integer> onYearSelected,
                     ChangeListener<String> onSectionSelected
    ) {

        ObservableList<String> collage = FXCollections.observableArrayList();
        collage.addAll(Constants.colleges);

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

        collages = new ComboBox<>(collage);
        departments = new ComboBox<>();
        programs = new ComboBox<>();
        years = new ComboBox<>();
        sections = new ComboBox<>();

        collages.setPromptText("Collage");

        collages.valueProperty().addListener(onCollegeSelected);
        departments.valueProperty().addListener(onDepartmentSelected);
        programs.valueProperty().addListener(onProgramSelected);
        years.valueProperty().addListener(onYearSelected);
        sections.valueProperty().addListener(onSectionSelected);

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

    public ComboBox<String> getCollages() {
        return collages;
    }

    public ComboBox<String> getDepartments() {
        return departments;
    }

    public ComboBox<Program> getPrograms() {
        return programs;
    }

    public ComboBox<Integer> getYears() {
        return years;
    }

    public ComboBox<String> getSections() {
        return sections;
    }

}
