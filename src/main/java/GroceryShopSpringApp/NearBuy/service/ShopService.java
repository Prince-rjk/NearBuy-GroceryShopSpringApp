package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.RegisterShopDto;
import GroceryShopSpringApp.NearBuy.models.RegistrationRequest;
import GroceryShopSpringApp.NearBuy.models.Shop;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.repositories.ShopRepository;
import GroceryShopSpringApp.NearBuy.utilities.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    MappingUtility mappingUtility;
    UserService userService;
    ShopRepository shopRepository;
    RegistrationRequestService registrationRequestService;

    @Autowired
    public ShopService(MappingUtility mappingUtility, UserService userService, ShopRepository shopRepository, RegistrationRequestService registrationRequestService) {
        this.mappingUtility = mappingUtility;
        this.userService = userService;
        this.shopRepository = shopRepository;
        this.registrationRequestService = registrationRequestService;
    }

    public void registerShop(RegisterShopDto registerShopDto) {
        // We need to map RegisterShopDto to shop model and user Model
        //First, Let's map shopkeeper details into the User model(Shopkeeper)
        User shopKeeper = mappingUtility.mapRegisterShopDtoToShopKeeper(registerShopDto);
        shopKeeper = userService.saveOrUpdateUser(shopKeeper);

        //second, Let's map the shop details into shop model
        Shop shop = mappingUtility.mapRegisterShopDtoToShopModel(registerShopDto, shopKeeper);
        shop = this.saveOrUpdateShop(shop); //to save the shop in the shop table(Database)

        //when the shop got registered, we need to raise the registration request also. And this Registration Request will be visible to Maint, Admin, Service Desk Executive also
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setShop(shop);
        registrationRequest.setShopDescription("New Shop Registration Request");
        registrationRequestService.saveOrUpdate(registrationRequest); //to save the registration request in the registrationRequestRepository
        //after this we need to send email to all the Maint, Admin, Service Desk Executive, that there is a new shop request comes
    }

    public Shop saveOrUpdateShop(Shop shop) {
        return shopRepository.save(shop); //save the shop in the shop table(Database)

    }
}
