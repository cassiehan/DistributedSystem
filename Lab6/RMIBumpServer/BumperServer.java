import java.rmi.*;
public class BumperServer {
	public static void main(String args[]){
          System.out.println("Calculator Server Running");
          try{
            // create the servant
            Bumper c = new BumperServant();
            System.out.println("Created Calculator object");
            System.out.println("Placing in registry");
            // publish to registry
	    Naming.rebind("MyBumper", c); 
            System.out.println("CalculatorServant object ready");
           }catch(Exception e) {
            System.out.println("CalculatorServer error main " + e.getMessage());
        }
    }
}
