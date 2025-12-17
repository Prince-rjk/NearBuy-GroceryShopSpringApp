package GroceryShopSpringApp.NearBuy.controllers;

import GroceryShopSpringApp.NearBuy.dto.InviteCoOrdinatorDto;
import GroceryShopSpringApp.NearBuy.exceptions.NotAuthorizedException;
import GroceryShopSpringApp.NearBuy.service.CoOrdinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coordinator/apis")

public class CoOrdinatorController {

    CoOrdinatorService coOrdinatorService;

    @Autowired
    public CoOrdinatorController(CoOrdinatorService coOrdinatorService) {
        this.coOrdinatorService = coOrdinatorService;
    }

    @PostMapping("/invite")
    public ResponseEntity inviteCoOrdinator(@RequestBody InviteCoOrdinatorDto inviteCoOrdinatorDto, @RequestParam int userId) { //userId -> which user invites this user
        try {
            coOrdinatorService.inviteCoOrdinator(inviteCoOrdinatorDto,userId);
            return new ResponseEntity<>("Service Desk Executive invited Successfully", HttpStatus.OK);
        }
        catch (NotAuthorizedException notAuthorizedException) {
            return new ResponseEntity(notAuthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accept")
    public ResponseEntity acceptInvite(@RequestParam int userId) { //When the user click the accept button in the html template where we sent as an invitation email, this api will get called
        try {
            coOrdinatorService.acceptInvite(userId);
            return new ResponseEntity<>("Status changed to Active", HttpStatus.OK);
        }
        catch (NotAuthorizedException notAuthorizedException) {
            return new ResponseEntity(notAuthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
