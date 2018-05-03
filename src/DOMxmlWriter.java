import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;

public class DOMxmlWriter {
    private String nameFile;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public DOMxmlWriter(String nameFile) {
        this.nameFile = nameFile;
    }

    public void WritePatientBase(LinkedHashSet<Patient> patientBase) {
        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element hospital = document.createElement("hospital");
            document.appendChild(hospital);
            for (Patient patients : patientBase) {
                Element patient = document.createElement("patient");
                Element firstName = document.createElement("firstName");
                firstName.setTextContent(patients.getFirstName());
                Element secondName = document.createElement("secondName");
                secondName.setTextContent(patients.getSecondName());
                Element birthday = document.createElement("birthday");
                birthday.setTextContent(format.format(patients.getBirthday()));
                Element healthy = document.createElement("healthy");
                healthy.setTextContent(String.valueOf(patients.isHealthy()));
                hospital.appendChild(patient);
                patient.appendChild(firstName);
                patient.appendChild(secondName);
                patient.appendChild(birthday);
                patient.appendChild(healthy);
                writeDocument(document);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return;
    }

    public void writeDocument(Document document) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream outputStream = new FileOutputStream(nameFile);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}


