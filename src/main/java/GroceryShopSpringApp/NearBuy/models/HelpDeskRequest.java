package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "help-desk-requests")

public class HelpDeskRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String query; //query of the user who raised the helpdesk request
    private String state; //state of the request : denied, accepted, etc..
    @ManyToOne //many help desk requests will be assigned to one user
    private User assignedTo;
    @OneToMany //one help desk request will have many list of activities
    List<Activity> activities;

    public HelpDeskRequest() {
    }

    public HelpDeskRequest(int id, String query, String state, User assignedTo, List<Activity> activities) {
        this.id = id;
        this.query = query;
        this.state = state;
        this.assignedTo = assignedTo;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
