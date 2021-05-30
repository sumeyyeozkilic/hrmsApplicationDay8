package kodlamaio.hrms.core.adapters;

import java.time.LocalDate;

public interface UsersCheckService {
	
	boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate birthOfDate);
	
	default boolean checkMailActivation() {
		return true;
	}
	
	default boolean checkUserActivationEmployee() {
		return true;
	}
}
