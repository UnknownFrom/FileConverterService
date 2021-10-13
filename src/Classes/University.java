package Classes;

import Interface.IFaculty;
import Interface.IStudent;
import Interface.IUniversity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class University implements IUniversity {
    private List<IFaculty> faculties;
    private String name;
    private List<IStudent> students;

    public University(String name) {
        faculties = new ArrayList<>();
        this.name = name;
        students = new ArrayList<>();
    }

    public IFaculty getFaculty(int index)
    {
        return faculties.get(index);
    }

    @Override
    public IStudent getStudent(int index) {
        return students.get(index);
    }

    @Override
    public List<IFaculty> getFaculties() {
        return faculties;
    }

    @Override
    public String getName() {
        return name;
    }

   // @Override
  /*  public void CheckingSuitableFaculties(IStudent stud) *//*проверка, на какой факультет проходит студент*//* {
        AddStudent(stud);
        HashMap<String, Integer> disStud = stud.getDisciplines();
        for (IFaculty faculty : faculties) { *//*проходим по факультетам университета*//*
            HashMap<String, Integer> disFac = faculty.getDisciplines(); *//*получаем список дисциплин факультета*//*
            if (disStud.size() >= disFac.size()) {
                int count = 0;
                for (String disName : disFac.keySet()) { *//*проходим по дисциплинам факультета*//*
                    if (disStud.containsKey(disName) && disStud.get(disName) >= disFac.get(disName))
                        *//*если у студента сдана эта дисциплина и балл достаточный*//* {
                        count++;
                    }
                }
                if (count == disFac.size())
                    *//*если все дисциплины для факультета у студента сданы*//* {
                    stud.AddFaculties(faculty.getName());
                    faculty.AddStudent(stud);
                }
            }
        }
    }*/

    /*@Override
    public List<IFaculty> getFaculties() {
        return faculties;
    }*/

    @Override
    public List<IStudent> getStudents() {
        return students;
    }

    @Override
    public void AddStudent(IStudent student)
    {
        if(!students.contains(student)) {
            students.add(student);
        }
    }

    @Override
    public void AddFaculty(IFaculty faculty) {
        if(!faculties.contains(faculty)) {
            faculties.add(faculty);
        }
    }




}
