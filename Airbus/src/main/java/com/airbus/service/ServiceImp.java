package com.airbus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbus.Repositery.AirRepositery;
import com.airbus.entitu.Airbus;

@Service
public class ServiceImp implements ServiceInterface
{

	@Autowired
	private AirRepositery airRepositery;
	
	@Override
	public int savaAirData(Airbus airbus) {
	
		try {
			airRepositery.save(airbus);
			return 1;
		}catch (Exception e) {
			return 0;
		}
	}
    //get the data from the data base
	@Override
	public Airbus updateDataById(Integer id) {
		
		 
		return airRepositery.findById(id).get();
	}
     // updated data stored in this method
	@Override
	public Airbus updateDataSave(Airbus airbus) {
		
		
		return airRepositery.save(airbus);
	}
   // retrive the data from the data base
	@Override
	public List<Airbus> getDetails() {
		// TODO Auto-generated method stub
		return (List<Airbus>) airRepositery.findAll();
	}

@Override
public void deleteById(Integer integer) {
	
	
	 airRepositery.deleteById(integer);
}
	

}
