package com.project.Repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.project.Model.CurrentUserSession;

public interface SessionRepo extends JpaRepositoryImplementation<CurrentUserSession, String>{
	
	public CurrentUserSession findByKey(String Key);

}
