import java.util.ArrayList;
import java.lang.Math;
public class Registrar {
    private ArrayList<Course> courses;
    
    Registrar(int n) {
        courses=new ArrayList<Course>();
    }

    public void makeNewCourse(int max_enroll,int course_id) {
        courses.add(new Course(max_enroll, course_id));
    }



    int getRandomCourse() {
        return (int)(courses.size()*Math.random());
    }

    public int addCourse(int student_id, int course_id) {
        int courseToAdd=0;
        boolean addedToCourse=courses.get(course_id).addCourse(student_id);
        if(addedToCourse) {
            if(courses.get(course_id).curr_enroll()>courses.get(course_id).max_enroll()) {
                System.out.println("ERROR: enrollment exceeded");
            }
            return course_id;
        }
       
        while(!addedToCourse) {
            courseToAdd=getRandomCourse();
            Course course=courses.get(courseToAdd);
            addedToCourse=course.addCourse(student_id);
            if(course.curr_enroll()>course.max_enroll()) {
                System.out.println("ERROR: enrollment exceeded");
            }
        }
       
        return courseToAdd;
    }

    public boolean removeCourse(int course_id, int student_id) {
        Course course=courses.get(course_id);
        try {
            course.removeCourse(student_id);
        } catch (Exception e) {
            System.out.println("Student not in course to remove");
        }
        return true;
    }

  
    
}
