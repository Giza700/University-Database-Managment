package models.Account;

import java.util.Arrays;

public class Account {

    private String userName;
    private String password;

    public Account(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateUserName(String newUser) {
        //TODO add validation, if staff or ...
        return false;
    }

    public static boolean validatePassword(String newPassword) {
        char[] password = newPassword.toCharArray();
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        for (char c : password) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasUpper = true;
            else if (Character.isUpperCase(c)) hasDigit = true;
        }
        return hasUpper && hasLower && hasDigit && newPassword.length() > 7;
    }

    public static boolean validateEmail(String email) {
        return email.contains("@") && email.contains(".com") && email.length() > 10;
    }

    public static boolean validateName(String name) {
        return Character.isUpperCase(name.charAt(0));
    }

    public static String correctName(String name) {
        char[] nameArray = name.toCharArray();
        if (!Character.isUpperCase(nameArray[0])) {
            nameArray[0] = Character.toUpperCase(nameArray[0]);
        }
        return Arrays.toString(nameArray);
    }

}
