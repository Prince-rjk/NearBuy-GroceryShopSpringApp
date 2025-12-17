package GroceryShopSpringApp.NearBuy.repositories;

import GroceryShopSpringApp.NearBuy.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
