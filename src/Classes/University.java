package Classes;

import Interface.IFaculty;
import Interface.IStudent;
import Interface.IUniversity;

import java.util.ArrayList;
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

    public IFaculty getFaculty(int index) {
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

    @Override
    public List<IStudent> getStudents() {
        return students;
    }

    @Override
    public void AddStudent(IStudent student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    @Override
    public void AddFaculty(IFaculty faculty) {
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
        }
    }
}
