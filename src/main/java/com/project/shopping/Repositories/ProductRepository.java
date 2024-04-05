package com.project.shopping.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.shopping.Entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
