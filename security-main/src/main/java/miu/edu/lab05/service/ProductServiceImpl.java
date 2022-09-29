package miu.edu.lab05.service;

import lombok.RequiredArgsConstructor;
import miu.edu.lab05.dto.ProductDTO;
import miu.edu.lab05.model.Product;
import miu.edu.lab05.repository.ProductRepository;
import miu.edu.lab05.security.WaaUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<ProductDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(u -> mapper.map(u, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        return repository.findById(id)
                .map(u -> mapper.map(u, ProductDTO.class));
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        WaaUserDetails userDetails = (WaaUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (product.getId() == null) {
            product.setCreatedBy(userDetails.getId());
            product.setCreatedAt(Instant.now());
        } else {
            product.setUpdatedBy(userDetails.getId());
            product.setUpdatedAt(Instant.now());
        }
        return mapper.map(repository.save(mapper.map(product, Product.class)), ProductDTO.class);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
