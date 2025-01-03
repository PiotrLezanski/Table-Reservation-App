package com.example.application.backend.restaurant;

import com.example.application.entities.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{
    @Query("select r from Restaurant r " +
            "where lower(r.name) like lower(concat('%', :filterText, '%'))")
    List<Restaurant> filterByName(@Param("filterText") String filterText);

    @Query("select r from Restaurant r " +
            "where lower(r.city) like lower(concat('%', :filterText, '%'))")    
    List<Restaurant> filterByCity(@Param("filterText") String filterText);
}