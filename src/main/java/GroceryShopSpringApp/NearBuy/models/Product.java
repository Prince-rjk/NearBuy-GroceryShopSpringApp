package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //every product has a unique id
    private String productName;
    private String productSpecification;
    private String manufacturer;
    private String price;
    private double discount;
    @ManyToOne //many products available in one shop
    private Shop shop;
    private String category;
    @OneToMany //one product will have many image links (many images for display purpose)
    private List<ProductImageLink> productImageLinks;

    public Product() {
    }

    public Product(int id, String productName, String productSpecification, String manufacturer, String price, double discount, Shop shop, String category, List<ProductImageLink> productImageLinks) {
        this.id = id;
        this.productName = productName;
        this.productSpecification = productSpecification;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.shop = shop;
        this.category = category;
        this.productImageLinks = productImageLinks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
