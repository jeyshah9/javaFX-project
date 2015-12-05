package controller;

import application.Model;

public class StudentData {
	private String[] value;
	private int i;

	public StudentData(int i) {
		this.i = i;
		this.value = Model.getLoginData(i);
	}
	
	public String getNumber() {
		
		return i+"";
	}

	public void setNumber(String number) {
	}

	public String getName() {
		return value[0];
	}

	public void setName(String name) {
	}

	public String getPassword() {
		return value[1];
	}

	public void setPassword(String password) {
	}

	public String getLast() {
		return value[3];
	}

	public void setLast(String last) {
	}

	public String getRegistered() {
		return value[2];
	}

	public void setRegistered(String registered) {
	}

}
