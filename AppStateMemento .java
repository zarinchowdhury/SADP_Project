import com.coachingcenter.CoachingCenterFacade;
import com.coachingcenter.Course;
import com.coachingcenter.Student1;

// ========================================================
// MEMENTO PATTERN
// ========================================================
class AppStateMemento {
    public final java.util.List<Student1> studentsSnapshot;
    public final java.util.List<Course> coursesSnapshot;
    public AppStateMemento(java.util.List<Student1> s, java.util.List<Course> c){
        this.studentsSnapshot = new java.util.ArrayList<>(s);
        this.coursesSnapshot = new java.util.ArrayList<>(c);
    }
}


class StateCaretaker {
    private final java.util.Stack<AppStateMemento> history = new java.util.Stack<>();
    public void save(CoachingCenterFacade facade){ history.push(new AppStateMemento(facade.getStudents(), facade.getCourses())); }
    public void undo(CoachingCenterFacade facade){
        if(!history.isEmpty()){
            AppStateMemento m = history.pop();
            facade.getStudents().clear();
            facade.getStudents().addAll(m.studentsSnapshot);
            facade.getCourses().clear();
            facade.getCourses().addAll(m.coursesSnapshot);
        }
    }
}