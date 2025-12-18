package GroceryShopSpringApp.NearBuy.controllers;

import GroceryShopSpringApp.NearBuy.dto.CommentDto;
import GroceryShopSpringApp.NearBuy.exceptions.NotAuthorizedException;
import GroceryShopSpringApp.NearBuy.models.RegistrationRequest;
import GroceryShopSpringApp.NearBuy.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrationRequest/apis")

public class RegistrationRequestController {

    RegistrationRequestService registrationRequestService;

    @Autowired
    public RegistrationRequestController(RegistrationRequestService registrationRequestService) {
        this.registrationRequestService = registrationRequestService;
    }

    //When the Maint or Admin or ServiceDeskExecutive login to their page, the Registration Request button will present there, when they clicks that button all the registration request should get loaded
    @GetMapping("/all")
    public ResponseEntity getAllRegistrationRequest(@RequestParam int userId) {
        try {
            List<RegistrationRequest> registrationRequestList = registrationRequestService.getAllRegistrationRequest(userId);
            return new ResponseEntity<>(registrationRequestList, HttpStatus.OK); //(registrationRequestList, ) -> here we will send the list of registration requests to the frontend
        }
        catch (NotAuthorizedException notAuthorizedException) {
            return new ResponseEntity(notAuthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //In the registration Request page there is a list of registration Requests, in the when we click a single registration Request a form gets opened and show all the details about that registration Request
    //in that form the Maint or Admin or ServiceDeskExecutive will update (approve or reject) the registration request state, they can aso add comments
    //if the Maint or Admin or ServiceDeskExecutive updated anything in the form and clicked the submit or post or post activity button (this will be consider as a new activity) and a new activity record will get created in the activity database table for that registration request
    //if any previous activities done by Maint or Admin or ServiceDeskExecutive in that registration request, that thing also shown as a list of activities when we clicks a specific registration Request
    @PostMapping("/activity/add")
    public ResponseEntity addComment(@RequestParam int reqId, @RequestParam int userId, @RequestBody CommentDto commentDto) { //reqId -> activity or comment should be added in this registration request id by this user (userId)
        try {
            RegistrationRequest registrationRequest = registrationRequestService.addComment(reqId, userId, commentDto);
            return new ResponseEntity<>(registrationRequest, HttpStatus.OK); //(registrationRequest, ) -> here we will send the updated registration requests to the frontend
        }
        catch (NotAuthorizedException notAuthorizedException) {
            return new ResponseEntity(notAuthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
