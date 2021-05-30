package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdDto {
	
	private int id;
	private int employerId;
	private String description;
	private int cityId;
	private int openPositions;
	private int minSalary;
	private int maxSalary;
	private boolean active;
	private LocalDate lastDate;
	
}
