package com.project.shopping.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopping.Entities.Coupon;
import com.project.shopping.Repositories.CouponRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CouponService {
	
	  @Autowired
	  CouponRepository couponRepository;
	  
	  public Coupon addCoupon(Coupon coupon) {
		  Coupon c = new Coupon();
		  c.setCode(coupon.getCode());
		  c.setDiscount(coupon.getDiscount());
		return couponRepository.save(c);
	  }
//	  
//	  public Coupon getCoupon(String code) {
//	    	Optional<Coupon> coupon =  couponRepository.findCouponByCode(code);
//	
//			if (coupon.isPresent()) {
//				return coupon.get();
//			}
//			throw  new EntityNotFoundException("Coupon not found");
//	    }

		 public Map<String, Integer> getAllCoupons() {
		        List<Coupon> coupons = couponRepository.findAll();
		        Map<String, Integer> couponMap = new HashMap<>();
		        for (Coupon coupon : coupons) {
		            couponMap.put(coupon.getCode(), coupon.getDiscount());
		        }
		        return couponMap;
		 
	}
}
