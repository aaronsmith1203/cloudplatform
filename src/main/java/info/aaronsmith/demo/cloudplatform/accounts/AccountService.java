package info.aaronsmith.demo.cloudplatform.accounts;

import java.util.List;

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
	
	// CREATE
	public Account createAccount(Account account) {
		return repo.save(account);
	}
	
	// READ
	public List<Account> getAccount() {
		return repo.findAll();
	}
	
	// READ
	public Account getAccount(Integer id) {
		try {
			return repo.findById(id).get();
		}
		catch (Exception e) {
			throw new AccountNotFoundException(id);
		}
	}
	
	// UPDATE
	public Account updateAccount(Integer id, Account account) {
		try {
			// get existing account from database
			Account foundAccount = getAccount(id);
			
			// update Account attributes
			foundAccount.setTenantName(account.getTenantName());
			
			// save Account back to database
			return repo.save(foundAccount);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// DELETE
	public void deleteAccount(Integer id) {
		try {
			// get account object to verify it exists in database
			getAccount(id);
			repo.deleteById(id);
		}
		catch (Exception e) {
			throw e;
		}
	}
}
