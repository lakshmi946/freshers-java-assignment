package com.shoppingsimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Store{

	static int stockItemCount;
	static int customerItemCount;
	HashMap<Integer, Item> stock;
	HashMap<Integer, Customer> customers;
	ArrayList<Item> items;
	
	{
		stock = new HashMap<Integer, Item>();
		customers = new HashMap<Integer, Customer>();
		stock.put(1, new Item("pen1", 1, "pen"));
		stock.put(2, new Item("pen2", 2, "pen"));
		stock.put(3, new Item("pen3", 3, "pen"));
		stock.put(4, new Item("pen4", 4, "pen"));
		stock.put(5, new Item("pencil1", 1, "pencil"));
		stock.put(6, new Item("pencil2", 2, "pencil"));
		stock.put(7, new Item("pencil3", 3, "pencil"));
		stock.put(8, new Item("pencil4", 4, "pencil"));
		stock.put(9, new Item("eraser1", 1, "eraser"));
		stock.put(10, new Item("eraser2", 2, "eraser"));
		stock.put(11, new Item("eraser3", 3, "eraser"));
		stock.put(12, new Item("eraser4", 4, "eraser"));
	}
	
	public Store(){
		// TODO Auto-generated constructor stub
	}
	
	public void addItem(Item item){
		
		stock.put(++stockItemCount, item);
	}
	
	public boolean removeItem(int number){
		
		Item item = stock.remove(number);
		
		if(item == null)
		{
			System.out.println("stock is already empty\n");
			return false;
		}
		
		HashMap<Integer, Item> tmpItems = new HashMap<Integer, Item>();
		for (int i = 1; i < number; i++){
			tmpItems.put(i, stock.get(i));
		}
		
		for (int j = number+1; j < stock.size(); j++){
			tmpItems.put((j-1), stock.get(j));
		}
		
		stock = new HashMap<Integer, Item>();
		stock.putAll(tmpItems);
		stockItemCount = stock.size();
		
		return true;
	}
	
	public synchronized void addCustomer(Customer customer){
		customers.put(++customerItemCount, customer);
	}
	
	public boolean removeCustomer(int number){
		
		Customer customer = customers.remove(number);
		
		if(customer == null)
			return false;
		
		HashMap<Integer, Customer> tmpCustomers = new HashMap<Integer, Customer>();
		for (int i = 1; i < number; i++){
			tmpCustomers.put(i, customers.get(i));
		}
		
		for (int j = number+1; j < customers.size(); j++){
			tmpCustomers.put((j-1), customers.get(j));
		}
		
		customers = new HashMap<Integer, Customer>();
		customers.putAll(tmpCustomers);
		customerItemCount = stock.size();
		
		return true;
	}
	
	public void assignCartsToCustomers(){
		for (int i = 0; i < customers.size(); i++){
			customers.get(i).getCart();
		}
	}
	
	public void removeCart(int customerNumber){
		customers.get(customerNumber).freeCart();
	}
	
	public synchronized void addItemsTOClientCart(int number){
		
		Random random = new Random();
		int count = random.nextInt()*100;
		if(Math.signum(count) == -1)
			count = count * -1;
		
			Item item;
			
			int size = count % 5;
			size += 1;
			
			if(size > stock.size())
				size = stock.size();
			if(size != 0 || (stock.size()==0))
			{
			  for(int j = 0; j < size; j++){
				
				  Customer customer = customers.get(number);
				  int type = random.nextInt()*1000;
				  if(Math.signum(type) == -1)
					  type = type * -1;
				  type = type % (stock.size());
				  type += 1;
				  
				  /*String typeString = Item.types.get(type);
				  Integer price = Item.prices.get(typeString);
				
				  item = new Item(typeString+(j+1), (j+1), typeString);*/
				  System.out.println("\nitem: "+type+"retrieved\n");
				  item = stock.get(type);
				  item.setPrice(item.getType(), item.getPrice(item.getType()));
				  customer.addItemTOCart(item);
				  removeItemFromStock(type);
			  }
			}
			else
				System.out.println("No items at stock to add to add to customerscart");
	}
	
	public synchronized boolean removeItemFromStock(int number){
		
		Item item = stock.remove(number);
		
		if(item == null)
			return false;
		
		HashMap<Integer, Item> tmpItems = new HashMap<Integer, Item>();
		for (int i = 1; i < number; i++){
			tmpItems.put(i, stock.get(i));
		}
		
		for (int j = number+1; j < stock.size(); j++){
			tmpItems.put((j-1), stock.get(j));
		}
		
		stock = new HashMap<Integer, Item>();
		stock.putAll(tmpItems);
		stockItemCount = stock.size();
		return true;
	}
	
	//public void removeItemFromClientCart(){
		
	//}
	
	public void display(){
		
		System.out.println("displaying");
		System.out.println(toString());
	}
	
	public String toString(){
		
		String result = "\n\n";
		
		for (int i = 1; i <= stock.size(); i++){
			result += "\n\nitem"+i+":\n"+stock.get(i).toString();
		}
		
		return result;
	}
}
