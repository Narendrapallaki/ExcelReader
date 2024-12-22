package com.airbus.entitu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Product {
	
	
  

	private String name;
    private String type1;
    private String type2;
    private int hp;
    private int column1;
    private int column2;
    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String type1, String type2, int hp, int column1, int column2) {
		super();
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.hp = hp;
		this.column1 = column1;
		this.column2 = column2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getColumn1() {
		return column1;
	}
	public void setColumn1(int column1) {
		this.column1 = column1;
	}
	public int getColumn2() {
		return column2;
	}
	public void setColumn2(int column2) {
		this.column2 = column2;
	}
	
    
    
    

}
