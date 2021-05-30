package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionsService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionsDao;
import kodlamaio.hrms.entities.concretes.JobPositions;

@Service
public class JobPositionsManager implements JobPositionsService{
	
	private JobPositionsDao jobPositionsDao;
	
    @Autowired
	public JobPositionsManager(JobPositionsDao jobPositionsDao) {
		super();
		this.jobPositionsDao = jobPositionsDao;
	}

	@Override
	public DataResult<List<JobPositions>> getAll() {
		return new SuccessDataResult<>(this.jobPositionsDao.findAll(),"Data listelendi");
	}
	
	public Result add(JobPositions jobPosition) {
		if (jobPositionsDao.findAllByPosition(jobPosition.getPosition()).stream().count()!=0){
            return new ErrorResult("Aynı İsimde İş Pozisyonu Kullanılamaz");
        }else{
            jobPositionsDao.save(jobPosition);
            return new SuccessResult(("Başarılı Bir Şekilde Sisteme Eklendi"));
        }
	}
	
}
