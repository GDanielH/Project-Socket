package Server;
import Client.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();

                ClientHandler clientHandler=new ClientHandler(socket);
                System.out.println("tafiditra "+clientHandler.getClientUsername());
                Thread thread=new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){

        }
    }
    public void closeServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket=new ServerSocket(1234);
        Server server=new Server(serverSocket);
        server.startServer();
        server.closeServerSocket();
    }


}
