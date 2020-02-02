package ui.customWidget;

import database.DataBaseManagement;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import models.Student;
import ui.pages.customer.registrar.StudentWindow;

import java.util.ArrayList;


public class RadioButtonGrid {
    private GridPane radioButtonGrid;
    private ArrayList<RadioButton> radioButtons;
    private ToggleGroup toggleGroup = new ToggleGroup();

    public GridPane getRadioButtonGrid() {
        return radioButtonGrid;
    }

    public RadioButtonGrid() {
        radioButtonGrid = new GridPane();
        radioButtons = new ArrayList<>();
        radioButtonGrid.setMaxHeight(50);
        radioButtonGrid.setVgap(5);
        radioButtonGrid.setHgap(5);
        radioButtonGrid.setPadding(new Insets(5));

    }

    public void addRadioButton(String radioButton) {
        RadioButton newButton = new RadioButton(radioButton);
        newButton.getStyleClass().add("checkBox");
        newButton.getStylesheets().add("./ui/css/label.css");
        newButton.setToggleGroup(toggleGroup);
        newButton.setId(radioButton);
        radioButtons.add(newButton);
        GridPane.setConstraints(newButton, (radioButtons.size() - 1) / 2, (radioButtons.size() - 1) % 2);
        radioButtonGrid.getChildren().add(newButton);
        if (radioButtons.size() == 1) radioButtons.get(0).setSelected(true);
    }

    public RadioButton getSelectedRadio() {
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isSelected()) return radioButton;
        }
        return radioButtons.get(0);
    }

}
