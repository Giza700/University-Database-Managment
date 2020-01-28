import database.DataBaseManagement;

public class Testing {
    public static void main(String[] args) {
        DataBaseManagement.getInstance().openDataBase();
        DataBaseManagement.getInstance().getDepartments();
    }
}
