package com.airbus.Repositery;

import org.springframework.data.repository.CrudRepository;

import com.airbus.entitu.Airbus;

public interface AirRepositery extends CrudRepository<Airbus, Integer>
{

}
