package kodlamaio.hrms.business.concretes;

import java.security.SecureRandom;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.core.utilities.ErrorResult;
import kodlamaio.hrms.core.utilities.Result;
import kodlamaio.hrms.core.utilities.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ActivationCodeDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.ActivationCode;
import kodlamaio.hrms.entities.concretes.Users;

public class ActivationCodeManager implements ActivationCodeService{

	private ActivationCodeDao activationCodeDao;
	private UserDao userDao;
	
    @Autowired
	public ActivationCodeManager(ActivationCodeDao activationCodeDao, UserDao userDao) {
		this.activationCodeDao = activationCodeDao;
		this.userDao = userDao;
	}

	@Override
	public ActivationCode getByCode(String code) {
		 return this.activationCodeDao.findByCode(code);
	}

	String generatedCode="";
	@Override
	public String createActivationCode(Users users) {
		for(int i=0;i==0;i=0){
            generatedCode = rastgeleDegerSaglayici(16);

            if(getByCode(generatedCode) == null){
                break;
            }
	}
		 ActivationCode activationCode=new ActivationCode();
	        activationCode.setUsersId(users.getId());
	        activationCode.setCode(generatedCode);

	        activationCodeDao.save(activationCode);

	        return generatedCode;
	    }

	@Override
	public Result activateUser(String code) {
		 if(activationCodeDao.findByCode(code) == null){
	            return new ErrorResult("Kod hatalı");
	        }

	        Users user = userDao.getById(activationCodeDao.findByCode(code).getUserId());
	        user.setMailVerify(true);
	        userDao.save(user);

	        ActivationCode activationCode=activationCodeDao.findByCode(code);
	        activationCode.setVerifayed(true);
	        activationCode.setVerifyDate(LocalDate.now());
	        activationCodeDao.save(activationCode);

	        return new SuccessResult("Kullanıcı aktif edildi");
	    }

	    private final String nelerOlsun = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private SecureRandom karistirici = new SecureRandom();
	    private String rastgeleDegerSaglayici(int uzunluk){
	        StringBuilder rastgeleDegerYapici = new StringBuilder(uzunluk);
	        for(int i=0;i<uzunluk;i++){
	            rastgeleDegerYapici.append(nelerOlsun.charAt(karistirici.nextInt(nelerOlsun.length())));
	        }
	        return rastgeleDegerYapici.toString();
	    }
	}


