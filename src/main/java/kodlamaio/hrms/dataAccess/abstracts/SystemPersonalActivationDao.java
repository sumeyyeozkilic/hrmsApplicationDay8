package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.SystemPersonalActivation;

public interface SystemPersonalActivationDao extends JpaRepository<SystemPersonalActivation, Integer>{
	
	SystemPersonalActivation findByEmployeeId(int id);
}
