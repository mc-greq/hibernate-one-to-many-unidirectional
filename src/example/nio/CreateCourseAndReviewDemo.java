package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import example.nio.entities.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

    public static void main(String[] args) {
	// write your code here
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try (factory;session){
            session.beginTransaction();

            // crete a course
            Course tempCourse = new Course("Pacman - How to Score one Million Points");

            // add some reviews
            tempCourse.addReview(new Review("Great course, loved it!"));
            tempCourse.addReview(new Review("Not too shabby!"));
            tempCourse.addReview(new Review("Cool course, recommended!"));
            tempCourse.addReview(new Review("What a stupid course, unsubscribed"));

            // save the course and leverage cascade all
            session.save(tempCourse);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }

    }
}
