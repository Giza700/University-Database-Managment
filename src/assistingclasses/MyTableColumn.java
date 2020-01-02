package assistingclasses;

public class MyTableColumn {
    private String columnName;
    private String modelAttributeName;

    public MyTableColumn(String columnName, String modelAttributeName) {
        this.columnName = columnName;
        this.modelAttributeName = modelAttributeName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getModelAttributeName() {
        return modelAttributeName;
    }

    public void setModelAttributeName(String modelAttributeName) {
        this.modelAttributeName = modelAttributeName;
    }


}
