
import java.net.*;
import java.io.*;
import java.util.*;

public class client {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        Scanner t = new Scanner(System.in);
        while(true){
            System.out.print("Client: ");
            String msg = t.nextLine();
            out.println(msg);

            if(msg.equalsIgnoreCase("exit")) break;

            String serverMsg = in.readLine();
            System.out.println("Server: " + serverMsg);

            if(serverMsg.equalsIgnoreCase("exit")) break;

        }
        socket.close();
    }
}
