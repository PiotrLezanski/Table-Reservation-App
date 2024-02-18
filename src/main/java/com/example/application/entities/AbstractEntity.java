package com.example.application.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() 
    {
        return id;
    }
    
    public boolean isPersisted()
    {
        return id != null;
    }
    
    @Override
    public int hashCode()
    {
        if(id != null)
        {
            return id.hashCode();
        }
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        
        AbstractEntity other = (AbstractEntity) obj;
        if(id == null || other.getId() == null)
            return false;
        
        return true;
    }
}
