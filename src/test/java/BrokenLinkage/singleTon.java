package BrokenLinkage;

public class singleTon {

 
    private static singleTon instance = null;

    // Private constructor to prevent instantiation
    private singleTon() {
    }

    public static singleTon getInstance() {
        if (instance == null) {
            instance = new singleTon();
        }
        return instance;
    }

    public static void main(String[] args) {
        // Get the single instance of the class
        singleTon test1 = singleTon.getInstance();
System.out.println("Instance 1 HashCode: " + System.identityHashCode(test1));

        singleTon test2 = singleTon.getInstance();
        System.out.println("Instance 2 HashCode: " + System.identityHashCode(test2));

        // Verify both instances are the same
        if (test1 == test2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Instances are different.");
        }
    }


	}


