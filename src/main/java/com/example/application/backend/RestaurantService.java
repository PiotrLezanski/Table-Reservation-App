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
    public RestaurantService(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }
    
    public List<Restaurant> findAll()
    {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> findAllByName(String text)
    {
        if(text == null || text.isEmpty())
            return findAll();
        return restaurantRepository.filterByName(text);
    }

    public List<Restaurant> findAllByCity(String text)
    {
        if(text == null || text.isEmpty())
            return findAll();
        return restaurantRepository.filterByCity(text);
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
    
    // delete after database connection
    @PostConstruct
    public void populateTestData()
    {
        if(restaurantRepository.count() == 0)
        {
            restaurantRepository.saveAll(generateTestRestaurants());
        }
    }
    
    private List<Restaurant> generateTestRestaurants()
    {
        Random r = new Random(0);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Delizioso Trattoria", "short description", "Krakow", "123 Pasta Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Quick Bites Express", "short description", "Poznan", "456 Fast Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Soy Sauce Sensations", "short description", "Krakow", "789 Wok Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Olive Grove Mediterranean", "short description", "Krakow", "101 Olive Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Taco Time Fiesta", "short description", "Warszawa", "202 Salsa Boulevard", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Classic Diner Delights", "short description", "Krakow", "303 Pancake Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Ocean Fresh Seafood", "short description", "Poznan", "404 Pier Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Green Leaf Vegan Haven", "short description", "Warszawa", "505 Sprout Court", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Aroma Roast Coffee", "short description", "Krakow", "606 Bean Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Global Fusion Flavors", "short description", "Gdansk", "707 Fusion Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Street Eats Food Truck", "short description", "Lodz", "808 Mobile Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Epicurean Excellence", "short description", "Krakow", "909 Gourmet Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Morning Sunshine Café", "short description", "Sosnowiec", "1010 Sunrise Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Sweet Indulgence Bakery", "short description", "Krakow", "1111 Sugar Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Fresh Start Health Cafe", "short description", "Warszawa", "1212 Wellness Drive", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Pub Grub Paradise", "short description", "Krakow", "1313 Ale Avenue", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Family Feast Haven", "short description", "Lodz", "1414 Harmony Lane", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Buffet Bonanza", "short description", "Krakow", "1515 Feast Street", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Swift Delivery Delights", "short description", "Warszawa", "1616 Express Boulevard", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        restaurants.add(new Restaurant("Ethnic Eats Explorer", "short description", "Poznan", "1717 Cultural Court", RestaurantType.values()[r.nextInt(RestaurantType.values().length)], "https://picsum.photos/200"));
        
        return restaurants;
    }

    private static final Logger LOGGER = Logger.getLogger(Restaurant.class.getName());
    private RestaurantRepository restaurantRepository;
}
