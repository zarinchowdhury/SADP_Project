import com.coachingcenter.Student1;

// ========================================================
// ITERATOR PATTERN
// ========================================================
class StudentIterator implements java.util.Iterator<Student1> {
private final java.util.List<Student1> list;
private int index = 0;
public StudentIterator(java.util.List<Student1> l){ this.list = l; }
public boolean hasNext(){ return index < list.size(); }
public Student1 next(){ return list.get(index++); }
}