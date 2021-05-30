package kodlamaio.hrms.business.concretes;

import java.util.List;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Users;

public class UserManager implements UserService{

	private UserDao userDao;
	
    public UserManager(UserDao userDao) {
		this.userDao=userDao;
	}
	
	@Override
	public DataResult<List<Users>> getAll() {
		return new SuccessDataResult<List<Users>>(this.userDao.findAll(),"Data listelendi");
	}

	@Override
	public DataResult<Users> getByEmail(String email) {
		return new SuccessDataResult<Users>(this.userDao.findByEmail(email),"Data listelendi");
	}

}
