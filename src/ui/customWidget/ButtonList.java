package ui.customWidget;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonList {
    public HBox getHBox() {
        return hBox;
    }

    private HBox hBox;

    public ButtonList(MyButton... buttons) {
        hBox = new HBox();
        hBox.setMinWidth(100);
        hBox.setPadding(new Insets(10, 0, 0, 10));


        for (MyButton myButton : buttons) {
            hBox.getChildren().add(myButton.getButton());
            hBox.getChildren().add(new Separator(Orientation.VERTICAL));
        }

    }
}
