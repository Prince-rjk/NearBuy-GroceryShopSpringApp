package GroceryShopSpringApp.NearBuy.repositories;

import GroceryShopSpringApp.NearBuy.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
