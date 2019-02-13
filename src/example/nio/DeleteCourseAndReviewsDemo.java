package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import example.nio.entities.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory;session){
            session.beginTransaction();

            // get the course
            int id = 10;
            Course tempCourse = session.get(Course.class, id);

            // print the course

            System.out.println(tempCourse);

            // print the course reviews
            System.out.println(tempCourse.getReviews());

            // delete the course
            System.out.println("Deleting the course");
            session.delete(tempCourse);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
