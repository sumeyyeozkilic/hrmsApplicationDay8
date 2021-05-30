package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployerForRegisterDto {
	
	private String companyName;
	private String webSite;
	private String phoneNumber;
	private String email;
	private String password;
	private String repassword;
	
}
