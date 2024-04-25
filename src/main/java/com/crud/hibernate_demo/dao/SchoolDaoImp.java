package com.crud.hibernate_demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.crud.hibernate_demo.onetomany.SchoolStudent;
import com.crud.hibernate_demo.util.HibernateUtil;

public class SchoolDaoImp implements SchoolDao {

	@Override
	public void saveStudentData(SchoolStudent schoolStudent) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(schoolStudent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println("Exception occure while saving student object" + e);
			}
		}

	}

	@Override
	public SchoolStudent getStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchoolStudent> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(SchoolStudent schoolStudent) {
		// TODO Auto-generated method stub

	}

}
