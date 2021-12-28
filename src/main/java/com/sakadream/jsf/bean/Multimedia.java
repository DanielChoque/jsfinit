package com.sakadream.jsf.bean;

public class Multimedia {
	private int num;
	private String name;
	private String url;
	private String size;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Multimedia(int num, String name, String url, String size) {
		super();
		this.num = num;
		this.name = name;
		this.url = url;
		this.size = size;
	}
}
