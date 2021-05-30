package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForRegisterDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repassword;
	private String identityNumber;
	private LocalDate birthOfDate;
}
