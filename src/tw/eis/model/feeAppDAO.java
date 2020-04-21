package tw.eis.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("feeAppDAO")
public class feeAppDAO implements IfeeAppDAO {

	private SessionFactory sessionFacotry;
	

	@Autowired
	public feeAppDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public boolean addFeeApp(String department, int employeeID, String appItem, String appTime, String invoiceTime,
			String invoiceNb, int editor, String remark, int appMoney, String signerTime, String signerStatus,
			int signerID) {
		Session session = sessionFacotry.getCurrentSession();
		feeAppMember feeapp = new feeAppMember(department, employeeID, appItem, appTime, invoiceTime, invoiceNb, editor,
				remark, appMoney, signerTime, signerStatus, signerID);

		session.save(feeapp);
		return true;

	}

	public List<feeAppMember> qFeeApp(int employeeID) {
		
		Session session = sessionFacotry.getCurrentSession();
		Query query = session.createQuery("from feeAppMember where employeeID=:employeeID",feeAppMember.class);
		query.setParameter("employeeID", employeeID);

		List<feeAppMember> lists = query.list();
//		Iterator iterator = lists.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//
//		}
		return lists;
	}
}
