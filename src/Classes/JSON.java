package Classes;

import Interface.IReader;
import Interface.IStudent;
import Interface.IUniversity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JSON implements IReader {


    @Override
    public void read(List<IUniversity> university, String pathDocument) throws IOException, ParseException {
        //ToReadUniversitiesFromJSON(university, pathDocument);
        //ToReadFacultiesFromJSON(university, pathDocument);
        //ToReadStudentsFromJSON(university, pathStudent);
        ToSaveUniversitiesToJSON(university, pathDocument);
    }

    private void ToSaveUniversitiesToJSON(List<IUniversity> university, String pathDocument) {
        JSONObject students = new JSONObject();
        for (int i = 0; i < university.size(); i++)
        {
            List<IStudent> stud = university.get(i).getStudents();
            JSONArray student = new JSONArray();
            for (int k = 0; k < stud.size(); k++)
            {
                List<String> facultyList = stud.get(k).getFaculties();
                JSONArray faculties = new JSONArray();
                for (int m = 0; m < facultyList.size(); m++)
                {
                    JSONObject faculty = new JSONObject();
                    faculty.put("name", facultyList.get(m));
                    faculty.put("university", university.get(i).getName());
                    faculties.add(faculty);
                }
                JSONObject objFaculties = new JSONObject();
                objFaculties.put("faculties", faculties);
                student.add(objFaculties);
            }
            JSONObject objStud = new JSONObject();
            objStud.put("name", stud.get(i).getName());
            objStud.put("student", student);
            students.put("students", objStud);
        }
        try{
            FileWriter file = new FileWriter("data,json");
            file.write(students.toJSONString());
            file.flush();
            file.close();
            System.out.print(students.toJSONString());
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
