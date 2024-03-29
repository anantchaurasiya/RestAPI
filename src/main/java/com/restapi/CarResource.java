package com.restapi;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//example with list
@Path("/carresource")
public class CarResource {

	CarRespository c = new CarRespository();
	
	@GET
	@Produces("application/XML")
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Car> showcar() {
//		Car car = new Car();
//		car.setName("Honda");
//		car.setModel(401);
//				
//		Car c1 = new Car();
//		c1.setName("Maruti");
//		c1.setModel(101);
//		
//		List<Car> cars = Arrays.asList(car,c1);
		return c.getcars();
	}
	
	@GET
	@Path("car/{model}")
	@Produces({"application/JSON", "application/XML"})
	public Car getcar(@PathParam("model") int model) {
		return c.getcar( model);
	}
	
	
	@POST
	@Path("/AddCar")
	@Produces("application/XML")
	public Car Create(Car c1) {
		
		System.out.println(c1);
		c.create(c1);
		
		return c1;
	}
}
