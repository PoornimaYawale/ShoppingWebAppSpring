package com.project.shopping.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.shopping.Entities.Coupon;


@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	Coupon findCouponByCode(String code);


}
