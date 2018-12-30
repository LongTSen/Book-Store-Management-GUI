package company;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class booksDataBase {
    public ArrayList<bookDataInString> booksData = new ArrayList<>();

    public void creatData() {
        booksData.add(new bookDataInString("Fiction", "Harden", "DJ Molles",
                "929394029344", "19.99", "20", "A Good fiction book"));
        booksData.add(new bookDataInString("Fiction", "The hideaway", "Lauren K. Denton",
                "90908393742", "10.97", "19", "It's a good book"));
        booksData.add(new bookDataInString("History", "A History Of Nearly Everything", "Bill Bryson",
                "908898989834", "8.91", "30", "History of the world"));
        booksData.add(new bookDataInString("Geography", "North American", "Bryant",
                "989898765241", "19.99", "88", "Geography Book of North American"));
        booksData.add(new bookDataInString("Science", "Big ideals Simply Explained", "DK",
                "976767544434", "9.99", "21", "Science Book"));
        booksData.add(new bookDataInString("Other", "How to Cook Pho", "Engela Ngo",
                "909090923134", "19.90", "55", "Cooking Book"));


    }


    public void addNewItem(String genders, String title, String author, String ISBN, String price, String quantity, String description) {
        booksData.add(new bookDataInString(genders, title, author, ISBN, price, quantity, description));
    }

    public void Modify(int i ,String genders, String title, String author, String ISBN, String price, String quantity, String description) {
        booksData.set(i,new bookDataInString(genders, title, author, ISBN, price, quantity, description));
    }


    public void readXMLFile() {
        try {
            File XmlFile = new File("bookData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Book");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    booksData.add(new bookDataInString(eElement.getElementsByTagName("Category").item(0).getTextContent(),
                            eElement.getElementsByTagName("Title").item(0).getTextContent(),
                            eElement.getElementsByTagName("Author").item(0).getTextContent(),
                            eElement.getElementsByTagName("ISBN").item(0).getTextContent(),
                            eElement.getElementsByTagName("Price").item(0).getTextContent(),
                            eElement.getElementsByTagName("Quantity").item(0).getTextContent(),
                            eElement.getElementsByTagName("Description").item(0).getTextContent()));
                }

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void writeXMLFile() {
//            creatData();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element Books = doc.createElement("Books");
            doc.appendChild(Books);


            for (int i = 0; i < booksData.size(); i++) {
                Element Book = doc.createElement("Book");
                Books.appendChild(Book);
                Element category = doc.createElement("Category");
                category.appendChild(doc.createTextNode(booksData.get(i).getCategory()));

                Book.appendChild(category);

                Element title = doc.createElement("Title");
                title.appendChild(doc.createTextNode(booksData.get(i).getTitle()));
                Book.appendChild(title);

                Element author = doc.createElement("Author");
                author.appendChild(doc.createTextNode(booksData.get(i).getAuthor()));
                Book.appendChild(author);

                Element ISBN = doc.createElement("ISBN");
                ISBN.appendChild(doc.createTextNode(booksData.get(i).getISBN()));
                Book.appendChild(ISBN);

                Element price = doc.createElement("Price");
                price.appendChild(doc.createTextNode(booksData.get(i).getPrice()));
                Book.appendChild(price);

                Element quantity = doc.createElement("Quantity");
                quantity.appendChild(doc.createTextNode(booksData.get(i).getQuantity()));
                Book.appendChild(quantity);

                Element description = doc.createElement("Description");
                description.appendChild(doc.createTextNode(booksData.get(i).getDescription()));
                Book.appendChild(description);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("bookData.xml"));
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();

        }
    }
}
