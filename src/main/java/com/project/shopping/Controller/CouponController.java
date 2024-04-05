package com.project.shopping.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopping.Entities.Coupon;
import com.project.shopping.Services.CouponService;


@RestController
@RequestMapping("/coupons")
public class CouponController {
	
	@Autowired
    CouponService couponService;

	@PostMapping("/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon){
    	Coupon c = couponService.addCoupon(coupon);
    	return ResponseEntity.ok(c);
    } 
//	@GetMapping("/fetchone/coupon")
//    public ResponseEntity<Coupon> fetchOne(@RequestParam String code) {
//       Coupon coupon = couponService.getCoupon(code);
//        return ResponseEntity.ok(coupon);
//    }
    @GetMapping("/fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        Map<String, Integer> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

}
