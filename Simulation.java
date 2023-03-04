import java.util.HashMap;
import java.util.Random;
public class Simulation {
    private static final double minStartingInterval = 0.1; // in seconds; should be at least 0.1
    private static final double startingIntervalVar = 2.0; 
    private static final int NUM_COURSES=5;
    private static final int DESIRED=2;
    private static final int OKAY=2;

    public static void runSequentialExample() {
        System.out.println("== Starting sequential example ==");

        System.out.println("== Sequential example is done now.");

    }

    public static void runConcurrentExample() {
      
        

        System.out.println("== Starting concurrent trucks example. ==");
		Registrar R = new Registrar(NUM_COURSES);
        for(int i=0;i<NUM_COURSES;i++) {
            R.makeNewCourse(2,i);
        }

		HashMap<Integer, Integer> desired=new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> okay=new HashMap<Integer, Integer>();
        for(int i=0;i<DESIRED;i++) {
            desired.put(i,i);
        }
        for(int i=DESIRED;i<NUM_COURSES;i++) {
            okay.put(i-DESIRED,i);
        }
       
        Student T= new Student(0,R,desired,okay);
		Student U = new Student(1,R,desired,okay);
		T.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		U.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		/*


		Truck V = new Truck("V",  80, 2, B);
		Truck W = new Truck("W", 200, 3, B);
		Truck X = new Truck("X", 100, 1, B);
		Truck Y = new Truck("Y",  80, 1, B);
		Truck Z = new Truck("Z", 100, 1, B);


		V.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		W.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		X.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		Y.start(); tryToSleep(minStartingInterval, startingIntervalVar);
		Z.start(); tryToSleep(minStartingInterval, startingIntervalVar);

		 */
		
		try {
			T.join();  // wait for T to be done
			U.join();
			/*
			V.join();
			W.join();
			X.join();
			Y.join();
			Z.join();
			 */
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
        System.out.println("hello world");
    }
    
}

