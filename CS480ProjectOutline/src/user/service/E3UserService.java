package user.service;

import java.util.List;

import user.dao.CRUDDao;
import user.dao.CRUDDao2;
import user.dao.CRUDDao3;
import user.domain.CovidUser;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class E3UserService {
	private CRUDDao3 userDao = new CRUDDao3();
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
}
