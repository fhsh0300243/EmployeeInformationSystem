package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CourseTypeDAO implements ICourseTypeDAO{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public CourseTypeDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCourseType(CourseType CourseType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(CourseType);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean updateCourseType(CourseType CourseType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(CourseType);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean deleteCourseType(int CourseId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CourseType CourseType = session.get(CourseType.class, CourseId);
			session.delete(CourseType);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public List<CourseType> queryCourseTypeRecords(int CourseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<CourseType> query = session.createQuery("from CourseType where CourseId = :CourseId and (GETDATE()-DateTo)>=0",
				CourseType.class);
		query.setInteger("CourseId", CourseId);
		List<CourseType> CourseType = query.list();
		return CourseType;
	}

	@Override
	public List<CourseType> queryCourseType(String CourseType) {
		Session session = sessionFactory.getCurrentSession();
		Query<CourseType> query = session.createQuery(
				"from CourseType where CourseType = :CourseType and ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",
				CourseType.class);
		query.setString("CourseType", CourseType);
		List<CourseType> CourseTypes = query.list();
		return CourseTypes;
	}

	@Override
	public List<CourseType> queryCourseTypeByAllow(int EmployeeID, String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<CourseType> query = session.createQuery("from CourseType where EmployeeID = :EmployeeID and name = :name and (GETDATE()-DateFrom)<=0 or ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",CourseType.class);
		query.setInteger("EmployeeID", EmployeeID);
		query.setString("name", name);
		List<CourseType> CourseType = query.list();
		return CourseType;
	}


}
