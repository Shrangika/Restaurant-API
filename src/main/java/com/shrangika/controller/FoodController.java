package com.shrangika.controller;

import com.shrangika.model.Food;
import com.shrangika.model.Restaurant;
import com.shrangika.model.User;
import com.shrangika.request.CreateFoodRequest;
import com.shrangika.service.FoodService;
import com.shrangika.service.RestaurantService;
import com.shrangika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Food> food=foodService.searchFood(name);
        return new ResponseEntity<>(food, HttpStatus.OK);

    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean  vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Food> food=foodService.getRestaurantsFood(restaurantId,vegetarian,nonVeg,seasonal,food_category);
        return new ResponseEntity<>(food, HttpStatus.OK);

    }
}
