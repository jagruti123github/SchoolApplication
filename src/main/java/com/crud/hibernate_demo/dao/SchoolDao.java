package com.crud.hibernate_demo.dao;

import java.util.List;

import com.crud.hibernate_demo.onetomany.SchoolStudent;



public interface SchoolDao {
	public void saveStudentData(SchoolStudent schoolStudent);
	public SchoolStudent getStudent();
	public void updateStudent(SchoolStudent schoolStudent);
	public List<SchoolStudent> getAllStudents();
	public void deleteStudent();
	

}
