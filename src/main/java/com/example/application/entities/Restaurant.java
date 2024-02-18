package com.example.application.entities;

import com.example.application.globals.RestaurantType;
import jakarta.persistence.Entity;

@Entity
public class Restaurant extends AbstractEntity
{
    public Restaurant() {}
    public Restaurant(String name, String description, String city, String address, RestaurantType type, String imageUrl)
    {
        this.name = name;
        this.description = description;
        this.city = city;
        this.address = address;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String name = "";
    private String description = "";
    private String city = "";
    private String address = "";

    private RestaurantType type;

    private String imageUrl;
}
