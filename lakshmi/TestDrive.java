package com.shoppingsimulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestDrive{
	
    Store store;

	int shoppers;
	int minimum;
	int maximum;
	boolean modification;

	//item specifications
	String name;
	int    number;
	int    price;
	String type;
	int quantity;

	//removing
	int removeitem;

	//modification looping 
	String s;

	//customer no
	static volatile int customerNo = 1;
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public TestDrive(int shoppers,int minimum,int maximum,boolean modification){
		
		this.shoppers		= shoppers;
		this.minimum		= minimum;
		this.maximum		= maximum;
		this.modification	= modification;
		
		store = new Store();

		if(shoppers <= 0 && modification == false && minimum > maximum){
			System.out.println("you have entered invalid arguments");
		}

		if(shoppers > 0 && modification == true && maximum > minimum){
			System.out.println("you can't do modification when shoppers are present in the shop");
		}
		
		if(shoppers <=0 && modification == true && maximum > minimum){
		  
		 while(true){	
			 int choice = 1;
		  System.out.println("do you want to go modification panel yes = y, no = n");
		  try {
			s = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  if(s.equals("y") || s.equals("Y")){
			System.out.println("Modification menu :\n 1.Add a product \n 2.Delete a product \n 3.display items");
			try{	
				choice = Integer.parseInt(br.readLine());
			}catch(Exception e){System.out.println("please enter a valid integer"); }
			switch(choice){
				case 1 :	System.out.println("enter the product name\n");
				try {
					name = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

							System.out.println("enter the product type\n");
				try {
					type = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

							System.out.println("enter your product number\n");
							try{	
								number = Integer.parseInt(br.readLine());
							}catch(Exception e){System.out.println("please enter a valid integer"); }
							
							System.out.println("enter your product price\n");
							try{	
								price = Integer.parseInt(br.readLine());
							}catch(Exception e){System.out.println("please enter a valid integer"); }

							System.out.println("enter your product quantity\n");
							try{	
								quantity = Integer.parseInt(br.readLine());
							}catch(Exception e){System.out.println("please enter a valid integer"); }

							Item item = new Item(name,number,type);
							item.setPrice(type,price);
					        item.setQuantity(type,quantity);
							store.addItem(item);
							System.out.println("edited store after adding");
							System.out.println("***********************************************");
							store.display();
							System.out.println("***********************************************");
							break;

				case 2 :    System.out.println("***********************************************");
							store.display();
							System.out.println("***********************************************");
							System.out.println("enter the item number to be removed");
				try {
					removeitem = Integer.parseInt(br.readLine());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							store.removeItem(removeitem);
							System.out.println("edited store after removing");
							System.out.println("***********************************************");
							store.display();
							System.out.println("***********************************************");
							break;

				case 3 :    System.out.println("***********************************************");
							store.display();
							System.out.println("***********************************************");
							break;

				default:    System.out.println(":(:(:(:(:(:( please enter valid menu number :(:(:(:(:(:(:(");
							break;
			}
		  }
		  if(s.equals("n") || s.equals("N"))
			  break;
		 }
		}
		if(maximum >= shoppers && shoppers >= minimum  && modification == false && maximum > minimum){
			
			for(int i=0 ; i<shoppers ; i++){
				
				Create c = new Create(i+1);
				Thread thread = new Thread(c);
				thread.start();
			}
		}
	}

	class Create implements Runnable{
		
		int customerNo;
		public Create(int customerNo){
			// TODO Auto-generated constructor stub
			this.customerNo = customerNo;
		}
		public void run() {
			Customer customer = new Customer();
			if(customer.getCart())
			{
				store.addCustomer(customer);
				store.addItemsTOClientCart(customerNo);
				//System.out.println("\n\ncustomer added and items have been added to customer"+customerNo+" cart\n\n");
			}
			else
				System.out.println("cart didn't retrieved");
			
			//System.out.println("success");
			//store.display();
			String line = "***********************************************************************";
			System.out.println(line+"\ncustomer"+customerNo+" cart details:\n"+store.customers.get(customerNo).getCartDetails()+"\n"+line);
		}
	}

    public static void main(String[] args){
		
		TestDrive testdrive = new TestDrive(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Boolean.getBoolean(args[3]));
	}
}