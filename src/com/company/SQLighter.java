package com.company;
import java.sql.*;
import java.util.*;


public class SQLighter {

    private Connection connection;
    private static final String CON_STR = "jdbc:sqlite:C:/Users/hp/IdeaProjects/untitled3/src/com/company/sqliteDB.db";

    private static SQLighter instance;

    public  static  synchronized SQLighter getInstance() throws SQLException{
        if (instance == null)
            instance = new SQLighter();
        return instance;
    }

    

}
