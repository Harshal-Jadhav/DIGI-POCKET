package com.project.Services;

import com.project.Exceptions.LoginException;
import com.project.Model.LoginDTO;

public interface LoginService {

	public String LoginToAccount(LoginDTO loginDTO)throws LoginException;
	public String LogOutFromAccount(String Key)throws LoginException;
	
}
