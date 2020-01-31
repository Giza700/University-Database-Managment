package ui.customWidget;

import assistingclasses.Constants;
import database.DataBaseManagement;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchTool {

    private VBox searchBar;
    private TextField search;
    private RadioButtonGrid radioButtonGrid;

    public SearchTool(String[] radioButtonLabels) {

        search = new TextField();

        radioButtonGrid = new RadioButtonGrid();
        for (String string : radioButtonLabels) {
            radioButtonGrid.addRadioButton(string);
        }
        search.setMinWidth(400);
        search.setPromptText("Search");

        HBox searchRow = new HBox();
        searchRow.setSpacing(5);
        searchRow.getChildren().addAll(search, radioButtonGrid.getRadioButtonGrid());

        searchBar = new VBox(searchRow, new Separator());
        searchBar.setPadding(new Insets(10, 0, 0, 10));
    }
    public void setOnSearch(ChangeListener<? super String> onSearchTextChanged){
        search.textProperty().addListener(onSearchTextChanged);
    }
    public VBox getSearchBar() {
        return searchBar;
    }

    public RadioButton getSelectedRadioButton() {
        return radioButtonGrid.getSelectedRadio();
    }

}
