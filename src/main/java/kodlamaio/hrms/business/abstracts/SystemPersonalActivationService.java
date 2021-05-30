package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.Employers;

public interface SystemPersonalActivationService {
	
	void createActivationBySytemPersonal(Employers employers);
	Result activateEmployer(int employersId, int systemPersonalId);
	
}
