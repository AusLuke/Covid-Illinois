package user.service;

import java.util.List;

import user.dao.CRUDDao;
import user.domain.CovidUser;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class E1UserService {
	private CRUDDao userDao = new CRUDDao();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
