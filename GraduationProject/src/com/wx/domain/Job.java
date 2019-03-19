package com.wx.domain;

public class Job {

	private String position;
	
	private String company;
	
	private String compensation;//工作地点
	
	private String wages;
	
	private String date;
	
	private String info;//相关工作要求
	
	private Integer order;
	
	private Integer pages;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public String getWages() {
		return wages;
	}

	public void setWages(String wages) {
		this.wages = wages;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Job [position=" + position + ", company=" + company
				+ ", compensation=" + compensation + ", wages=" + wages
				+ ", date=" + date + ", order=" + order + "]";
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
