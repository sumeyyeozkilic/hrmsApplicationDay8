package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Employees;

public interface EmployeesDao extends JpaRepository<Employees, Integer>{
	List<Employees> findAllByEmail(String email);
	List<Employees> findAllByIdentityNumber(String identityNumber);
}
