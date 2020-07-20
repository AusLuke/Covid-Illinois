package user.service;

import java.util.List;

import user.dao.CRUDDao;
import user.dao.CRUDDao2;
import user.domain.CovidUser;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class E2UserService {
	private CRUDDao2 userDao = new CRUDDao2();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
