package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {
	
	private CourseDAO courseDAO;
	
	@Autowired
	public CourseService(CourseDAO CourseDAO) {
		this.courseDAO = CourseDAO;
	}
	

	@Override
	public boolean insertCourse(Course Course) {
		return courseDAO.insertCourse(Course);
	}

	@Override
	public boolean updateCourse(Course Course) {
		return courseDAO.updateCourse(Course);
	}

	@Override
	public boolean deleteCourse(int CourseId) {
		return courseDAO.deleteCourse(CourseId);
	}

	@Override
	public List<Course> queryCourseRecords(int CourseId) {
		return courseDAO.queryCourseRecords(CourseId);
	}

	@Override
	public List<Course> queryCourse(String CourseType) {
		return courseDAO.queryCourse(CourseType);
	}

	@Override
	public List<Course> queryCourseByAllow(int EmployeeID, String name) {
		return courseDAO.queryCourseByAllow(EmployeeID, name);
	}

}
