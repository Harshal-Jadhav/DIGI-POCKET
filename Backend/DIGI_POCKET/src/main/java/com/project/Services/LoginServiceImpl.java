//package com.project.Services;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.project.Exceptions.LoginException;
//import com.project.Model.CurrentUserSession;
//import com.project.Model.Customer;
//import com.project.Model.LoginDTO;
//import com.project.Repositories.CustomerRepo;
//import com.project.Repositories.SessionRepo;
//
//import net.bytebuddy.utility.RandomString;
//
//public class LoginServiceImpl implements LoginService{
//	@Autowired
//	private CustomerRepo cRepo;
//	@Autowired
//	private SessionRepo sRepo;
//
//	@Override
//	public String LoginToAccount(LoginDTO loginDTO) throws LoginException {
//		Customer existingCustomer = cRepo.findByMobile(loginDTO.getMobile());
//		if(existingCustomer == null) {
//			throw new LoginException("please enter valid mobile number");
//			
//		}
//		Optional<CurrentUserSession> validCustomerSessionOpt = sRepo.findById(existingCustomer.getMobile());
//		if(validCustomerSessionOpt.isPresent()) {
//			throw new LoginException("User already logged in with this number");
//		}
//		if(existingCustomer.getPassword().equals(loginDTO.getPassword())) {
//			String key = RandomString.make(6);
//			CurrentUserSession currentUserSession = new CurrentUserSession();
//			currentUserSession.setKey(key);
//			currentUserSession.setMobile(existingCustomer.getMobile());
//			sRepo.save(currentUserSession);
//			return currentUserSession.toString();
//		}else {
//			throw new LoginException("please enter valid password");
//		}
//	}
//
//	@Override
//	public String LogOutFromAccount(String Key) throws LoginException {
//		CurrentUserSession currentUserSession = sRepo.findByKey(Key);
//		if(currentUserSession == null) {
//			throw new LoginException("user not logged in with this number");
//		}else {
//			sRepo.delete(currentUserSession);
//			return "logged out !";
//		}
//	}
//
//	
//}
