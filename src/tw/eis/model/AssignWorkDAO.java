package tw.eis.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
@Repository
public class AssignWorkDAO {
	private SessionFactory sessionFactory;
@Autowired
public AssignWorkDAO(@Qualifier(value = "sessionFactory")SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
public void InsertAssignWork(int empid,int wid,String work) {
	Session session = sessionFactory.getCurrentSession();
	AssignWork aw = new AssignWork(empid,wid,work);
	session.save(aw);
}
}
