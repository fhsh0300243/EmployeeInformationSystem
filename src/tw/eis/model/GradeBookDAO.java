package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class GradeBookDAO implements IGradeBookDAO{
	
private SessionFactory sessionFactory;
	
	@Autowired
	public GradeBookDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertGradeBook(GradeBook GradeBook) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(GradeBook);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean updateGradeBook(GradeBook GradeBook) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(GradeBook);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public boolean deleteGradeBook(int CourseId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			GradeBook GradeBook = session.get(GradeBook.class, CourseId);
			session.delete(GradeBook);
			return true;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return false;
	}

	@Override
	public List<GradeBook> queryGradeBookRecords(int CourseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<GradeBook> query = session.createQuery("from GradeBook where CourseId = :CourseId and (GETDATE()-DateTo)>=0",
				GradeBook.class);
		query.setInteger("CourseId", CourseId);
		List<GradeBook> GradeBook = query.list();
		return GradeBook;
	}

	@Override
	public List<GradeBook> queryGradeBook(String GradeBook) {
		Session session = sessionFactory.getCurrentSession();
		Query<GradeBook> query = session.createQuery(
				"from GradeBook where GradeBook = :GradeBook and ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",
				GradeBook.class);
		query.setString("GradeBook", GradeBook);
		List<GradeBook> GradeBooks = query.list();
		return GradeBooks;
	}

	@Override
	public List<GradeBook> queryGradeBookByAllow(int EmployeeID, String EmployeeName) {
		Session session = sessionFactory.getCurrentSession();
		Query<GradeBook> query = session.createQuery("from GradeBook where EmployeeID = :EmployeeID and EmployeeName = :EmployeeName and (GETDATE()-DateFrom)<=0 or ((GETDATE()-DateFrom)>=0 and (GETDATE()-DateTo)<=0)",GradeBook.class);
		query.setInteger("EmployeeID", EmployeeID);
		query.setString("EmployeeName", EmployeeName);
		List<GradeBook> GradeBook = query.list();
		return GradeBook;
	}

	
}
