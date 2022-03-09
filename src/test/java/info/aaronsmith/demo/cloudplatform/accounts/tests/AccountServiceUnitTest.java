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
public class AccountServiceUnitTest {

	@Autowired
	private AccountService service;

	@MockBean
	private AccountRepo repo;

	// CREATE

	@Test
	public void Given_ValidArguments_When_CreateAccount_Then_ReturnNewAccount() throws Exception {
		// GIVEN
		final Account INPUT_ACCOUNT = new Account(
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Account EXPECTED_ACCOUNT = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);

		// WHEN
		Mockito.when(repo.save(INPUT_ACCOUNT)).thenReturn(EXPECTED_ACCOUNT);
		Account actual = service.createAccount(INPUT_ACCOUNT);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_ACCOUNT);
		Mockito.verify(repo, Mockito.times(1)).save(INPUT_ACCOUNT);
	}

	@Test
	public void Given_UnavailableTenantName_When_CreateAccount_Then_ThrowTenantNameUnavailableException() {
		// GIVEN
		final Account INPUT_ACCOUNT = new Account(
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Exception EXPECTED_EXCEPTION = new TenantNameUnavailableException("asuretenant");

		// WHEN
		Mockito.when(repo.save(INPUT_ACCOUNT)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.createAccount(INPUT_ACCOUNT));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).save(INPUT_ACCOUNT);
	}

	// READ

	@Test
	public void Given_NoArguments_When_GetAccount_Then_ReturnListOfAllAccounts() {
		// GIVEN
		final Account[] ACCOUNT_ARRAY = { 
			new Account(
				1,
				"Mr",
				"Donald",
				"Gibson",
				"asure",
				"Flat 4",
				"22 Roadington Road",
				"Mockleton",
				"Java",
				"JA1 2JA",
				"01234 567890",
				"donald.gibson@asure.com"
			),
			new Account(
				2,
				"Miss",
				"Jennifer",
				"Green",
				"qa-training",
				"22a Road Avenue",
				"",
				"Mockiton",
				"Javashire",
				"MO22 8JA",
				"0123 4567 890",
				"jengreen@qa.com"
			),
			new Account(
				3,
				"Mr",
				"Aaron",
				"Smith",
				"smith-development",
				"83 Apple Turnover",
				"Bakery Way",
				"Mockassin",
				"Javerley",
				"AP12 1TO",
				"0123 456 7890",
				"asmith@mydomain.com"
			)
		};
		final List<Account> EXPECTED_ACCOUNTS = new ArrayList<Account>(Arrays.asList(ACCOUNT_ARRAY));

		// WHEN
		Mockito.when(repo.findAll()).thenReturn(EXPECTED_ACCOUNTS);
		List<Account> actual = service.getAccount();

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_ACCOUNTS);
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	public void Given_ValidId_When_GetAccount_Then_ReturnAccount() {
		// GIVEN
		final Integer INPUT_ID = 1;
		final Account EXPECTED_ACCOUNT = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenReturn(Optional.of(EXPECTED_ACCOUNT));
		Account actual = service.getAccount(INPUT_ID);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_ACCOUNT);
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	@Test
	public void Given_NonExistantId_When_GetAccount_Then_ThrowAccountNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final Exception EXPECTED_EXCEPTION = new AccountNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.getAccount(INPUT_ID));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	// UPDATE

	@Test
	public void Given_ValidIdAndArguments_When_UpdateAccount_Then_ReturnUpdatedAccount() {
		// GIVEN
		final Integer INPUT_ID = 1;
		final Account INPUT_ACCOUNT = new Account(
			"Mr",
			"Aaron",
			"Smith",
			"NEWasuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Account FOUND_ACCOUNT = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Account EXPECTED_ACCOUNT = new Account(
			1,
			"Mr",
			"Aaron",
			"Smith",
			"NEWasuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenReturn(Optional.of(FOUND_ACCOUNT));
		Mockito.when(repo.save(FOUND_ACCOUNT)).thenReturn(EXPECTED_ACCOUNT);
		Account actual = service.updateAccount(INPUT_ID, INPUT_ACCOUNT);

		// THEN
		assertThat(actual).isEqualTo(EXPECTED_ACCOUNT);
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
		Mockito.verify(repo, Mockito.times(1)).save(FOUND_ACCOUNT);
	}

	@Test
	public void Given_NonExistantId_When_UpdateAccount_Then_ThrowAccountNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final Account INPUT_ACCOUNT = new Account(
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Exception EXPECTED_EXCEPTION = new AccountNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.updateAccount(INPUT_ID, INPUT_ACCOUNT));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}

	@Test
	public void Given_UnavailableTenantName_When_UpdateAccount_Then_ThrowTenantNameUnavailableException() {
		// GIVEN
		final Integer INPUT_ID = 2;
		final Account INPUT_ACCOUNT = new Account(			
			"Mr",
			"Aaron",
			"Smith",
			"NEWasuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
		);
		final Account FOUND_ACCOUNT = new Account(
			2,
			"Mr",
			"Aaron",
			"Smith",
			"asuretenant",
			"22a Road Avenue",
			"",
			"Mockiton",
			"Javashire",
			"MO22 8JA",
			"01234 567890",
			"aaronsmith@mydomain.com"
			);
		final Exception EXPECTED_EXCEPTION = new TenantNameUnavailableException("NEWasuretenant");

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenReturn(Optional.of(FOUND_ACCOUNT));
		Mockito.when(repo.save(FOUND_ACCOUNT)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.updateAccount(INPUT_ID, INPUT_ACCOUNT));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
		Mockito.verify(repo, Mockito.times(1)).save(FOUND_ACCOUNT);
	}

	// DELETE

	@Test
	public void Given_ValidId_When_DeleteAccount_Then_NoReturnValue() {
		// GIVEN
		final Integer INPUT_ID = 1;

		// WHEN
		Mockito.doNothing().when(repo).deleteById(INPUT_ID);
		service.deleteAccount(INPUT_ID);

		// THEN expect deleteById to have been called once
		Mockito.verify(repo, Mockito.times(1)).deleteById(INPUT_ID);
	}

	@Test
	public void Given_NonExistantId_When_DeleteAccount_Then_ThrowAccountNotFoundException() {
		// GIVEN
		final Integer INPUT_ID = 99;
		final Exception EXPECTED_EXCEPTION = new AccountNotFoundException(INPUT_ID);

		// WHEN
		Mockito.when(repo.findById(INPUT_ID)).thenThrow(EXPECTED_EXCEPTION);
		Throwable actual = catchThrowable(() -> service.getAccount(INPUT_ID));

		// THEN
		assertThat(actual.getClass()).isEqualTo(EXPECTED_EXCEPTION.getClass());
		Mockito.verify(repo, Mockito.times(1)).findById(INPUT_ID);
	}
}
