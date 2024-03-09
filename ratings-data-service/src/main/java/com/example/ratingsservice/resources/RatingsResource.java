package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ratingsservice.models.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @RequestMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) throws SQLException {
        Statement st =DBConnection.connect();
        System.out.println("select * from Rating where userId="+"\'"+userId+"\'");
        ResultSet res=st.executeQuery("select * from Rating where userId="+"\'"+userId+"\'");
        List<Rating> ratings=new ArrayList<>();
        while(res.next()){
            ratings.add(new Rating(res.getString(2),Integer.parseInt(res.getString(3))));
        }

        return new UserRating(ratings);
    }

    @RequestMapping("/{topElement}")
    public UserRating getTopRating(@PathVariable int topElement) throws SQLException {
        Statement st =DBConnection.connect();
//        System.out.println("select * from Rating where userId="+"\'"+userId+"\'");
        ResultSet res=st.executeQuery("SELECT movieId, CEIL(AVG(rating)) AS average_value  FROM Rating" +
                "  GROUP BY movieId ORDER BY average_value DESC  LIMIT 2 ;");
        List<Rating> ratings=new ArrayList<>();
        while(res.next()){
            ratings.add(new Rating(res.getString(1),Integer.parseInt(res.getString(2))));
        }

        return new UserRating(ratings);
    }
}
