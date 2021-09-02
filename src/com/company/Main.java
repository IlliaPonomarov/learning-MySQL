package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseHandler db = new DatabaseHandler();

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
