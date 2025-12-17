package GroceryShopSpringApp.NearBuy.repositories;

import GroceryShopSpringApp.NearBuy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email); //this method will be automatically implemented by JPA
}
