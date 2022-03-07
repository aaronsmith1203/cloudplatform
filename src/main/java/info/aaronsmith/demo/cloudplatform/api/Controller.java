package info.aaronsmith.demo.cloudplatform.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import info.aaronsmith.demo.cloudplatform.accounts.Account;
import info.aaronsmith.demo.cloudplatform.accounts.AccountNotFoundException;
import info.aaronsmith.demo.cloudplatform.accounts.AccountService;
import info.aaronsmith.demo.cloudplatform.accounts.TenantNameUnavailableException;

@RestController
public class Controller {

	private AccountService as;
	
	@Autowired
	public Controller(AccountService accountService) {
		this.as = accountService;
	}
	
	// CREATE
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) 
		throws TenantNameUnavailableException {
			return new ResponseEntity<Account>(as.createAccount(account), HttpStatus.CREATED);
	}
	
	// READ - get all accounts
	@GetMapping("/getAccounts")
	public ResponseEntity<List<Account>> getAccount() {
		return new ResponseEntity<List<Account>>(as.getAccount(), HttpStatus.OK);
	}
	
	// READ - get account by id
	@GetMapping("/getAccount/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable Integer id) throws AccountNotFoundException {
		return new ResponseEntity<Account>(as.getAccount(id), HttpStatus.OK);
	}
	
	// UPDATE
	@PutMapping("/updateAccount/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account account) throws TenantNameUnavailableException {
		return new ResponseEntity<Account>(as.updateAccount(id, account), HttpStatus.OK);
	}
	
	// DELETE
	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
		try {
			as.deleteAccount(id);
			return new ResponseEntity<String>("Successfully deleted account with id (" + id + ")", HttpStatus.OK);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
