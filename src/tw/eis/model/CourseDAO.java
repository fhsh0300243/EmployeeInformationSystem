package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO implements ICourseDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public CourseDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCourse(Course Course) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(Course);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean updateCourse(Course Course) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(Course);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean deleteCourse(int CourseId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Course Course = session.get(Course.class, CourseId);
			session.delete(Course);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public List<Course> queryCourseRecords(int CourseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> query = session.createQuery("from Course where CourseId = :CourseId and (GETDATE()-DateTo)>=0",
				Course.class);
		query.setInteger("CourseId", CourseId);
		List<Course> Course = query.list();
		return Course;
	}

	@Override
	public List<Course> queryCourse(String CourseType) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> query = session.createQuery(
				"from Course where CourseType = :CourseType and ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",
				Course.class);
		query.setString("CourseType", CourseType);
		List<Course> Course = query.list();
		return Course;
	}

	@Override
	public List<Course> queryCourseByAllow(int EmployeeID, String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<Course> query = session.createQuery("from Course where EmployeeID = :EmployeeID and name = :name and (GETDATE()-DateFrom)<=0 or ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",Course.class);
		query.setInteger("EmployeeID", EmployeeID);
		query.setString("name", name);
		List<Course> Course = query.list();
		return Course;
	}
	
	@Override
	public byte[] queryAttachmentFiles(int CourseId) {
		Session Session = sessionFactory.getCurrentSession();
		Course Course = Session.get(Course.class, CourseId);
		
		return Course.getAttachmentFiles();
	}

}
