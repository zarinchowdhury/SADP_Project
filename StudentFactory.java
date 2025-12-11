import com.coachingcenter.Student1;

public class StudentFactory {
    public static Student1 create(String name, int age, String contact, String email) {
        return new Student1(name, age, contact, email);
    }
}