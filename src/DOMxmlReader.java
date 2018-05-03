import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

public class DOMxmlReader {
    private LinkedHashSet<Patient> basePatient = new LinkedHashSet<>();
    private String nameFile;
    private String firstName;
    private String secondName;
    private Date birthday;
    private boolean healthy;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public DOMxmlReader(String nameFile) {
        this.nameFile = nameFile;
    }

    public LinkedHashSet<Patient> ReadPatientBase() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(nameFile);
            NodeList nodeList = document.getElementsByTagName("patient");
            for (int i = 0; i < nodeList.getLength(); i++) {
                basePatient.add(getPatient(nodeList.item(i)));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return basePatient;
    }

    public String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public Patient getPatient(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            firstName = getTagValue("firstName", element);
            secondName = getTagValue("secondName", element);
            try {
                birthday = format.parse(getTagValue("birthday", element));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            healthy = Boolean.valueOf(getTagValue("healthy", element));
        }
        return new Patient(firstName, secondName, birthday, healthy);
    }
}
