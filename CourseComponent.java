import com.coachingcenter.Course;

// ========================================================
// COMPOSITE PATTERN
// ========================================================
interface CourseComponent {
void show();
}


class SingleCourse implements CourseComponent {
private final Course course;
public SingleCourse(Course c){ this.course = c; }
public void show(){ System.out.println("Course: " + course.getCourseName()); }
}


class CourseGroup implements CourseComponent {
private final java.util.List<CourseComponent> components = new java.util.ArrayList<>();
public void add(CourseComponent c){ components.add(c); }
public void show(){ components.forEach(CourseComponent::show); }
}