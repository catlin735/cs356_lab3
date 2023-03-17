import java.util.HashMap;
import java.util.Random;
public class Simulation {
    private static final double MIN_STARTING_INTERVAL = 0.1; // in seconds; should be at least 0.1
    private static final double STARTING_INTERVAL_VAR= 2.0; 
    private static final int NUM_COURSES=5;
    private static final int DESIRED=2;


    public static void runConcurrentExample() {
      
        

        System.out.println("== Starting concurrent trucks example. ==");
		Registrar R = new Registrar(NUM_COURSES);
        for(int i=0;i<NUM_COURSES;i++) {
            R.makeNewCourse(1,i);
        }

		HashMap<Integer, Integer> desired=new HashMap<Integer, Integer>();
     
        for(int i=0;i<DESIRED;i++) {
            desired.put(i,i);
        }
       
        Student T= new Student(0,R,desired);
		Student U = new Student(1,R,desired);
   
		
        T.start(); tryToSleep(MIN_STARTING_INTERVAL, STARTING_INTERVAL_VAR);
		U.start(); tryToSleep(MIN_STARTING_INTERVAL, STARTING_INTERVAL_VAR);
	
		
		try {
			T.join();  // wait for T to be done
			U.join();
		} catch  (Exception e) {
            System.out.println("Not Handling exceptions yet ... goodbye");
        }
        
        System.out.println("== Concurrent trucks example is done now. ==");
	}
        
    

    private static final java.util.Random dice = new java.util.Random(); // random number generator, for delays mostly
	public static void tryToSleep(double minimumSeconds, double upToMoreSeconds) {
        try {
            java.lang.Thread.sleep(Math.round(minimumSeconds*1000) + Math.round(dice.nextDouble()*(upToMoreSeconds)*1000));
        } catch (InterruptedException e) {
            System.out.println("Not Handling interruptions yet ... just going on with the program without as much sleep as needed ... how appropriate!");
        }
	}

    public static void main(String[] args) {
        runConcurrentExample();
    }
    
}

