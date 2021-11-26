package Classes;

import Interface.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Json implements IReader, IWriter {

    @Override
    public void read(List<University> universities, String path) throws IOException, ParseException {
        readFromJson(universities, path);
    }

    @Override
    public void write(List<University> universities, String path) {
        writeToJson(universities, path);
    }

    private static void readFromJson(List<University> universities, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray faculties = (JSONArray) jsonObject.get("faculties");
        Iterator i = faculties.iterator();
        int indFac = -1;
        while (i.hasNext()) {
            JSONObject faculty = (JSONObject) i.next();
            String nameUniversity = (String) faculty.get("university");
            String nameFaculty = (String) faculty.get("name");

            /* получение индекса университета в списке */
            int indUn = -1;
            for (int ind = 0; ind < universities.size(); ind++) {
                if (universities.get(ind).getName().equals(nameUniversity)) {
                    indUn = ind;
                }
            }
            if (indUn == -1) /* если университета нет, добавляем */ {
                universities.add(new University(nameUniversity));
                indUn = universities.size() - 1;
            }
            universities.get(indUn).addFaculty(new Faculty(nameFaculty));
            indFac++;

            JSONArray students = (JSONArray) faculty.get("students");
            Iterator k = students.iterator();
            while (k.hasNext()) {
                JSONObject student = (JSONObject) k.next();
                String nameStudent = (String) student.get("name");
                /* общий список студентов в университете */
                universities.get(indUn).addStudent(new Student(nameStudent));
                /* добавляем студента на факультет */
                universities.get(indUn).getFaculty(indFac).addStudent(new Student(nameStudent));
            }
        }
    }

    private void writeToJson(List<University> universities, String path) {
        JSONObject result = new JSONObject();/* основной объект для записи результата */
        JSONArray faculties = new JSONArray();
        for (int i = 0; i < universities.size(); i++) {
            /* список факультетов университета */
            List<Faculty> fac = universities.get(i).getFaculties();

            for (int k = 0; k < fac.size(); k++) {
                List<Student> studentsList = fac.get(k).getStudents();
                JSONArray students = new JSONArray();
                for (int m = 0; m < studentsList.size(); m++) {
                    JSONObject student = new JSONObject();
                    student.put("name", studentsList.get(m).getName());
                    students.add(student);
                }
                /* объединение студентов под надписью students */
                JSONObject studentTitle = new JSONObject();
                studentTitle.put("students", students);
                studentTitle.put("name", fac.get(k).getName());
                studentTitle.put("university", universities.get(i).getName());

                /* занесение факультета в массив */
                faculties.add(studentTitle);

            }
            result.put("faculties", faculties);

        }
        try {
            FileWriter file = new FileWriter(path);
            file.write(result.toJSONString());
            file.flush();
            file.close();
            //System.out.print(result.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
