package ui.customWidget;

import assistingclasses.Constants;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import models.Program;



public class ComboList {
    private VBox vBox;
    private ComboBox<String> collages;
    private ComboBox<String> departments;
    private ComboBox<String> programs;
    private ComboBox<Integer> years;
    private ComboBox<String> sections;

    public void setUPComboList(ChangeListener<String> onCollegeSelected,
                               ChangeListener<String> onDepartmentSelected,
                               ChangeListener<String> onProgramSelected,
                               ChangeListener<Integer> onYearSelected,
                               ChangeListener<String> onSectionSelected,
                               EventHandler<ActionEvent> onClearButtonClicked
    ) {

        ObservableList<String> collage = FXCollections.observableArrayList();
        collage.addAll(Constants.colleges);

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

        Button clearButton = new Button("Clear");
        clearButton.setId("clearComboButton");
        clearButton.getStyleClass().add("ui/css/label.css");
        clearButton.setOnAction(onClearButtonClicked);

        vBox.getChildren().addAll(collages, departments, programs, years, sections, clearButton);
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

    public ComboBox<String> getPrograms() {
        return programs;
    }

    public ComboBox<Integer> getYears() {
        return years;
    }

    public ComboBox<String> getSections() {
        return sections;
    }

}
