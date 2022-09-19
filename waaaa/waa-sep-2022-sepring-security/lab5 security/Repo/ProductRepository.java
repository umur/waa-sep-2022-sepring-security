package miu.edu.lab3.Repo;


import miu.edu.lab3.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
// Crud implementations
      List<Products> findAll();
      Products findById(int Id);
      Products save(int id,Products products);

      Products deleteById(int id);

      // THis are queries implemented

      List<Products> findProductsByPriceGreaterThan(double minPrice);
      List<Products> findProductsByPriceLessThanAAndAndCategoryName(double price,String catagoryName);
      List<Products> findProductsByNameContainingIgnoreCase(String name);
      Products findProductsById(int id);
}
