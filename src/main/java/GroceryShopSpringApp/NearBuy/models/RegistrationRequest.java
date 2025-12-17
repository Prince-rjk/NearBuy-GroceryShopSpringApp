package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "registration-requests")

public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne //many registration request will be registered by one shop
    private Shop shop;
    private String shopDescription;
    @OneToMany //one registration request will have many activities
    private List<Activity> activities;

    public RegistrationRequest() {
    }

    public RegistrationRequest(int id, Shop shop, String shopDescription, List<Activity> activities) {
        this.id = id;
        this.shop = shop;
        this.shopDescription = shopDescription;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
