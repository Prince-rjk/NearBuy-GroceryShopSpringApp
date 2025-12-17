package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.dto.SignInDto;
import GroceryShopSpringApp.NearBuy.enums.UserType;
import GroceryShopSpringApp.NearBuy.exceptions.InvalidCredentialsException;
import GroceryShopSpringApp.NearBuy.exceptions.UserNotFoundException;
import GroceryShopSpringApp.NearBuy.models.User;
import GroceryShopSpringApp.NearBuy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository; //instance variable (userRepository)
    @Autowired
    public UserService(UserRepository userRepository) { //constructor based Autowired //method parameters will be provided by springBoot
        this.userRepository = userRepository; //we assign the method parameters that will by provided by springBoot to the instance variable (userRepository)
    }


    public User signin(SignInDto signInDto) {
        String email = signInDto.getEmail();
        User user = userRepository.findByEmail(email); // We need to get UserRepository and from userRepository we will get User by email
        //validation : step 1 -> validate email (If user exists)
        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s does not exist", email)); //Throw exception, if User does not exist
        }

        //step 2 -> if the User != null that means the user with this email id is exist and has a user account in our app. So, let's move to next step of validation
        // check if the password entered is correct or not
        if(user.getPassword().equals(signInDto.getPassword())) { //if password matches with the password already in the database, that means login successful, user has entered correct email & password, the user eligible to use the app
            return user;
        }
        throw new InvalidCredentialsException("Wrong email password entered"); //if password entered is wrong
    }

    public boolean isMaintUser(User user) {
      return user.getUserType().equals(UserType.MAINT.toString()); //if the userType is MAINT, this will return true, otherwise false
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }
}
