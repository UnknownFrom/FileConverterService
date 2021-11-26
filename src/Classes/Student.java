package Classes;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<String> faculties; /*на какие факультеты поступил*/

    public Student(String name) {
        this.name = name;
        faculties = new ArrayList<>();
    }

    public void addFaculties(String fac) {
        if (!faculties.contains(fac)) {
            faculties.add(fac);
        }
    }

    public String getName() {
        return name;
    }
}
