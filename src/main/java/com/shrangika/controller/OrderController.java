package com.shrangika.controller;

import com.shrangika.model.CartItem;
import com.shrangika.model.Order;
import com.shrangika.model.User;
import com.shrangika.request.AddCartItemRequest;
import com.shrangika.request.OrderRequest;
import com.shrangika.service.OrderService;
import com.shrangika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> addItemToCart(@RequestBody OrderRequest req,
                                               @RequestHeader("Authentication") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> addItemToCart(
                                                     @RequestHeader("Authentication") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        List<Order> order=orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
