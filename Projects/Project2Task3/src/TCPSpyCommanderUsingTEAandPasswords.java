
import java.net.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hanxi
 */
public class TCPSpyCommanderUsingTEAandPasswords {

    final static String USERID = "seanb";
    final static String PASSWORD = "sean";
    final static String COORDINATES = "-79.945289,40.44431,0.00000";
    static HashMap<String, String> spies = null;
    static HashMap<String, String> spylocation = null;
    static int counter = 0;
    static String salt = "awsfdjfdkfjisdjksajdi10003920392039";

    public static void main(String args[]) throws NoSuchAlgorithmException {
        HashMap<String, String> spies = new HashMap<>();
        spies.put("jamesb", hash(salt+"james"));
        spies.put("joem", hash(salt+"joe"));
        spies.put("mikem", hash(salt+"joe"));
        createKMLFile();
        System.out.println("Enter symmetric key for TEA (taking first first sixtenn bytes):" + "\n"
                + "thisissecretshon'ttellanyone" + "\n"
                + "Waiting for spies to visit...");

        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                c.spies = spies;
                counter++;
                c.counter = counter;
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
    
    public static String hash(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String output = getHexString(digest);
        return output;
    }
    
    
    private static String getHexString(byte[] b ){
        String result = "";
        for(int i=0; i<b.length; i++){
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result.toUpperCase();
    }

    public static void createKMLFile() {

        String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                + "<Document>\n";
        String kmlstyle = "<Style id=\"style1\">\n"
                + "<IconStyle>\n"
                + "<Icon>\n"
                + "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/buledot.png</href>\n"
                + "</Icon>\n"
                + "</IconStyle>\n"
                + "</Style>\n";
        String kmlelementCommander = "\t<Placemark>\n"
                + "\t<name>seanb</name>\n"
                + "\t<description>Spy Commander</description>\n"
                + "\t<Point>\n"
                + "\t\t<coordinates>-79.945289,40.44431,0.00000</coordinates>\n"
                + "\t</Point>\n"
                + "\t</Placemark>\n";

        String kmlelementSpy = "\t<Placemark>\n"
                + "\t<name>joem</name>\n"
                + "\t<description>Spy</description>\n"
                + "\t<Point>\n"
                + "\t\t<coordinates>-79.945289,40.44431,0.00000</coordinates>\n"
                + "\t</Point>\n"
                + "\t</Placemark>\n\n"
                + "\t<Placemark>\n"
                + "\t<name>mikem</name>\n"
                + "\t<description>Spy</description>\n"
                + "\t<Point>\n"
                + "\t\t<coordinates>-79.945289,40.44431,0.00000</coordinates>\n"
                + "\t</Point>\n"
                + "\t</Placemark>\n\n"
                + "\t<Placemark>\n"
                + "\t<name>jamesb</name>\n"
                + "\t<description>Spy</description>\n"
                + "\t<Point>\n"
                + "\t\t<coordinates>-79.945289,40.44431,0.00000</coordinates>\n"
                + "\t</Point>\n"
                + "\t</Placemark>\n\n";
        String kmlend = "</Document>\n"
                + "</kml>";
        String kmlcontent = kmlstart + kmlstyle + kmlelementCommander + kmlelementSpy + kmlend;
        File file = new File("/Users/hanxi/Desktop/SecretAgents.kml");
        Writer fwriter;

        if (file.exists()) {
            file.delete();
        }
        try {
            fwriter = new FileWriter("/Users/hanxi/Desktop/SecretAgents.kml");
            fwriter.write(kmlcontent);
            fwriter.flush();
            fwriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    HashMap<String, String> spies;
    int counter;
    String userid;
    String coordinates;
    int userAuthentication;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            byte[] receiveTEA = new byte[in.readInt()];
            in.readFully(receiveTEA, 0, receiveTEA.length);
            TEA tea = new TEA("And is there honey still for coffee?".getBytes());
            byte[] result = tea.decrypt(receiveTEA);

            /* Ensure that all went well */
            String receiveData = new String(result);
            String[] tokens = receiveData.split("\t");
            if (tokens.length == 3) {
                userid = tokens[0];
                String password = tokens[1];
                coordinates = tokens[2];
                String sendData = "";

                userAuthentication = authentication(spies, userid, password);

                if (userAuthentication == -1) {
                    System.out.println("Got visit " + counter + " illegal symmetric key used.");
                    //sendData = null;
                } else if (userAuthentication == 0) {
                    System.out.println("Got visit " + counter + " from " + userid + ". Illegal Password attempt.");
                    //sendData = "WRONG";
                } else {
                    System.out.println("Got visit " + counter + " from " + userid);
                    sendData = "RIGHT";
                    adjustKMLFile(userid, coordinates);
                }

                if (sendData.length() > 0) {
                    byte[] crypt = tea.encrypt(sendData.getBytes());
                    out.write(crypt);
                } else {
                    out.write(sendData.getBytes());
                }
            } else {
                System.out.println("Please enter full userid, password and coordinates");
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*close failed*/

            }
        }
    }

    static void adjustKMLFile(String userid, String coordinates) {
        try {
            String inputFileName = "/Users/hanxi/Desktop/SecretAgents.kml";
            File inputFile = new File(inputFileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFileName));
            BufferedReader br = new BufferedReader(reader);
            String s = "";
            s = br.readLine();
            String name = "";
            String fileContent = "";
            while (s != null) {

                if (s.startsWith("\t<name>" + userid)) {
                    name = userid;
                }

                if (s.startsWith("\t\t<coordinates>") && name.equals(userid)) {
                    s = "       <coordinates>" + coordinates + "</coordinates>";
                    name = "";
                }
                String thisline = s + "\n";
                fileContent += thisline;
                s = br.readLine();
            }
            inputFile.delete();

            BufferedWriter out = new BufferedWriter(new FileWriter("/Users/hanxi/Desktop/SecretAgents.kml"));
            out.write(fileContent);
            out.flush();
            out.newLine();
            br.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int authentication(HashMap<String, String> spies, String userid, String password) throws NoSuchAlgorithmException {
        boolean isAscii = userid.matches("\\A\\p{ASCII}*\\z");
        if (!isAscii) {
            return -1;
        }

        if (spies.containsKey(userid)) {
            if (spies.get(userid).equals(
                    TCPSpyCommanderUsingTEAandPasswords.hash(TCPSpyCommanderUsingTEAandPasswords.salt+password))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
