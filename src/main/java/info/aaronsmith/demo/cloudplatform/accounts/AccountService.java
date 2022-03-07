package info.aaronsmith.demo.cloudplatform.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	// 'Account' is the entity modelling a user account
	// used to access the Cloud platform.
	
	private AccountRepo repo;
	
	@Autowired
	public AccountService(AccountRepo repo) {
		this.repo = repo;
	}
}
