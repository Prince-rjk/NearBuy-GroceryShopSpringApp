package GroceryShopSpringApp.NearBuy.controllers;

import GroceryShopSpringApp.NearBuy.dto.AddProductDto;
import GroceryShopSpringApp.NearBuy.dto.RegisterShopDto;
import GroceryShopSpringApp.NearBuy.models.Product;
import GroceryShopSpringApp.NearBuy.models.Shop;
import GroceryShopSpringApp.NearBuy.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/apis")

public class ShopController {

    ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/register")
    public void registerShop(@RequestBody RegisterShopDto registerShopDto) {
        shopService.registerShop(registerShopDto);
    }

    //when the shopkeeper login to the app, their shop should get loaded and should show the shop details in the dashboard
    @GetMapping("/getShop/{shopKeeperId}") //get the shop details by using shopkeeper id
    public Shop getShopByshopKeeperId(@PathVariable int shopKeeperId) {
        return shopService.getShopByshopKeeperId(shopKeeperId);
    }

    @PostMapping("/addProduct")
    public Product addProductToShop(@RequestBody AddProductDto addProductDto) { //to add the products in the shop
        return shopService.addProductToShop(addProductDto);
    }

    //show all the products present in the shop by using shop Id
    @GetMapping("/{shopId}/products")
    public List<Product> getAllProductsByShopId(@PathVariable int shopId) {
        return shopService.getAllProductsByShopId(shopId);
    }

    //before showing and loading all the customer dashboard and it's details, the shops should load based on the customer pincode and should show in the dashboard (Nearest shop based on the Customer pincode)
    @GetMapping("/pincode/{pincode}")
    public List<Shop> getShopByPincode(@PathVariable int pincode) {
        return shopService.getShopByPincode(pincode);
    }
}
