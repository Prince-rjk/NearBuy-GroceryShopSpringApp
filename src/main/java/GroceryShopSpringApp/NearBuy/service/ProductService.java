package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.models.Product;
import GroceryShopSpringApp.NearBuy.models.Shop;
import GroceryShopSpringApp.NearBuy.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProductsByShop(Shop shop) {
        return productRepository.findByShop(shop);
    }

}
