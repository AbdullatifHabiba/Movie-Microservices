package com.moviecatalogservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class DBConnection  {
    public static Statement connect(){
        String url="jdbc:mysql://localhost:3306/RATINGS";
        String username="abdu";
        String filePath = "/media/abdu/01D6630A06A5A4B0/Last year/BigData/lab2/spring-boot-microservices/ratings-data-service/src/main/java/com/example/ratingsservice/models/Password.txt";
        String password="";
        try {
            // Open the file for reading
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the password from the file
            password = bufferedReader.readLine();

            // Close the file
            bufferedReader.close();

           

        } catch (IOException e) {
            // Handle potential exceptions (e.g., file not found, IO error)
            e.printStackTrace();
        }

        try{
//            System.out.println(password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =DriverManager.getConnection(url,username,password);
            System.out.println(con.createStatement());
            Statement statement =con.createStatement();
            return statement;
        }catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }


}
