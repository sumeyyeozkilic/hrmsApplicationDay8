package kodlamaio.hrms.core.adapters;

import java.time.LocalDate;

public class FakeMernisServiceAdapter implements UsersCheckService{

	@Override
	public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate birthOfDate) {
		return false;
	}
	
}
