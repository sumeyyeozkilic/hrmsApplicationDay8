package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAd;

public interface JobAdDao extends JpaRepository<JobAd, Integer>{
	
	List<JobAd> findByActive(boolean active);
	List<JobAd> findByActiveTrueAndEmployerId(int id);
	List<JobAd> findByActiveOrderByLastDate(boolean active);
}
