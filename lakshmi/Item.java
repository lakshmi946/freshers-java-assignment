package com.shoppingsimulation;

import java.util.ArrayList;
import java.util.HashMap;

public class Item{

	String name;
	int number;
	String type;
	static HashMap<String, Integer> prices = new HashMap<String,Integer>();
	static HashMap<String, Integer> quantity = new HashMap<String,Integer>();
	static ArrayList<String> types = new ArrayList<String>();
	static int price;
	
	static
	{
		types.add("pen");
		types.add("pencil");
		types.add("eraser");
		
		prices.put(types.get(0), 10);
		prices.put(types.get(1), 5);
		prices.put(types.get(2), 5);
		
		quantity.put(types.get(0), 100);
		quantity.put(types.get(1), 150);
		quantity.put(types.get(2), 200);
	}
	
	public Item(String name, int number, String type){
		this.name = name;
		this.number = number;
		
		if (types.contains(type)){
			this.type = type;
			Integer tmpQuantity = quantity.get(type);
			quantity.put(type, tmpQuantity++);
		}
		else
		{
			types.add(type);
			
		}	
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public void setType(String type){
		
		if(types.contains(type))
			this.type = type;
		else
			System.out.println("wrong type");
	}
	
	public void setQuantity(String type, int total){
		if (types.contains(type)) {
			quantity.put(type, total);
		}
		else
			System.out.println("wrong type");
	}
	
	public void setPrice(String type, int priceValue){
		
		if(types.contains(type))
			prices.put(type, priceValue);
		else
			System.out.println("wrong type");
	}
	
	public String getName(){
		return name;
	}
	
	public int getItemNumber(){
		return number;
	}
	
	public String getType(){
		return type;
	}
	
	public int getQuantity(String type){
		return quantity.get(type);
	}
	
	public int getPrice(String type){
		return prices.get(type);
	}
	
	public Boolean equals(Item item){
		
		if(this.name == item.getName() && this.number == item.getItemNumber() && this.type.equals(item.type))
			return true;
		
		return false;
	}
	
	public String toString(){
		
		String result = "";
		
		result += "name: "+name+"\n"+
				  "number: "+number+"\n"+
				  "type: "+type+"\n"+
				  //"quantity: "+quantity+"\n"+
				  "price: "+prices.get(type);
		
		return result;
	}
}