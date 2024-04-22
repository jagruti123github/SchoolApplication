package com.crud.hibernate_demo.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.crud.hibernate_demo.entity.Address;
import com.crud.hibernate_demo.entity.SchoolStudent;
// look into StandardServiceRegistryBuilder
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null) {
			Configuration configuration = new Configuration();
			Properties properties = new Properties();
			properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/javapractise");
			properties.put(Environment.USER, "root");
			properties.put(Environment.PASS, "Mysql@2238399");
			properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			properties.put(Environment.SHOW_SQL, true);
			properties.put(Environment.HBM2DDL_AUTO, "create");
			
			configuration.addAnnotatedClass(SchoolStudent.class);
			configuration.addAnnotatedClass(Address.class);
			configuration.setProperties(properties);
		 StandardServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return sessionFactory;
		
	}

}
