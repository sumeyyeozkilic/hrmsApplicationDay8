package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobPositionsService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.JobPositions;


@RestController
@RequestMapping("/api/jobpositions")
public class JobPositionsController {
	
	private JobPositionsService jobPositionsSevice;
	
    @Autowired
	public JobPositionsController(JobPositionsService jobPositionsSevice) {
		super();
		this.jobPositionsSevice = jobPositionsSevice;
	}
	
    @GetMapping("/getall")
    public DataResult<List<JobPositions>> getAll(){
    	return this.jobPositionsSevice.getAll();
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody JobPositions jobPosition) {
    	return this.jobPositionsSevice.add(jobPosition);
    }
    
}
