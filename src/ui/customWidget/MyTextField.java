package ui.customWidget;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MyTextField {
    private HBox hBox;
    private Label label;
    private TextField textField;

    public MyTextField(String name) {
        label = new Label(name);
        label.setMinWidth(60);
        textField = new TextField();
        hBox = new HBox();
        hBox.getChildren().addAll(label, textField);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(20);
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

}
