package com.airbus.service;

import java.util.List;

import com.airbus.entitu.Airbus;


public interface ServiceInterface
{
    
	public int savaAirData(Airbus airbus);
	
	public Airbus updateDataById(Integer id);
	
	public Airbus updateDataSave(Airbus airbus);
	
	 public List<Airbus>getDetails();

public	void deleteById(Integer integer);
}
