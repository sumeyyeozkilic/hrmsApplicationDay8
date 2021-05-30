package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.SystemPersonalActivationService;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployersDao;
import kodlamaio.hrms.dataAccess.abstracts.SystemPersonalActivationDao;
import kodlamaio.hrms.entities.concretes.Employers;
import kodlamaio.hrms.entities.concretes.SystemPersonalActivation;

public class SystemPersonalActivationManager implements SystemPersonalActivationService{

	private SystemPersonalActivationDao systemPersonalActivationDao;
	private EmployersDao employersDao;
	
	@Autowired
	public SystemPersonalActivationManager(SystemPersonalActivationDao systemPersonalActivationDao,
			EmployersDao employersDao) {
		
		this.systemPersonalActivationDao = systemPersonalActivationDao;
		this.employersDao = employersDao;
	}

	@Override
	public void createActivationBySytemPersonal(Employers employers) {
		SystemPersonalActivation systemPersonalActivation= new SystemPersonalActivation();
		systemPersonalActivation.setEmployeeId(employers.getId());
		systemPersonalActivation.setIsVerfied(false);
		systemPersonalActivationDao.save(systemPersonalActivation);
	}

	@Override
	public Result activateEmployer(int employersId, int systemPersonalId) {
		 Employers employer = employersDao.getById(employerId);
	        employer.setActive(true);
	        employersDao.save(employer);



	        SystemPersonalActivation systemPersonalActivation = systemPersonalActivationDao.findByEmployeeId(employersId);
	        systemPersonalActivation.setVerifyed(true);
	        systemPersonalActivation.(LocalDate.now());
	        systemPersonalActivation.setStaffId(systemPersonalId);
	        systemPersonalActivationDao.save(systemPersonalActivation);

	        return new SuccessResult("Kullanıcı aktif edildi");
	    }
	}

}
