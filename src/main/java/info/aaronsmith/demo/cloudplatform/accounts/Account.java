package info.aaronsmith.demo.cloudplatform.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	// 'Account' is the entity modelling a user account
	// used to access the Cloud platform.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Account() { }
}
