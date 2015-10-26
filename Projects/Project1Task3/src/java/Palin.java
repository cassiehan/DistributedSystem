/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.Stack;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanxi
 */
public class Palin extends HttpServlet {

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
        String input = request.getParameter("input");
        
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        
        String nextView="";
        
        if(input != null){
            String output="";
            if(isPalin(input)){
                output = input + " is a palindromes!";
            }
            else{
                output = input + " is not a palindromes!";
            }
            request.setAttribute("output", output);
            nextView = "result.jsp";
        }
        else{
            nextView = "index.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
        
    }
    
   public static boolean isPalin(String input){
        boolean isPalin = true;
        if(input==null){
            return isPalin;
        }
        else{
            input = input.trim();
            input = input.toLowerCase();
            String omit = "";
            for(int i=0; i<input.length(); i++){
                if(input.charAt(i)>='a'&& input.charAt(i)<='z'){
                    omit += Character.toString(input.charAt(i));
                }
            }
            input = omit;
            
            int size = input.length();
            
            if(size<=1){
                return isPalin;
            }
            else{
                for(int i=0; i<size/2;i++){
                    if(input.charAt(i)!=input.charAt(size-1-i)){
                        isPalin = false;
                        break;
                    }
                }
                
            }
        }
        return isPalin;
    }
}
