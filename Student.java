import java.util.HashMap;
import java.util.PriorityQueue;


public class Student extends Thread {
    private int student_id;
    HashMap<Integer, Integer> desired;
    HashMap<Integer, Integer> okay;
    private final Registrar registrar;
    private final int COURSE_NUM=4;
    private final int ALSO_OKAY=8;
    private final int MAX_TRIES=25;
    private final long TIME_TO_WAIT=200;
    PriorityQueue<StudentCourse> student_courses;
    int tries=0;
    int success=0;

    Student(int student_id,Registrar registrar, HashMap<Integer,Integer> desired,HashMap<Integer,Integer> okay) {
        this.student_id=student_id;
        this.registrar=registrar;
        this.desired=desired;
        student_courses=new PriorityQueue<StudentCourse>();
        for(int i=0;i<4;i++) {
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
        int desiredCourse;
        if(student_courses.peek().desire_rank()==-1) {
            desiredCourse=okay.get((int)(Math.random()*ALSO_OKAY));
        }
        else {
            desiredCourse=desired.get((int)(Math.random()*COURSE_NUM));
        }
        int courseToAdd=registrar.addCourse(student_id,desiredCourse);

        if(desired.containsValue(courseToAdd)) {
            success++;
            student_courses.add(new StudentCourse(courseToAdd, 1));
        }
        else if(okay.containsValue(courseToAdd)) {
            student_courses.add(new StudentCourse(courseToAdd, 0));
        }
        else {
            student_courses.add(new StudentCourse(courseToAdd, -1));
        }
        assert numCourses()==COURSE_NUM;
       
    }

    void removeCourse() {
        assert numCourses()==COURSE_NUM;
        int courseToRemove=student_courses.peek().course_id();
        boolean removedFromCourse=false;
        do {
            removedFromCourse=registrar.removeCourse(courseToRemove,student_id);

        }while(!removedFromCourse);
        

        //remove course
        student_courses.poll();
         
        assert numCourses()==COURSE_NUM-1;
 
    }

    /* 
    boolean hasCourse(int course_id) {
        for(StudentCourse sc:student_courses) {
            if(sc.course_id()==course_id) {
                return true;
            }

        }
        return false;
    }
    */



    public void run() {
        while(success<COURSE_NUM && tries<MAX_TRIES) {
            addCourse();
            try {
                java.lang.Thread.sleep(TIME_TO_WAIT);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        if(success==COURSE_NUM) {
            System.out.println(":)");
        }
        else {
            System.out.println(":(");
        }
    }

    
}
