package Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String clientUsername;

    public static ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public static void setClientHandlers(ArrayList<ClientHandler> clientHandlers) {
        ClientHandler.clientHandlers = clientHandlers;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public ClientHandler(Socket socket){
        try{
            this.setSocket(socket);
            this.setBufferedWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            this.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            this.setClientUsername(getBufferedReader().readLine());
            getClientHandlers().add(this);
            broadcastMessage("SERVER: "+ getClientUsername() +" dia tafiditra");
        }catch (IOException e){
            closeEverything(getSocket(), getBufferedReader(),getBufferedWriter());
        }
    }
    @Override
    public void run() {
        String messageFromClient;

        while(getSocket().isConnected()){
            try{
                messageFromClient=getBufferedReader().readLine();
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(getSocket(), getBufferedReader(),getBufferedWriter());
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler:getClientHandlers()){
            try{
                if(!clientHandler.getClientUsername().equals(getClientUsername())){
                    clientHandler.getBufferedWriter().write(messageToSend);
                    clientHandler.getBufferedWriter().newLine();
                    clientHandler.getBufferedWriter().flush();
                }
            }catch (IOException e){
                closeEverything(getSocket(), getBufferedReader(),getBufferedWriter());
            }
        }
    }
    public void removeClientHandler(){
        getClientHandlers().remove(this);
        broadcastMessage("SERVER: "+getClientUsername()+" dia lasa");
    }

    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
