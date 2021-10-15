package Classes;

import Interface.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JSON implements IReader, IWriter {


    @Override
    public void read(List<IUniversity> university, String pathDocument) throws IOException, ParseException {
        ToReadUniversitiesFromJSON(university, pathDocument);
    }

    @Override
    public void write(List<IUniversity> universityList, String pathDocument) {
        ToWriteUniversitiesToJSON(universityList, pathDocument);
    }

    private static void ToReadUniversitiesFromJSON(List<IUniversity> university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray faculties = (JSONArray) jsonObject.get("faculties");
        Iterator i = faculties.iterator();
        int indFac = -1;
        while (i.hasNext()) {
            JSONObject faculty = (JSONObject) i.next();
            String nameUniversity = (String)faculty.get("university");
            String nameFaculty = (String) faculty.get("name");

            /* получение индекса университета в списке */
            int indUn = -1;
            for (int ind = 0; ind < university.size(); ind++)
            {
                if(university.get(ind).getName().equals(nameUniversity))
                {
                    indUn = ind;
                }
            }
            if(indUn == -1) /* если университета нет, добавляем */
            {
                university.add(new University(nameUniversity));
                indUn = university.size()-1;
            }
            university.get(indUn).AddFaculty(new Faculty(nameFaculty));
            indFac++;

            JSONArray students = (JSONArray) faculty.get("students");
            Iterator k = students.iterator();
            while (k.hasNext())
            {
                JSONObject student = (JSONObject) k.next();
                String nameStudent = (String)student.get("name");
                /* общий список студентов в университете */
                university.get(indUn).AddStudent(new Student(nameStudent));
                /* добавляем студента на факультет */
                university.get(indUn).getFaculty(indFac).AddStudent(new Student(nameStudent));
            }
        }
    }

    private void ToWriteUniversitiesToJSON(List<IUniversity> universityList, String pathDocument) {
        JSONObject result = new JSONObject();/* основной объект для записи результата */
        JSONArray faculties = new JSONArray();
        for (int i = 0; i < universityList.size(); i++)
        {
            /* список факультетов университета */
            List<IFaculty> fac = universityList.get(i).getFaculties();

            for (int k = 0; k < fac.size(); k++)
            {
                JSONArray faculty = new JSONArray();

                List<IStudent> studentsList = fac.get(k).getStudents();
                JSONArray students = new JSONArray();
                for (int m = 0; m < studentsList.size(); m++)
                {
                    JSONObject student = new JSONObject();
                    student.put("name", studentsList.get(m).getName());
                    students.add(student);
                }
                /* объединение студентов под надписью students */
                JSONObject studentTitle = new JSONObject();
                studentTitle.put("students", students);
                studentTitle.put("name", fac.get(k).getName());
                studentTitle.put("university", universityList.get(i).getName());

                /*JSONObject nameFac = new JSONObject();
                nameFac.put("name", fac.get(k).getName());

                JSONObject nameUni = new JSONObject();
                nameUni.put("university", universityList.get(i).getName());*/

                //faculty.add(nameFac);/* название факультета */
                //faculty.add(nameUni);/* название университета */

                //faculty.add(studentTitle);/* студенты факультета */

                /* объединение всей информации о факультете под надписью faculty */
                //JSONObject facultyTitle = new JSONObject();
                //facultyTitle.put("faculty", faculty);
                faculties.add(studentTitle);

            }
            result.put("faculties", faculties);

        }
        try{
            FileWriter file = new FileWriter("data.json");
            file.write(result.toJSONString());
            file.flush();
            file.close();
            System.out.print(result.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*private static void ToReadStudentsFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("students");
        Iterator i = disciplineJ.iterator();
        while (i.hasNext()) {
            HashMap<String, Integer> discipline = new HashMap<>();
            JSONObject obj = (JSONObject) i.next();
            String name = (String) obj.get("name");
            String[] dis = ((String) obj.get("discipline")).split(" ");
            for (int k = 0; k < dis.length; k += 2) {
                discipline.put(dis[k], Integer.parseInt(dis[k + 1]));
            }
            university.CheckingSuitableFaculties(new Student(name, discipline));
        }
    }

    private static void ToReadFacultiesFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("faculties");
        Iterator i = disciplineJ.iterator();
        while (i.hasNext()) {
            HashMap<String, Integer> disciplines = new HashMap<>();
            JSONObject obj = (JSONObject) i.next();
            String name = (String) obj.get("name");
            String[] dis = ((String) obj.get("discipline")).split(" ");
            for (int k = 0; k < dis.length; k += 2) {
                disciplines.put(dis[k], Integer.parseInt(dis[k + 1]));
            }
            university.AddFaculty(new Faculty(name, disciplines));
        }
    }*/
}
