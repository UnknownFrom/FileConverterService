package Classes;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private List<Student> students;
    private String name;

    public Faculty(String name) {
        students = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student stud) {
        if (!students.contains(stud)) {
            students.add(stud);
        }
    }
}
