package tw.eis.model;

import java.util.List;

public interface IGradeBookDAO {
	
	public boolean insertGradeBook(GradeBook GradeBook);
	public boolean updateGradeBook(GradeBook GradeBook);
	public boolean deleteGradeBook(int id);
	public List<GradeBook> queryGradeBookRecords(int EmployeeID);
	public List<GradeBook> queryGradeBook(String department);
	public List<GradeBook> queryGradeBookByAllow(int EmployeeID,String EmployeeName);
//	public byte[] queryGradeBookFile(int CourseID);


}
