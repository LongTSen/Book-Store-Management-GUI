package company;

public class bookDataInString {
    private String category;
    private String title;
    private String author;
    private String ISBN;
    private String price;
    private String quantity;
    private String description;

    public bookDataInString(String category,String title,String author,String ISBN,String price,String quantity,String description)
    {
        this.category = category;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }



    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}
