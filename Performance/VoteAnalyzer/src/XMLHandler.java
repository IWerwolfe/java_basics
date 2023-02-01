import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {

    private String voterName = "";
    private String birthDay = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        try {
            if (qName.equals("voter") && voterName.isEmpty()) {
                voterName = attributes.getValue("name");
                birthDay = attributes.getValue("birthDay");
            } else if (qName.equals("visit") && !voterName.isEmpty()) {
                DBConnection.countVoter(voterName, birthDay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voterName = "";
            birthDay = "";
        }
    }
}
