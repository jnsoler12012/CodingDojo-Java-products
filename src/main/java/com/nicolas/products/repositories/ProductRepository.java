package com.nicolas.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nicolas.products.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findByOrderByName();
	@Query("SELECT p from Product p WHERE p.id NOT IN (SELECT p.id FROM Product p LEFT OUTER JOIN p.categories c WHERE c.id = :categoryId) ORDER BY p.name")
	List<Product> findAllNotAssoc(@Param("categoryId") Long categoryId);
}
