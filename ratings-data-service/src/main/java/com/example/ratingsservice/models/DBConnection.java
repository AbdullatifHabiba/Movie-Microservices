package com.example.ratingsservice.models;

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

            // Print or use the password as needed
            System.out.println("Password: " + password);

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
        public static void main(String[] args) throws SQLException, JsonProcessingException {

            Set<Integer> uniqueuser = new HashSet<>();
            Set<Integer> uniqueMovieID = new HashSet<>();
            ArrayList<Integer> randRating =new ArrayList<>();
            // Create a Random object
            Random random = new Random();
            for(int i=100;i<500;i++) {
                // Generate a random number between 100 and 999 (inclusive)
                int randomNumber = random.nextInt(1000) + 20;
                int randomNumber2 = random.nextInt(1000) + 20;
                int randomNumber3 = random.nextInt(5)+1;
                uniqueuser.add(randomNumber);
                uniqueMovieID.add(randomNumber2);
                randRating.add(randomNumber3);
                // Print the generated random number
                System.out.println("Random Number: " + randomNumber+"  "+randomNumber2+" "+randomNumber3);
            }
            List<Integer> userID = new ArrayList<>(uniqueuser);
            List<Integer> movieID = new ArrayList<>(uniqueMovieID);
            try {
                Statement state = DBConnection.connect();
              for (int i=0;i<400;i++){
                    String query_user = "insert into UserInfo values(" + "\'" + userID.get(i) + "\'" +",\'mohamed\');";
                    String query = "insert into Rating values(" + "\'" + userID.get(i) + "\'" +
                            "," + "\'" + movieID.get(i) + "\'" + "," + randRating.get(i) + ");";
                  System.out.println(query_user);
                  state.executeUpdate(query_user);
                  state.executeUpdate(query);
                  System.out.println("Successfully registered");
                }
            }catch(Exception e)
            {
                System.out.println("Error");
            }

    }

}
