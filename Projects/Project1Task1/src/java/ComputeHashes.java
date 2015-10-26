/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanxi
 */
public class ComputeHashes extends HttpServlet {
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
        //processRequest(request, response);
        String inputText = request.getParameter("inputText");
        
        String encodingType = request.getParameter("encodingType");
        String nextView = "";
        if(inputText != null && encodingType!=null){
            String hex = "";
            String base64 = "";
            try {
                if(encodingType.equals("SHA-1")){
                    hex = sha1Hex(inputText);
                    base64 = sha1Base64(inputText);
                }
                else{
                    hex = MD5Hex(inputText);
                    base64 = MD5Base64(inputText);
                }
                
                request.setAttribute("inputText", inputText);
                request.setAttribute("encodingType", encodingType);
                request.setAttribute("hex", hex);
                request.setAttribute("base64", base64);
                
                nextView = "result.jsp";
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            nextView = "index.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

    public String sha1Base64(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String output = new sun.misc.BASE64Encoder().encode(digest);
        return output;
    }
    
    public String sha1Hex(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String output = getHexString(digest);
        return output;
    }
    
    public String MD5Base64(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String output = new sun.misc.BASE64Encoder().encode(digest);
        return output;
    }
    
    public String MD5Hex(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String output = getHexString(digest);
        return output;
    }
    
    public String getHexString(byte[] b ){
        String result = "";
        for(int i=0; i<b.length; i++){
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result.toUpperCase();
    }
}
