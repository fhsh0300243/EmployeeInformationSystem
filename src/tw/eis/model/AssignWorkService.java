package tw.eis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AssignWorkService {
private AssignWorkDAO awDao;
@Autowired
public AssignWorkService(AssignWorkDAO awDao) {
	this.awDao = awDao;
}
public void InsertAssignWork(int empid,int wid,String work) {
	awDao.InsertAssignWork(empid, wid, work);
}
}
