package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTypeService implements ICourseTypeService{

private CourseTypeDAO CourseTypeDAO;
	
	@Autowired
	public CourseTypeService(CourseTypeDAO CourseTypeDAO) {
		this.CourseTypeDAO = CourseTypeDAO;
	}
	

	@Override
	public boolean insertCourseType(CourseType CourseType) {
		return CourseTypeDAO.insertCourseType(CourseType);
	}

	@Override
	public boolean updateCourseType(CourseType CourseType) {
		return CourseTypeDAO.updateCourseType(CourseType);
	}

	@Override
	public boolean deleteCourseType(int CourseId) {
		return CourseTypeDAO.deleteCourseType(CourseId);
	}

	@Override
	public List<CourseType> queryCourseTypeRecords(int CourseId) {
		return CourseTypeDAO.queryCourseTypeRecords(CourseId);
	}

	@Override
	public List<CourseType> queryCourseType(String CourseType) {
		return CourseTypeDAO.queryCourseType(CourseType);
	}

	@Override
	public List<CourseType> queryCourseTypeByAllow(int EmployeeID, String name) {
		return CourseTypeDAO.queryCourseTypeByAllow(EmployeeID, name);
	}

}
