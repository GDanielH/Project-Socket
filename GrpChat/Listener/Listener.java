package Listener;
import  Interface.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;

public class Listener implements MouseListener {
    Bulle b ;
//    Socket socket;
//    Client client;
    public Listener(Bulle b){

        this.setB(b);

    }

    public Bulle getB() {
        return b;
    }

    public void setB(Bulle b) {
        this.b = b;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        String text=getB().getJf().getText();
//        getClient().setUsername(text);
//        System.out.println("hello");
//        System.out.println(getUsername());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
