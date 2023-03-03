import java.util.HashMap;
public class Course {
    private int max_enroll;
    private HashMap<Integer,Integer>students;
    private int course_id;

    Course(int max_enroll, int course_id) {
        this.max_enroll=max_enroll;
        this.course_id=course_id;
        students=new HashMap<Integer,Integer>();
    }

    public boolean isFull() {
        return curr_enroll()==max_enroll;
    }

    public int curr_enroll() {
        return students.size();
    }

    public int max_enroll() {
        return max_enroll;
    }
    public boolean addCourse(int student_id) {
        if(isFull()) {
            return false;
        }
        if(students.containsKey(student_id)) {
            return false;
        }
        //put delay here
        students.put(student_id,students.size());
        return true;
    }

    public boolean removeCourse(int student_id) throws Exception {
        if(!students.containsKey(student_id)) {
            throw new Exception("student not in course");
        }
        else {
            students.remove(student_id);
        }
        return true;
    }
    
}
