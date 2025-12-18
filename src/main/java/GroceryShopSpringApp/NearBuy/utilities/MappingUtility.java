package GroceryShopSpringApp.NearBuy.utilities;

import GroceryShopSpringApp.NearBuy.dto.*;
import GroceryShopSpringApp.NearBuy.enums.ShopState;
import GroceryShopSpringApp.NearBuy.enums.UserState;
import GroceryShopSpringApp.NearBuy.enums.UserType;
import GroceryShopSpringApp.NearBuy.models.Product;
import GroceryShopSpringApp.NearBuy.models.Shop;
import GroceryShopSpringApp.NearBuy.models.User;
import org.springframework.stereotype.Service;

@Service
public class MappingUtility {

    public User mapInviteAdminDtoToUserObject(InviteUserDto inviteAdminDto) {
        User user = new User();
        user.setName(inviteAdminDto.getName());
        user.setEmail(inviteAdminDto.getEmail());
        user.setPassword("TempPass@123");
        user.setUserType(UserType.ADMIN.toString());
        user.setPhoneNumber(inviteAdminDto.getPhoneNumber());
        user.setGender(inviteAdminDto.getGender());
        user.setAddressLine1(inviteAdminDto.getAddressLine1());
        user.setAddressLine2(inviteAdminDto.getAddressLine2());
        user.setAddressLine3(inviteAdminDto.getAddressLine3());
        user.setStatus(UserState.INVITED.toString());
        user.setPincode(inviteAdminDto.getPincode());
        return user;
    }

    public User mapRegisterShopDtoToShopKeeper(RegisterShopDto registerShopDto) {
        User user = new User();
        user.setName(registerShopDto.getName());
        user.setEmail(registerShopDto.getEmail());
        user.setPassword("TempPass@123");
        user.setUserType(UserType.SHOPKEEPER.toString());
        user.setPhoneNumber(registerShopDto.getPhoneNumber());
        user.setGender(registerShopDto.getGender());
        user.setAddressLine1(registerShopDto.getAddressLine1());
        user.setAddressLine2(registerShopDto.getAddressLine2());
        user.setAddressLine3(registerShopDto.getAddressLine3());
        user.setStatus(UserState.PENDING_REVIEW.toString());
        user.setPincode(registerShopDto.getPincode());
        return user;
    }

    public Shop mapRegisterShopDtoToShopModel(RegisterShopDto registerShopDto, User shopKeeper) {
        Shop shop = new Shop();
        shop.setShopName(registerShopDto.getShopName());
        shop.setState(ShopState.PENDING_REVIEW.toString());
        shop.setGstNumber(registerShopDto.getGstNumber());
        shop.setPhoneNumber(registerShopDto.getShopPhoneNumber());
        shop.setPincode(registerShopDto.getPincode());
        shop.setAddressLine1(registerShopDto.getShopAddressLine1());
        shop.setAddressLine2(registerShopDto.getAddressLine2());
        shop.setAddressLine3(registerShopDto.getAddressLine3());
        shop.setShopKeeper(shopKeeper);
        return shop;
    }

    public Product mapAddProductDtoToProduct(AddProductDto addProductDto, Shop shop){
        Product product = new Product();
        product.setProductName(addProductDto.getProductName());
        product.setProductSpecification(addProductDto.getProductSpecification());
        product.setCategory(addProductDto.getCategory());
        product.setPrice(addProductDto.getPrice());
        product.setManufacturer(addProductDto.getManufacturer());
        product.setDiscount(addProductDto.getDiscount());
        product.setShop(shop);
        return product;
    }

    public User mapCustomerDtoToUser(CustomerDto customerDto){
        User user = new User();
        user.setStatus(UserState.ACTIVE.toString());
        user.setName(customerDto.getName());
        user.setUserType(UserType.CUSTOMER.toString());
        user.setEmail(customerDto.getEmail());
        user.setGender(customerDto.getGender());
        user.setPassword(customerDto.getPassword());
        user.setAddressLine1(customerDto.getAddressLine1());
        user.setAddressLine2(customerDto.getAddressLine2());
        user.setAddressLine3(customerDto.getAddressLine3());
        user.setPincode(customerDto.getPincode());
        user.setPhoneNumber(customerDto.getPhoneNumber());
        return user;
    }

}
