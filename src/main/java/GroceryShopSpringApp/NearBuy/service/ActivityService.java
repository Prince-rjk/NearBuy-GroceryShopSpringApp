package GroceryShopSpringApp.NearBuy.service;

import GroceryShopSpringApp.NearBuy.models.Activity;
import GroceryShopSpringApp.NearBuy.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity saveorUpdateActivity(Activity activity) {
        return activityRepository.save(activity);
    }


}
