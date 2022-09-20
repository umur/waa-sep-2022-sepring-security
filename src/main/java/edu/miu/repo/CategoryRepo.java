package edu.miu.repo;

import edu.miu.entity.Category;
import edu.miu.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Query("select c.products from Category c where c.id = :id")
    List<Product> findAllProductsByCategoryId(int id);
}
