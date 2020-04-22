package tw.eis.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import tw.eis.model.DepartmentalAnnualGoals;

@Repository
public class DepartmentalAnnualGoalsDAO {
private SessionFactory sessionFactory;
@Autowired
public DepartmentalAnnualGoalsDAO(@Qualifier(value="sessionFactory")SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
//此參數的deptID為抓JavaBean來的
public DepartmentalAnnualGoals getdag(int deptID,Model m) {
	Session session = sessionFactory.getCurrentSession();
	String sqlstr = "From DepartmentalAnnualGoals Where deptID := deptid";
	Query<DepartmentalAnnualGoals> query = session.createQuery(sqlstr,DepartmentalAnnualGoals.class);
	query.setParameter("deptid",deptID);
	DepartmentalAnnualGoals d = query.uniqueResult();
	return d;
}
}
