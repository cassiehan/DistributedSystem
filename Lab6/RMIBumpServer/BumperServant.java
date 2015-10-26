// CalculatorServant.java        
// A Remote object class that implements Calculator. 
import java.rmi.*;
import java.math.BigInteger;
import java.rmi.server.UnicastRemoteObject;
public class BumperServant extends UnicastRemoteObject implements Bumper {
       BigInteger number = new BigInteger("0");
       public BumperServant() throws RemoteException {
       }
       public boolean bump() throws RemoteException {
            number = number.add(BigInteger.ONE);
            return true;
       }

       public BigInteger get() {
            return number;
       }
}

