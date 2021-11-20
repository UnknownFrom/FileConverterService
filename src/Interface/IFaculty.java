package Interface;

import java.util.List;

public interface IFaculty {
    public String getName(); /*название факультета*/
    public List<IStudent> getStudents(); /*название факультета*/
    public void AddStudent(IStudent stud);
}
