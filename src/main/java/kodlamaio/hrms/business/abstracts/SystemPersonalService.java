package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.SystemPersonal;

public interface SystemPersonalService {
	
	public Result create(SystemPersonal systemPersonal);
	public DataResult<List<SystemPersonal>> getAll();
}
