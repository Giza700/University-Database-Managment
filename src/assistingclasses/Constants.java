package assistingclasses;

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
            "House No"
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
}


