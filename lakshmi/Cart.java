package com.shoppingsimulation;

import java.util.HashMap;

public class Cart{
	
	static int number;//current number of carts
	static int total;//total number of carts
	int itemCount;//total number of items in the cart
	HashMap<Integer, Item> items;
	
	private Cart(){
		// TODO Auto-generated constructor stub
		itemCount = 0;
		items = new HashMap<Integer, Item>();
	}
	
	public void setQuantity(int total){
		Cart.total = total;
	}
	
	public static Cart getInstance(){
		number++;
		if(number == total)
			return null;
		
		return new Cart();
	}
	
	public void freeCart(){
		number--;
	}
	
	public void addItem(Item item){
		items.put(++itemCount, item);
	}
	
	public Boolean removeItem(int number){
		
		Item item = items.remove(number);
		
		if(item == null)
			return false;
		
		HashMap<Integer, Item> tmpItems = new HashMap<Integer, Item>();
		for (int i = 1; i < number; i++){
			tmpItems.put(i, items.get(i));
		}
		
		for (int j = number+1; j < items.size(); j++){
			tmpItems.put((j-1), items.get(j));
		}
		
		items = new HashMap<Integer, Item>();
		items.putAll(tmpItems);
		itemCount = items.size();
		
		return true;
	}
	
	public Item getItem(int cartItemNumber){
		return items.get(cartItemNumber);
	}
	
	public String toString(){
		
		String result = "\n\n";
		for (int i = 1; i <= items.size(); i++){
			result += "item"+i+": "+items.get(i).toString()+"\n";
		}
		
		return result;
	}
}