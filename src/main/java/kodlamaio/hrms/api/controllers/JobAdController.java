package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;

@RestController
@RequestMapping("/api/jobAd")
public class JobAdController {
	
	private JobAdService jobAdService;

	@Autowired
	public JobAdController(JobAdService jobAdService) {
		this.jobAdService = jobAdService;
	}
	
	 @GetMapping("/getall")
	    public DataResult<List<JobAd>> getAll(){
	        return this.jobAdService.getAll();
	    }

	    @PostMapping("/create")
	    public Result create(@RequestBody JobAdDto jobAdDto){
	        return this.jobAdService.create(jobAdDto);
	    }

	    @GetMapping("/setPassive")
	    public Result setPasssive(@RequestParam int jobAdId){
	        return this.jobAdService.setPasssive(jobAdId);
	    }

	    @GetMapping("/setActive")
	    public Result setActive(@RequestParam int jobAdId){
	        return this.jobAdService.setActive(jobAdId);
	    }

	    @GetMapping("/getActiveAds")
	    public DataResult<List<JobAd>> getActiveAds(){
	        return this.jobAdService.getActiveAds();
	    }

	    @GetMapping("/getActivesOrderLastDate")
	    public DataResult<List<JobAd>> getActivAndOrderLastDate(){
	        return this.jobAdService.getActiveAndOrderLastDate();
	    }

	    @GetMapping("/getActiveAndCompanyId")
	    public DataResult<List<JobAd>> getActiveAndCompanyId(@RequestParam int companyId){
	        return this.jobAdService.getActiveAndCompanyId(companyId);
	    }
}
