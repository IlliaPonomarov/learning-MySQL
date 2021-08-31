package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseHandler db = new DatabaseHandler();

        Scanner in = new Scanner(System.in);
        System.out.println("SELECT STUDENTS or TEACHER: ");
        String select_table = in.nextLine();


        db.output(select_table.toLowerCase());
    }
}
