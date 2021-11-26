package Classes;

import Interface.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Xml implements IReader, IWriter {

    @Override
    public void read(List<University> universities, String path) throws ParserConfigurationException, IOException, SAXException {
        readFromXml(universities, path);
    }

    @Override
    public void write(List<University> universities, String path) throws ParserConfigurationException, TransformerException {
        writeToXml(universities, path);
    }

    private void writeToXml(List<University> universities, String path) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.newDocument();

        /* создание корневого элемента */
        Element rootElement = document.createElement("universities");
        document.appendChild(rootElement);

        for (int i = 0; i < universities.size(); i++) {
            /* создание отдельных университетов */
            Element universityElement = document.createElement("university");
            rootElement.appendChild(universityElement);

            /* создание атрибутов */
            Attr nameUniversity = document.createAttribute("name");
            nameUniversity.setValue(universities.get(i).getName());
            universityElement.setAttributeNode(nameUniversity);

            List<Faculty> facultiesList = universities.get(i).getFaculties();
            for (int k = 0; k < facultiesList.size(); k++) {
                /* создание отдельных факультетов */
                Element facultyElement = document.createElement("faculty");
                universityElement.appendChild(facultyElement);

                /* создание атрибутов */
                Attr nameFaculty = document.createAttribute("name");
                nameFaculty.setValue(facultiesList.get(k).getName());
                facultyElement.setAttributeNode(nameFaculty);

                List<Student> studentsList = facultiesList.get(k).getStudents();
                for (int m = 0; m < studentsList.size(); m++) {
                    /* создание отдельных факультетов */
                    Element studentElement = document.createElement("student");
                    facultyElement.appendChild(studentElement);

                    /* создание атрибутов */
                    Attr nameStudent = document.createAttribute("name");
                    nameStudent.setValue(studentsList.get(m).getName());
                    studentElement.setAttributeNode(nameStudent);
                }
            }
        }
        /* запись в XML документ */
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }

    private void readFromXml(List<University> universities, String path) throws ParserConfigurationException, IOException, SAXException {
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
            universities.add(new University(nameUni));

            NodeList facultiesElements = university.getChildNodes();
            int countStud = -1;
            int countFac = -1;
            for (int k = 0; k < facultiesElements.getLength(); k++) {
                Node faculty = facultiesElements.item(k);

                if (faculty.getNodeType() != Node.TEXT_NODE) {

                    NamedNodeMap attributesFac = faculty.getAttributes();
                    String nameFac = attributesFac.getNamedItem("name").getNodeValue();
                    universities.get(i).addFaculty(new Faculty(nameFac));
                    countFac++;

                    NodeList studentsElements = faculty.getChildNodes();

                    for (int m = 0; m < studentsElements.getLength(); m++) {
                        Node student = studentsElements.item(m);
                        if (student.getNodeType() != Node.TEXT_NODE) {
                            NamedNodeMap attributesStud = student.getAttributes();
                            String nameStud = attributesStud.getNamedItem("name").getNodeValue();
                            universities.get(i).getFaculty(countFac).addStudent(new Student(nameStud));
                            universities.get(i).addStudent(new Student(nameStud));
                            countStud++;
                            universities.get(i).getStudent(countStud).addFaculties(nameFac);
                        }
                    }
                }
            }
        }
    }
}
