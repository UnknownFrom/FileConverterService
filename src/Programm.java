import Classes.JSON;
import Classes.ManageExtentsion;
import Classes.XML;
import Interface.IUniversity;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Programm {
    public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, SAXException, TransformerException {
        List<IUniversity> universityList = new ArrayList<>();
        ManageExtentsion extentsion = new ManageExtentsion();
        File read = new File(args[0]);
        File write = new File(args[1]);
        switch (getFileExtension(read)) {
            case "json" -> extentsion.setReader(new JSON());
            case "xml" -> extentsion.setReader(new XML());
        }
        extentsion.getReader().read(universityList, read.getAbsolutePath()); /* заносим данные */

        switch (getFileExtension(write)) {
            case "json" -> extentsion.setWriter(new JSON());
            case "xml" -> extentsion.setWriter(new XML());
        }
        extentsion.getWriter().write(universityList, write.getAbsolutePath()); /* заносим данные */
    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
