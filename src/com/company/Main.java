package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseHandler db = null;
        try {
            db = DatabaseHandler.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        System.out.println("SELECT STUDENTS or TEACHER: ");
        String select_table = in.nextLine();


        db.output(select_table.toLowerCase());
        db.insertSQL("Kevin", "Hellless", "PPI, ZPRPR2", "Male");
        db.insertSQL("Jim", "Cinderblood", "ADM", "Female");
        db.insertSQL("Marcie", "Lonetide", "IVZDEL", "Male");
        db.insertSQL("Juan", "Hawkridge", "ML", "Female");

    }
}
