package GroceryShopSpringApp.NearBuy.repositories;

import GroceryShopSpringApp.NearBuy.models.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {
}
