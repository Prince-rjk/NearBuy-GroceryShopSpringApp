package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

@Entity

public class OrderProduct { //this class(OrderProduct) is a single row in a cart or order. So, a cart or order may contains list of OrderProducts (class OrderProduct)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int pid; //product id
    private int quantity;
    private double price;

    public OrderProduct() {
    }

    public OrderProduct(int id, int pid, int quantity, double price) {
        this.id = id;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
