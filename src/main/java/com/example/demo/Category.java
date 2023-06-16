package com.example.demo;

public class Category {
	private static int lastId=0;
	String name;
	int id;
	public Category(String name) {
		this.name=name;
		this.id=generateId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", id=" + id + "]";
	}
	private int generateId() {
		return ++lastId;
	}
}
