package ui;

import assistingclasses.Constants;

import java.time.LocalDate;

public class Validation {

    public static String validateName(String name) {
        if (name.equals("")) return "Name filed can't be empty";
        else if (name.contains("/"))
            return "Name can't contain special character";// TODO Add a method for all special characters
        else return null;
    }

    public static String validateId(String id) {
        if (id.equals("")) return "Id filed can't be empty";
        else if (id.length() < 11 || idIsInValid(id)) return "Invalid Id Number";
        else return null;
    }

    public static String validateSex(String sex) {
        if (!(sex.equals("Male") || sex.equals("Female") || sex.equals("M") || sex.equals("F")))
            return "Please Check the sex field";
        else return null;
    }

    public static String validateYear(int year) {
        if (year > 6) return "Invalid Year";
        else return null;
    }

    public static String validateDob(String dob) {
        LocalDate localDate = Constants.getLocalDateFromString(dob);
        if (localDate == null) return "Invalid DOB";
        LocalDate currentDate = LocalDate.now();
        if (localDate.getYear() > currentDate.getYear() - 17) return "Small DOB";
        else return null;
    }

    public static String validatePhone(int phone) {
        if (phone / 1000000000 > 1 /*|| phone / 1000000000 < 0.9*/) return "Invalid phone Number";
        else return null;
    }

    private static boolean idIsInValid(String id) {
        char characters[] = id.toCharArray();
        if (characters[3] == '/' && characters[8] == '/') return false;// TODO Add more conditions
        else return true;
    }
    public static String validateuserName(String userName) {
        if (userName.equals("")) return "User Name filed can't be empty";
        else if (userName.contains("/"))
            return "User Name  can't contain special character";// TODO Add a method for all special characters
        else return null;
    }

    public static String validatePassword(String password) {
        if (password.equals("")) return "password field can't be empty";
        else if (password.contains("/"))
            return "Password can't contain special character";// TODO Add a method for all special characters
        else return null;
    }
    public static String validateLetterGrade(String letterGrade){
        char[] letterGradee = letterGrade.toCharArray();
        char letterGradee2 = letterGradee[0];
        if(letterGrade.equals("")) return "Grade field cant be empty";
        else if(Character.isDigit(letterGradee2)) return "Grade field can't be an integer";
        //TODO Add a checlist for grades above F
        else return null;
    }
}
