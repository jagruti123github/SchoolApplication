package com.crud.hibernate_demo;

import org.hibernate.SessionFactory;

import com.crud.hibernate_demo.dao.SchoolDaoImp;
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
        SchoolStudent schoolStudent =new SchoolStudent("Jagruti","Surat","V.W.S.School");
        SchoolDaoImp schoolDaoImp=new SchoolDaoImp();
        schoolDaoImp.saveStudentData(schoolStudent);
    }
}
