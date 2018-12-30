package company;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class BookStoreGUI {
    public JPanel main;
    public JTextField searchArea;
    private JButton searchButton;
    private JCheckBox All;
    private JCheckBox Fiction;
    private JCheckBox History;
    private JCheckBox Geography;
    private JCheckBox Science;
    public JList<bookDataInString> content;
    private JCheckBox Other;
    private JButton AddButton;
    private JButton general;
    private JTextArea jTextArea;
    DefaultListModel model;
    private Listener listener;
    public boolean addBook =false;
    booksDataBase data = new booksDataBase();
    public String[] checkBox = new String[10];
    DetailGUI detailGUI1 = new DetailGUI();

    public BookStoreGUI()
    {

        addToJlist();
        createContent();
    }

    public void createContent()
    {

        listener = new Listener();
        All.addActionListener(listener);
        searchButton.addActionListener(listener);
        Fiction.addActionListener(listener);
        History.addActionListener(listener);
        Geography.addActionListener(listener);
        Science.addActionListener(listener);
        Other.addActionListener(listener);
        AddButton.addActionListener(listener);
        general.addActionListener(listener);



        content.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String selected1 = "";
                String selected2 = "";
                    if(e.getClickCount()==2 ){
                        selected1 = content.getSelectedValue().getTitle();
                        selected2 = content.getSelectedValue().getISBN();
                        for(int i=0;i<data.booksData.size();i++)
                        {
                            if(selected1.equalsIgnoreCase(data.booksData.get(i).getTitle())&&
                            selected2.equalsIgnoreCase(data.booksData.get(i).getISBN()))
                            {
                                detailGUI1.tittle.setText(data.booksData.get(i).getTitle());
                                detailGUI1.author.setText(data.booksData.get(i).getAuthor());
                                detailGUI1.price.setText(data.booksData.get(i).getPrice());
                                detailGUI1.quantity.setText(data.booksData.get(i).getQuantity());
                                detailGUI1.ISBN.setText(data.booksData.get(i).getISBN());
                                detailGUI1.description.setText(data.booksData.get(i).getDescription());
                                detailGUI1.genders.setSelectedItem(data.booksData.get(i).getCategory());
                                detailGUI1.addButton.setEnabled(false);
                                detailGUI1.detailGUI.setVisible(true);
                                detailGUI1.frame2.setVisible(true);
                                detailGUI1.frame2.setTitle("Book");
                                detailGUI1.setFalse();
                                detailGUI1.modifyButton.setEnabled(true);
                                detailGUI1.removeButton.setEnabled(true);
                            }
                        }
                }
            }
        });

    }

    public void addToJlist()
    {
        data.readXMLFile();
        model = new DefaultListModel();
        model.removeAllElements();
        for(int i=0;i<data.booksData.size();i++)
        {
            model.addElement(data.booksData.get(i));
        }
        content.setModel(model);
        content.setCellRenderer(new MyListCellRenderer());
        content.setFixedCellHeight(40);

    }

    public void showResult()
    {
        for(int i=0;i<data.booksData.size();i++)
        {
            for(int j=0;j<checkBox.length;j++)
            if(data.booksData.get(i).getCategory().equalsIgnoreCase(checkBox[j])) {
                model.addElement(data.booksData.get(i));
                content.setModel(model);

            }
        }
    }


    public class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            bookDataInString label = (bookDataInString) value;
            String title = label.getTitle();
            String author = label.getAuthor();
            String labelText = "<html>Title: " + title + "<br/>By: " + author;
            setText(labelText);

            return this;
        }

    }

    public class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            checkBox[0] = Fiction.isSelected() ?  "Fiction" : "nothing";
            checkBox[1] = History.isSelected() ?  "History" : "nothing";
            checkBox[2] = Geography.isSelected() ?  "Geography" : "nothing";
            checkBox[3] = Science.isSelected() ?  "Science" : "nothing";
            checkBox[4] = Other.isSelected() ?  "Other" : "nothing";

            model.removeAllElements();
            content.setModel(model);
            if(e.getActionCommand().equalsIgnoreCase("searchButton")) {
                model.removeAllElements();
                content.setModel(model);
                boolean find = false;
                for (int i = 0; i < data.booksData.size(); i++) {
                    for (int j = 0; j < checkBox.length; j++) {
                        if ((data.booksData.get(i).getCategory().equalsIgnoreCase(checkBox[j]))
                                && (searchArea.getText().equalsIgnoreCase(data.booksData.get(i).getTitle()) ||
                                searchArea.getText().equalsIgnoreCase(data.booksData.get(i).getAuthor()))) {
                            model.addElement(data.booksData.get(i));
                            content.setModel(model);
                            find = true;
                        }

                    } if(All.isSelected())
                    {
                        if(searchArea.getText().equalsIgnoreCase(data.booksData.get(i).getTitle()) ||
                                searchArea.getText().equalsIgnoreCase(data.booksData.get(i).getAuthor()))
                        {
                            model.addElement(data.booksData.get(i));
                            content.setModel(model);
                            find = true;
                        }
                    }
                }

                    if (searchArea.getText().equalsIgnoreCase("") && !find) {
                        JOptionPane.showMessageDialog(null, "Please input tittle or author" +
                                "of books you want to search");
                        showResult();
                    } else if (!find) {
                        JOptionPane.showMessageDialog(null, "Does not find " + searchArea.getText());
                        showResult();
                    }

                }


            else if((!(e.getActionCommand().equalsIgnoreCase("All/Refresh")
                    ||e.getActionCommand().equalsIgnoreCase("searchButton")||
                    e.getActionCommand().equalsIgnoreCase("Add New Book")||
                    e.getActionCommand().equalsIgnoreCase("General")))){
                All.setSelected(false);
                if(Fiction.isSelected()||Other.isSelected()||History.isSelected()||Geography.isSelected()||Science.isSelected()) {
                    showResult();}
            }

            else if (All.isSelected()) {
                Fiction.setSelected(false);
                History.setSelected(false);
                Geography.setSelected(false);
                Science.setSelected(false);
                Other.setSelected(false);
                checkBox[0] = "Fiction";
                checkBox[1] = "History";
                checkBox[2] = "Geography";
                checkBox[3] = "Science";
                checkBox[4] = "Other";
                checkBox[5] = "All";
                data.booksData.clear();
                data.readXMLFile();
                model.removeAllElements();
                content.setModel(model);
                for(int i=0;i<data.booksData.size();i++)
                {
                    model.addElement(data.booksData.get(i));
                }
                content.setModel(model);
            }
            else if(Fiction.isSelected()||Other.isSelected()||History.isSelected()||Geography.isSelected()||Science.isSelected()) {
                showResult();
            }

            if(e.getActionCommand().equalsIgnoreCase("Add New Book"))
            {
                detailGUI1.setTrue();
                detailGUI1.clearContent();
                addBook = true;
                detailGUI1.addButton.setEnabled(true);
                detailGUI1.modifyButton.setEnabled(false);
                detailGUI1.removeButton.setEnabled(false);
            }

            if(e.getActionCommand().equalsIgnoreCase("General"))
            {
               generalClass general = new generalClass();
               general.general();
               jTextArea = new JTextArea();
                 jTextArea.setText(
                         "Book Store has total "+ general.totalBooks +" books" +"\n"
                +"Total Assets :"+ general.totalAssets+
                "\nInclude: "+
                "\nTotal Fiction Books: "+general.fictionBook+" books"+
                "\nTotal Fiction Books' Assets: $"+general.fictionAssets+
                "\nTotal History Books: "+general.historyBoos+" books"+
                "\nTotal History Books' Assets: $"+general.historyAssets+
                "\nTotal Geography Books: "+general.geographyBook+" books"+
                "\nTotal Geography Books' Assets: $"+general.geographyAssets+
                "\nTotal Science Books: "+general.scienceBook+" books"+
                "\nTotal Science Books' Assets: $"+general.scienceAssets+
                "\nTotal Other Books: "+general.otherBook+" books"+
                "\nTotal Other Books' Assets: $"+general.otherAssets);
               JOptionPane.showMessageDialog(null,jTextArea);
            }


        }
    }


}
