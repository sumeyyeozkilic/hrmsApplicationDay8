package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="system_personal_activation")
public class SystemPersonalActivation {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="system_personal_id")
	private int systemPersonalId;
	
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="is_verified")
	private boolean isVerified;
	
	@Column(name="verify_date")
	private LocalDate verifyDate;
}
