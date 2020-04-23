package tw.eis.model;

import java.util.List;

public interface ICourseTypeDAO {
	public boolean insertCourseType(CourseType CourseType);
	public boolean updateCourseType(CourseType CourseType);
	public boolean deleteCourseType(int CourseId);
	public List<CourseType> queryCourseTypeRecords(int CourseId);
	public List<CourseType> queryCourseType(String CourseType);
	public List<CourseType> queryCourseTypeByAllow(int EmployeeID, String name);

}
