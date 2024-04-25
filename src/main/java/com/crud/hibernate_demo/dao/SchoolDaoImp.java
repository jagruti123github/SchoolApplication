package com.crud.hibernate_demo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.crud.hibernate_demo.entity.Address;
import com.crud.hibernate_demo.entity.SchoolStudent;
import com.crud.hibernate_demo.exception.ResourceNotFoundException;
import com.crud.hibernate_demo.util.HibernateUtil;

public class SchoolDaoImp implements SchoolDao {
	
	private static final Logger logger = Logger.getLogger(SchoolDaoImp.class.getName());
	
	EntityManager entityManager;

	
//	public void saveStudentData(SchoolStudent schoolStudent) {
//		Transaction transaction=null;
//		try (Session session=HibernateUtil.getSessionFactory().openSession()){
//			transaction=session.beginTransaction();
//			session.save(schoolStudent);
//			transaction.commit();
//			System.out.println("Student data saved successfully.");
//		     // Loading existing workbook if it exists, or create a new one
//	        Workbook workbook;
//	        File file = new File("SchoolStudentData.xlsx");
//	        if (file.exists()) {
//	            workbook = WorkbookFactory.create(file);
//	        } else {
//	            workbook = new XSSFWorkbook();
//	        }
//	        
//	        // Get or create the sheet
//	        Sheet sheet = workbook.getSheet("SchoolStudentData");
//	        if (sheet == null) {
//	            sheet = workbook.createSheet("SchoolStudentData");
//	            // Create headers only if the sheet is newly created
//	            Row headerRow = sheet.createRow(0);
//	            headerRow.createCell(0).setCellValue("Student Id");
//	            headerRow.createCell(1).setCellValue("Student Name");
//	            headerRow.createCell(2).setCellValue("Student City");
//	            headerRow.createCell(3).setCellValue("Student School");
//	            headerRow.createCell(4).setCellValue("Student Country");
//	        }
//	        
//	        // Get the last row number and add a new row
//	        int lastRowNum = sheet.getLastRowNum();
//	        Row dataRow = sheet.createRow(lastRowNum + 1);
//	        dataRow.createCell(0).setCellValue(schoolStudent.getStud_id());
//	        dataRow.createCell(1).setCellValue(schoolStudent.getStud_name());
//	        dataRow.createCell(2).setCellValue(schoolStudent.getCity_name());
//	        dataRow.createCell(3).setCellValue(schoolStudent.getSchool_name());
//	        dataRow.createCell(4).setCellValue(schoolStudent.getAddress().getCountry());
//	        
//	        // Write the workbook to a file
//	        try (FileOutputStream fos = new FileOutputStream("SchoolStudentData.xlsx")) {
//	            workbook.write(fos);
//	            logger.info("Student record written ti excel file successfully.");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        
//		} catch (Exception e) {
//			if(transaction!=null) {
//				transaction.rollback();				
//			}
//			logger.warning("Exception occure while saving student object : "+e);			
//		}
//		
//	}
	@Override
	public void saveStudentData(SchoolStudent schoolStudent) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(schoolStudent);
	        transaction.commit();
	        System.out.println("Student data saved successfully.");

	        File file = new File("SchoolStudentData.xlsx");
	        Workbook workbook;
	        if (file.exists()) {
	            FileInputStream fis = new FileInputStream(file);
	            workbook = WorkbookFactory.create(fis);
	            fis.close();
	        } else {
	            workbook = new XSSFWorkbook();
	        }

	        Sheet sheet = workbook.getSheet("SchoolStudentData");
	        if (sheet == null) {
	            sheet = workbook.createSheet("SchoolStudentData");
	        }

	        
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            headerRow = sheet.createRow(0);
	            headerRow.createCell(0).setCellValue("Student Id");
	            headerRow.createCell(1).setCellValue("Student Name");
	            headerRow.createCell(2).setCellValue("Student City");
	            headerRow.createCell(3).setCellValue("Student School");
	            headerRow.createCell(4).setCellValue("Student Country");
	        }

	        
	        int lastRowNum = sheet.getLastRowNum();
	        Row dataRow = sheet.createRow(lastRowNum + 1);
	        dataRow.createCell(0).setCellValue(schoolStudent.getStud_id());
	        dataRow.createCell(1).setCellValue(schoolStudent.getStud_name());
	        dataRow.createCell(2).setCellValue(schoolStudent.getCity_name());
	        dataRow.createCell(3).setCellValue(schoolStudent.getSchool_name());
	        dataRow.createCell(4).setCellValue(schoolStudent.getAddress().getCountry());

	        FileOutputStream fos = new FileOutputStream(file);
	        workbook.write(fos);
	        fos.close();
	        logger.info("Student record successfully saved in excel file.");
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        logger.warning("Exception occurred while saving student object : " + e);
	    }
	}


	@Override
	public void updateStudent(int updateId, String updatedName, String updatedCity, String updatedSchool,String updatedCountry){
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
	        	
	            throw new ResourceNotFoundException("School Student with ID " + updateId + " not found.");
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();	            	            
	        }
	        logger.warning("Exception caught while updating school student updateStudent()");	        
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
		}catch (HibernateException e) {
			if(transaction!=null) {
				transaction.rollback();				
			}
			logger.warning("Exception occured while retriving all students"+e);
			throw new ResourceNotFoundException("Unexpected error while retrieving school students record "+e);
		}
		return students;
	}

	@Override
	public void deleteStudent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean studentExists(int studentId) {
		Transaction tx = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			tx = session.beginTransaction();
			SchoolStudent schoolStudentId = session.get(SchoolStudent.class, studentId);
			tx.commit();
			return schoolStudentId!=null;
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw new ResourceNotFoundException("Caught Error while checking student existance: " + e);
		}
	}


	@Override
	public SchoolStudent getStudent() {
		// TODO Auto-generated method stub
		return null;
	}

}
