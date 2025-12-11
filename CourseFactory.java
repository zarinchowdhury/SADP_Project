import com.coachingcenter.Course;

public class CourseFactory {
public static Course create(String name) {
return new Course(name);
}
}