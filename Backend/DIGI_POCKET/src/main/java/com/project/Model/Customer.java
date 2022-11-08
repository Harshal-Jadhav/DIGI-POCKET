package com.project.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	private String mobile;
	private String name;
	private String password;

	// Entity Relationships
	private Wallet wallet;
	
}
