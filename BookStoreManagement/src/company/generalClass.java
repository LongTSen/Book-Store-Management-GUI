package company;

public class generalClass {

    booksDataBase data = new booksDataBase();
    public double totalAssets=0;
    public int totalBooks=0;
    public int fictionBook = 0;
    public double fictionAssets = 0;
    public int historyBoos = 0;
    public double historyAssets = 0;
    public int geographyBook=0;
    public double geographyAssets = 0;
    public int scienceBook = 0;
    public double scienceAssets = 0;
    public int otherBook = 0;
    public double otherAssets = 0;


    private Integer count(int a)
    {
        return Integer.valueOf(data.booksData.get(a).getQuantity());
    }
    private Double assets(int a)
    {
        return Double.valueOf(data.booksData.get(a).getPrice());
    }
    public void general()
    {
        data.readXMLFile();
        for(int i=0;i<data.booksData.size();i++)
        {
            totalBooks+= count(i);
            totalAssets+= count(i)*assets(i);
            if(data.booksData.get(i).getCategory().equalsIgnoreCase("Fiction"))
            {
                fictionBook+= count(i);
                fictionAssets+=count(i)*assets(i);
            }
            else if(data.booksData.get(i).getCategory().equalsIgnoreCase("Geography"))
            {
                geographyBook+=count(i);
                geographyAssets+=count(i)*assets(i);
            }
            else if(data.booksData.get(i).getCategory().equalsIgnoreCase("History"))
            {
                historyBoos+=count(i);
                historyAssets+=count(i)*assets(i);
            }
            else if(data.booksData.get(i).getCategory().equalsIgnoreCase("Science"))
            {
                scienceBook+=count(i);
                scienceAssets+=count(i)*assets(i);
            }
            else if(data.booksData.get(i).getCategory().equalsIgnoreCase("Other"))
            {
                otherBook+=count(i);
                otherAssets+=count(i)*assets(i);
            }
        }

    }

}
