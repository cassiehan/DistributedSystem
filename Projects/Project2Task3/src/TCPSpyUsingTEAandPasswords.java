

import java.net.*;
import java.io.*;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hanxi
 */
public class TCPSpyUsingTEAandPasswords {

    public static void main(String args[]) {
        // arguments supply message and hostname
        Socket soket = null;
        Scanner scan = new Scanner(System.in);
        
        try {
            byte[] receiveData = new byte[1000];
            int serverPort = 7896;
            soket = new Socket(args[0], serverPort);
            
            DataInputStream in = new DataInputStream(soket.getInputStream());
            DataOutputStream out = new DataOutputStream(soket.getOutputStream());
            
            System.out.println("Enter symmetric key for TEA (taking first first sixtenn bytes):" + "\n"
                  + "thisissecretshon'ttellanyone" + "\n");
            System.out.print("Enter your ID: ");
            String userid = scan.nextLine();
            System.out.println();
            System.out.print("Enter your Password: ");
            String password = scan.nextLine();
            System.out.println();
            System.out.print("Enter your location: ");
            String location = scan.nextLine();
            System.out.println();
            
            String sendDataString = userid + "\t" + password + "\t" + location;
            TEA tea = new TEA("And is there honey still for coffee?".getBytes());
            byte[] crypt = tea.encrypt(sendDataString.getBytes());
            //System.out.println("Send data: " + sendDataString);
            //System.out.println("Send TEA: " + crypt);
            out.writeInt(crypt.length);
            out.write(crypt);
            
            int authentication = in.read();
            if(authentication == -1){
                System.out.println("Not a valid user-id or password.");
            }else{
                System.out.println("Thank you. Your location was securely transmitted to Intelligence Headquarters.");
            }
//            out.writeUTF(args[0]);      	// UTF is a string encoding see Sn. 4.4
//            String data = in.readUTF();	    // read a line of data from the stream
//            System.out.println("Received: " + data);
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (soket != null) {
                try {
                    soket.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }
}
