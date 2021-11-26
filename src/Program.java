import Classes.Json;
import Classes.ManageExtension;
import Classes.University;
import Classes.Xml;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, SAXException, TransformerException {
        /* "C:\Program Files\Java\jdk-16.0.2\bin\java.exe" -jar FileConverterService.iml.jar data.xml dataResult.json */
        try {
            List<University> universities = new ArrayList<>();
            ManageExtension extension = new ManageExtension();
            File read, write;
            if (args.length > 0) {
                read = new File(args[0]);
                write = new File(args[1]);
            } else {
                read = new File("src/data/data.json");
                write = new File("src/data/dataResult.json");
            }
            switch (FilenameUtils.getExtension(read.getAbsolutePath())) {
                case "json" -> extension.setReader(new Json());
                case "xml" -> extension.setReader(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            switch (FilenameUtils.getExtension(write.getAbsolutePath())) {
                case "json" -> extension.setWriter(new Json());
                case "xml" -> extension.setWriter(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            extension.getReader().read(universities, read.getAbsolutePath()); /* считываем данные */
            extension.getWriter().write(universities, write.getAbsolutePath()); /* записываем данные */
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
