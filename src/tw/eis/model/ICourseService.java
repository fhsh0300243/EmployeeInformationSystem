package tw.eis.model;

import java.util.List;

public interface ICourseService {
	public boolean insertCourse(Course Course);
	public boolean updateCourse(Course Course);
	public boolean deleteCourse(int CourseId);
	public List<Course> queryCourseRecords(int CourseId);
	public List<Course> queryCourse(String CourseType);
	public List<Course> queryCourseByAllow(int EmployeeID, String name);
	public byte[] queryAttachmentFiles(int CourseId);

}
