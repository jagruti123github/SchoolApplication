package com.crud.hibernate_demo;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.crud.hibernate_demo.dao.SchoolDao;
import com.crud.hibernate_demo.dao.SchoolDaoImp;
import com.crud.hibernate_demo.entity.Address;
import com.crud.hibernate_demo.entity.SchoolStudent;

public class App {
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		SchoolDao schoolDao = new SchoolDaoImp(); 

		while (true) {
			System.out.println("Choose an operation:");
			System.out.println("1. Save");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Retrieve All");
			System.out.println("5. Retrieve Single");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
            //Conversion of string to Integer cause BR takes only string data as input 
			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {
			case 1:
				// Accepting data from user

				System.out.println("Enter student name:");
				String name = reader.readLine();
				System.out.println("Enter city name:");
				String city =reader.readLine();
				System.out.println("Enter school name:");
				String school = reader.readLine();
				System.out.println("Enter country:");
				String country =reader.readLine();

				// SchoolStudent object
				Address address = new Address();
				address.setCountry(country);
				SchoolStudent newStudent = new SchoolStudent(name, city, school, address);

				schoolDao.saveStudentData(newStudent);
				break;
			case 2:
				// Update
				System.out.println("Enter student ID to update record:");
				int updateId = Integer.parseInt(reader.readLine());
				
				if(schoolDao.studentExists(updateId)) {
				System.out.println("Enter updated student name:");
				String updatedName =reader.readLine();
				System.out.println("Enter updated city name:");
				String updatedCity = reader.readLine();
				System.out.println("Enter updated school name:");
				String updatedSchool = reader.readLine();
				System.out.println("Enter updated country:");
				String updatedCountry = reader.readLine();
				schoolDao.updateStudent(updateId, updatedName, updatedCity, updatedSchool, updatedCountry);
				}else {												    
				    System.err.println("Resource Not Found for "+"School Student with ID " + updateId + " does not exist. Try again");
				}
				break;
			case 3:
				// Delete
				System.out.println("Enter student ID to delete:");
				// int deleteId = scanner.nextInt();

				// Call the delete method
				// schoolDao.deleteStudent(deleteId);
				// System.out.println("Student data deleted successfully.");
				break;
			case 4:
				// Retrieve all
				System.out.println("Retrieving all students:");
				schoolDao.getAllStudents().forEach(System.out::println);
				break;
			case 5:
				// Retrieve single
				System.out.println("Enter student ID to retrieve:");
				// int retrieveId = scanner.nextInt();

				// Call the getStudent method
				// SchoolStudent student = schoolDao.getStudent(retrieveId);
				// if (student != null) {
				// System.out.println("Retrieved student:");
				// System.out.println(student);
				// } else {
				// System.out.println("Student not found.");
				// }
				break;
			case 6:
				// Exit the program
				System.out.println("Exit.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 6.");
			}
		}
	}
}
