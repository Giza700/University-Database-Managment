package ui.customWidget;

import assistingclasses.MyTableColumn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class MyTableView<T> {

    private TableView<T> tableView;

    public MyTableView(MyTableColumn... columns) {
        tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");
        tableView.getStylesheets().add("./ui/css/label.css");

        TableColumn<T, Integer> noColumn = new TableColumn<>("#");
        noColumn.setMaxWidth(30);
        noColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getTableView().getItems().indexOf(param.getValue()) + 1));

        tableView.getColumns().add(noColumn);

        for (MyTableColumn column : columns) {

            TableColumn newColumn = new TableColumn<>(column.getColumnName());

            newColumn.setCellValueFactory(new PropertyValueFactory<>(column.getModelAttributeName()));
             tableView.getColumns().add(newColumn);
        }
    }

    public void setItem(ObservableList<T> item) {
        tableView.setItems(item);
    }
    public TableView.TableViewSelectionModel<T> getSelectionModels(){
        return tableView.getSelectionModel();
    }

    public TableView<T> getTableView() {
        return tableView;
    }


}
