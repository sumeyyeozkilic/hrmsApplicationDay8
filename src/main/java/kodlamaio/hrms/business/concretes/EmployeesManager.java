package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.EmployeesService;
import kodlamaio.hrms.core.adapters.UsersCheckService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeesDao;
import kodlamaio.hrms.entities.concretes.Employees;

public class EmployeesManager implements EmployeesService{
	
	private EmployeesDao employeesDao;
	private UsersCheckService usersCheckService;
	
	@Autowired
	public EmployeesManager(EmployeesDao employeesDao, UsersCheckService usersCheckService) {
		this.employeesDao=employeesDao;
		this.usersCheckService=usersCheckService;
	}

	@Override
	public DataResult<List<Employees>> getAll() {
		return new SuccessDataResult<>(this.employeesDao.findAll(),"Data Listelendi");
	}

	@Override
	public Result add(Employees employee) {
		if (employee.getFirstName()==null || employee.getLastName()==null || employee.getIdentityNumber()==null ||
                employee.getBirthOfDate()==null || employee.getEmail()==null || employee.getPassword()==null){
            return new ErrorResult("Alanlar Boş Geçilemez");
        }else if(usersCheckService.checkIfRealPerson(employee.getIdentityNumber(),employee.getFirstName(),employee.getLastName(),employee.getBirthDate())==false){
            return new ErrorResult("Mernis Doğrulaması Gerçekleşmedi");
        }else if(employeesDao.findAllByEmail(employee.getEmail()).stream().count()!=0){
            return new ErrorResult("Aynı Email Kullanılmakta");
        }else if(employeesDao.findAllByNationalIdentity(employee.getIdentityNumber()).stream().count()!=0){
            return new ErrorResult("Aynı T.C. kimlik numarası kullanılamaz");
        }else if(!this.usersCheckService.checkMailActivation()){
            return new ErrorResult("Lütfen Emailinizi Onaylayınız");
        }
        else{
            employeesDao.save(employee);
            return new SuccessResult("Başarılı Bir Şekilde Sisteme Eklenmiştir...");
        }

	}
}
