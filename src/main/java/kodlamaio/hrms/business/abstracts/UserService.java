package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.DataResult;
import kodlamaio.hrms.entities.concretes.Users;

public interface UserService {
	DataResult<List<Users>> getAll();
	DataResult<Users> getByEmail(String email);
}
