package com.company;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static String loginREG;
    private static String passwordREG;
    private static String firstnameREG;
    private static String lastnameREG;

    private static String loginLOG;
    private static String loginLOGin;
    private static String firstnameLOG;
    private static String lastnameLOG;
    private static String passwordLOG;
    private static String passwordLOGin;

    private static int logoereg = 0;

    private static String logorregchoosing = "1 to login || 2 to register";
    private static String logorregchoosingerror = "wrong number";

    private static String  loginhintLOG= "login --> ";
    private static String  passwordhintLOG= "password -> ";

    private static String  firstnamehintREG= "first name -> ";
    private static String  lastnamehintREG= "last name -> ";
    private static String  loginhintREG= "login -> ";
    private static String  passwordhintREG= "password -> ";

    private static Scanner input;

    private static Connection connection;
    private static Driver driver;

    private static final String URL="jdbc:mysql://localhost:3306/accounts";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static Statement statement;

    private static String finalaccountdata;
    private static String finalTokendata;

    private static ResultSet rs;

    private static int Token=0;
    private static int Tokenin;

    private static String firstname;
    private static String lastname;

    private static int id;


    public static void main(String[] args) {

        input = new Scanner(System.in);

        System.out.println(logorregchoosing);

        logoereg = input.nextInt();

        if(logoereg == 1)
        {
            try {
                driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM users");
                System.out.println(loginhintLOG);
                loginLOGin = input.next();
                System.out.println(passwordhintLOG);
                passwordLOGin = input.next();
                while (rs.next() ) {
                    id = rs.getInt("id");
                    loginLOG = rs.getString("login");
                    passwordLOG = rs.getString("password");
                    firstname = rs.getString("firstname");
                    lastname = rs.getString("lastname");
                    if (loginLOGin.equals(loginLOG) && passwordLOGin.equals(passwordLOG)) {
                        System.out.println("authorised ");
                        Token = (int) (Math.random()*(999999999-111111111));
                        System.out.println("Your token is -->"+Token);
                        System.out.println("Token generated\n Enter it to see firstname and lastname ");
                        Tokenin = input.nextInt();
                        if(Tokenin == Token){
                            System.out.println("Your first name is --> " + firstname+"\nYour last name is --> "+lastname);
                        }
                    }
                }
                connection.close();
                if(connection.isClosed()){
                    System.out.println("Disconnected");
                }else{
                    connection.close();
                    System.out.println("Disconnected");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            if(logoereg == 2) {
                 try {
                        driver = new FabricMySQLDriver();
                        DriverManager.registerDriver(driver);
                        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                        statement = connection.createStatement();
                        System.out.println(firstnamehintREG);
                        firstnameREG = input.next();
                        System.out.println(lastnamehintREG);
                        lastnameREG = input.next();
                        System.out.println(loginhintREG);
                        loginREG = input.next();
                        System.out.println(passwordhintREG);
                        passwordREG = input.next();
                        finalaccountdata = "INSERT INTO users(firstname, lastname, login, password) values ("+"'"+firstnameREG+"'"+","+"'"+lastnameREG+"'"+","+"'"+loginREG+"'"+","+"'"+passwordREG+"'"+")";
                        statement.execute(finalaccountdata);
                        System.out.println("Account added");
                        connection.close();
                        if(connection.isClosed()){
                            System.out.println("Disconnected");
                        }else{
                            connection.close();
                            System.out.println("Disconnected");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Error with connection");
                    }
            }else{
                System.out.println(logorregchoosingerror);
            }
        }
    }
}
