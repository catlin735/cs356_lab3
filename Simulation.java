public class Simulation {

    public static void runSequentialExample() {

    }

    public static void runConcurrentExample() {
        
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

