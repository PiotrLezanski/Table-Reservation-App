package com.example.application.entities.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationInfo 
{
    public ReservationInfo() {}
    
    public ReservationInfo(long restaurantId, long numberOfPeople, LocalDate date, LocalTime time)
    {
        this.restaurantId = restaurantId;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.time = time;
    }
    
    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    private long restaurantId;
    private long numberOfPeople;
    private LocalDate date;
    private LocalTime time;
}
