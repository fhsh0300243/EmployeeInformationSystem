package tw.eis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tw.eis.model.DepartmentalAnnualGoals;
import tw.eis.model.DepartmentalAnnualGoalsDAO;

@Service
public class DepartmentalAnnualGoalsService {
private DepartmentalAnnualGoalsDAO dagDao;
@Autowired
public DepartmentalAnnualGoalsService(DepartmentalAnnualGoalsDAO dagDao) {
	this.dagDao = dagDao;
}
public DepartmentalAnnualGoals getdag(int deptID,Model m) {
	return dagDao.getdag(deptID, m);
}
}
