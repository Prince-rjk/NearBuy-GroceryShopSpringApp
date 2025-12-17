package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.models.RegistrationRequest;
import GroceryShopSpringApp.NearBuy.repositories.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationRequestService {

    RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository) {
        this.registrationRequestRepository = registrationRequestRepository;
    }

    public RegistrationRequest saveOrUpdate(RegistrationRequest registrationRequest) {
        return registrationRequestRepository.save(registrationRequest); //this method saves the registration request in the registrationRequestRepository
    }

}
