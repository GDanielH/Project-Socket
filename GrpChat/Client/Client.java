package Client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String username;
//    Bulle tapename=new Bulle();

//    public Bulle getTapename() {
//        return tapename;
//    }
//
//    public void setTapename(Bulle chowconnect) {
//        this.tapename = chowconnect;
//    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client(Socket socket,String username){
        try{
            this.setSocket(socket);
            this.setBufferedWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            this.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            this.setUsername(username);
        }catch (IOException e){
            closeEverything(getSocket(),getBufferedReader(),getBufferedWriter());
        }
    }
    public void sendMessage(){
        try{
            getBufferedWriter().write(getUsername());
            getBufferedWriter().newLine();
            getBufferedWriter().flush();

            Scanner scanner=new Scanner(System.in);
            while(getSocket().isConnected()){
                String messagetosend=scanner.nextLine();
                getBufferedWriter().write(getUsername()+": "+messagetosend);
                getBufferedWriter().newLine();
                getBufferedWriter().flush();
            }
        }catch (IOException e){
            closeEverything(getSocket(),getBufferedReader(),getBufferedWriter());
        }
    }
    public void ListenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while(getSocket().isConnected()){
                    try{
                        msgFromGroupChat=getBufferedReader().readLine();
                        System.out.println(msgFromGroupChat);
                    }catch (IOException e){
                        closeEverything(getSocket(),getBufferedReader(),getBufferedWriter());
                    }
                }
            }
        }).start();
    }
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
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

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("ampidiro ny anaranao hoan ny rehetra: ");
        String username=scanner.nextLine();
//        Bulle tapename=new Bulle();
//        Listener lt=new Listener(tapename);
//        tapename.getJb().addMouseListener(lt);
//        tapename.nameFrame();
        Socket socket=new Socket("localhost",1234);
        Client client=new Client(socket,username);
        client.ListenForMessage();
        client.sendMessage();

    }
}