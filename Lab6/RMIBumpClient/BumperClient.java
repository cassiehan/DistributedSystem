// CalculatorClient.java                 
// This client gets a remote reference from the rmiregistry
// that is listening on the default port of 1099.
// It allows the client to quit with a "!". 
// Otherwise, it computes the sum of two integers 
// using the remote calculator.

import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class BumperClient {
   public static void main(String args[]) throws Exception {
        // connect to the rmiregistry and get a remote reference to the Calculator 
        // object.
        Bumper c  = (Bumper) Naming.lookup("//localhost/MyBumper");
 	      System.out.println("Found Bumper.");
        BigInteger ctr = new BigInteger("0");
                 BigInteger n = new BigInteger("10000");
                 long start = System.currentTimeMillis();
                 while(ctr.compareTo(n)<=0){
                     ctr=ctr.add(BigInteger.ONE);
                     c.bump();
                 }
                 long stop = System.currentTimeMillis();
                 int seconds = (int) (stop-start)/1000;
                 System.out.println("Final number: "+c.get());
                 System.out.println("Running time: " + seconds);

   	// while(true) {
    //        try { 
                 
    //            }catch(RemoteException e) {
    //                System.out.println("allComments: " + e.getMessage());
    //            }
	   // }
    }
}

