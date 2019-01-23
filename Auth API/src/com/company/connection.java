package com.company;
import java.sql.*;

public class connection {

    private  final String URL="jdbc:mysql://localhost:3306/accounts";
    private  final String USERNAME="root";
    private  final String PASSWORD="root";

    private  Connection connection;



        public connection(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
