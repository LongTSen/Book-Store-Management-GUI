package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DetailGUI {
    public JPanel detailGUI;
    public JTextField tittle;
    public JTextField author;
    public JTextField price;
    public JTextField quantity;
    public JTextArea description;
    public JTextField ISBN;
    public JButton addButton;
    public JComboBox genders;
    public JButton modifyButton;
    public JButton removeButton;
    public JFrame frame2;
    private Listener2 listener2;

    public DetailGUI() {
        frame2 = new JFrame("Book");
        frame2.add(detailGUI);
        frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame2.setSize(800, 600);
        frame2.pack();
        frame2.setVisible(false);
        genders.addItem("Fiction");
        genders.addItem("History");
        genders.addItem("Geography");
        genders.addItem("Science");
        genders.addItem("Other");
        detailGUI.setVisible(false);
        listener2 = new Listener2();
        addButton.addActionListener(listener2);
        modifyButton.addActionListener(listener2);
        removeButton.addActionListener(listener2);

    }

    public void setTrue() {
        author.setEditable(true);
        tittle.setEditable(true);
        price.setEditable(true);
        quantity.setEditable(true);
        description.setEditable(true);
        genders.setEnabled(true);
        ISBN.setEditable(true);
    }

    public void setFalse() {
        author.setEditable(false);
        tittle.setEditable(false);
        price.setEditable(false);
        quantity.setEditable(false);
        description.setEditable(false);
        genders.setEnabled(false);
        ISBN.setEditable(false);
    }

    public void clearContent() {
        frame2.setTitle("Add a new book");
        author.setText("");
        tittle.setText("");
        price.setText("");
        quantity.setText("");
        description.setText("");
        ISBN.setText("");
        detailGUI.setVisible(true);
        frame2.setVisible(true);
    }

    public class Listener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            booksDataBase newBook = new booksDataBase();

            if (e.getActionCommand().equalsIgnoreCase("Add")) {

                addNewBook add = new addNewBook();
                String getAuthor = add.getInfoOfBook(author.getText());
                String getTitle = add.getInfoOfBook(tittle.getText());
                String getDescription = add.getInfoOfBook(description.getText());
                String getPrice = String.valueOf(add.getPrice(price.getText()));
                String getQuantity = String.valueOf(add.getQuantity(quantity.getText()));
                String getISBN = String.valueOf(add.getISBN(ISBN.getText()));
                String getGenders = String.valueOf(genders.getSelectedItem());
                if (getAuthor.equalsIgnoreCase("") || getTitle.equalsIgnoreCase("") ||
                        getISBN.equalsIgnoreCase("0") || getPrice.equalsIgnoreCase("0.0")
                        || getQuantity.equalsIgnoreCase("0")) {
                    JOptionPane.showMessageDialog(null, "Unsuccessfully add book");
                    frame2.setVisible(true);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Do you want to add");
                    if (confirm == JOptionPane.YES_OPTION) {
                        newBook.addNewItem(getGenders, getTitle, getAuthor, getISBN, getPrice, getQuantity, getDescription);
                        JOptionPane.showMessageDialog(null, "Adds new book successfully");
                        newBook.readXMLFile();
                        newBook.writeXMLFile();
                        frame2.setVisible(false);

                        if (confirm == JOptionPane.CANCEL_OPTION) {
                            return;
                        }
                    }
                }

            }
            if (e.getActionCommand().equalsIgnoreCase("Modify")) {
                setTrue();
                tittle.setEditable(false);
                author.setEditable(false);
                ISBN.setEditable(false);
                frame2.setTitle("Confirm Modify");
                modifyButton.setText("Confirm Modify");
                quantity.setBackground(Color.blue);
                price.setBackground(Color.blue);
                description.setBackground(Color.blue);
            }

            if (e.getActionCommand().equalsIgnoreCase("Confirm Modify")) {
                addNewBook add = new addNewBook();
                newBook.readXMLFile();
                modifyButton.setText("Modify");

                String getGenders = (String) genders.getSelectedItem();
                String getAuthor = author.getText();
                String getTitle = tittle.getText();
                String getISBN = ISBN.getText();
                String getPrice = String.valueOf(add.getPrice(price.getText()));
                System.out.println(getPrice);
                String getQuantity = String.valueOf(add.getQuantity(quantity.getText()));
                String getDescription = description.getText();
                if (getPrice.equalsIgnoreCase("0.0") || getQuantity.equalsIgnoreCase("0")) {
                    JOptionPane.showMessageDialog(null, "Unsuccessfully Modify book");
                } else if (!(getPrice.equalsIgnoreCase("0")) && !(getQuantity.equalsIgnoreCase("0"))) {
                    for (int i = 0; i < newBook.booksData.size(); i++) {
                        if (tittle.getText().equalsIgnoreCase(newBook.booksData.get(i).getTitle()) &&
                                ISBN.getText().equalsIgnoreCase(newBook.booksData.get(i).getISBN())) {
                            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to modify this book?");
                            if (confirm == JOptionPane.YES_OPTION) {
                                newBook.Modify(i, getGenders, getTitle, getAuthor, getISBN, getPrice, getQuantity, getDescription);
                                JOptionPane.showMessageDialog(null, "Modified book successfully");

                                frame2.setVisible(false);
                            }
                            if (confirm == JOptionPane.CANCEL_OPTION) {
                                return;
                            }
                        }
                    }
                }
                quantity.setBackground(Color.white);
                price.setBackground(Color.white);
                description.setBackground(Color.white);
                newBook.writeXMLFile();
                newBook.readXMLFile();
                System.out.println("done");
            }
            if (e.getActionCommand().equalsIgnoreCase("Remove")) {
                newBook.readXMLFile();
                for (int i = 0; i < newBook.booksData.size(); i++) {
                    System.out.println("test");
                    if (tittle.getText().equalsIgnoreCase(newBook.booksData.get(i).getTitle()) &&
                            ISBN.getText().equalsIgnoreCase(newBook.booksData.get(i).getISBN())) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to remove this book?");
                        if (confirm == JOptionPane.YES_OPTION) {
                            newBook.booksData.remove(i);
                            JOptionPane.showMessageDialog(null, "Removed book successfully");
                            frame2.setVisible(false);
                        }
                        if (confirm == JOptionPane.CANCEL_OPTION) {
                            return;
                        }
                    }
                }
                removeButton.setEnabled(false);
                newBook.writeXMLFile();
                newBook.readXMLFile();

            }
        }
    }
}