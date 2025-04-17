import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;

@Entity
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    // Getters and Setters
    public int getId() { return id; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setAge(int age) { this.age = age; }
    public int getAge() { return age; }
}

public class MediumLevel {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        // CREATE
        Session session = factory.openSession();
        session.beginTransaction();
        Student student = new Student();
        student.setName("Bob");
        student.setAge(22);
        session.save(student);
        session.getTransaction().commit();
        session.close();

        int studentId = student.getId();

        // READ
        session = factory.openSession();
        Student readStudent = session.get(Student.class, studentId);
        System.out.println("Read: " + readStudent.getName() + " (" + readStudent.getAge() + ")");
        session.close();

        // UPDATE
        session = factory.openSession();
        session.beginTransaction();
        readStudent.setAge(23);
        session.update(readStudent);
        session.getTransaction().commit();
        session.close();

        // DELETE
        session = factory.openSession();
        session.beginTransaction();
        Student delStudent = session.get(Student.class, studentId);
        session.delete(delStudent);
        session.getTransaction().commit();
        session.close();

        factory.close();
    }
}
