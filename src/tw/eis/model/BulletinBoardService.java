package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class BulletinBoardService implements IBulletinBoardService {

	private BulletinBoardDAO BulletinBoardDAO;

	@Autowired
	public BulletinBoardService(BulletinBoardDAO BulletinBoardDAO){
		this.BulletinBoardDAO=BulletinBoardDAO;
	}
	
	
	
	@Override
	public boolean insertBulletin(BulletinBoard BulletinBoard) {
		
		return BulletinBoardDAO.insertBulletin(BulletinBoard);
	}

	@Override
	public boolean updateBulletin(BulletinBoard BulletinBoard) {
		
		return BulletinBoardDAO.updateBulletin(BulletinBoard);
	}

	@Override
	public List<BulletinBoard> queryBulletinRecord(int EmployeeID) {
		
		return BulletinBoardDAO.queryBulletinRecord(EmployeeID);
	}

	@Override
	public List<BulletinBoard> queryBulletinForLook(String department) {
		return BulletinBoardDAO.queryBulletinForLook(department);
	}

	@Override
	public List<BulletinBoard> queryBulletinByOwnCreate(int EmployeeID) {
		return BulletinBoardDAO.queryBulletinByOwnCreate(EmployeeID);
	}



	@Override
	public boolean deleteBulletin(int id) {
		return BulletinBoardDAO.deleteBulletin(id);
	}



	@Override
	public byte[] queryFile(int BulletinBoardID) {
		
		return BulletinBoardDAO.queryFile(BulletinBoardID);
	}
	
	

}
