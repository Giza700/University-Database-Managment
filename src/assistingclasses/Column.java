package assistingclasses;

public class Column {
    private String columnName;
    private String dataType;
    private int constraint;

    public Column(String columnName, String dataType, int constraint) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.constraint = constraint;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setSQLDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSQLDataType() {
        if (dataType.equals("String")) {
            return "varchar(" + constraint + ")";
        } else if (dataType.equals("Int")) {
            return "int";
        } else if (dataType.equals("Double")) {
            return "double";
        } else {
            return "";
        }
    }
}
