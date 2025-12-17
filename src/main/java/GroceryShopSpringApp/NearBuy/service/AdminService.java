package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.InviteAdminDto;
import GroceryShopSpringApp.NearBuy.exceptions.NotAuthorizedException;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.utilities.MappingUtility;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    UserService userService;
    MappingUtility mappingUtility;
    MailService mailService;

    @Autowired
    public AdminService(UserService userService, MappingUtility mappingUtility, MailService mailService) {
        this.userService = userService;
        this.mappingUtility = mappingUtility;
        this.mailService = mailService;
    }

    public void inviteAdmin(InviteAdminDto inviteAdminDto, int userId) throws MessagingException {
        // First thing validate userId -> Is it belonging to maint user or not.
        User maint = userService.getUserById(userId);
        boolean isMaint = userService.isMaintUser(maint); //check if this user is MAINT

        if(maint == null || !isMaint) { //if the user is not maint, the user is not able to add or invite Admin in our application
            throw new NotAuthorizedException("User is not allowed to perform this action");
        }

        // if the code comes here, the user is a valid maint user
        // So, We want to save the admin details in our user table.
        // So, For that first we need to map inviteAdminDto details to user object.
        // If i will write mapping logic here directly so our code will look clumsy
        // I will create another mapping class and there i will be keeping the mapping logic
        User admin = mappingUtility.mapInviteAdminDtoToUserObject(inviteAdminDto); //here we get the new admin object
        // I need to save this in the user table
        admin = userService.saveOrUpdateUser(admin);
        // After saving admin object in table we need to mail the admin regarding the invite -> That he want to join or or not
        mailService.sendInvitationEmailToAdmin(admin, maint);



    }
}
