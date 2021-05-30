package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobPositions;

public interface JobPositionsService {
	DataResult<List<JobPositions>> getAll();
	Result add(JobPositions jobPosition);
	DataResult<JobPositions> getByName(String name);
}
