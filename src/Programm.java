import Classes.JSON;
import Classes.ManageExtentsion;
import Classes.XML;
import Interface.IUniversity;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programm {
    public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, SAXException, TransformerException {
        List<IUniversity> universityList = new ArrayList<>();
        ManageExtentsion extentsion = new ManageExtentsion();
        File read = new File(args[0]);
        File write = new File(args[1]);
        //System.out.println("Из какого формата считать файлы?\n1) JSON\n2) XML");
        switch (getFileExtension(read)) {
            case "json":
                extentsion.setReader(new JSON());
                break;
            case "xml":
                extentsion.setReader(new XML());
                break;
        }
        /*int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                extentsion.setReader(new JSON());
                break;
            case 2:
                extentsion.setReader(new XML());
                break;
        }*/
        //System.out.println("Введите путь к файлу:");
        //String pathDocument = new Scanner(System.in).nextLine();
        extentsion.getReader().read(universityList, read.getAbsolutePath()); /* заносим данные */

        System.out.println("В какой формат записать файлы?\n1) JSON\n2) XML");
        switch (getFileExtension(write)) {
            case "json":
                extentsion.setWriter(new JSON());
                break;
            case "xml":
                extentsion.setWriter(new XML());
                break;
        }
        /*choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                extentsion.setWriter(new JSON());
                break;
            case 2:
                extentsion.setWriter(new XML());
                break;
        }*/
        //System.out.println("Введите путь к файлу:");
        //pathDocument = new Scanner(System.in).nextLine();
        extentsion.getWriter().write(universityList, write.getAbsolutePath()); /* заносим данные */

    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
