package com.example.application.backend;

import com.example.application.globals.RestaurantType;
import com.helger.commons.annotation.Nonempty;
import jakarta.persistence.Entity;

@Entity
public class Restaurant extends AbstractEntity
{
    @Nonempty
    private String name = "";
    
    @Nonempty
    private String address = "";
    
//    @ManyToOne
    private RestaurantType type;

    public Restaurant() {}
    public Restaurant(String name, String address, RestaurantType type)
    {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
