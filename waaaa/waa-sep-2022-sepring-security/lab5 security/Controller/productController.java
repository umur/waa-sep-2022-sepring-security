package miu.edu.lab3.Controller;


import miu.edu.lab3.Aspect.Annotation.ExecutionTIme;
import miu.edu.lab3.Aspect.Annotation.LogMe;
import miu.edu.lab3.DTO.ProductsDTO;
import miu.edu.lab3.Service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class productController {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @GetMapping
    @ExecutionTIme
    public List<ProductsDTO> findProducts(){
        return productServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public ProductsDTO findProductsById(@PathVariable int id){
        return productServiceInterface.findById(id);
    }


    @PostMapping("/{id}")
    public ProductsDTO update(@PathVariable int id,@RequestBody ProductsDTO product){
        return productServiceInterface.save(id,product);
    }
    @DeleteMapping("/{id}")
   @LogMe
    public ProductsDTO delete(@PathVariable int id){
        return productServiceInterface.deleteById(id);
    }

    @GetMapping("/minPrice")
    @ExecutionTIme
    public List<ProductsDTO> findProductsWIthPriceGreater(@RequestParam int minprice){
        return productServiceInterface.findProductsByPriceGreaterThan(minprice);
    }

    @GetMapping("/byCategoryAndName")
    @ExecutionTIme
    public List<ProductsDTO> findByCategoryNameandLessPrice(@RequestParam double price ,@RequestParam String categoryName){
        return productServiceInterface.findProductsByPriceLessThanAAndAndCategoryName(price,categoryName);
    }

    @GetMapping("/{name}")
    public List<ProductsDTO> findProductsByNameContainingIgnoreCase(@PathVariable String name){
        return productServiceInterface.findProductsByNameContainingIgnoreCase(name);
    }
}
