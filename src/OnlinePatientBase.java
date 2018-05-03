import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OnlinePatientBase {
    private List<Patient> basePatientOnline = new ArrayList<>();
    private boolean firstNameFlag;
    private boolean secondNameFlag;
    private boolean birthdayFlag;
    private boolean healthyFlag;
    private String firstName;
    private String secondName;
    private Date birthday;
    private boolean healthy;

    public List<Patient> getOnlinePatientBase() throws IOException, ParseException {
        URL myUrl = new URL("https://raw.githubusercontent.com/KebichVladimir/Files/master/OnlinePatientBase.xml");
        HttpURLConnection myUrlCon = (HttpURLConnection) myUrl.openConnection();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new InputStreamReader(myUrlCon.getInputStream()));
            int event = reader.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (reader.getLocalName().equals("firstName")) {
                            firstNameFlag = true;
                        } else if (reader.getLocalName().equals("secondName")) {
                            secondNameFlag = true;
                        } else if (reader.getLocalName().equals("birthday")) {
                            birthdayFlag = true;
                        } else if (reader.getLocalName().equals("healthy")) {
                            healthyFlag = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (firstNameFlag) {
                            firstName = reader.getText();
                            firstNameFlag = false;
                        } else if (secondNameFlag) {
                            secondName = reader.getText();
                            secondNameFlag = false;
                        } else if (birthdayFlag) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            birthday = format.parse(reader.getText());
                            birthdayFlag = false;
                        } else if (healthyFlag) {
                            healthy = Boolean.valueOf(reader.getText());
                            healthyFlag = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals("patient")) {
                            basePatientOnline.add(new Patient(firstName, secondName, birthday, healthy));
                        }
                        break;
                }
                if (!reader.hasNext())
                    break;
                event = reader.next();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return basePatientOnline;
    }
}