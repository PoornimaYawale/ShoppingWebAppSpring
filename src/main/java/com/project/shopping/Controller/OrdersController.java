package com.project.shopping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopping.Entities.Orders;
import com.project.shopping.Services.OrdersService;


@RestController
@RequestMapping("/Odd")
public class OrdersController {

	 @Autowired
	  OrdersService orderService;
	 

	    @PostMapping("/{userId}/orders")
	    public ResponseEntity<Orders> placeOrder(@PathVariable int userId,
	                                            @RequestParam int qty,
	                                            @RequestParam String couponcode) {
	    	
	    	Orders order = orderService.placeOrder(userId, qty, couponcode);
	        return ResponseEntity.ok(order);
	    }

	    @PostMapping("/{userId}/{orderId}/pay")
	    public ResponseEntity<Orders> makePayment(@PathVariable int userId,
	                                             @PathVariable int orderId,
	                                             @RequestParam double amount) {
	        Orders order = orderService.makePayment(userId, orderId, amount);
	        return ResponseEntity.ok(order);
	    }

	    @GetMapping("/{userId}/allorders")
	    public ResponseEntity<List<Orders>> getUserOrders(@PathVariable int userId) {
	        List<Orders> userOrders = orderService.getUserOrders(userId);
	        return ResponseEntity.ok(userOrders);
	    }

	    @GetMapping("/{userId}/{orderId}")
	    public ResponseEntity<Orders> getOrder(@PathVariable int userId, @PathVariable int orderId) {
	        Orders order = orderService.getOrder(userId, userId);
	        return ResponseEntity.ok(order);
	    }
}
