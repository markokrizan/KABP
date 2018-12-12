package util;

import java.util.Date;

public class Thing {
	
	private Integer id;
	private String name;
	private Date date;
	
	
	public Thing() {
		
	}
	
	public Thing(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Thing [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
	
	
	

}
