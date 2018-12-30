package company;

import javax.swing.*;

public class main {
    public static void main(String[] args){
        JFrame frame = new JFrame("BookStore");
        frame.setContentPane(new BookStoreGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024,768);
        frame.pack();
        frame.setVisible(true);

    }
}
