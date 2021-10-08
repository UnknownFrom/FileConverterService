package Interface;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IReader {
    public void read(List<IUniversity> universityList, String pathDocument) throws ParserConfigurationException, IOException, SAXException, ParseException;
}
