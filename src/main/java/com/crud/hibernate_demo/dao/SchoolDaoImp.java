package com.crud.hibernate_demo.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.crud.hibernate_demo.entity.Address;
import com.crud.hibernate_demo.entity.SchoolStudent;
import com.crud.hibernate_demo.util.HibernateUtil;
import com.google.protobuf.Struct;

public class SchoolDaoImp implements SchoolDao {
	
	private static final Logger logger = Logger.getLogger(SchoolDaoImp.class.getName());

	@Override
	public void saveStudentData(SchoolStudent schoolStudent) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.save(schoolStudent);
			transaction.commit();
			System.out.println("Student data saved successfully.");
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				logger.warning("Exception occure while saving student object : "+e);
			}
		}
		
	}

	@Override
	public SchoolStudent getStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStudent(int updateId, String updatedName, String updatedCity, String updatedSchool,
			String updatedCountry){
		Transaction tx = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        SchoolStudent studentToUpdate = session.get(SchoolStudent.class, updateId);
	        if (studentToUpdate != null) {
	            // Updating the school student
	            studentToUpdate.setStud_name(updatedName);
	            studentToUpdate.setCity_name(updatedCity);
	            studentToUpdate.setSchool_name(updatedSchool);
	            // Updating the address 
	            Address address = studentToUpdate.getAddress();
	            if (address != null) {
	                address.setCountry(updatedCountry);
	            } else {
	                Address newAddress = new Address();
	                newAddress.setCountry(updatedCountry);
	                studentToUpdate.setAddress(newAddress);
	            }
	            session.update(studentToUpdate);
	            tx.commit();
	            System.out.println("Student data updated successfully.");
	        } else {
	            logger.warning("Student with ID " + updateId + " not found.");
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	            logger.warning("Exception occurred while updating school student.");
	        }
	    }
		
	}

	@Override
	public List<SchoolStudent> getAllStudents() {
		Transaction transaction=null;
		List<SchoolStudent> students =null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			students = session.createQuery("from SchoolStudent",SchoolStudent.class).list();
			transaction.commit();
			logger.info("Retrival Operation done successfully");
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				logger.warning("Exception occured while retriving all students"+e);
			}
		}
		return students;
	}

	@Override
	public void deleteStudent(int deleteId) {
		Transaction transaction = null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			 SchoolStudent studentToDelete = session.get(SchoolStudent.class, deleteId);
			 if (studentToDelete != null) {
				 session.remove(studentToDelete);
				 transaction.commit();
				 logger.info("Delete Operation done successfully");
			 }else {
				 logger.warning("Student with ID " + deleteId + " not found.");
			 }
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				logger.warning("Exception occured while deleting students"+e);
			}
		}
		
	}

}
