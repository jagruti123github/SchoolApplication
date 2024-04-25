package com.crud.hibernate_demo;

import org.hibernate.SessionFactory;

import com.crud.hibernate_demo.dao.SchoolDaoImp;
import com.crud.hibernate_demo.entity.Address;
import com.crud.hibernate_demo.entity.SchoolStudent;
import com.crud.hibernate_demo.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println(sessionFactory);
        SchoolStudent schoolStudent =new SchoolStudent();
//        schoolStudent.setStud_id(1);
        schoolStudent.setStud_name("test 1");
        schoolStudent.setSchool_name("test school 1");
        schoolStudent.setCity_name("test city 1");
        Address addr = new Address();
//        addr.setAddressId(1);
        addr.setCountry("Australia");
        schoolStudent.setAddress(addr);
        
        SchoolDaoImp schoolDaoImp=new SchoolDaoImp();
       // schoolDaoImp.saveStudentData(schoolStudent);
    }
}
