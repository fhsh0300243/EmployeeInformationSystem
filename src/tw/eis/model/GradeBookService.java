package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class GradeBookService implements IGradeBookService{
	
private GradeBookDAO GradeBookDAO;
	
	@Autowired
	public GradeBookService(GradeBookDAO GradeBookDAO) {
		this.GradeBookDAO = GradeBookDAO;
	}
	

	@Override
	public boolean insertGradeBook(GradeBook GradeBook) {
		return GradeBookDAO.insertGradeBook(GradeBook);
	}

	@Override
	public boolean updateGradeBook(GradeBook GradeBook) {
		return GradeBookDAO.updateGradeBook(GradeBook);
	}

	@Override
	public boolean deleteGradeBook(int CourseId) {
		return GradeBookDAO.deleteGradeBook(CourseId);
	}

	@Override
	public List<GradeBook> queryGradeBookRecords(int CourseId) {
		return GradeBookDAO.queryGradeBookRecords(CourseId);
	}

	@Override
	public List<GradeBook> queryGradeBook(String GradeBook) {
		return GradeBookDAO.queryGradeBook(GradeBook);
	}

	@Override
	public List<GradeBook> queryGradeBookByAllow(int EmployeeID, String name) {
		return GradeBookDAO.queryGradeBookByAllow(EmployeeID, name);
	}

	

}
