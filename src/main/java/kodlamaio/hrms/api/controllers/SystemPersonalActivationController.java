package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SystemPersonalActivationService;
import kodlamaio.hrms.core.utilities.Result;

@RestController
@RequestMapping("/api/systempersonalactivation")
public class SystemPersonalActivationController {
	
	private SystemPersonalActivationService systemPersonalActivationService;

    @Autowired
    public SystemPersonalActivationController(SystemPersonalActivationService systemPersonalActivationService) {
        this.systemPersonalActivationService = systemPersonalActivationService;
    }

    @GetMapping("/activateemployer")
    public Result activateEmployer(@RequestParam int employerId,@RequestParam int systemPersonalId){
        return systemPersonalActivationService.activateEmployer(employerId,systemPersonalId);
    }
}
