package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.CommentDto;
import GroceryShopSpringApp.NearBuy.enums.UserType;
import GroceryShopSpringApp.NearBuy.exceptions.NotAuthorizedException;
import GroceryShopSpringApp.NearBuy.models.Activity;
import GroceryShopSpringApp.NearBuy.models.RegistrationRequest;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.repositories.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationRequestService {

    RegistrationRequestRepository registrationRequestRepository;
    UserService userService;
    ActivityService activityService;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository, UserService userService, ActivityService activityService) {
        this.registrationRequestRepository = registrationRequestRepository;
        this.userService = userService;
        this.activityService = activityService;
    }

    public RegistrationRequest saveOrUpdate(RegistrationRequest registrationRequest) {
        return registrationRequestRepository.save(registrationRequest); //this method saves the registration request in the registrationRequestRepository
    }

    //When the Maint or Admin or ServiceDeskExecutive login to their page, the Registration Request button will present there, when they clicks that button all the registration request should get loaded
    public List<RegistrationRequest> getAllRegistrationRequest(int userId) { //userId -> userId of the user who clicked the getAllRegistrationRequest button
        User user = userService.getUserById(userId); //get the user detail(user object) from the database
        //if the user is not Admin or Maint or Service Desk Executive, that user is not allowed to see all the registration Requests
        if(user == null || !(  user.getUserType().equals(UserType.ADMIN.toString()) || user.getUserType().equals(UserType.MAINT.toString()) || user.getUserType().equals(UserType.SERVICE_DESK_EXECUTIVE.toString())  ) ) {
            throw new NotAuthorizedException("User is not allowed to perform this operation");
        }
        //if the user is maint or admin or ServiceDeskExecutive, we reach this line
        return registrationRequestRepository.findAll(); //this will give the list of all the registration requests present in the database
    }

    public RegistrationRequest addComment(int reqId, int userId, CommentDto commentDto) { //reqId -> activity or comment should be added in this registration request id by this user (userId)
        RegistrationRequest registrationRequest = this.getRegistrationRequestById(reqId); //get the registration request by using it's id
        User user = userService.getUserById(userId); //get the user who raised the registration request by using user id
        //if the user is not Admin or Maint or Service Desk Executive, that user is not allowed to add comment or changes in the registration Requests
        if(user == null || !(  user.getUserType().equals(UserType.ADMIN.toString()) || user.getUserType().equals(UserType.MAINT.toString()) || user.getUserType().equals(UserType.SERVICE_DESK_EXECUTIVE.toString())  ) ) {
            throw new NotAuthorizedException("User is not allowed to perform this operation");
        }
        //if the user is maint or admin or ServiceDeskExecutive, we reach this line
        Activity activity = new Activity(); //create a new activity object and set the commentDto details to this activity object
        activity.setText(commentDto.getComment());
        activity.setCreatedAt(LocalDateTime.now()); //set the current time to this field, because this activity created now only
        activity.setUser(user); //user who raised the registration request
        activity = activityService.saveorUpdateActivity(activity); //save the current activity in the activity table in the database
        registrationRequest.getActivities().add(activity); //get all the list of activities posted in this registrationRequest and add the current activity
        return this.saveOrUpdate(registrationRequest);
    }

    public RegistrationRequest getRegistrationRequestById(int reqId) {
        return registrationRequestRepository.findById(reqId).orElse(null);
    }

}
