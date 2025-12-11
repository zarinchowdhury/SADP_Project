import com.coachingcenter.CoachingCenterFacade;
import com.coachingcenter.Course;
import com.coachingcenter.Student1;

// ========================================================
// COMMAND PATTERN
// ========================================================
interface Command {
void execute();
}


class AddStudentCommand implements Command {
private final CoachingCenterFacade facade;
private final Student1 student;
public AddStudentCommand(CoachingCenterFacade f, Student1 s){ this.facade = f; this.student = s; }
public void execute(){ facade.getStudents().add(student); }
}


class AddCourseCommand implements Command {
private final CoachingCenterFacade facade;
private final Course course;
public AddCourseCommand(CoachingCenterFacade f, Course c){ this.facade = f; this.course = c; }
public void execute(){ facade.getCourses().add(course); }
}