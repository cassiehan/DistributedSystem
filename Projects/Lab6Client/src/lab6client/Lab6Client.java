/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6client;

import java.math.BigInteger;

/**
 *
 * @author hanxi
 */
public class Lab6Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigInteger ctr = new BigInteger("0");
        BigInteger n = new BigInteger("10000");
        long start = System.currentTimeMillis();
        while(ctr.compareTo(n)<=0){
            ctr=ctr.add(BigInteger.ONE);
            bump();
        }
        long stop = System.currentTimeMillis();
        int seconds = (int) (stop-start)/1000;
        System.out.println("Final number: "+get());
        System.out.println("Running time: " + seconds);
        
    }

    private static boolean bump() {
        edu.cmu.andrew.xih.Lab6Service_Service service = new edu.cmu.andrew.xih.Lab6Service_Service();
        edu.cmu.andrew.xih.Lab6Service port = service.getLab6ServicePort();
        return port.bump();
    }

    private static BigInteger get() {
        edu.cmu.andrew.xih.Lab6Service_Service service = new edu.cmu.andrew.xih.Lab6Service_Service();
        edu.cmu.andrew.xih.Lab6Service port = service.getLab6ServicePort();
        return port.get();
    }
    
}
