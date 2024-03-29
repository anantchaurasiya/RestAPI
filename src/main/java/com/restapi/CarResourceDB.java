package com.restapi;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/cars")
public class CarResourceDB {
	
   CarRespositoryDB cd = new CarRespositoryDB();
	
	@GET
	@Produces("application/JSON")
	public List<Car> getCars(){
		
		return cd.getCars();
		
	}
	@GET
	@Path("/getcar/{id}")
	@Produces("application/JSON")
	public Car getCar(@PathParam("id") int id) {
		
		return cd.getCar(id);
	}
	
	@POST
	@Path("/create")
	@Produces("application/JSON")
	public Car Create(Car c) {
		
		cd.setCar(c);
		return c;
	}
	
	@DELETE
	@Path("/deletecar/{id}")
	@Produces("application/JSON")
	public String deleteCar(@PathParam("id") int id) {
		
		return cd.deleteCar(id);
	}
    
	@PUT
	@Path("/updatecar")
	@Produces("application/JSON")
	public String updateCar(Car c) {
		
		return cd.updateCar(c);
	}
}
