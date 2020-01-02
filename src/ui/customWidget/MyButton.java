package ui.customWidget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class MyButton {
    private Button button;

    public Button getButton() {
        return button;
    }

    public MyButton(String name, EventHandler<ActionEvent> onButtonClick) {
        button = new Button(name);
        button.setOnAction(onButtonClick);
        button.setUnderline(true);
        button.getStyleClass().add("transparentButton");
        button.getStylesheets().add("./ui/css/label.css");
    }
}
