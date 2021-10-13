package Classes;

import Interface.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSON implements IReader, IWriter {


    @Override
    public void read(List<IUniversity> university, String pathDocument) throws IOException, ParseException {
        //ToReadUniversitiesFromJSON(university, pathDocument);
    }

    @Override
    public void write(List<IUniversity> universityList, String pathDocument) {
        ToWriteUniversitiesToJSON(universityList, pathDocument);
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

                JSONObject nameFac = new JSONObject();
                nameFac.put("name", fac.get(k).getName());

                JSONObject nameUni = new JSONObject();
                nameUni.put("university", universityList.get(i).getName());

                faculty.add(nameFac);/* название факультета */
                faculty.add(nameUni);/* название университета */
                faculty.add(studentTitle);/* студенты факультета */

                /* объединение всей информации о факультете под надписью faculty */
                JSONObject facultyTitle = new JSONObject();
                facultyTitle.put("faculty", faculty);
                faculties.add(facultyTitle);

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
