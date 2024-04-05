package com.project.shopping.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.shopping.Entities.Coupon;
import com.project.shopping.Entities.Orders;
import com.project.shopping.Entities.Product;
import com.project.shopping.Repositories.CouponRepository;
import com.project.shopping.Repositories.OrdersRepository;
import com.project.shopping.Repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdersService {

	@Autowired
	OrdersRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CouponRepository couponRepository;

	public Orders placeOrder(int userId, int quantity, String couponCode) {

		Product product = productRepository.findById(2)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));

		// Check if quantity is valid
		if (quantity <= 0 || quantity > product.getAvailableQuantity()) {
			throw new IllegalArgumentException(
					"Required Quantity " + quantity + " is more than available i.e " + product.getAvailableQuantity());
		}
		double totalAmount = quantity * product.getPrice();

		// Check if coupon is valid
		if (couponCode != null && !couponCode.isEmpty()) {
			Coupon coupon = couponRepository.findCouponByCode(couponCode);
			if (coupon == null) {
				throw new EntityNotFoundException("Coupon not found with code: " + couponCode);
			}
			// Apply coupon discount
			double discount = coupon.getDiscount() / 100.0;
			totalAmount *= (1 - discount);
		}

		// Create and save order
		Orders order = new Orders();
		order.setAmount(totalAmount);
		order.setCoupon(couponCode);
		order.setQuantity(quantity);
		order.setUserId(userId);
		order.setDate(LocalDate.now().toString());
		order.setStatus("pending");// Assuming order status is pending until payment is made
		orderRepository.save(order);

		// Update product availability
		product.setOrdered(product.getOrdered() + quantity);
		product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
		productRepository.save(product);

		return order;
	}

	// Mock payment processing
	private int generateRandomStatusCode() {
		// Return 200, 400, or 504 randomly
		return new Random().nextInt(3) * 200 + 200;
	}
	// Mock method to generate transaction ID
    private String generateTransactionId() {
        // Generate a random transaction ID for demonstration
        return "tran" + UUID.randomUUID().toString().replaceAll("-", "");
    }


	public Orders makePayment(int userId, int orderId, double amount) {

		Orders order = orderRepository.findByUserIdAndOrderId(userId, orderId);
		if (order == null) {
			throw new EntityNotFoundException("Order not found");
		}

		int code = generateRandomStatusCode();
		String transactionId = generateTransactionId();

		if(amount!=order.getAmount()) {
			order.setStatus(" Failed ");
   	       order.setTransactionId(transactionId);
   		 order.setDescription("Payment Failed as amount is invalid");
		}
		
		else if(amount == order.getAmount()& orderId == order.getOrderId()) {
			  order.setStatus("Successfull");
              order.setTransactionId(transactionId);
              order.setDescription("Payment Successfull");
		}
		else if (code == 504) {
			order.setStatus("Failed");
            order.setTransactionId(transactionId);
            order.setDescription("No response from payment server");
           
		}
		else {
			order.setStatus("Failed");
            order.setTransactionId(transactionId);
            order.setDescription("Order is already paid for");
           
		}
   
		return orderRepository.save(order);
		
	
	}

	public List<Orders> getUserOrders(int userId) {
		return orderRepository.findByUserId(userId);
	}

	public Orders getOrder(int userId, int orderId) {
		if (orderRepository.findByUserIdAndOrderId(userId, orderId) != null) {
			return orderRepository.findByUserIdAndOrderId(userId, orderId);
		}
		throw new EntityNotFoundException("Order not found");
	}

}
