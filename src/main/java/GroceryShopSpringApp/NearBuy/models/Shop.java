package GroceryShopSpringApp.NearBuy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shops")

public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false)
    private String gstNumber;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private int pincode;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @Column(nullable = false)
    private Long phoneNumber;
    @OneToOne //One shop has one shop keeper
    private User shopKeeper;

    public Shop() {
    }

    public Shop(int id, String shopName, String gstNumber, String state, int pincode, String addressLine1, String addressLine2, String addressLine3, Long phoneNumber, User shopKeeper) {
        this.id = id;
        this.shopName = shopName;
        this.gstNumber = gstNumber;
        this.state = state;
        this.pincode = pincode;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.phoneNumber = phoneNumber;
        this.shopKeeper = shopKeeper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getShopKeeper() {
        return shopKeeper;
    }

    public void setShopKeeper(User shopKeeper) {
        this.shopKeeper = shopKeeper;
    }
}
