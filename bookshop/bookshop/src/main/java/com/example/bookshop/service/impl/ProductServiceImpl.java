package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.dto.ProductSearchCriteria;
import com.example.bookshop.entity.Product;
import com.example.bookshop.mapper.ProductMapper;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<Product> searchProducts(ProductSearchCriteria criteria) {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> calculatePriority(product, criteria))
                .sorted((p1, p2) -> Float.compare(p2.getPriority(), p1.getPriority())) // Sort by descending priority
                .collect(Collectors.toList());
    }

    private Product calculatePriority(Product product, ProductSearchCriteria criteria) {
        float priority = 0;

        // Check title
        if (criteria.getTitle() != null && product.getTitle().contains(criteria.getTitle())) {
            priority += criteria.getTitleWeight();
        }

        // Check author
        if (criteria.getAuthor() != null && product.getAuthors().stream().anyMatch(a -> a.getName().contains(criteria.getAuthor()))) {
            priority += criteria.getAuthorWeight();
        }

        // Check category
        if (criteria.getCategory() != null && product.getCategories().stream().anyMatch(c -> c.getName().contains(criteria.getCategory()))) {
            priority += criteria.getCategoryWeight();
        }

        // Check publisher
        if (criteria.getPublisher() != null && product.getPublisher().getName().contains(criteria.getPublisher())) {
            priority += criteria.getPublisherWeight();
        }

        // Check publication year
        if (criteria.getPublicationYear() != null && product.getPublicationYear().equals(criteria.getPublicationYear())) {
            priority += criteria.getYearWeight();
        }

        // Check tag
        if (criteria.getTag() != null && product.getTags().stream().anyMatch(t -> t.getName().contains(criteria.getTag()))) {
            priority += criteria.getTagWeight();
        }

        // Check rating
        if (criteria.getMinRating() != null && criteria.getMaxRating() != null &&
                product.getStarRating() != null &&
                product.getStarRating() >= criteria.getMinRating() &&
                product.getStarRating() <= criteria.getMaxRating()) {
            priority += criteria.getRatingWeight();
        }

        // Check price
        if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null &&
                product.getPrice() >= criteria.getMinPrice() &&
                product.getPrice() <= criteria.getMaxPrice()) {
            priority += criteria.getPriceWeight();
        }

        // Set the priority in product for sorting
        product.setPriority(priority);
        return product;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            Product product = productMapper.toEntity(productDTO);
            product.setId(id);
            return productMapper.toDTO(productRepository.save(product));
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
