package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double totalPrice;
    @OneToOne //Every customer(user) will have one cart
    User customer;
    @OneToMany //Every cart will have list of products in the cart, which was selected and added to the cart by customer(user)
    List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(int id, double totalPrice, User customer, List<CartProduct> cartProducts) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.cartProducts = cartProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
