import java.util.*;
import java.net.*;
import java.io.*;


class ClientHandler extends Thread{
    Socket socket;
    static List<PrintWriter> clients = new ArrayList<>();

    ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            out.println("Enter your name: ");
            String name  = in.readLine();

            synchronized(clients){
                clients.add(out);
            }

            broadcast(name + " has joined the chat.", out);

            String msg;
            while((msg = in.readLine())!= null){
                if(msg.equalsIgnoreCase("exit")) break;
                broadcast(name + " : " + msg , out );
            }

            synchronized(clients){
                clients.remove(out);
            }

            broadcast(name + " has left the chat.", out);
            socket.close();
        }
        catch(Exception e ){
            System.out.println("Exception: " + e);
        }
    }

    static void broadcast(String msg, PrintWriter sender){

        for(PrintWriter client : clients){
            if(client != sender){
                client.println(msg);
            }
        }

    }

}

class server {
    
    public static void main(String args[]) throws Exception {

        ServerSocket server = new ServerSocket(5000);
        System.out.println("Group communication started.");


        while(true){
            Socket socket = server.accept();
            System.out.println("New Client Connected");

            new ClientHandler(socket).start();
        }
        
    }

}
/*
    Side	    Why thread is used
    Server	    handle multiple clients
    Client	    send & receive simultaneously
*/
