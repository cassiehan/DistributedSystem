/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanxi
 */
public class BigCalc extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xString = request.getParameter("x");
        String yString = request.getParameter("y");
        String operator = request.getParameter("operator");
        String output = "";
        String nextView = "";
        
        if(xString!=null && yString != null && operator != null){
            BigInteger x = new BigInteger(xString);
            BigInteger y = new BigInteger(yString);
            if(operator.equals("add")){
                output = x.add(y).toString();
            }
            else if(operator.equals("multiply")){
                output = x.multiply(y).toString();
            }
            else if(operator.equals("prime")){
                if(x.gcd(y).equals(BigInteger.ONE)){
                    output = "are relatively prime.";
                }
                else{
                    output = "are not relatively prime.";
                }
            }
            else if(operator.equals("mod")){
                output = x.mod(y).toString();
            }
            else if(operator.equals("modInverse")){
                if(x.gcd(y).equals(BigInteger.ONE)){
                    output = x.modInverse(y).toString();
                }
                else{
                    output = x +" are not invertable.";
                }
            }
            else{
                output = x.pow(y.intValue()).toString();
            }
            
            request.setAttribute("output", output);
            request.setAttribute("x", x);
            request.setAttribute("y", y);
            request.setAttribute("operator", operator);
            nextView = "result.jsp";
        }
        else{
            nextView = "index.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
