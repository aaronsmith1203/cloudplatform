package info.aaronsmith.demo.cloudplatform.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	private Account saveAccountToDatabase(Account account) throws TenantNameUnavailableException {
		try {
			return repo.save(account);
		}
		catch (Exception e) {
			throw new TenantNameUnavailableException(account.getTenantName());
		}
	}
	
	// CREATE
	public Account createAccount(Account account) throws TenantNameUnavailableException {
		try {
			return saveAccountToDatabase(account);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// READ
	public List<Account> getAccount() {
		return repo.findAll();
	}
	
	// READ
	public Account getAccount(Integer id) throws AccountNotFoundException {
		try {
			return repo.findById(id).get();
		}
		catch (Exception e) {
			throw new AccountNotFoundException(id);
		}
	}
	
	// UPDATE
	public Account updateAccount(Integer id, Account account) throws AccountNotFoundException, TenantNameUnavailableException {
		try {
			// get existing account from database
			Account foundAccount = getAccount(id);
			
			// update Account attributes
			foundAccount.setTenantName(account.getTenantName());
			
			// save Account back to database
			return saveAccountToDatabase(foundAccount);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// DELETE
	public void deleteAccount(Integer id) throws AccountNotFoundException {
		repo.deleteById(id);
	}
}
