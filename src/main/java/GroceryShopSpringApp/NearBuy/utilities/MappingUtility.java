package GroceryShopSpringApp.NearBuy.utilities;

import GroceryShopSpringApp.NearBuy.dto.InviteAdminDto;
import GroceryShopSpringApp.NearBuy.enums.UserState;
import GroceryShopSpringApp.NearBuy.enums.UserType;
import GroceryShopSpringApp.NearBuy.models.User;
import org.springframework.stereotype.Service;

@Service
public class MappingUtility {

    public User mapInviteAdminDtoToUserObject(InviteAdminDto inviteAdminDto) {
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

}
