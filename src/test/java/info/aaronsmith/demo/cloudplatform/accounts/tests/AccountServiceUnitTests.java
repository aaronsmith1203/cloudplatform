package info.aaronsmith.demo.cloudplatform.accounts.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import info.aaronsmith.demo.cloudplatform.accounts.Account;
import info.aaronsmith.demo.cloudplatform.accounts.AccountNotFoundException;
import info.aaronsmith.demo.cloudplatform.accounts.AccountRepo;
import info.aaronsmith.demo.cloudplatform.accounts.AccountService;
import info.aaronsmith.demo.cloudplatform.accounts.TenantNameUnavailableException;

@SpringBootTest
public class AccountServiceUnitTests {

	@Autowired
	private AccountService service;
	
	@MockBean
	private AccountRepo repo;
	
	// service.createAccount() tests
	
		@Test
		public void Given_ValidAccountArguments_When_CreateAccount_Then_ReturnNewAccount() throws Exception {
			// GIVEN
			final Account INPUT_ACCOUNT = new Account("asuretenant");
			final Account EXPECTED_ACCOUNT = new Account(1, "asuretenant");
			
			// WHEN
			Mockito.when(repo.save(INPUT_ACCOUNT)).thenReturn(EXPECTED_ACCOUNT);
			Account actual = service.createAccount(INPUT_ACCOUNT);
			
			// THEN
			assertThat(service.createAccount(INPUT_ACCOUNT)).isEqualTo(EXPECTED_ACCOUNT);
		}
		
		@Test
		public void Given_UnavailableTenantName_When_CreateAccount_Then_ThrowTenantNameUnavailableException() {
			// GIVEN
			final String TENANT_NAME = "asuretenant";
			final Account INPUT_ACCOUNT = new Account(TENANT_NAME);
			final Exception EXPECTED_EXCEPTION = new TenantNameUnavailableException(TENANT_NAME);
			
			// WHEN
			Mockito.when(repo.save(INPUT_ACCOUNT)).thenThrow(EXPECTED_EXCEPTION);
			
			// THEN
			assertThat(catchThrowable(() -> service.createAccount(INPUT_ACCOUNT)).getClass())
				.isEqualTo(EXPECTED_EXCEPTION.getClass());
		}
	
	// service.getAccount() tests

		@Test
		public void Given_NoArguments_When_GetAccount_Then_ReturnListOfAllAccounts() {
			// GIVEN
			final Account[] ACCOUNT_ARRAY = {
				new Account(1, "mybusiness"),
				new Account(2, "qa-training"),
				new Account(4, "asuretenant")
			};
			final List<Account> EXPECTED_ACCOUNTS = new ArrayList<Account>(Arrays.asList(ACCOUNT_ARRAY));
	
			// WHEN
			Mockito.when(repo.findAll()).thenReturn(EXPECTED_ACCOUNTS);
			List<Account> actual = service.getAccount();
			
			// THEN
			assertThat(actual).isEqualTo(EXPECTED_ACCOUNTS);
		}
	
		@Test
		public void Given_ExistingId_When_GetAccount_Then_ReturnAccount() {
			// GIVEN
			final Integer TEST_ID = 1;
			final Account EXPECTED_ACCOUNT = new Account(7, "asuretenant");
			
			// WHEN
			Mockito.when(repo.findById(TEST_ID)).thenReturn(Optional.of(EXPECTED_ACCOUNT));
			Account actual = service.getAccount(TEST_ID);
			
			// THEN
			assertThat(actual).isEqualTo(EXPECTED_ACCOUNT);
		}
		
		@Test 
		public void Given_NonExistantId_When_GetAccount_Then_ThrowAccountNotFoundException() {
			// GIVEN
			final Integer TEST_ID = 99;
			final Exception EXPECTED_EXCEPTION = new AccountNotFoundException(TEST_ID);
			
			// WHEN
			Mockito.when(repo.findById(TEST_ID)).thenThrow(EXPECTED_EXCEPTION);
			
			// THEN
			assertThat(catchThrowable(() -> service.getAccount(TEST_ID)).getClass())
				.isEqualTo(EXPECTED_EXCEPTION.getClass());
		}
	
	// service.updateAccount() tests
		
	// service.deleteAccount() tests
		
		@Test
		public void Given_ExistingId_When_DeleteAccount_Then_NoReturnValue() {
			//GIVEN
			final Integer TEST_ID = 1;
			//final Account EXPECTED_ACCOUNT = new Account(1, "asuretenant");
			
			//WHEN
			//Mockito.when(repo.findById(TEST_ID)).thenReturn(Optional.of(EXPECTED_ACCOUNT));
			Mockito.doNothing().when(repo).deleteById(TEST_ID);
			service.deleteAccount(TEST_ID);
			
			//THEN
			Mockito.verify(repo, Mockito.times(1)).deleteById(TEST_ID);
		}
		
		@Test
		public void Given_NonExistantId_When_DeleteAccount_Then_ThrowAccountNotFoundException() {
			// GIVEN
			final Integer TEST_ID = 99;
			final Exception EXPECTED_EXCEPTION = new AccountNotFoundException(TEST_ID);
			
			// WHEN
			Mockito.when(repo.findById(TEST_ID)).thenThrow(EXPECTED_EXCEPTION);
			
			// THEN
			assertThat(catchThrowable(() -> service.getAccount(TEST_ID)).getClass())
				.isEqualTo(EXPECTED_EXCEPTION.getClass());
		}
}
