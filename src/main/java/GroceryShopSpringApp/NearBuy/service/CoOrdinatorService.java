package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.InviteCoOrdinatorDto;
import GroceryShopSpringApp.NearBuy.enums.UserState;
import GroceryShopSpringApp.NearBuy.enums.UserType;
import GroceryShopSpringApp.NearBuy.exceptions.NotAuthorizedException;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.utilities.MappingUtility;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CoOrdinatorService {

    UserService userService;
    MappingUtility mappingUtility;
    MailService mailService;

    @Autowired
    public CoOrdinatorService(UserService userService, MappingUtility mappingUtility, MailService mailService) {
        this.userService = userService;
        this.mappingUtility = mappingUtility;
        this.mailService = mailService;
    }

    public void inviteCoOrdinator(InviteCoOrdinatorDto inviteCoOrdinatorDto, int userId) throws MessagingException {
        // First thing validate userId -> Is it belonging to maint user or not.
        User invitedUser = userService.getUserById(userId);

        if(invitedUser == null || !( (invitedUser.getUserType().equals(UserType.ADMIN.toString())) || (invitedUser.getUserType().equals(UserType.MAINT.toString())) ) ) { //if the invitedUser is not Admin or Maint //if the invitedUser is not the already registered user, that invitedUser is not able to add CoOrdinator in our Application or invite CoOrdinator in our application
            throw new NotAuthorizedException("User is not allowed to perform this action");
        }

        // if the code comes here, the user is a valid maint user
        // So, We want to save the admin details in our user table.
        // So, For that first we need to map inviteAdminDto details to user object.
        // If i will write mapping logic here directly so our code will look clumsy
        // I will create another mapping class and there i will be keeping the mapping logic
        User coOrdinator = mappingUtility.mapInviteAdminDtoToUserObject(inviteCoOrdinatorDto); //here we get the new admin object
        coOrdinator.setUserType(UserType.SERVICE_DESK_EXECUTIVE.toString()); //we set UserType for coOrdinator manually here, because after mapping in the above line it returns the userType as Admin, so here we need to change it
        // I need to save this in the user table
        coOrdinator = userService.saveOrUpdateUser(coOrdinator);
        // After saving admin object in table we need to mail the admin regarding the invite -> That he want to join or or not
        mailService.sendInvitationEmailToAdmin(coOrdinator, invitedUser);
    }

    public void acceptInvite(int userId) {
        User coOrdinator = userService.getUserById(userId);
        if(coOrdinator == null || !coOrdinator.getUserType().equals(UserType.SERVICE_DESK_EXECUTIVE.toString()) || coOrdinator.getStatus().equals(UserState.ACTIVE.toString())) { //if the user is not already registered or the user is not a SERVICE_DESK_EXECUTIVE or the user is status is already active
            throw new NotAuthorizedException("User is not allowed to perform this operation"); //we throw the exception
        }

        //if the exception is not occured, we can change the user status
        coOrdinator.setStatus(UserState.ACTIVE.toString()); //change the status
        userService.saveOrUpdateUser(coOrdinator); //update it in the database
    }
}
