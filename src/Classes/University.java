package Classes;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Faculty> faculties;
    private String name;
    private List<Student> students;

    public University(String name) {
        faculties = new ArrayList<>();
        this.name = name;
        students = new ArrayList<>();
    }

    public Faculty getFaculty(int index) {
        return faculties.get(index);
    }

    public Student getStudent(int index) {
        return students.get(index);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void addFaculty(Faculty faculty) {
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
        }
    }
}
