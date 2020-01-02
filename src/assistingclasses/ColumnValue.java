package assistingclasses;

public class ColumnValue<T> {
    private String columnName;
    private T value;

    public ColumnValue(T value, String columnName) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;//TODO try to make setValue return this making columnName assignment optional
    }
}
