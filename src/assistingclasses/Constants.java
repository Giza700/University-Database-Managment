package assistingclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import models.Program;

import java.time.LocalDate;

public class Constants {

    public static final String[] REGISTRAR_INPUTS = {
            "First Name",
            "Last Name",
            "Email",
            "User Name",
            "Password"   //
    };
    public static final String[] STUDENT_INPUTS = {
            "First Name",
            "Last Name",
            "ID",
            "Sex",
            "Year",
            "DOB",
            "Phone Number",
            "City",
            "SubCity",
            "Street",
            "House No",
    };
    public static final String[] SCHOOLADMIN_INPUTS = {
            "First Name",
            "Last Name",
            "Email",
            "Username",
            "Password"

    };
    public static final String[] TEACHER_INPUTS = {
            "First Name",
            "Last Name",
            "ID",
            "Sex",
            "DOB",
            "Phone Number",
            "City",
            "SubCity",
            "Street",
            "House No",
            "Salary",
            "Office Number",
            "Rank"
    };
    public static final String[] STUDENTACCOUNT_INPUTS = {
            "First Name",
            "Last Name",
            "Email",
            "Username",
            "Password"

    };
    public static final String[] colleges = {
            "AAIT", "CNCS", "CBE", "CDS", "CEBS", "CHS", "CHLJC", "CLGS", "CSS", "CVMA", "CPVA"
    };

    public static String getLocalDateString(LocalDate localDate) {
        return localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
    }

    public static LocalDate getLocalDateFromString(String dateString) {
        try {
            String[] dateArray = dateString.split("/");
            return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of Bound in Student getLocalDateFromString method");
        }
        return null;
    }
    //TODO replace with data from database

    public static ObservableList<String> getSectionOfYear(int year) {
        if (year == 1) return FXCollections.observableArrayList("A", "B", "C", "D");
        else if (year == 2) return FXCollections.observableArrayList("A", "B", "C");
        else if (year == 3) return FXCollections.observableArrayList("A", "B", "C");
        else if (year == 4) return FXCollections.observableArrayList("A", "B");
        else if (year == 5) return FXCollections.observableArrayList("A");
        else return FXCollections.observableArrayList();
    }
    public static String getComparingColumn(RadioButton radioButton) {
        if (radioButton.getText().equals(Constants.STUDENT_INPUTS[1])) return "lastName";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[2])) return "id";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[3])) return "sex";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[4])) return "year";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[5])) return "dateOfBirth";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[6])) return "phoneNumber";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[7])) return "city";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[8])) return "subCity";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[9])) return "street";
        else if (radioButton.getText().equals(Constants.STUDENT_INPUTS[10])) return "houseNo";
        else if (radioButton.getText().equals(Constants.TEACHER_INPUTS[10])) return "salary";
        else if (radioButton.getText().equals(Constants.TEACHER_INPUTS[11])) return "officeNumber";
        else if (radioButton.getText().equals(Constants.TEACHER_INPUTS[12])) return "rank";
        else if (radioButton.getText().equals(Constants.REGISTRAR_INPUTS[2])) return "email";
        else if (radioButton.getText().equals(Constants.REGISTRAR_INPUTS[3])) return "userName";
        else if (radioButton.getText().equals(Constants.REGISTRAR_INPUTS[4])) return "password";
        else return "firstName";

    }
}


