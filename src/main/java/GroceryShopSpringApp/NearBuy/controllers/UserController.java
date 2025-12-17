package GroceryShopSpringApp.NearBuy.controllers;

import GroceryShopSpringApp.NearBuy.dto.SignInDto;
import GroceryShopSpringApp.NearBuy.exceptions.InvalidCredentialsException;
import GroceryShopSpringApp.NearBuy.exceptions.UserNotFoundException;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/apis")

public class UserController {

    /*
        ResponseEntity -> (Status Code)
            Whenever our api response back to front end, it just not only return response body
            It returns multiple things
            Headers, ResponseBody and status code.
            When user tried to login and entered wrong credentials then it is a failure then our api will return what status code ? -> 401 UnAuthorized
            On Post success call what status we should return ? 201 (HTTP 201 Created successful response status code indicates that the HTTP request has led to the creation of a resource)
    */

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody SignInDto signInDto) {
        try {
            User user = userService.signin(signInDto);
            return new ResponseEntity(user, HttpStatus.CREATED); //201 status code
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND); //404 status code
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED); //401 status code
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //500 status code
        }
    }
}
