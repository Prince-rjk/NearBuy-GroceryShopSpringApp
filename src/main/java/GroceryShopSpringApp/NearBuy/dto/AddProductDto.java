package GroceryShopSpringApp.NearBuy.dto;

import GroceryShopSpringApp.NearBuy.models.ProductImageLink;

import java.util.List;

public class AddProductDto {
    String productName;
    String productSpecification;
    String manufacturer;
    String price;
    double discount;
    int shopId;
    String category;
    List<ProductImageLink> productImageLinks;

    public AddProductDto() {
    }

    public AddProductDto(String productName, String productSpecification, String manufacturer, String price, double discount, int shopId, String category, List<ProductImageLink> productImageLinks) {
        this.productName = productName;
        this.productSpecification = productSpecification;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.shopId = shopId;
        this.category = category;
        this.productImageLinks = productImageLinks;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductImageLink> getProductImageLinks() {
        return productImageLinks;
    }

    public void setProductImageLinks(List<ProductImageLink> productImageLinks) {
        this.productImageLinks = productImageLinks;
    }
}
