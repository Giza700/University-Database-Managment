package models;

public enum Sex {
    Male,
    Female;

    public static Sex getSexObject(String sex) {
        if (sex.equals("Male")||sex.equals("M")) return Sex.Male;
        else return Sex.Female;
    }

}
