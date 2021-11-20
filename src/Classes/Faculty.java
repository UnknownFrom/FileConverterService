package Classes;

import Interface.IFaculty;
import Interface.IStudent;

import java.util.ArrayList;
import java.util.List;

public class Faculty implements IFaculty {
    private List<IStudent> students;
    private String name;

    public Faculty(String name) {
        students = new ArrayList<>();
        this.name = name;
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
    public void AddStudent(IStudent stud) {
        if (!students.contains(stud)) {
            students.add(stud);
        }
    }
}
