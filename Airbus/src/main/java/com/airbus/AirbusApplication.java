package com.airbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.airbus.Repositery.AirRepositery;
import com.airbus.entitu.Airbus;

@SpringBootApplication
public class AirbusApplication 
//implements CommandLineRunner
{
//	@Autowired
//	private AirRepositery airRepositery;

	public static void main(String[] args) {
		SpringApplication.run(AirbusApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		Airbus air=new Airbus(1, "siri", "siri@gmail.com", "12345678");
//		
//		airRepositery.save(air);
//		System.out.println("data inserted");
//		
//		
//	}

}
