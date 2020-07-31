package user.service;

import java.util.List;

import user.dao.CQ1Dao;
import user.dao.CQ2Dao;
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
public class CQ2UserService {
	private CQ2Dao userDao = new CQ2Dao();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
