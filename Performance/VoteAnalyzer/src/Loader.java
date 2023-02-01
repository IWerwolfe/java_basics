import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.Connection;

public class Loader {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        String fileName = "res/data-1572M.xml";
        Connection connection = DBConnection.getConnection();

        if (connection == null) {
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        DBConnection.printVoterCounts();
        System.out.println("Main: " + (System.currentTimeMillis() - start));
    }
}