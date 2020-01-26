package ui.customWidget;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class RadioButtonGrid {
    private GridPane radioButtonGrid;
    private RadioButton[] radioButtons;

    public GridPane getRadioButtonGrid() {
        return radioButtonGrid;
    }

    public RadioButtonGrid(String... name) {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonGrid = new GridPane();
        radioButtons = new RadioButton[name.length];
        radioButtonGrid.setMaxHeight(50);
        radioButtonGrid.setVgap(5);
        radioButtonGrid.setHgap(5);
        radioButtonGrid.setPadding(new Insets(5));

        for (int i = 0, k = 0; i < name.length; i += 2) {
            try {
                int j = 0;
                RadioButton radioButton = new RadioButton(name[i]);
                radioButtons[i] = radioButton;
                radioButton.getStyleClass().add("checkBox");
                radioButton.getStylesheets().add("./ui/css/label.css");
                radioButton.setToggleGroup(toggleGroup);
                radioButton.setId(name[i]);
                GridPane.setConstraints(radioButton, k, j);
                radioButtonGrid.getChildren().add(radioButton);

                j++;

                RadioButton radioButton1 = new RadioButton(name[i + 1]);
                radioButtons[i + 1] = radioButton1;
                radioButton1.getStyleClass().add("checkBox");
                radioButton1.getStylesheets().add("./ui/css/label.css");
                radioButton1.setToggleGroup(toggleGroup);
                radioButton1.setId(name[i + 1]);
                GridPane.setConstraints(radioButton1, k, j);
                radioButtonGrid.getChildren().add(radioButton1);
                k++;
            } catch (IndexOutOfBoundsException e) {

            }
        }
        radioButtons[0].setSelected(true);
    }

    public int getSelectedRadio() {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) return i;
        }
        return 0;
    }

}
