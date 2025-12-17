package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double totalPrice; //total price of the current order
    @ManyToOne //many orders will be placed by one customer(user)
    private User customer;
    private String state; //current status or state of the order. i.e., placed, not placed, etc.
    @ManyToOne //many orders will be delivered by one shop
    private Shop shop;
    @ManyToOne //many orders were delevered by one delivery partner
    private User deliveryPartner;
    private LocalDateTime assignedDateTime;
    private LocalDateTime deliveredDateTime;
    private LocalDateTime placedDateTime;
    @OneToMany //one order may contains many OrderProducts (class OrderProduct)
    private List<OrderProduct> orderProductList;

    public Order() {
    }

    public Order(int id, double totalPrice, User customer, String state, Shop shop, User deliveryPartner, LocalDateTime assignedDateTime, LocalDateTime deliveredDateTime, LocalDateTime placedDateTime, List<OrderProduct> orderProductList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.state = state;
        this.shop = shop;
        this.deliveryPartner = deliveryPartner;
        this.assignedDateTime = assignedDateTime;
        this.deliveredDateTime = deliveredDateTime;
        this.placedDateTime = placedDateTime;
        this.orderProductList = orderProductList;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(User deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public LocalDateTime getAssignedDateTime() {
        return assignedDateTime;
    }

    public void setAssignedDateTime(LocalDateTime assignedDateTime) {
        this.assignedDateTime = assignedDateTime;
    }

    public LocalDateTime getDeliveredDateTime() {
        return deliveredDateTime;
    }

    public void setDeliveredDateTime(LocalDateTime deliveredDateTime) {
        this.deliveredDateTime = deliveredDateTime;
    }

    public LocalDateTime getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(LocalDateTime placedDateTime) {
        this.placedDateTime = placedDateTime;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
