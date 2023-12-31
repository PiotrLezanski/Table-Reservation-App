package com.example.application.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{
    @Query("select r from Restaurant r " +
            "where lower(r.name) like lower(concat('%', :filterText, '%')) " +
            "or lower(r.name) like lower(concat('%', :filterText, '%'))")
    List<Restaurant> filter(@Param("filterText") String filterText);
}