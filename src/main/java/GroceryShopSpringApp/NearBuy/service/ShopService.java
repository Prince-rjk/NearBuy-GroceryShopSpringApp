package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.AddProductDto;
import GroceryShopSpringApp.NearBuy.dto.RegisterShopDto;
import GroceryShopSpringApp.NearBuy.models.*;
import GroceryShopSpringApp.NearBuy.repositories.ShopRepository;
import GroceryShopSpringApp.NearBuy.utilities.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    MappingUtility mappingUtility;
    UserService userService;
    ShopRepository shopRepository;
    RegistrationRequestService registrationRequestService;
    ProductImageLinkService productImageLinkService;
    ProductService productService;

    @Autowired
    public ShopService(MappingUtility mappingUtility, UserService userService, ShopRepository shopRepository, RegistrationRequestService registrationRequestService, ProductImageLinkService productImageLinkService, ProductService productService) {
        this.mappingUtility = mappingUtility;
        this.userService = userService;
        this.shopRepository = shopRepository;
        this.registrationRequestService = registrationRequestService;
        this.productImageLinkService = productImageLinkService;
        this.productService = productService;
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

    public Shop getShopByshopKeeperId(int shopKeeperId) { //get the shop details by using shopkeeper id
        return shopRepository.findShopByShopKeeperId(shopKeeperId);
    }

    public Shop getShopByshopId(int shopId) {
        return this.shopRepository.findById(shopId).orElse(null);
    }

    public Product addProductToShop(AddProductDto addProductDto) { //to add the products in the shop
        //We need to map addProductDto to product model
        int shopId = addProductDto.getShopId(); //get the shop id
        Shop shop = this.getShopByshopId(shopId); //get the shop by shop id
        Product product = mappingUtility.mapAddProductDtoToProduct(addProductDto, shop);
        //before saving the product in the product table, we should add product image links in this product object
        List<ProductImageLink> productImageLinkList = new ArrayList<>();
        for(ProductImageLink ele : addProductDto.getProductImageLinks()) { //loop over the product images links in the addProductDto, store it in the product image link database and add it into the list
            productImageLinkList.add(productImageLinkService.saveOrUpdateProductImageLink(ele));
        }
        product.setProductImageLinks(productImageLinkList); //add or set or update this above product image links list to the product object
        return productService.saveOrUpdateProduct(product);
    }

    public List<Product> getAllProductsByShopId(int shopId) {  //show all the products present in the shop by using shop Id
        Shop shop = this.getShopByshopId(shopId);
        return productService.getAllProductsByShop(shop);
    }

    public List<Shop> getShopByPincode(int pincode) {
        return shopRepository.findShopsByPincode(pincode);
    }
}
