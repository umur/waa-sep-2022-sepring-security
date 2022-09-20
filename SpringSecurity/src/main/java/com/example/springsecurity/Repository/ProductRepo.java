package com.example.springsecurity.Repository;


import com.example.springsecurity.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    public List<Product> findProductByPriceLessThan(double price);
    public List<Product>findByNameContaining(String name);

   @Query(value = "select * from product  where category_id=:id and price<:price", nativeQuery = true)
   public List<Product>findProductinCategoryAndPriceLessThan(@Param("id") int categoryid, @Param("price") double price);

}
