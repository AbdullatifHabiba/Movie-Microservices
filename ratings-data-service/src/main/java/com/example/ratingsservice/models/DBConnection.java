package com.example.ratingsservice.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DBConnection  {
    public static Statement connect(){
        String url="jdbc:mysql://localhost:3306/RATINGS";
        String username="mohamed";
        String filePath = "/home/mohamed/spring-boot-microservices/" +
                "ratings-data-service/src/main/java/com/example/ratingsservice/models/Password.txt";
        String password="";
        try {
            // Open the file for reading
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the password from the file
            password = bufferedReader.readLine();

            // Close the file
            bufferedReader.close();

            // Print or use the password as needed
            System.out.println("Password: " + password);

        } catch (IOException e) {
            // Handle potential exceptions (e.g., file not found, IO error)
            e.printStackTrace();
        }

        try{

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
        public static void main(String[] args) throws SQLException, JsonProcessingException {
        Statement st =DBConnection.connect();
        ResultSet res=st.executeQuery("SELECT movieId, CEIL(AVG(rating)) AS average_value  FROM Rating" +
                "  GROUP BY movieId ORDER BY average_value DESC  LIMIT 2 ;");
        List<Rating> ratings=new ArrayList<>();
        while(res.next()){
            ratings.add(new Rating(res.getString(1),Integer.parseInt(res.getString(2))));
        }
        System.out.println(ratings);
    }

}