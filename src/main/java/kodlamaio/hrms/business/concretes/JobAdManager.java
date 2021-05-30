package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.JobAdService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessDataResult;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployersDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionsDao;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;

public class JobAdManager implements JobAdService{

	 private JobAdDao jobAdDao;
	 private JobPositionsDao jobPositionDao;
	 private EmployersDao employerDao;
	 private CityDao cityDao;
	
	@Autowired
	public JobAdManager(JobAdDao jobAdDao, JobPositionsDao jobPositionDao, EmployersDao employerDao, CityDao cityDao) {
		super();
		this.jobAdDao = jobAdDao;
		this.jobPositionDao = jobPositionDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
	}

	@Override
	public Result create(JobAdDto jobAdDto) {
		if(!cityDao.existsById(jobAdDto.getCityId())){
            return new ErrorResult("Şehir bulunamadı");
        }
        else if(!this.employerDao.existsById(jobAdDto.getEmployerId())){
            return new ErrorResult("İş veren bulunamadı");
        }
        else if(jobAdDto.getDescription().isEmpty()){
            return new ErrorResult("Açıklama boş birakılamaz");
        }
        else if(jobAdDto.getMinSalary()==0){
            return new ErrorResult("Minumum maaş 0 verilemez");
        }
        else if(jobAdDto.getMaxSalary()==0){
            return new ErrorResult("Maximum maaş sıfır verilemez");
        }
        else if(jobAdDto.getMinSalary() >= jobAdDto.getMaxSalary()){
            return new ErrorResult("Minumum maaş maksimum maala eşit yada büyük olamaz");
        }
        else if(jobAdDto.getOpenPositions()<1){
            return new ErrorResult("Açık pozisyon adeti 1 den küçük olamaz");
        }
        else if(jobAdDto.getLastDate() == null){
            return new ErrorResult("Son başvuru tarihi boş bırakılamaz");
	}else {
		JobAd jobAd=new JobAd();
        jobAd.setId(0);
        jobAd.setJobPosition(this.jobPositionDao.getById(jobAdDto.getJobPositionId()));
        jobAd.setEmployer(this.employerDao.getById(jobAdDto.getEmployerId()));
        jobAd.setDescription(jobAdDto.getDescription());
        jobAd.setCity(this.cityDao.getById(jobAdDto.getCityId()));
        jobAd.setMinSalary(jobAdDto.getMinSalary());
        jobAd.setMaxSalary(jobAdDto.getMaxSalary());
        jobAd.setOpenPositions(jobAdDto.getOpenPositions());
        jobAd.setLastDate(jobAdDto.getLastDate());
        jobAd.setActive(true);
        jobAd.setCreateDate(LocalDate.now());


        this.jobAdDao.save(jobAd);
        return new SuccessResult("İlan başarılı bir şekilde eklendi");
    }
	}

	@Override
	public Result setPassive(int jobAdId) {
		  JobAd jobAd=this.jobAdDao.getById(jobAdId);
	        jobAd.setActive(false);
	        jobAdDao.save(jobAd);
	        return new SuccessResult("İş ilanı pasifleştirildi");
	}

	@Override
	public Result setActive(int jobAdId) {
		 JobAd jobAd=this.jobAdDao.getById(jobAdId);
	        jobAd.setActive(true);
	        this.jobAdDao.save(jobAd);
	        return new SuccessResult("İş ilanı aktifleştirildi");
	}

	@Override
	public DataResult<List<JobAd>> getAll() {
		 return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findAll(),"Data listelendi");
	}

	@Override
	public DataResult<List<JobAd>> getActiveAds() {
		 return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActive(true),"Aktif iş ilanları listelendi");
		
	}

	@Override
	public DataResult<List<JobAd>> getActiveAndCompanyId() {
		return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActiveTrueAndEmployerId(id),"Şirkere göre aktif iş işanları listelendi");
	}

	@Override
	public DataResult<List<JobAd>> getActiveAndOrderLastDate() {
		 return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActiveOrderByLastDate(true),"Aktif iş ilanları tarihe göre listelendi");
	}

}
