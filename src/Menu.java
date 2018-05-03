import java.io.IOException;
import java.text.ParseException;

public interface Menu {
    int makeChoice();

    void chooseOperation() throws IOException, ParseException;
}
