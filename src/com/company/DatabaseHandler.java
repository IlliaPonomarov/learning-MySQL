package com.company;


//Don't forget to connect the SQL library
import java.sql.*;
import java.lang.String;


public class DatabaseHandler extends Configs {

        // This variable is needed to connect to the database
        Connection connection;

        // statement is needed to send queries to sql server
        Statement statement;

        // we will use the singleton pattern,
        // so as not to create unnecessary copies for our database (Singleton)
        private static DatabaseHandler instance;

        // This function checks if more than one instance exists
        public static synchronized DatabaseHandler getInstance() throws SQLException{

            if (instance == null)
                instance = new DatabaseHandler();

            return instance;
        }


        //jdbc:mysql://127.0.0.1:3306/?user=root

        private DatabaseHandler() {
        try {

            // to connect to the Database, you need to specify the "connection address".
            // You can write the link yourself, you can find all the data you need in the MySQL Workbench.
            // If you can't connect, then you probably didn't write the link correctly or didn't download the JDBC driver.
            connection = DriverManager.getConnection("jdbc:mysql://localhost/university", dbUser, dbPass);
            statement = connection.createStatement();

            System.out.println("Connected.");

            // SQLException is needed so that we can see where in the code we have problems or we have a connection error.
        } catch (SQLException throwables) {
            System.out.println("Connected failed ...");

            throwables.printStackTrace();
        }
    }

    // In this function, we will display all the data we added.
    // If your table is empty, then nothing will be displayed.
    // We'll use the insert function to add data
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

            // Using 'ResultSet' we will get data from the table
            // executeQuery is responsible for the SELECT query,
            // in the future we will also use executeUpdate, which is responsible for UPDATE, DELETE, INSERT requests
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

        // This is what the request for adding new data looks like.
        // I wrote all sql queries in a separate sql file 'commands' INSERT INTO table_names (column_1, coumn_2, column_3) VALUES (?,?,?) ";
        try{

            // In addition to the Statement class in java.sql, we can use another class to execute queries - PreparedStatement.
            // In addition to actually executing the request, this class allows you to prepare a request, format it properly.
            // In this case, data is entered from the console and then added to the database.
            // To create a PreparedStatement object, use the prepareStatement () method of the Connection class. This method is passed the expression
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


    // After the above examples, I hope that you are already clear what is happening in this function.
    // If not, review all the functions above again.
    public void delete(int id){

            // This is how the request for deleting data looks like
            String delete = "DELETE FROM teacher WHERE idt = " + id;

            try {
                int resultSet = statement.executeUpdate(delete);
                System.out.println("We did delete " + resultSet);
                output("teacher");
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }

    }

    // After the above examples, I hope that you are already clear what is happening in this function.
    // If not, review all the functions above again.

    public void update(int id){

            // This is how the request to update the data looks like
            // subjectst is the name of the column, informatics is the line to which we will change the basic data
            String rows = "UPDATE teacher SET subjectst = \'informatics\' ";

            try{
                int columns_update = statement.executeUpdate(rows);
                System.out.println(columns_update);
            }catch (SQLException throwsable)
            {
                throwsable.printStackTrace();
            }


    }




}
