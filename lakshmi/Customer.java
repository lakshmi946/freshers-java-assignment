package com.shoppingsimulation;

public class Customer {
	
	private String  name;
	private int     age;
	private boolean type; //true = heavy customer   false = normal customer
	Cart cart ; // cart for individual customer

	public Customer(){
		
		this.name = "lakshmi";
		this.age  = 21;
		this.type = true;
	}

	public Customer(String name,int age,boolean type){
		
		this.name = name;
		this.age  = age;
		this.type = type;
	}

	// setters
	public void setName(String name){
		
		this.name = name;
	}

	public void setAge(int age){
		
		this.age = age;
	}

	public void setType(boolean type){
		
		this.type = type;
	}
	
	public void addItemTOCart(Item item){
		cart.addItem(item);
	}
	
	public void removeItemFromCart(int number){
		cart.removeItem(number);
	}

	//getters
	
	public boolean getCart(){
		
	   cart = Cart.getInstance();
	   if(cart == null){
		   //System.out.println("cart didn't retrieved");
		   return false;
	   }
	   System.out.println("cart retrieved");
	   return true;
		
	}
	
	public void freeCart(){
		cart.freeCart();
		cart = null;
	}
	public String getName(){
		
		return name;
	}

	public int getAge(){
		
		return age;
	}

	public boolean getType(){
		
		return type;
	}

	//equals method
	public boolean equals(Customer c){
		
		return (name.equals(c.name)&&(age == c.age)&&(type == c.type));
	}
	
	public String getCartDetails(){
		
		String result = "\n";
		
		for (int i = 1; i <= cart.items.size(); i++){
			result += "\n\nItem"+i+":\n\n"+cart.items.get(i).toString()+"\n";
			//result += "\n\nhello";
		}
		
		result += "\n\nTotal Price: "+totalPrice();
		
		return result;
	}
	
	public int totalPrice(){
		int total=0;
		
		for (int i = 1; i <= cart.items.size(); i++){
			total += cart.items.get(i).getPrice(cart.items.get(i).getType());
		}
		
		return total;
	}
	
	//tostring method - to display the details
	public String toString(){
		String result;
		result = ("\n name:"+name+"\n age:"+age+"\n type:"+type);
		return result;
	}
}