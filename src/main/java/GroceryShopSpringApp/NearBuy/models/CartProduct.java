package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cart-products")

public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private UUID cartId;
    private UUID productId;
    private int quantity;
    private double price;

    public CartProduct() {
    }

    public CartProduct(int id, UUID cartId, UUID productId, int quantity, double price) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
