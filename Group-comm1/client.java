import java.util.*;
import java.net.*;
import java.io.*;

public class client {
    public static void main(String args[]) throws Exception{
        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //Receiving messages
        new Thread(()->{
            try{
                String msg;
                while((msg = in.readLine())!=null){
                    System.out.println(msg);
                }
            }catch(Exception e) {
                System.out.println(e);
            }
        }).start();

        Scanner sc = new Scanner(System.in);

        //Sending messages
        while (true) {
            String msg = sc.nextLine();
            out.println(msg);

            if (msg.equalsIgnoreCase("exit")) break;
        }

        socket.close();
        sc.close();
    }
}
