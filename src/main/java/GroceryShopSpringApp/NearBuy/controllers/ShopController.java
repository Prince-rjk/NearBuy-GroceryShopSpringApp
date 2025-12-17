package GroceryShopSpringApp.NearBuy.controllers;

import GroceryShopSpringApp.NearBuy.dto.RegisterShopDto;
import GroceryShopSpringApp.NearBuy.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
