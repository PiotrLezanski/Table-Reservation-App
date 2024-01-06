package com.example.application.backend;

import com.example.application.globals.RestaurantType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestaurantService
{
    private static final Logger LOGGER = Logger.getLogger(Restaurant.class.getName());
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }
    
    public List<Restaurant> findAll()
    {
        return restaurantRepository.findAll();
    }
    
    public long count()
    {
        return restaurantRepository.count();
    }
    
    public void delete(Restaurant restaurant)
    {
        restaurantRepository.delete(restaurant);
    }
    
    public void save(Restaurant restaurant)
    {
        if(restaurant == null)
        {
            LOGGER.log(Level.SEVERE, "Restaurant is null. I can't save it.");
            return;
        }
        restaurantRepository.save(restaurant);
    }
    
    // testing without database connection
    @PostConstruct
    public void populateTestData()
    {
        if(restaurantRepository.count() == 0)
        {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            restaurantRepository.saveAll(generateTestRestaurants());
        }
    }
    
    private List<Restaurant> generateTestRestaurants()
    {
        Random r = new Random(0);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Delizioso Trattoria", "123 Pasta Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Quick Bites Express", "456 Fast Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Soy Sauce Sensations", "789 Wok Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Olive Grove Mediterranean", "101 Olive Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Taco Time Fiesta", "202 Salsa Boulevard", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Classic Diner Delights", "303 Pancake Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Ocean Fresh Seafood", "404 Pier Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Green Leaf Vegan Haven", "505 Sprout Court", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Aroma Roast Coffee", "606 Bean Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Global Fusion Flavors", "707 Fusion Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Street Eats Food Truck", "808 Mobile Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Epicurean Excellence", "909 Gourmet Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Morning Sunshine Café", "1010 Sunrise Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Sweet Indulgence Bakery", "1111 Sugar Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Fresh Start Health Cafe", "1212 Wellness Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Pub Grub Paradise", "1313 Ale Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Family Feast Haven", "1414 Harmony Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Buffet Bonanza", "1515 Feast Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Swift Delivery Delights", "1616 Express Boulevard", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        restaurants.add(new Restaurant("Ethnic Eats Explorer", "1717 Cultural Court", RestaurantType.values()[r.nextInt(RestaurantType.values().length)]));
        
        return restaurants;
    }
}
