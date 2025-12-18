package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.CustomerDto;
import GroceryShopSpringApp.NearBuy.models.Shop;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.repositories.ShopRepository;
import GroceryShopSpringApp.NearBuy.utilities.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    MappingUtility mappingUtility;
    UserService userService;

    @Autowired
    public CustomerService(MappingUtility mappingUtility, UserService userService) {
        this.mappingUtility = mappingUtility;
        this.userService = userService;
    }

    public User createNewCustomer(CustomerDto customerDto) {
        //We need to map customerDto to User model
        User customer = mappingUtility.mapCustomerDtoToUser(customerDto);
        return userService.saveOrUpdateUser(customer);
    }

}
