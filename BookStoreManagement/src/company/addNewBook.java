package company;

import javax.swing.*;

public class addNewBook {

    public double getPrice(String price)
    {
        double x=0,bookPrice=0;
        boolean valid = false;
            try {
                x = Double.parseDouble(price);
                valid = true;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Enter double");
            }
            if(x==0)
            {
                JOptionPane.showMessageDialog(null,"Set price for the book");
                valid = false;
            }
            if(x!=0&&valid)
            {
                bookPrice=x;
            }
            return bookPrice;
    }

    public long getISBN(String ISBN)
    {
        long x=0,myISBN=0;
        boolean valid = false;
        try {
            x = Long.parseLong(ISBN);
            valid = true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Enter number");
        }
        if(x==0)
        {
            JOptionPane.showMessageDialog(null,"Must input ISBN for the book");
            valid = false;
        }
        if(x!=0&&valid)
        {
            myISBN=x;
        }
        return myISBN;
    }

    public int getQuantity(String quantity)
    {
        int x=0,myQuantity=0;
        boolean valid = false;
        try {
            x = Integer.parseInt(quantity);
            valid = true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Enter number a number");
        }
        if(x==0)
        {
            JOptionPane.showMessageDialog(null,"Must input quantity of the book");
            valid = false;
        }
        if(x!=0&&valid)
        {
            myQuantity=x;
        }
        return myQuantity;
    }

    public String getInfoOfBook(String string)
    {
        String info="";
        if(string.equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null,"Must input to all box");
        }
        else {
            info = string;
        }
        return info;
    }
}
