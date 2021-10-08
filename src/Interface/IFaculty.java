package Interface;

import java.util.HashMap;

public interface IFaculty {
    public String getName(); /*название факультета*/


    public void AddStudent(IStudent stud);

    public void RemoveStudent(IStudent stud);
    //public void NotifyStudent() throws IOException, COSVisitorException, DocumentException;
    //public void NotifyStudentPDF() throws IOException, COSVisitorException, DocumentException;
}
