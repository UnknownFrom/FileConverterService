import Classes.JSON;
import Classes.ManageExtentsion;
import Classes.XML;
import Interface.IUniversity;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programm {
    public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, SAXException {
        List<IUniversity> universityList = new ArrayList<>();
        ManageExtentsion extentsion = new ManageExtentsion();
        System.out.println("Из какого формата считать файлы?\n1) JSON\n2) XML");
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                extentsion.setReader(new JSON());
                break;
            case 2:
                extentsion.setReader(new XML());
                break;
        }
        System.out.println("Введите путь к файлу:");
        String pathDocument = new Scanner(System.in).nextLine();
        extentsion.getReader().read(universityList, pathDocument); /* заносим данные */

        System.out.println("В какой формат записать файлы?\n1) JSON\n2) XML");
        choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                extentsion.setWriter(new JSON());
                break;
            case 2:
                extentsion.setWriter(new XML());
                break;
        }
        System.out.println("Введите путь к файлу:");
        pathDocument = new Scanner(System.in).nextLine();
        extentsion.getWriter().write(universityList, pathDocument); /* заносим данные */

    }
}
