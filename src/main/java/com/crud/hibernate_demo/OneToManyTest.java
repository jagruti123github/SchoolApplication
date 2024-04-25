package com.crud.hibernate_demo;

import java.util.ArrayList;
import java.util.List;

import com.crud.hibernate_demo.dao.SchoolDaoImp;
import com.crud.hibernate_demo.onetomany.Address;
import com.crud.hibernate_demo.onetomany.SchoolStudent;

public class OneToManyTest {

	public static void main(String[] args) {
		Address adr = new Address("Permanent", "MH");
		Address adr1 = new Address("Current", "Gujrat");
		List<Address> addList = new ArrayList<Address>();
		addList.add(adr);
		addList.add(adr1);
		SchoolStudent schoolStudent = new SchoolStudent();
		schoolStudent.setStud_name("Jagruti");
		schoolStudent.setCity_name("Surat");
		schoolStudent.setSchool_name("V.W.S.School");
		schoolStudent.setAddress(addList);
		
		SchoolDaoImp schoolDaoImp= new SchoolDaoImp();
		schoolDaoImp.saveStudentData(schoolStudent);
		System.out.println("Student data saved successfully");

	}

}
