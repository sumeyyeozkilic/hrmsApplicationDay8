package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.ActivationCode;
import kodlamaio.hrms.entities.concretes.Users;

public interface ActivationCodeService {
	
	ActivationCode getByCode(String code);
	String createActivationCode(Users users);
	Result activateUser(String code);
}
