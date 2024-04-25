package com.crud.hibernate_demo.onetomany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SchoolStudent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stud_id;
	private String stud_name;
	private String city_name;
	private String school_name;
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<Address> address;

	public SchoolStudent(String stud_name, String city_name, String school_name, List<Address> address) {
		super();
		this.stud_name = stud_name;
		this.city_name = city_name;
		this.school_name = school_name;
		this.address = address;
	}

	public SchoolStudent() {
		super();
	}

	public int getStud_id() {
		return stud_id;
	}

	public void setStud_id(int stud_id) {
		this.stud_id = stud_id;
	}

	public String getStud_name() {
		return stud_name;
	}

	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SchoolStudent [stud_id=" + stud_id + ", stud_name=" + stud_name + ", city_name=" + city_name
				+ ", school_name=" + school_name + ", address=" + address + "]";
	}

}
