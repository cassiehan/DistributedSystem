/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanxi
 */
import java.net.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EchoServerTCP {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using
            
            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            
            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making 
             * the socket ready for reading and writing.
             */
            clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.
            
            // Set up "in" to read from the client socket
            Scanner in;
            in = new Scanner(clientSocket.getInputStream());
            
            // Set up "out" to write to the client socket
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            
            /* 
             * Forever,
             *   read a line from the socket
             *   print it to the console
             *   echo it (i.e. write it) back to the client
             */
            while (true) {
                while(!in.hasNextLine()){
                    
                }
                String data = in.nextLine();
                System.out.println(data);
                if(parseOut(data)!=null){
                    String fileName = "/Users/hanxi/Desktop/DS/Projects/EchoServerTCP/src/java/"+parseOut(data);
                    File file = new File(fileName);
                    System.out.println("filename " + fileName); 
                    if(file.exists()){
                        String path = file.getAbsolutePath();
                        System.out.println("filename " + fileName); 
                        System.out.println("path "+path.toString());
                        System.out.println("file exist");
                        BufferedReader br = new BufferedReader(new FileReader(fileName));
                        String line = "";
                        while((line = br.readLine())!=null){
                            System.out.println(line);
                            out.println(line);
                            out.flush();
                        }
                        br.close();
                    }else{
                        out.println("The file does not exist!");
                        out.flush();
                    }
                    
                }
                out.close();
            }
            //out.close();
        // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
            
        // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
    //GET /test.html HTTP/1.1
    public static String parseOut(String input){
        if(input==null||input.length()==0){
            return null;
        }
        String output=null;
        if(input.substring(0, 3).equals("GET")){
            int startPoint = input.indexOf("/")+1;
            int endPoint = input.indexOf(" HTTP/");
            output = input.substring(startPoint, endPoint);
        }
        return output;
    }
}