package Classes;

import Interface.IFaculty;
import Interface.IStudent;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements IStudent {
    private String name;
    private List<String> faculties; /*на какие факультеты поступил*/

    public Student(String name) {
        this.name = name;
        faculties = new ArrayList<>();
    }

    @Override
    public void AddFaculties(String fac) {
        if (!faculties.contains(fac)) {
            faculties.add(fac);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getFaculties() {
        return faculties;
    }

}
