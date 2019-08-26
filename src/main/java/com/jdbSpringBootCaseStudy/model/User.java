package com.jdbSpringBootCaseStudy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="uId")
	private int uId;
	
	@Column(name="uEmail")
	private String uEmail; 
	
	@Column(name="uFirstName")
	private String uFirstName;
	
	@Column(name="uLastName")
	private String uLastName;
	
	@Column(name="uPassword")
	private String uPassword;
	
	@Column(name="uUserName")
	private String uUserName; 

	@OneToMany
	private List<Product> uProducts;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String uEmail, String uPassword) {
		super();
		this.uEmail = uEmail;
		this.uPassword = uPassword;
	}

	public User(String uFirstName, String uLastName, String uEmail, String uPassword) {
		super();
		this.uEmail = uEmail;
		this.uFirstName = uFirstName;
		this.uLastName = uLastName;
		this.uPassword = uPassword;		
		// TODO Auto-generated constructor stub
	}
	
	public User(int uId, String uEmail, String uFirstName, String uLastName, String uPassword, String uUserName) {
		this.uId = uId;
		this.uEmail = uEmail;
		this.uFirstName = uFirstName;
		this.uLastName = uLastName;
		this.uPassword = uPassword;
		this.uUserName = uUserName;
	}






	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuUserName() {
		return uUserName;
	}
	public void setuUserName(String uUserName) {
		this.uUserName = uUserName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuFirstName() {
		return uFirstName;
	}
	public void setuFirstName(String uFirstName) {
		this.uFirstName = uFirstName;
	}
	public String getuLastName() {
		return uLastName;
	}
	public void setuLastName(String uLastName) {
		this.uLastName = uLastName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	
	
	public List<Product> getuProducts() {
		return uProducts;
	}
	public void setuProducts(List<Product> uProducts) {
		this.uProducts = uProducts;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uUserName=" + uUserName + ", uEmail=" + uEmail + ", uFirstName=" + uFirstName
				+ ", uLastName=" + uLastName + ", uPassword=" + uPassword + "]";
	}
	
	
	
}
