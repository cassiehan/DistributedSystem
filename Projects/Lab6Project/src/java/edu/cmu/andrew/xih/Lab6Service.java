/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.andrew.xih;

import java.math.BigInteger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author hanxi
 */
@WebService(serviceName = "Lab6Service")
public class Lab6Service {
    BigInteger number = new BigInteger("0");
    /**
     * Web service operation
     */
    
    @WebMethod(operationName = "get")
    public BigInteger get() {
        return number;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "bump")
    public boolean bump() {
        number = number.add(BigInteger.ONE);
        return true;
    }
}
