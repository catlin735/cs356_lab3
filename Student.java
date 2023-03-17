import java.util.HashMap;
import java.util.PriorityQueue;


public class Student extends Thread {
    private static final double MIN_STARTING_INTERVAL = 0.1; // in seconds; should be at least 0.1
    private static final double STARTING_INTERVAL_VAR= 2.0; 
    private int student_id;
    HashMap<Integer, Integer> desired;
    private final Registrar registrar;
    private int COURSE_NUM;
    private final int MAX_TRIES=2;
    PriorityQueue<StudentCourse> student_courses;
    int tries=0;
    int success=0;

    Student(int student_id,Registrar registrar, HashMap<Integer,Integer> desired) {
        this.student_id=student_id;
        this.registrar=registrar;
        this.desired=desired;
        COURSE_NUM=desired.size();
        student_courses=new PriorityQueue<StudentCourse>();
        for(int i=1;i<COURSE_NUM+1;i++) {
            StudentCourse dummy=new StudentCourse(-i, -1);
            student_courses.add(dummy);
        }
       
    }

    public int getID() {
        return student_id;
    }

  

    int numCourses() {
        return student_courses.size();
    }

    void addCourse() {

        removeCourse();

        //choose course to add
        int desiredCourse=desired.get(tries%COURSE_NUM);
       
        //add whichever course the registrar allows
        int courseToAdd=registrar.addCourse(student_id,desiredCourse);

        if(desired.containsValue(courseToAdd)) {
            success++;
            student_courses.add(new StudentCourse(courseToAdd, 1));
        }
        else {
            student_courses.add(new StudentCourse(courseToAdd, -1));
        }
        if(numCourses()!=COURSE_NUM) {
            System.out.println("oh no add courses problem");
        }
       
    }

    void removeCourse() {
        if(numCourses()!=COURSE_NUM) {
            System.out.println("oh no remove courses problem");
        }
        int courseToRemove=student_courses.peek().course_id();
        if(courseToRemove<0 | courseToRemove > registrar.getNumCourses()-1) {
            student_courses.poll();
            return;
        }
        boolean removedFromCourse=false;
        System.out.println("Course to remove");
        System.out.println(courseToRemove);
        System.out.println("Student");
        System.out.println(student_id);
        do {
            removedFromCourse=registrar.removeCourse(courseToRemove,student_id);

        }while(!removedFromCourse);
        
        student_courses.poll();
         
        assert numCourses()==COURSE_NUM-1;
 
    }

 

    void printSchedule() {
        for(StudentCourse sc:student_courses) {
            System.out.println("CourseID: "+sc.course_id());

        }
      
    }
    public void run() {
        while(success<COURSE_NUM && tries<MAX_TRIES) {
            addCourse();
            Simulation.tryToSleep(MIN_STARTING_INTERVAL, STARTING_INTERVAL_VAR);
            tries++;
        }
        if(success==COURSE_NUM) {
            System.out.println(":)");
            printSchedule();
        }
        else {
            System.out.println(":(");
            printSchedule();
        }
    }

    
}
