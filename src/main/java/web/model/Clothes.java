package web.model;

import java.util.Date;

public class Clothes {
	int num;
	String name;
	int price;
	String description;
	String files;
	Date regdate;
	
	public Clothes() {
		// TODO Auto-generated constructor stub
	}
	
	public Clothes(int num, String name, int price, String description, String files, Date regdate) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.description = description;
		this.files = files;
		this.regdate = regdate;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
