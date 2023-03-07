package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            // create 3 student objects
            System.out.println("Creating objects");
            Student student1 = new Student("Paul", "Wall", "paul@example.com");
            Student student2 = new Student("John", "Doe", "john@example.com");
            Student student3 = new Student("Mary", "Avocado", "avocado@example.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            session.save(student1);
            session.save(student2);
            session.save(student3);
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
