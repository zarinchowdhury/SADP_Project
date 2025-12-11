package com.coachingcenter;

import java.util.*;

public class CoachingCenterFacade {

    private final List<Student1> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();

    public List<Student1> getStudents() { return students; }
    public List<Course> getCourses() { return courses; }
    public List<Enrollment> getEnrollments() { return enrollments; }

    // Factory-like creation
    public Student1 createStudent(String name, int age, String contact, String email) {
        Student1 s = new Student1(name, age, contact, email);
        students.add(s);
        return s;
    }

    public Course createCourse(String name) {
        Course c = new Course(name);
        courses.add(c);
        return c;
    }

    public Enrollment enroll(Student1 student, Course course) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student) && e.getCourse().equals(course)) {
                return null;
            }
        }
        Enrollment e = new Enrollment(student, course);
        enrollments.add(e);
        return e;
    }
}
