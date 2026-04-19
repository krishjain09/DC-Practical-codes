import java.net.*;
import java.io.*;
import java.util.Scanner;
class server{
    public static void main(String args[]) throws Exception{

        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server waiting....");
        Socket socket = server.accept();
        System.out.println("Connection  established. "+ socket.getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


        Scanner t = new Scanner(System.in);

        while(true){
            
            String clinetMsg = in.readLine();
            System.out.print("Client: " + clinetMsg);

            if(clinetMsg.equalsIgnoreCase("exit")) break;

            System.out.print("\nServer: ");
            String msg = t.nextLine();
            out.println(msg);

            if(msg.equalsIgnoreCase("exit")) break;
        }

        server.close();
        socket.close();

    }
}