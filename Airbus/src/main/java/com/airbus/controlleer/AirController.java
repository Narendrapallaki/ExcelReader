package com.airbus.controlleer;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airbus.entitu.Airbus;
import com.airbus.entitu.Product;
import com.airbus.service.ProductService;
import com.airbus.service.ServiceInterface;
@RestController
public class AirController 
{
	@Autowired
	private ServiceInterface interface1;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/insertData")
	public String getPassengerData(Model model) {
		
//		Airbus a1=new Airbus();
//		model.addAttribute("data", a1);
		
		return "insert";
		
	}
	
	@RequestMapping(path ="datapost",method = RequestMethod.GET)
   public String dataPost(@ModelAttribute Airbus airbus)// model annotation it take input from the html page
   {
		//System.out.println(airbus);
		interface1.savaAirData(airbus);
		
	return "redirect:/done";//redirect to result page
	   
   }
	
	@RequestMapping(path="/done")
    public String dataget(Model model)
    {
		model.addAttribute("data", interface1.getDetails());
		
		return "success";
    	
    }
	
	@RequestMapping("/updata/{id}")
    public String updateDataaa(@PathVariable Integer id,Model model)
    {
		
		model.addAttribute("student", interface1.updateDataById(id));
		
		return "update";
    	
    }

	@RequestMapping(path ="/modifyData/{id}" ,method = RequestMethod.GET)
	public String updatedDataStore(@PathVariable Integer id ,@ModelAttribute Airbus airbus,Model model)
	{
		
		Airbus a1=interface1.updateDataById(id);
		
		a1.setId(airbus.getId());
		a1.setName(airbus.getName());
		a1.setMail(airbus.getMail());
		a1.setPhone(airbus.getPhone());
		
		
		interface1.updateDataSave(a1);
	
		return "redirect:/done";
		
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteData(@PathVariable Integer id)
	{
		interface1.deleteById(id);
		
		return "redirect:/done";
		
	}
	
	@RequestMapping("/home")
	public String homePage()
	{
		return "home";
	}
	
//	@RequestMapping("/updiv/{id}")
//	public String upNav(@PathVariable Integer id,Model model)
//	{
//		model.addAttribute("student", interface1.updateDataById(id));
//		return"redirect:/updata/{id}";
//	}
//	
	
	@GetMapping("/fetchForecastSheet")
	public ResponseEntity<List<Product>>fetchForecastSheet()
	{
		
		try {
			List<Product> atmDateExcelSheetReader = productService.atmDateExcelSheetReader();
			return new ResponseEntity<List<Product>>(atmDateExcelSheetReader, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
