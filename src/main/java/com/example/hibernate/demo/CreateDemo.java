package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create the objects

            Instructor instructor = new Instructor("Chad","Darby","darby@example.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 code!!");

            Instructor anotherInstructor = new Instructor("Nomad","Darby","darby@example.com");
            InstructorDetail anotherInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 code!!");
            // associate the objects
            instructor.setInstructorDetail(instructorDetail);
            anotherInstructor.setInstructorDetail(anotherInstructorDetail);
            // start a transaction
            session.beginTransaction();

            // save the instructor (and instructor detail because of CascadeType.ALL
            System.out.println("Saving instructor: " + instructor);

            session.save(anotherInstructor);


            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
