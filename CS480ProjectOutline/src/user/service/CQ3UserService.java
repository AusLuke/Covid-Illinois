package user.service;

import java.util.List;

import user.dao.CQ1Dao;
import user.dao.CQ2Dao;
import user.dao.CQ3Dao;
import user.dao.CRUDDao;
import user.dao.CRUDDao2;
import user.dao.CRUDDao3;
import user.dao.SQ1Dao;
import user.domain.CovidUser;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class CQ3UserService {
	private CQ3Dao userDao = new CQ3Dao();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
