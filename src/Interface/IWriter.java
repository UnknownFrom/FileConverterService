package Interface;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface IWriter {
    public void write(List<IUniversity> universityList, String pathDocument) throws ParserConfigurationException, TransformerException;
}
