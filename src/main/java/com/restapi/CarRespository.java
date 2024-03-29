package com.restapi;


import java.util.ArrayList;
import java.util.List;
//example with list
public class CarRespository {
	
	List<Car> cars;
	
	public CarRespository() {
		
	 cars= new ArrayList<Car>();
	 
		Car c1 = new Car();
		c1.setName("Honda");
		c1.setModel(401);	
		Car c2 = new Car();
		c2.setName("Maruti");
		c2.setModel(101);
		cars.add(c1);
		cars.add(c2);
	
	}
	public List<Car> getcars(){
		return cars;	
	}
	public Car getcar(int model){
		for(Car a:cars) {
			if(a.getModel()== model) {
				return a;
			}		
		}
		return new Car();	
	}
	public void create(Car c1) {
		// TODO Auto-generated method stub
		cars.add(c1);
	}
}
