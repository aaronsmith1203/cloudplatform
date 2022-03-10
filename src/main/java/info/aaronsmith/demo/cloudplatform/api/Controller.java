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
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudService;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceNotFoundException;
import info.aaronsmith.demo.cloudplatform.cloudservices.CloudServiceService;

@RestController
public class Controller {

	private AccountService as;
	private CloudServiceService cs;
	
	@Autowired
	public Controller(AccountService accountService, CloudServiceService cloudServiceService) {
		this.as = accountService;
		this.cs = cloudServiceService;
	}

	/////////////
	// ACCOUNT //
	/////////////
	
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
		public ResponseEntity<String> deleteAccount(@PathVariable Integer id) throws AccountNotFoundException {
			as.deleteAccount(id);
			return new ResponseEntity<String>("Successfully deleted Account with id (" + id + ")", HttpStatus.OK);
		}
	
	///////////////////
	// CLOUD SERVICE //
	///////////////////
	
		// CREATE
		@PostMapping("/createService")
		public ResponseEntity<CloudService> createService(@RequestBody CloudService service) {
			return new ResponseEntity<CloudService>(cs.createService(service), HttpStatus.CREATED);
		}
	
		// READ - get all cloud services
		@GetMapping("/getServices")
		public ResponseEntity<List<CloudService>> getService() {
			return new ResponseEntity<List<CloudService>>(cs.getService(), HttpStatus.OK);
		}
		
		// READ - get cloud service by id
		@GetMapping("/getService/{id}")
		public ResponseEntity<CloudService> getService(@PathVariable Integer id) throws CloudServiceNotFoundException {
			return new ResponseEntity<CloudService>(cs.getService(id), HttpStatus.OK);
		}
		
		// UPDATE
		@PutMapping("/updateService/{id}")
		public ResponseEntity<CloudService> updateService(@PathVariable Integer id, @RequestBody CloudService service) {
			return new ResponseEntity<CloudService>(cs.updateService(id, service), HttpStatus.OK);
		}
		
		// DELETE
		@DeleteMapping("/deleteService/{id}")
		public ResponseEntity<String> deleteService(@PathVariable Integer id) throws CloudServiceNotFoundException {
			cs.deleteService(id);
			return new ResponseEntity<String>("Successfully deleted CloudService with id (" + id + ")", HttpStatus.OK);
		}
		
}
