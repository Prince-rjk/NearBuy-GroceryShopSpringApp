package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.models.ProductImageLink;
import GroceryShopSpringApp.NearBuy.repositories.ProductImageLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageLinkService {
    ProductImageLinkRepository productImageLinkRepository;

    @Autowired
    public ProductImageLinkService(ProductImageLinkRepository productImageLinkRepository) {
        this.productImageLinkRepository = productImageLinkRepository;
    }

    public ProductImageLink saveOrUpdateProductImageLink(ProductImageLink productImageLink) {
        return productImageLinkRepository.save(productImageLink); //save the product image link
    }
}
