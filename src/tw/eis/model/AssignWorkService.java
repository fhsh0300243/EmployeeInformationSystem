package tw.eis.model;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
public void ChangeAssignWork(int empid, int wid,int cwid,String work) {
	awDao.ChangeAssignWork(empid,wid, cwid, work);
}
public JSONArray engworklist(int empid) {
	return awDao.engworklist(empid);
}
public void setworkstatus0(Model m,int awid) {
	awDao.setworkstatus0(m, awid);
}
public void setworkstatus1(Model m,int awid) {
	awDao.setworkstatus1(m, awid);
}
public void setworkstatus2(Model m,int awid) {
	awDao.setworkstatus2(m, awid);
}
public void setworkstatus3(Model m,int awid) {
	awDao.setworkstatus3(m, awid);
}
public JSONArray wkstatus1(int empid) {
	return awDao.wkstatus1(empid);
}
public JSONArray wkstatus2(int empid) {
	return awDao.wkstatus2(empid);
}
public JSONArray wkstatus3(int empid) {
	return awDao.wkstatus3(empid);
}
public void deleteaw(Model m,int awid) {
	awDao.deleteaw(m, awid);
}
}
