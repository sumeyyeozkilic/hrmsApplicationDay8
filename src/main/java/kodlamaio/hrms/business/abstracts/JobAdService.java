package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;

public interface JobAdService {
	
	Result create(JobAdDto jobAdDto);
	Result setPassive(int jobAdId);
	Result setActive(int jobAdId);
	
	DataResult<List<JobAd>> getAll();
	DataResult<List<JobAd>> getActiveAds();
	DataResult<List<JobAd>> getActiveAndCompanyId();
	DataResult<List<JobAd>> getActiveAndOrderLastDate();
}
