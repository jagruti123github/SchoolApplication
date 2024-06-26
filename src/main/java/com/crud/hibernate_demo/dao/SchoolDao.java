package com.crud.hibernate_demo.dao;

import java.util.List;

import com.crud.hibernate_demo.entity.SchoolStudent;

public interface SchoolDao {
	public void saveStudentData(SchoolStudent schoolStudent);
	public SchoolStudent getStudent();
	public boolean studentExists(int studentId);
//	public void updateStudent(SchoolStudent schoolStudent);
	public List<SchoolStudent> getAllStudents();
	public void deleteStudent();
	public void updateStudent(int updateId, String updatedName, String updatedCity, String updatedSchool,
			String updatedCountry);

}
