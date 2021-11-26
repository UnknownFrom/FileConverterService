package Interface;

import Classes.University;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface IWriter {
    public void write(List<University> universities, String path) throws ParserConfigurationException, TransformerException;
}
