package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SystemPersonalService;
import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.entities.concretes.SystemPersonal;

@RestController
@RequestMapping("/api/systempersonal")
public class SystemPersonalContoller {
	
	private SystemPersonalService systemPersonalService;

	@Autowired
	public SystemPersonalContoller(SystemPersonalService systemPersonalService) {
		this.systemPersonalService = systemPersonalService;
	}
	
	 @GetMapping("/getall")
	    public DataResult<List<SystemPersonal>> getAll(){
	        return this.systemPersonalService.getAll();
	    }

	    @PostMapping("/add")
	    public Result create(@RequestBody SystemPersonal systemPersonal){
	        return this.systemPersonalService.create(systemPersonal);
	    }
}
