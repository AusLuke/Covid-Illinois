package user.service;

import java.util.List;

import user.dao.CRUDDao;
import user.dao.CRUDDao2;
import user.dao.CRUDDao3;
import user.dao.SQ1Dao;
import user.dao.SQ2Dao;
import user.domain.CovidUser;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class SQ2UserService {
	private SQ2Dao userDao = new SQ2Dao();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
