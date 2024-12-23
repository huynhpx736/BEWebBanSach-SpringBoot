package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.dto.ProductSearchCriteria;
import com.example.bookshop.entity.Product;
import com.example.bookshop.exception.ResourceNotFoundException;
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
    public List<ProductDTO> findProducts(String keyword) {
        return productRepository.findByTitleContaining(keyword).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updatStatus(Integer id, Integer status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        product.setStatus(status);
        productRepository.save(product);
    }

    @Override
    public Product updateProductImage(Integer productId, String imageUrl) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
        product.setImage(imageUrl);
        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> getNewestProducts() {
        //trả về danh sách 10 sản phẩm mới nhất từ database
        return productRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
//        return productRepository.findAll().stream()
//                .map(productMapper::toDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllByCategoriesID(Integer categoryId) {
        return productRepository.findByCategoriesId(categoryId).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

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
        //Chỉ lấy những sản phẩm có priority > 0
        return products.stream()
                .map(product -> calculatePriority(product, criteria))
                .filter(product -> product.getPriority() > 0)
                .sorted((p1, p2) -> Float.compare(p2.getPriority(), p1.getPriority()))
                .collect(Collectors.toList());


    }

    private Product calculatePriority(Product product, ProductSearchCriteria criteria) {
        float priority = 0;

        // Check title
        if (criteria.getTitle() != null && !criteria.getTitle().trim().isEmpty()) {
            String title = product.getTitle() != null ? product.getTitle().toLowerCase().trim() : "";
//            title = title.replaceAll("\\s+", "");
            String searchTitle = criteria.getTitle().toLowerCase().trim();
//            searchTitle = searchTitle.replaceAll("\\s+", "");
            if (title.contains(searchTitle)) {
                priority += criteria.getTitleWeight();
            }
        }

        // Check author
        if (criteria.getAuthor() != null && !criteria.getAuthor().trim().isEmpty()) {
            String searchAuthor = criteria.getAuthor().toLowerCase().trim();
            if (product.getAuthors().stream()
                    .anyMatch(a -> a.getName() != null && a.getName().toLowerCase().trim().contains(searchAuthor))) {
                priority += criteria.getAuthorWeight();
            }
        }

        // Check category
        if (criteria.getCategory() != null && !criteria.getCategory().trim().isEmpty()) {
            String searchCategory = criteria.getCategory().toLowerCase().trim();
            if (product.getCategories().stream()
                    .anyMatch(c -> c.getName() != null && c.getName().toLowerCase().trim().contains(searchCategory))) {
                priority += criteria.getCategoryWeight();
            }
        }
        // Check topic
        if (criteria.getTopic() != null && !criteria.getTopic().trim().isEmpty()) {
            String searchTopic = criteria.getTopic().toLowerCase().trim();
            String topic = product.getTopic() != null ? product.getTopic().toLowerCase().trim() : "";
            if (topic.contains(searchTopic)) {
                priority += criteria.getTopicWeight();
            }
        }

        // Check publisher
        if (criteria.getPublisher() != null && !criteria.getPublisher().trim().isEmpty()) {
            String publisher = product.getPublisher() != null ? product.getPublisher().getName().toLowerCase().trim() : "";
            String searchPublisher = criteria.getPublisher().toLowerCase().trim();
            if (publisher.contains(searchPublisher)) {
                priority += criteria.getPublisherWeight();
            }
        }

        // Check publication year
        if (criteria.getPublicationYear() != null) {
            if (product.getPublicationYear() != null && product.getPublicationYear().equals(criteria.getPublicationYear())) {
                priority += criteria.getYearWeight();
            }
        }

        // Check tag
        if (criteria.getTag() != null && !criteria.getTag().trim().isEmpty()) {
            String searchTag = criteria.getTag().toLowerCase().trim();
            if (product.getTags().stream()
                    .anyMatch(t -> t.getName() != null && t.getName().toLowerCase().trim().contains(searchTag))) {
                priority += criteria.getTagWeight();
            }
        }

        // Check rating
        if (criteria.getMinRating() != null && criteria.getMaxRating() != null &&
                criteria.getMinRating() <= criteria.getMaxRating()) {
            if (product.getStarRating() != null &&
                    product.getStarRating() >= criteria.getMinRating() &&
                    product.getStarRating() <= criteria.getMaxRating()) {
                priority += criteria.getRatingWeight();
            }
        }

        // Check price
        if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null &&
                criteria.getMinPrice() <= criteria.getMaxPrice()) {
            if (product.getPrice() >= criteria.getMinPrice() &&
                    product.getPrice() <= criteria.getMaxPrice()) {
                priority += criteria.getPriceWeight();
            }
        }

//        // Kiểm tra số lượng đã bán (quantity_sold)
//        if (criteria.getMinQuantitySold() != null && criteria.getMaxQuantitySold() != null &&
//                criteria.getMinQuantitySold() <= criteria.getMaxQuantitySold()) {
//            if (product.getQuantity_sold() != null &&
//                    product.getQuantity_sold() >= criteria.getMinQuantitySold() &&
//                    product.getQuantity_sold() <= criteria.getMaxQuantitySold()) {
//                priority += criteria.getQuantitySoldWeight();
//            }
//        }
        // Kiểm tra số lượng đã bán (quantity_sold)
        if (criteria.getMinQuantitySold() != null) {
            if (product.getQuantity_sold() != null &&
                    product.getQuantity_sold() >= criteria.getMinQuantitySold()) {
                priority += criteria.getQuantitySoldWeight();
            }
        }

        // Kiểm tra nội dung mục lục (content)
        if (criteria.getContent() != null && !criteria.getContent().trim().isEmpty()) {
            String content = product.getContent() != null ? product.getContent().toLowerCase().trim() : "";
            String searchContent = criteria.getContent().toLowerCase().trim();
            if (content.contains(searchContent)) {
                priority += criteria.getContentWeight();
            }
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
        //kiem tra san pham co thuoc ve don hang nao khong
        //neu co thi khong cho xoa
        //neu khong thi xoa

//        if (orderRepository.existsByProductId(id)) {
//            throw new RuntimeException("Product is in order, cannot delete");
//        }
//
        productRepository.deleteById(id);
    }
}
