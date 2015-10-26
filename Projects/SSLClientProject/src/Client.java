
/**
 *
 * @author hanxi
 */
import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import java.util.Scanner;
import javax.net.*;

public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust: name of system property, value of system property
            System.setProperty("javax.net.ssl.trustStore","../myCool.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6502);
            
            Scanner scan = new Scanner(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String fromServer = in.readLine();
            
            System.out.println(fromServer);
            String userName = scan.nextLine();
            out.write(userName);
            out.newLine();
            out.flush();
            
            fromServer = in.readLine();
            System.out.println(fromServer);
            String password = scan.nextLine();
            out.write(password);
            out.newLine();
            out.flush();
            
            fromServer = in.readLine();
            System.out.println(fromServer);
            in.close();
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 