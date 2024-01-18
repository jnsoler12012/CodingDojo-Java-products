package com.nicolas.products.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nicolas.products.models.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	List<Category> findByOrderByName();
	@Query("SELECT c FROM Category c WHERE c.id NOT IN (SELECT c.id FROM Category c LEFT OUTER JOIN c.products p WHERE p.id = :productId) ORDER BY c.name")
	List<Category> findAllNotAssoc(@Param("productId") Long productId);
}
