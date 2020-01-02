package ui.customWidget;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class CheckBoxGrid {
    private GridPane checkBoxGrid;

    public GridPane getCheckBoxGrid() {
        return checkBoxGrid;
    }

    public CheckBoxGrid(String... name) {

        checkBoxGrid = new GridPane();
        checkBoxGrid.setMaxHeight(50);
        checkBoxGrid.setVgap(5);
        checkBoxGrid.setHgap(5);
        checkBoxGrid.setPadding(new Insets(5));

        for (int i = 0, k = 0; i < name.length; i += 2) {
            try {
                int j = 0;
                CheckBox checkBox = new CheckBox(name[i]);
                checkBox.getStyleClass().add("checkBox");
                checkBox.getStylesheets().add("./ui/css/label.css");
                checkBox.setId(name[i]);
                checkBox.selectedProperty().addListener(observable -> makeMutuallyExclusive(checkBox));
                GridPane.setConstraints(checkBox, k, j);
                checkBoxGrid.getChildren().add(checkBox);

                j++;

                CheckBox checkBox2 = new CheckBox(name[i + 1]);
                checkBox2.getStyleClass().add("checkBox");
                checkBox2.getStylesheets().add("./ui/css/label.css");
                checkBox2.setId(name[i + 1]);
                checkBox2.selectedProperty().addListener(observable -> makeMutuallyExclusive(checkBox2));
                GridPane.setConstraints(checkBox2, k, j);
                checkBoxGrid.getChildren().add(checkBox2);
                k++;
            } catch (IndexOutOfBoundsException e) {

            }
        }

    }

    public boolean getIsXChecked(String textFieldId) {

        ObservableList<Node> list = checkBoxGrid.getChildren();
        for (Node node : list) {
            if (node.getId().equals(textFieldId)) {
                return ((CheckBox) node).isSelected();
            }
        }
        return false;
    }

    public void makeMutuallyExclusive(CheckBox checkBox) {
        ObservableList<Node> checkBoxes = checkBoxGrid.getChildren();
        for (Node node : checkBoxes) {
            if (node == checkBox) continue;
            else ((CheckBox) node).setSelected(false);
        }
    }
}
