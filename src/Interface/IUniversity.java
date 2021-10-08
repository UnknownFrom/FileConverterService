package Interface;

import java.io.IOException;
import java.util.List;

public interface IUniversity {

    public List<IStudent> getStudents();
    public IFaculty getFaculty(int index);
    public String getName();
    public void AddStudent(IStudent student);

    public void AddFaculty(IFaculty faculty);


}
