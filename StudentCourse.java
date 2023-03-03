public class StudentCourse implements Comparable<StudentCourse> {

    private int course_id;
    private int desire_rank;
    
    StudentCourse(int course_id,int desire_rank) {
        this.course_id=course_id;
        this.desire_rank=desire_rank;

    }

    public int course_id() {
        return course_id;
    }

    public int desire_rank() {
        return desire_rank;
    }


    @Override
    public int compareTo(StudentCourse o) {
        if(desire_rank>o.desire_rank) {
            return 1;
        }
        if(desire_rank<o.desire_rank) {
            return -1;
        }
        return 0;
       
    }
    
}
