package assistingclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Program;

import java.time.LocalDate;

public class Constants {

    public static final String[] REGISTRAR_INPUTS = {
            "First Name",
            "Last Name",
            "Email",
            "Username",
            "Password"
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
}


