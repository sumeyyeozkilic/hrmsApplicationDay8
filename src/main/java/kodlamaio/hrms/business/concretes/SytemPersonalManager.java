package kodlamaio.hrms.business.concretes;

import java.util.List;

import kodlamaio.hrms.business.abstracts.SystemPersonalService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemPersonalDao;
import kodlamaio.hrms.entities.concretes.SystemPersonal;

public class SytemPersonalManager implements SystemPersonalService{

	private SystemPersonalDao systemPersonalDao;
	private UserService userService;
	
	
	public SytemPersonalManager(SystemPersonalDao systemPersonalDao, UserService userService) {
		this.systemPersonalDao = systemPersonalDao;
		this.userService = userService;
	}

	@Override
	public Result create(SystemPersonal systemPersonal) {
		if(systemPersonal.getPassword().length()<7) {
			return new ErrorResult("Şifre 6 karakterden uzun olmalıdır");
		}else if(!isEmailValid(systemPersonal.getEmail())) {
			return new ErrorResult("Geçersiz email");
		}else if(userService.getByEmail(systemPersonal.getEmail()).getData() !=null) {
			return new ErrorResult(Bu email zaten kayıtlı);
		}else {
			systemPersonal.setMailVerify(true);
			systemPersonalDao.save(systemPersonal);
			return new SuccessResult("Kayıt yapıldı");
		}
	}

	@Override
	public DataResult<List<SystemPersonal>> getAll() {
		return new SuccessDataResult<List<SystemPersonal>>(this.systemPersonalDao.findAll(),"Data listelendi");
	}
	
	

}
