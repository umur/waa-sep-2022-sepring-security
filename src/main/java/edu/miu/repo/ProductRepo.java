package edu.miu.repo;


import edu.miu.entity.Category;
import edu.miu.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {

    @Query("select  p FROM Product p where p.price >= ?1")
    public List<Product> findByPriceGreaterThan(float price);

//    @Query("select p FROM Product p where p.reviews.size >= :num")
//    public List<Product> findProductWithReviews(int num);

    @Query("SELECT p from Product p where p.price <= ?1 and p.category.name=?2")
    public List<Product> findByCategoryAndPriceLessThan (float price);
//    public List<Product> findByCategoryAndPriceLessThan()
    public List<Product> findByNameContaining(String s);


//    public List<ProductDto> findByUserId(int id);
    /*
    * understand how query work in java and be able to annotate this*/
//    @Query(value = "select * from Product where name = :name", nativeQuery = true)
//    List<Product> findHaveReviewMoreThan(int n);



}
