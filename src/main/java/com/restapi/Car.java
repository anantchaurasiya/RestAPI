package com.restapi;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {

	private String name;
	private int model;
	private String color;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "Car [name=" + name + ", model=" + model + ", color=" + color + "]";
	}
	
	
}
