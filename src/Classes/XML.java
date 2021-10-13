package Classes;

import Interface.IReader;
import Interface.IUniversity;
import Interface.IWriter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class XML implements IReader, IWriter {

    @Override
    public void read(List<IUniversity> universityList, String pathDocument) throws ParserConfigurationException, IOException, SAXException {
        ToReadUniversitiesFromXML(universityList, pathDocument);
    }

    @Override
    public void write(List<IUniversity> universityList, String pathDocument) {
        //ToWriteUniversitiesToXML(universityList, pathDocument);
    }

    private void ToReadUniversitiesFromXML(List<IUniversity> universityList, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        /*получение списка всех элементов university*/
        NodeList universitiesElements = document.getDocumentElement().getElementsByTagName("university");

        /*перебор всех элементов university*/
        for (int i = 0; i < universitiesElements.getLength(); i++) {
            Node university = universitiesElements.item(i);
            NamedNodeMap attributesUni = university.getAttributes();
            String nameUni = attributesUni.getNamedItem("name").getNodeValue();
            universityList.add(new University(nameUni));

            NodeList facultiesElements = university.getChildNodes();
            int countStud = -1;
            int countFac = -1;
            for (int k = 0; k < facultiesElements.getLength(); k++) {
                Node faculty = facultiesElements.item(k);

                if (faculty.getNodeType() != Node.TEXT_NODE) {

                    NamedNodeMap attributesFac = faculty.getAttributes();
                    String nameFac = attributesFac.getNamedItem("name").getNodeValue();
                    universityList.get(i).AddFaculty(new Faculty(nameFac));
                    countFac++;

                    NodeList studentsElements = faculty.getChildNodes();

                    for (int m = 0; m < studentsElements.getLength(); m++) {
                        Node student = studentsElements.item(m);
                        if (student.getNodeType() != Node.TEXT_NODE) {
                            NamedNodeMap attributesStud = student.getAttributes();
                            String nameStud = attributesStud.getNamedItem("name").getNodeValue();
                            universityList.get(i).getFaculty(countFac).AddStudent(new Student(nameStud));
                            universityList.get(i).AddStudent(new Student(nameStud));
                            countStud++;
                            universityList.get(i).getStudent(countStud).AddFaculties(nameFac);
                        }
                    }
                }
            }
        }
    }


}
