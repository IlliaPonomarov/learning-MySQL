package com.company;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.String;


public class DatabaseHandler extends Configs {

        Connection connection;
        Statement statement;
        private static DatabaseHandler instance;


        public static synchronized DatabaseHandler getInstance() throws SQLException{

            if (instance == null)
                instance = new DatabaseHandler();

            return instance;
        }

    {
        //jdbc:mysql://127.0.0.1:3306/?user=root
        try {
            String MS_DRIVER = "com.mysql.jdbc.Driver";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/university", dbUser, dbPass);
            statement = connection.createStatement();

            System.out.println("Connected.");

        } catch (SQLException throwables) {
            System.out.println("Connected failed ...");

            throwables.printStackTrace();
        }
    }
    public void output(String txt){

        try {

            // MYSQL COMMANDS

           String sql1 = "SELECT * FROM table_name";
           String sql2 = "SELECT * FROM table_name WHERE column_name = value";
           String sql3 = "SELECT column_name FROM table_name";
           String sql4 = "SELECT count(*) FROM table_name";
            String sql5 = "SELECT * FROM table_name LIMIT 2,3";
            String sql6 = "SELECT * FROM table_name ORDER BY id";
            String sql7 = "SELECT * FROM table_name ORDER BY id DESC";
            ;

            ResultSet resultSet = statement.executeQuery("SELECT * FROM teacher ORDER BY idt DESC ");

            while (resultSet.next()){
                System.out.println(resultSet.getString("namet"));
                System.out.println(resultSet.getString("surnamet"));
                System.out.println(resultSet.getString("subjectst"));
                System.out.println(resultSet.getString("gender"));
                System.out.println();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void insertSQL(String name, String surname, String subjects, String gender){


        String sql_insert = "INSERT INTO table_names (column_1, coumn_2, column_3) VALUES (?, ?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teacher (namet, surnamet, subjectst, gender) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, subjects);
            preparedStatement.setString(4, gender);

            int row = preparedStatement.executeUpdate();
            System.out.printf("Insert %d elements", row);

        }catch (SQLException throwsable){
            throwsable.printStackTrace();
        }
    }

    public void delete(int id){
            String delete = "DELETE FROM teacher WHERE idt = " + id;

            try {
                int resultSet = statement.executeUpdate(delete);
                System.out.println("We did delete " + resultSet);
                output("teacher");
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }

    }

    public void update(int id){
            String rows = "UPDATE teacher SET subjectst = \'informatics\' ";

            try{
                int columns_update = statement.executeUpdate(rows);
                System.out.println(columns_update);
            }catch (SQLException throwsable){throwsable.printStackTrace();}


    }




}
