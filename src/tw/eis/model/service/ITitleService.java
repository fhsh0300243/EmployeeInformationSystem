package tw.eis.model.service;

import java.util.List;

import tw.eis.model.Title;

public interface ITitleService {
	public Title titleData(int id);
	public List<?> titleDataByDeptId(int deptId);
}
