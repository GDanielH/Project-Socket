package Interface;
import javax.swing.*;
import java.awt.*;

public class Bulle extends JFrame {
//    ClientHandler clientHandler;
    JTextField jf=new JTextField();
    JButton jb=new JButton("confirm");

    public JButton getJb() {
        return jb;
    }

    public void setJb(JButton jb) {
        this.jb = jb;
    }

    public JTextField getJf() {
        return jf;
    }

    public void setJf(JTextField jf) {
        this.jf = jf;
    }

    public Bulle(){

    }
    public void nameFrame(){
        this.setSize(300,100);
        this.setLayout(new GridLayout(1,1));
        this.setVisible(true);
        getJf().setBounds(20,20,30,25);

        getJb().setBounds(60,60,10,10);
//        this.setLocationRelativeTo(null);
        this.add(getJf());
        this.add(getJb());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
