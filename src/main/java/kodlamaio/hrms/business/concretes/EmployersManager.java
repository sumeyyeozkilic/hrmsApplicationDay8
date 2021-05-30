package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.EmployersService;
import kodlamaio.hrms.core.adapters.UsersCheckService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployersDao;
import kodlamaio.hrms.entities.concretes.Employers;

public class EmployersManager implements EmployersService{
	
	private EmployersDao employersDao;
	private UsersCheckService usersCheckService;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao, UsersCheckService userCheckService) {
		this.employersDao=employersDao;
		this.usersCheckService=userCheckService;
	}

	@Override
	public DataResult<List<Employers>> getAll() {
		return new SuccessDataResult(this.employersDao.findAll(),"Data Listelendi");
	}

	@Override
	public Result add(Employers employer) {
		if (employer.getCompanyName()==null || employer.getPhoneNumber()==null || employer.getWebAddress()==null || employer.getPassword()==null){
            return new ErrorResult("Alanlar Boş Bırakılamaz");
        }else if(employer.getPassword().length()<6){
            return new ErrorResult("Şifre 6 Karakterden Az Olamaz");
        }else if(employer.getWebAddress().contains(employer.getEmail())==false){
            return new ErrorResult(("Aynı Domaine Sahip Mail Adresi Kullanılmalıdır"));
        }else if(employersDao.findAllByEmail(employer.getEmail()).stream().count()!=0){
            return new ErrorResult("Aynı E posta Kullanılmaktadır");
        }else if(!this.usersCheckService.checkMailActivation()){
            return new ErrorResult("Lütfen Emailinizi Onaylayınız");
        }else if(!this.usersCheckService.checkUserActivationEmployee()){
            return new ErrorResult("Çalışanımız Tarafından Daha Onaylanmadınız");
        }else{
            employersDao.save(employer);
            return new SuccessResult("Sisteme Başarılı Bir Şekilde Kaydınız Gerçekleşmiştir");
        }

	}
}
