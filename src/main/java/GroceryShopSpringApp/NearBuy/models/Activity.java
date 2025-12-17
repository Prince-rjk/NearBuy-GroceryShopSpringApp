package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    @ManyToOne //many activities were done by one user
    private User user;
    private LocalDateTime createdAt; //Every activity will be created at this local Time, this stores the local time of the device at the time of the creation of the activity, this purpose is to know in which time this activity has been created

    public Activity() {
    }

    public Activity(int id, String text, User user, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
